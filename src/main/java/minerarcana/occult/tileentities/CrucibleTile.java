package minerarcana.occult.tileentities;

import minerarcana.occult.api.pressure.PressureType;
import minerarcana.occult.recipe.crucible.recipe.*;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static minerarcana.occult.api.pressure.chunkpressure.ChunkPressureCap.addChunkPressure;
import static minerarcana.occult.content.OccultBlocks.CRUCIBLE;
import static minerarcana.occult.recipe.OccultRecipeTypes.*;
import static minerarcana.occult.util.TagHelper.*;

public class CrucibleTile extends InventoryTile {

    private final int capacity = 1296;
    private final FluidTank tank = new FluidTank(capacity);
    private final LazyOptional<IFluidHandler> fluidHandler = LazyOptional.of(() -> tank);
    private AbstractCrucibleRecipe currentRecipe;

    private int progress;

    public CrucibleTile() {
        super(CRUCIBLE.getTileEntityType(), 4);
    }

    @Override
    public void tick() {
        super.tick();
        if (isHot()) {
            matchRecipe();
            if (getCurrentRecipe() != null) {
                if (getMachineType() == 1 && !isFluidFull()) {
                    if (getTempFromBelow() >= getMeltingRecipe().getMinTemp() && getTempFromBelow() <= getMeltingRecipe().getMaxTemp()) {
                        meltMyItems();
                    }
                } else if (getMachineType() == 2 ){
                    if (getTempFromBelow() >= getMixingRecipe().getMinTemp() && getTempFromBelow() <= getMixingRecipe().getMaxTemp()) {
                        mixMyItemsNFluid();
                    }
                }else if (getMachineType() == 3 ){
                    if (getTempFromBelow() >= getDippingRecipe().getMinTemp() && getTempFromBelow() <= getDippingRecipe().getMaxTemp()) {
                        dipMyItemsInFluid();
                    }
                }else if (getMachineType() == 4 ){
                    if (getTempFromBelow() >= getCookingRecipe().getMinTemp() && getTempFromBelow() <= getCookingRecipe().getMaxTemp()) {
                        cookMyItems();
                    }
                }
            } else {
                resetProgress();
            }
        } else if (!getFluidStack().isEmpty()) {
            matchCoolingRecipe();
            if (getCurrentRecipe() != null) {
                if (getMachineType() == 5) {
                    if (getItemInSlot(3).isEmpty() || getItemInSlot(3).getItem().equals(getCoolingRecipe().getItemOut().getItem()) && getItemInSlot(3).getCount() + getCoolingRecipe().getItemOut().getCount() <=64) {
                        if (getTempFromBelow() <= getCoolingRecipe().getMinTemp() && getTempFromBelow() >= getCoolingRecipe().getMaxTemp())
                            coolMyItems();
                    }
                }
            } else {
                resetProgress();
            }
        }
    }

    public List<ItemStack> getItemList() {
        List<ItemStack> stacks = new ArrayList<>();
        for (int x = 0; x < 3; ++x) {
            if (!getItemInSlot(x).isEmpty()) {
                stacks.add(getItemInSlot(x));
            }
        }
        return stacks;
    }

    public LazyOptional<IFluidHandler> getFluidHandler() {
        return fluidHandler;
    }

    public boolean isFluidFull() {
        return getFluidHandler().map(tank -> tank.getFluidInTank(0).getAmount() == tank.getTankCapacity(0)).orElse(true);
    }

    public FluidStack getFluidStack() {
        return getFluidHandler().map(fluid -> fluid.getFluidInTank(0)).orElse(FluidStack.EMPTY);
    }

    public void setFluidStack(FluidStack stack) {
        if (getFluidStack().isEmpty()) {
            getFluidHandler().ifPresent(tank -> tank.fill(stack, IFluidHandler.FluidAction.EXECUTE));
        }
    }

    public int getCapacity() {
        return capacity;
    }

    private void matchRecipe() {
        if (world != null) {
            currentRecipe = world.getRecipeManager()
                    .getRecipes()
                    .stream()
                    .filter(recipe -> recipe instanceof AbstractCrucibleRecipe)
                    .map(recipe -> (AbstractCrucibleRecipe) recipe)
                    .filter(recipe -> matchRecipe(recipe, getItemList(), getFluidStack()))
                    .findFirst()
                    .orElse(null);
        }
    }

