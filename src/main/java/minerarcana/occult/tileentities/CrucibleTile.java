package minerarcana.occult.tileentities;

import minerarcana.occult.recipe.crucible.recipe.AbstractCrucibleRecipe;
import minerarcana.occult.recipe.crucible.recipe.CrucibleMeltingRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.ArrayList;
import java.util.List;

import static minerarcana.occult.content.OccultBlocks.CRUCIBLE;
import static minerarcana.occult.recipe.OccultRecipeTypes.CRUCIBLE_MELTING;
import static minerarcana.occult.util.TagHelper.*;

public class CrucibleTile extends InventoryTile {

    private final FluidTank tank = new FluidTank(1000);
    private final LazyOptional<IFluidHandler> fluidHandler = LazyOptional.of(() -> tank);

    private AbstractCrucibleRecipe currentRecipe;

    private int progress;

    public CrucibleTile() {
        super(CRUCIBLE.getTileEntityType(), 4);
    }

    @Override
    public void tick() {
        super.tick();
        if(isHot()) {
            matchRecipe();
            if (getCurrentRecipe() != null) {
                if (getMachineType() == 1) {
                    if (getTempFromBelow() > getMeltingRecipe().getMinTemp() && getTempFromBelow() < getMeltingRecipe().getMaxTemp()) {
                        meltMyItems();
                    }
                }
            }
        }
    }

    public List<ItemStack> getItemList(){
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

    public FluidStack getFluidStack(){
        return getFluidHandler().map(fluid -> fluid.getFluidInTank(0)).orElse(FluidStack.EMPTY);
    }

    private void matchRecipe(){
        if(world != null) {
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
        } else if (!tank.isEmpty()) {
            return 5;
        }
        return 0;
    }

    public int getProgress() {
        return progress;
    }

    public void addProgress() {
        ++progress;
    }

    private CrucibleMeltingRecipe getMeltingRecipe() {
        return (CrucibleMeltingRecipe) getCurrentRecipe();
    }

    private void meltMyItems() {
        if (getCurrentRecipe() != null && getProgress() >= getMeltingRecipe().getCookTime()) {
            int itemsToRemove = getMeltingRecipe().getItemsIn().size();
            for (int x = 0; x < 3; ++x) {
                if (itemsToRemove != 0) {
                    ItemStack stack = getItemInSlot(x);
                    for (ItemStack item : getMeltingRecipe().getItemsIn()) {
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
            if (itemsToRemove == 0) {
                getFluidHandler().ifPresent(tank -> tank.fill(getMeltingRecipe().getFluidOut(), IFluidHandler.FluidAction.EXECUTE));
            }
        } else {
            addProgress();
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
            for (int x = 0; x < 3; ++x) {
                if (!getItemInSlot(x).isEmpty()) {
                    player.addItemStackToInventory(extractItem(x));
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.PASS;
    }


}
