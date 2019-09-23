package com.minerarcana.occult.tileentity.crucible;

import com.google.common.collect.Maps;
import com.minerarcana.occult.recipes.OccultRecipeTypes;
import com.minerarcana.occult.recipes.machines.CrucibleRecipes;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Map;

import static com.minerarcana.occult.recipes.OccultRecipeTypes.CRUCIBLETYPE;
import static com.minerarcana.occult.tileentity.OccultTileEntities.CRUCIBLETILE;

public class CrucibleTile extends TileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity
{

    private static final int[] SLOTS_UP = new int[]{0};
    private static final int[] SLOTS_HORIZONTAL = new int[]{1};

    protected NonNullList<ItemStack> inventory = NonNullList.withSize(3, ItemStack.EMPTY);
    private boolean tooHot;
    private boolean hasFuel;
    private int minTemp;
    private int maxTemp;
    private int FuelTemp;
    private int meltTime;
    private int meltTimeTotal;
    protected final IIntArray crucibleData = new IIntArray() {
        public int get(int index) {
            switch(index) {
                case 0:
                    return CrucibleTile.this.meltTime;
                case 1:
                    return CrucibleTile.this.meltTimeTotal;
                case 2:
                    return CrucibleTile.this.minTemp;
                case 3:
                    return CrucibleTile.this.maxTemp;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch(index) {
                case 0:
                    CrucibleTile.this.meltTime = value;
                    break;
                case 1:
                    CrucibleTile.this.meltTimeTotal = value;
                    break;
                case 2:
                    CrucibleTile.this.minTemp = value;
                    break;
                case 3:
                    CrucibleTile.this.maxTemp = value;
                    break;

            }

        }

        public int size() {
            return 4;
        }
    };


    private final Map<ResourceLocation, Integer> recipeAmounts = Maps.newHashMap();
    private final IRecipeType<CrucibleRecipes> recipeType;

    public CrucibleTile() {
        super(CRUCIBLETILE);
        recipeType = OccultRecipeTypes.CRUCIBLETYPE;
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return direction == Direction.UP ? SLOTS_UP : SLOTS_HORIZONTAL;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack stack, @Nullable Direction direction) {
        return this.isItemValidForSlot(index, stack);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return true;
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.inventory)
            if (!itemstack.isEmpty())
                return false;

        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.inventory.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.inventory.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());

        if (index == 0 && !flag) {
            this.meltTimeTotal = this.getMeltTimeTotal();
            this.meltTime = 0;
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        if (this.world.getTileEntity(this.pos) != this)
            return false;
        else
            return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        for (ItemStack itemstack : this.inventory)
            helper.accountStack(itemstack);
    }

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    private IItemHandler createHandler() {
        return new ItemStackHandler(1) {


            @Override
            protected int getStackLimit(int slot, @Nonnull ItemStack stack)
            {
                return Math.min(0, 1);
            }
            @Override
            public int getSlotLimit(int slot)
            {
                return 3;
            }

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

        };
    }

    @Override
    public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
        if (recipe != null)
            this.recipeAmounts.compute(recipe.getId(), (location, integer) -> 1 + (integer == null ? 0 : integer));
    }

    @Nullable
    @Override
    public IRecipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void tick() {
        boolean flag = this.hasFuel();
        boolean flag1 = false;
        if (this.hasFuel()) {
            if (!this.world.isRemote) {
                ItemStack itemstack = this.inventory.get(1);
                if (!itemstack.isEmpty() && !this.inventory.get(0).isEmpty()) {
                    IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe(CRUCIBLETYPE, this, this.world).orElse(null);
                    if (!this.hasFuel() && this.canSmelt(irecipe)) {
                        if (this.hasFuel()) {
                            flag1 = true;
                            if (itemstack.hasContainerItem())
                                this.inventory.set(1, itemstack.getContainerItem());
                            else if (!itemstack.isEmpty()) {
                                Item item = itemstack.getItem();
                                itemstack.shrink(1);
                                if (itemstack.isEmpty()) {
                                    this.inventory.set(1, itemstack.getContainerItem());
                                }
                            }
                        }
                    }

                    if (this.hasFuel() && this.canSmelt(irecipe) && !this.tooHot()) {
                        ++this.meltTime;
                        if (this.meltTime == this.meltTimeTotal) {
                            this.meltTime = 0;
                            this.meltTimeTotal = this.getMeltTimeTotal();
                            this.smeltRecipe(irecipe);
                            flag1 = true;
                        }
                    } else {
                        this.meltTime = 0;
                    }
                } else if (!this.hasFuel() && this.meltTime > 0) {
                    this.meltTime = MathHelper.clamp(this.meltTime - 2, 0, this.meltTimeTotal);
                }

            }

            if (flag1) {
                this.markDirty();
            }

        }
    }

    private boolean canSmelt(@Nullable IRecipe<?> iRecipe) {
        if (!(iRecipe instanceof CrucibleRecipes))
            return false;

        if (!this.inventory.get(0).isEmpty()) {
            CrucibleRecipes recipe = (CrucibleRecipes) iRecipe;
            ItemStack outStack = recipe.getRecipeOutput();
            if (outStack.isEmpty())
                return false;
            else {
                if (this.inventory.isEmpty())
                    return true;

            }
        }
        return false;
    }

    private void smeltRecipe(@Nullable IRecipe<?> iRecipe) {
        if (!(iRecipe instanceof CrucibleRecipes))
            return;

        if (this.canSmelt(iRecipe)) {
            CrucibleRecipes recipe = (CrucibleRecipes) iRecipe;
            ItemStack ingredientStack = this.inventory.get(0);
            ItemStack recipeOutStack = recipe.getRecipeOutput();
            Item alternateOutput = recipe.getAlternateOutput();
            ItemStack outStack = this.inventory.get(0);
            if (outStack.isEmpty() && !this.tooHot())
                this.inventory.set(1 ,recipeOutStack.copy());
            if (outStack.isEmpty() && this.tooHot())
                ((CrucibleRecipes) iRecipe).getAlternateOutput();
            else if (outStack.getItem() == recipeOutStack.getItem())
                this.inventory.set(1,recipeOutStack);

            if (!this.world.isRemote)
                this.setRecipeUsed(recipe);

            ingredientStack.shrink(1);
        }
    }

    private int getMeltTimeTotal() {
        return this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).map(CrucibleRecipes::getMeltTime).orElse(200);
    }

    private ItemStack  getRecipeResult() {
        return this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).map(CrucibleRecipes::getRecipeOutput).orElse(ItemStack.EMPTY);
    }

    public int getFuelTemp() {
        return FuelTemp;
    }

    private boolean tooHot()
    {
        if(getFuelTemp() > this.maxTemp){
            tooHot = true;
        }
        else{ tooHot = false; }
        return tooHot;
    }

    public int setFuelTemp(World world, BlockState state, BlockPos pos) {
        BlockPos pos1 = pos.down();
        BlockState state1 = state.getBlockState();

        if(world.getBlockState(pos1) == Blocks.LAVA.getDefaultState())
        {
            FuelTemp = 1000;
        }
        return FuelTemp;
    }

    public boolean hasFuel() {
        if(getFuelTemp() >= this.minTemp) {
            return hasFuel = true;
        }
        else{ return false;}
    }

}
