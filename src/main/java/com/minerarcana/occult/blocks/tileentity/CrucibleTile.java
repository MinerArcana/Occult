package com.minerarcana.occult.blocks.tileentity;


import com.minerarcana.occult.api.recipes.OccultRecipeTypes;
import com.minerarcana.occult.util.lib.OccultTagLib;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.minerarcana.occult.api.recipes.machines.CrucibleRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static com.minerarcana.occult.content.OccultTileEntity.CRUCIBLE_TYPE;

public class CrucibleTile extends TileEntity implements ITickableTileEntity, IRecipeHolder, IRecipeHelperPopulator, ISidedInventory {
    private boolean hasFuel;
    private boolean tooHot;
    private int maxTemp;
    private int minTemp;
    private int fuelTemp;
    private int meltTime = 0;
    private int meltTimeTotal;

    private final Map<ResourceLocation, Integer> recipeAmounts = Maps.newHashMap();
    private final IRecipeType<CrucibleRecipes> recipeType;
    private final CrucibleRecipes currentRecipe = null;

    public CrucibleTile() {
        super(CRUCIBLE_TYPE.get());
        recipeType = OccultRecipeTypes.CRUCIBLETYPE;
    }


    public int getFuelTemp() {
        return fuelTemp;
    }

    private void setFuelTemp() {
        BlockPos pos1 = pos.down();
        World world = this.world.getWorld();
        BlockState state = world.getBlockState(pos1);
        Block block = state.getBlock();

        if (block.isIn(OccultTagLib.Blocks.LOWHEATSOURCE)) {
            fuelTemp = 250;
        } else if (block.isIn(OccultTagLib.Blocks.MEDIUMLOWHEATSOURCE)) {
            fuelTemp = 650;
        } else if (block.isIn(OccultTagLib.Blocks.MEDIUMHEATSOURCE)) {
            fuelTemp = 1000;
        } else if (block.isIn(OccultTagLib.Blocks.MEDIUMHIGHHEATSOURCE)) {
            fuelTemp = 1500;
        } else if (block.isIn(OccultTagLib.Blocks.HIGHHEATSOURCE)) {
            fuelTemp = 2000;
        } else if (block.isIn(OccultTagLib.Blocks.EXTREMELYHIGHHEATSOURCE)) {
            fuelTemp = 3000;
        } else {
            fuelTemp = 0;
        }
    }

    private boolean tooHot() {
        if (getFuelTemp() > getMaxTemp()) {
            tooHot = true;
        } else {
            tooHot = false;
        }
        return tooHot;
    }


    private boolean hasFuel() {
        return getFuelTemp() >= 150;
    }