    private void matchCoolingRecipe() {
        if (world != null) {
            currentRecipe = world.getRecipeManager()
                    .getRecipes()
                    .stream()
                    .filter(recipe ->  recipe instanceof CrucibleCoolingRecipe)
                    .map(recipe -> (AbstractCrucibleRecipe) recipe)
                    .filter(recipe -> matchRecipe(recipe, getItemList(), getFluidStack()))
                    .findFirst()
                    .orElse(null);
        }
    }

    private boolean matchRecipe(AbstractCrucibleRecipe recipe, List<ItemStack> list, FluidStack stack) {
        return recipe.matches(list, stack);
    }

    private boolean isHot() {
        return getTempFromBelow() > 249;
    }

    public AbstractCrucibleRecipe getCurrentRecipe() {
        return currentRecipe;
    }

    private int getMachineType() {
        if (getCurrentRecipe().getType() == CRUCIBLE_MELTING) {
            return 1;
        }  else if (getCurrentRecipe().getType() == CRUCIBLE_MIXING){
            return 2;
        } else if (getCurrentRecipe().getType() == CRUCIBLE_DIPPING){
            return 3;
        }else if (getCurrentRecipe().getType() == CRUCIBLE_COOKING){
            return 4;
        } else if (getCurrentRecipe().getType() == CRUCIBLE_COOLING) {
            return 5;
        }
        return 0;
    }

    public int getProgress() {
        return progress;
    }

    public void resetProgress() {
        progress = 0;
    }

    public void addProgress() {
        ++progress;
    }

    private CrucibleMeltingRecipe getMeltingRecipe() {
        return (CrucibleMeltingRecipe) getCurrentRecipe();
    }

    private CrucibleCoolingRecipe getCoolingRecipe() {
        return (CrucibleCoolingRecipe) getCurrentRecipe();
    }

    private CrucibleCookingRecipe getCookingRecipe() {
        return (CrucibleCookingRecipe) getCurrentRecipe();
    }

    private CrucibleMixingRecipe getMixingRecipe() {
        return (CrucibleMixingRecipe) getCurrentRecipe();
    }

    private CrucibleDippingRecipe getDippingRecipe() {
        return (CrucibleDippingRecipe) getCurrentRecipe();
    }

    private void meltMyItems() {
        if (getCurrentRecipe() != null && getProgress() >= getMeltingRecipe().getCookTime()) {
            int itemsToRemove = resolveRecipeItemRemoval(getMeltingRecipe().getItemsIn(), getMeltingRecipe().getItemsIn().size());
            if (itemsToRemove == 0) {
                addFluidToTank(getMeltingRecipe().getFluidOut());
                addPressureFromRecipe();
                resetProgress();
            }
        } else {
            addProgress();
        }
    }

    private void cookMyItems(){
        if (getCurrentRecipe() != null && getProgress() >= getCookingRecipe().getCookTime()) {
            int itemsToRemove = resolveRecipeItemRemoval(getCookingRecipe().getItemsIn(), getCookingRecipe().getItemsIn().size());
            if (itemsToRemove == 0) {
                insertItem(3,getCookingRecipe().getItemOut().copy());
                addPressureFromRecipe();
                resetProgress();
            }
        }else {
            addProgress();
        }
    }

    private void dipMyItemsInFluid(){
        if (getCurrentRecipe() != null && getProgress() >= getDippingRecipe().getCookTime()) {
            int itemsToRemove = resolveRecipeItemRemoval(getDippingRecipe().getItemsIn(), getDippingRecipe().getItemsIn().size());
            if (itemsToRemove == 0 && wasFluidRemoved(getDippingRecipe().getFluidIn())) {
                insertItem(3,getDippingRecipe().getItemOut().copy());
                addPressureFromRecipe();
                resetProgress();
            }
        }else {
            addProgress();
        }
    }

    private void mixMyItemsNFluid(){
        if (getCurrentRecipe() != null && getProgress() >= getMixingRecipe().getCookTime()) {
            int itemsToRemove = resolveRecipeItemRemoval(getMixingRecipe().getItemsIn(), getMixingRecipe().getItemsIn().size());
            if (itemsToRemove == 0 && wasFluidRemoved(getMixingRecipe().getFluidIn())) {
                setFluidStack(getMixingRecipe().getFluidOut());
                addPressureFromRecipe();
                resetProgress();
            }
        }else {
            addProgress();
        }
    }

