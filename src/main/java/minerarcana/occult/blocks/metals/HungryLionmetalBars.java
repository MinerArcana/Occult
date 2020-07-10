package minerarcana.occult.blocks.metals;

import minerarcana.occult.tileentities.HungryLionMetalBarTile;
import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class HungryLionmetalBars extends PaneBlock {

    protected HungryLionmetalBars(Properties builder) {
        super(builder);
    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        if(blockState.hasTileEntity()) {
            HungryLionMetalBarTile tile = (HungryLionMetalBarTile) blockAccess.getTileEntity(pos);
            assert tile != null;
            if(tile.getPowerLevelFromBlock() == 1){
                return 0;
            }
            return (int) (tile.getPowerLevelFromBlock()*.75);
        }
        return 0;
    }


}
