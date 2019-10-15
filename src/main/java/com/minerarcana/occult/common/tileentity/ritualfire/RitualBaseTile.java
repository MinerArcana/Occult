package com.minerarcana.occult.common.tileentity.ritualfire;

import com.google.common.collect.Maps;
import com.minerarcana.occult.Occult;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tags.Tag;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Map;

import static com.minerarcana.occult.common.tileentity.OccultTileEntities.RITUALBASETILE;
import static com.minerarcana.occult.update.util.lib.OccultNameLib.ritualbase;
import static net.minecraft.tags.ItemTags.LOGS;

public class RitualBaseTile extends TileEntity implements INamedContainerProvider, IBaseIsMultiBlock, ITickableTileEntity {

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
    protected NonNullList<ItemStack> inventory = NonNullList.withSize(16, ItemStack.EMPTY);
    private RitualBaseTile master;

    private int burnTime;

    private boolean isMultiBlock = false;
    private boolean isMaster = false;
    private boolean isSlave = false;
    private boolean isBig = false;
    private boolean isSmall = false;

    public RitualBaseTile() {
        super(RITUALBASETILE);
    }

    public boolean isOn() {
        return this.burnTime > 0;
    }

    @Override
    public boolean isMaster() {
        return isMaster;
    }

    @Override
    public void setMaster() {

        World world = this.getWorld();
        int small = 0;
        int big = 0;

        for (int i = 0; i < 2; ++i) {
            for (int k = 0; k < 2; ++k) {
                BlockPos multiblock = pos.add(i, 0, k);
                BlockState state = world.getBlockState(multiblock).getBlockState();
                Block block = state.getBlock();
                if (block == ritualbase) {
                    if(state instanceof IBaseIsMultiBlock){
                      if(((IBaseIsMultiBlock) state).isMultiBlock()) {
                          ++small;
                      }
                    }

                }
            }
        }

        if (small == 8) {
            this.isMultiBlock = true;
            this.isMaster = true;
            this.isSmall = true;

        }


        if (!isSmall) {
            for (int i = 0; i <= 2; ++i) {
                for (int k = 0; k <= 2; ++k) {
                    BlockPos multiblock = pos.add(i, 0, k);
                    BlockState state = world.getBlockState(multiblock).getBlockState();
                    Block block = state.getBlock();
                    if (block == ritualbase) {
                        if (state instanceof IBaseIsMultiBlock) {
                            if (!((IBaseIsMultiBlock) state).isMultiBlock()) {
                                ++big;
                            }
                        }
                    }
                }
            }
            if (big == 24) {
                this.isMultiBlock = true;
                this.isMaster = true;
                this.isBig = true;
            }
        }

    }

    @Override
    public void setSlave() {
        if (this.isMaster) {
            isSlave = false;
        } else {
            isSlave = true;
        }
    }

    @Override
    public boolean isSlave() {
        return isSlave;
    }

    @Override
    public boolean isMultiBlock() {
        return isMultiBlock;
    }


    @Override
    public void setMultiBlock() {
        int small = 0;
        int big = 0;
        if (!this.isMultiBlock) {
            setMaster();
            Occult.LOGGER.info("This is not a multiblock");
        } else if (this.isMultiBlock && this.isMaster) {
            if (this.isSmall) {
                for (int i = 0; i <  2; ++i) {
                    for (int k = 0; k < 2; ++k) {
                        BlockPos multiblock = pos.add(i, 0, k);
                        BlockState state = world.getBlockState(multiblock).getBlockState();
                        Block block = state.getBlock();
                        if (block == ritualbase) {
                            if (state instanceof IBaseIsMultiBlock) {
                                if (!((IBaseIsMultiBlock) state).isSlave()) {
                                    ++small;
                                    ((IBaseIsMultiBlock) state).setSlave();
                                }
                            }
                        }
                    }
                }

                if (small == 8) {
                    this.isMultiBlock = true;
                    this.isMaster = true;
                    this.isSmall = true;
                    Occult.LOGGER.info("This is a MultiBlock");


                } else if (this.isBig) {
                    for (int i = 0; i < 3; ++i) {
                        for (int k = 0; k < 3; ++k) {
                            BlockPos multiblock = pos.add(i, 0, k);
                            BlockState state = world.getBlockState(multiblock).getBlockState();
                            Block block = state.getBlock();
                            if (block == ritualbase) {
                                if (state instanceof IBaseIsMultiBlock) {
                                    if (!((IBaseIsMultiBlock) state).isSlave()) {
                                        ++big;
                                        ((IBaseIsMultiBlock) state).setSlave();
                                    }
                                }
                            }
                        }
                    }

                    if (big == 24) {
                        this.isMultiBlock = true;
                        this.isMaster = true;
                        this.isBig = true;
                        Occult.LOGGER.info("This is a MultiBlock");

                    }
                }
            }

        } else if (this.isSlave) {
            this.isMultiBlock = true;
        }

    }

    public RitualBaseTile getMaster() {
        if (this.isMaster) {
            return this.master;
        }
        return null;
    }

    @Override
    public void read(CompoundNBT tag) {
        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tag, this.inventory);
        this.isMaster = tag.getBoolean("isMaster");
        this.isSlave = tag.getBoolean("isSlave");
        this.isMultiBlock = tag.getBoolean("isMultiBlock");
        this.isBig = tag.getBoolean("isBig");
        this.isSmall = tag.getBoolean("isSmall");
        super.read(tag);
        this.burnTime = tag.getInt("BurnTime");
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            tag.put("inv", compound);
        });
        tag.putBoolean("isBig", this.isBig);
        tag.putBoolean("isSmall", this.isSmall);
        tag.putBoolean("isMultiBlock", this.isMultiBlock);
        tag.putBoolean("isMaster", this.isMaster);
        tag.putBoolean("isSlave", this.isSlave);
        tag.putInt("BurnTime", this.burnTime);

        return super.write(tag);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (isMaster) {
            if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                return handler.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    private IItemHandler createHandler() {
        return new ItemStackHandler(16) {

            @Override
            protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
                return 1;
            }


            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.getItem().isIn(LOGS);
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!stack.getItem().getTags().contains(LOGS)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }

        };


    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        return new SUpdateTileEntityPacket(this.pos, 1, nbt);
    }

    public static Map<Item, Integer> getBurnTimes() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        addItemTagBurnTime(map, LOGS, 300);
        return map;
    }

    private static void addItemTagBurnTime(Map<Item, Integer> map, Tag<Item> itemTag, int burntime) {
        for (Item item : itemTag.getAllElements()) {
            map.put(item, burntime);
        }
    }


    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new RitualBaseContainer(i, world, pos, playerInventory);
    }

    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public void tick() {
        if(!this.isMaster) {
            setMultiBlock();
        }
    }
}