    private void coolMyItems(){
        if (getCurrentRecipe() != null && getProgress() >= getCoolingRecipe().getCookTime()) {
            if (wasFluidRemoved(getCoolingRecipe().getFluidIn())) {
                insertItem(3,getCoolingRecipe().getItemOut().copy());
                addPressureFromRecipe();
                resetProgress();
            }
        }else {
            addProgress();
        }
    }

    private boolean wasFluidRemoved(FluidStack stack){
        if(!getFluidStack().isEmpty()){
            getFluidHandler().ifPresent(tank -> tank.drain(stack, IFluidHandler.FluidAction.EXECUTE));
            return true;
        }
        return false;
    }

    private void addFluidToTank(FluidStack stack) {
        getFluidHandler().ifPresent(tank -> tank.fill(stack, IFluidHandler.FluidAction.EXECUTE));
    }

    private int resolveRecipeItemRemoval(List<ItemStack> recipeItems, int size) {
        int itemsToRemove = size;
        for (int x = 0; x < 3; ++x) {
            if (itemsToRemove != 0) {
                ItemStack stack = getItemInSlot(x);
                for (ItemStack item : recipeItems) {
                    if (stack.getItem().equals(item.getItem())) {
                        int count = item.getCount();
                        stack.shrink(count);
                        itemsToRemove--;
                    }
                }
            } else {
                break;
            }
        }
        return itemsToRemove;
    }

    private void addPressureFromRecipe() {
        for (PressureType type : currentRecipe.getPressureCreated().keySet()) {
            if (!world.isRemote()) {
                addChunkPressure(world.getChunk(pos.getX(), pos.getZ()), type, currentRecipe.getPressureCreated().get(type));
            }
        }
    }

    private int getTempFromBelow() {
        if (world != null) {
            BlockState belowState = world.getBlockState(pos.down());
            if (belowState.isIn(LOW_HEAT)) {
                return 250;
            } else if (belowState.isIn(MEDIUM_HEAT)) {
                return 650;
            } else if (belowState.isIn(HIGH_HEAT)) {
                return 1000;
            } else if (belowState.isIn(EXTREME_HEAT)) {
                return 1500;
            } else if (belowState.isIn(INFERNAL_HEAT)) {
                return 2000;
            }
        }
        return 0;
    }

    public ActionResultType insertExtractItem(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!stack.isEmpty() && !player.isCrouching()) {
            for (int x = 0; x < 3; ++x) {
                ItemStack targetSlot = getItemInSlot(x);
                if (targetSlot.isEmpty()) {
                    insertItem(x, stack);
                    return ActionResultType.SUCCESS;
                } else if (targetSlot.getItem().equals(stack.getItem()) && targetSlot.getCount() != 64) {
                    if (targetSlot.getCount() + stack.getCount() != 64) {
                        targetSlot.setCount(targetSlot.getCount() + stack.getCount());
                        stack.shrink(stack.getCount());
                    } else {
                        int difference = 64 - targetSlot.getCount();
                        targetSlot.setCount(64);
                        stack.shrink(difference);
                    }
                    return ActionResultType.SUCCESS;
                }
            }
        } else {
            if (!getItemInSlot(3).isEmpty()) {
                player.addItemStackToInventory(extractItem(3));
                return ActionResultType.SUCCESS;
            } else {
                for (int x = 0; x < 3; ++x) {
                    if (!getItemInSlot(x).isEmpty()) {
                        player.addItemStackToInventory(extractItem(x));
                        return ActionResultType.SUCCESS;
                    }
                }
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        if (!compound.getString("fluid").isEmpty()) {
            setFluidStack(new FluidStack(Objects.requireNonNull(ForgeRegistries.FLUIDS.getValue(new ResourceLocation(compound.getString("fluid")))), compound.getInt("fluidAmount")));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!getFluidStack().isEmpty()) {
            compound.putString("fluid", getFluidStack().getFluid().getRegistryName().toString());
            compound.putInt("fluidAmount", getFluidStack().getAmount());
        }
        return compound;
    }
}