    private void smeltRecipe(@Nullable IRecipe<?> iRecipe) {
        if (!(iRecipe instanceof CrucibleRecipes))
            return;

        if (this.canSmelt(iRecipe)) {
            CrucibleRecipes recipe = (CrucibleRecipes) iRecipe;
            ItemStack ingredientStack = this.inventory.get(0);
            ItemStack recipeOutStack = recipe.getRecipeOutput();
            ItemStack recipeAlternateOut = recipe.getAlternateOutput();
            ItemStack outStack = this.inventory.get(0);
            ingredientStack.shrink(1);
            if (outStack.isEmpty() && !this.tooHot()) {
                this.inventory.set(1, recipeOutStack.copy());
            } else if (outStack.isEmpty() && this.tooHot()) {
                this.inventory.set(1, recipeAlternateOut.copy());
            } else if (outStack.getItem() == recipeOutStack.getItem())
                this.inventory.set(1, recipeOutStack);

            if (!this.world.isRemote)
                this.setRecipeUsed(recipe);

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


    @Override
    public void tick() {
        setFuelTemp();
        boolean flag = this.hasFuel();
        boolean flag1 = false;
        if (this.hasFuel()) {
            if (!this.world.isRemote) {
                ItemStack itemstack = this.inventory.get(1);
                world.addParticle((IParticleData) ParticleTypes.ITEM, pos.getX() + .5, pos.getY() + 1.05, .5, 0, 0, 0);
                if (!itemstack.isEmpty() && !this.inventory.get(0).isEmpty()) {
                    IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).orElse(null);
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

                    if (this.hasFuel() && this.canSmelt(irecipe) && this.fuelTemp >= getMinTemp()) {
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

    //NBT Read/Write

    public CompoundNBT write(CompoundNBT tag) {
        super.write(tag);
        tag.putInt("MeltTime", meltTime);
        tag.putInt("MeltTimeTotal", meltTimeTotal);
        tag.putInt("FuelTemp", fuelTemp);
        tag.putInt("MinTemp", minTemp);
        tag.putInt("MaxTemp", maxTemp);
        tag.putBoolean("HasFuel", hasFuel);
        tag.putBoolean("TooHot", tooHot);

        ItemStackHelper.saveAllItems(tag, this.inventory);
        tag.putShort("RecipesUsedSize", (short) this.recipeAmounts.size());
        int i = 0;

        for (Map.Entry<ResourceLocation, Integer> entry : this.recipeAmounts.entrySet()) {
            tag.putString("RecipeLocation" + i, entry.getKey().toString());
            tag.putInt("RecipeAmount" + i, entry.getValue());
            ++i;
        }

        return tag;
    }

    public void read(CompoundNBT tag) {
            super.read(tag);
        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tag, this.inventory);
        this.hasFuel = tag.getBoolean("HasFuel");
        this.tooHot = tag.getBoolean("TooHot");
        this.fuelTemp = tag.getInt("FuelTemp");
        this.minTemp = tag.getInt("MinTemp");
        this.maxTemp = tag.getInt("MaxTemp");
        this.meltTime = tag.getInt("MeltTime");
        this.meltTimeTotal = tag.getInt("MeltTimeTotal");

        int i = tag.getShort("RecipesUsedSize");
        for (int j = 0; j < i; ++j) {
            ResourceLocation resourcelocation = new ResourceLocation(tag.getString("RecipeLocation" + j));
            int k = tag.getInt("RecipeAmount" + j);
            this.recipeAmounts.put(resourcelocation, k);
        }
    }


    //Inventory Handler

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (this.hasFuel()) {
                return handler.cast();
            }
        }
        return super.getCapability(cap, side);
    }


    private IItemHandler createHandler() {
        return new ItemStackHandler(3) {


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
                return stack.getItem().isIn(OccultTagLib.Items.CRUCIBLE) ;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                Item item = stack.getItem();
                if (!stack.getItem().isIn(OccultTagLib.Items.CRUCIBLE)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }

            @Override
            @Nonnull
            public ItemStack extractItem(int slot, int amount, boolean simulate)
            {
                return extractItem(slot, amount, simulate);
            }


        };

    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        for (ItemStack itemstack : this.inventory)
            helper.accountStack(itemstack);
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


    //Inventory

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, Direction direction) {
        return false;
    }

    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int i, int i1) {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int i) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {

    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity playerEntity) {
        return false;
    }

    public void clear() {
        this.inventory.clear();
    }


    //Recipe Getters

    protected int getMeltTimeTotal() {
        return this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).map(CrucibleRecipes::getMeltTime).orElse(200);
    }

    protected int getMaxTemp() {
        return this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).map(CrucibleRecipes::getMaxtemp).orElse(1000);
    }

    protected int getMinTemp() {
        return this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).map(CrucibleRecipes::getMintemp).orElse(200);
    }


    //Experience handling

    public void getExperience(PlayerEntity player) {
        List<IRecipe<?>> list = Lists.newArrayList();

        for (Map.Entry<ResourceLocation, Integer> entry : this.recipeAmounts.entrySet()) {
            player.world.getRecipeManager().getRecipe(entry.getKey()).ifPresent((experience) -> {
                list.add(experience);
                experienceHandler(player, entry.getValue(), (((CrucibleRecipes) experience).getExperience()));
            });
        }

        player.unlockRecipes(list);
        this.recipeAmounts.clear();
    }


    private static void experienceHandler(PlayerEntity player, int amount, float exp) {
        if (exp == 0.0F) {
            amount = 0;
        } else if (exp < 1.0F) {
            int i = MathHelper.floor((float) amount * exp);
            if (i < MathHelper.ceil((float) amount * exp) && Math.random() < (double) ((float) amount * exp - (float) i)) {
                ++i;
            }
        }
    }



}
