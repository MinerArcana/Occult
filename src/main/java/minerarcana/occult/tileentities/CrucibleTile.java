package minerarcana.occult.tileentities;

import net.minecraft.block.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import static minerarcana.occult.content.OccultBlocks.CRUCIBLE;
import static minerarcana.occult.util.TagHelper.*;

public class CrucibleTile extends InventoryTile {

    protected FluidTank tank = new FluidTank(1000);
    private LazyOptional<IFluidHandler> fluidHandler = LazyOptional.of(() -> tank);

    private int progress;
    private final int fuelTemp = getTempFromBelow();

    public CrucibleTile() {
        super(CRUCIBLE.getTileEntityType(), 4);
    }















    private int getTempFromBelow(){
        BlockState belowState = world.getBlockState(pos.down());
        if(belowState.isIn(LOW_HEAT)){
            return 250;
        }else if(belowState.isIn(MEDIUM_HEAT)){
            return 650;
        }else if(belowState.isIn(HIGH_HEAT)){
            return 1000;
        }else if(belowState.isIn(EXTREME_HEAT)){
            return 1500;
        }else if(belowState.isIn(INFERNAL_HEAT)){
            return 2000;
        }
        return 0;
    }

}
