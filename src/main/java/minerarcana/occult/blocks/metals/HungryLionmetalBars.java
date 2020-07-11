package minerarcana.occult.blocks.metals;

import minerarcana.occult.tileentities.HungryLionMetalBarTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class HungryLionmetalBars extends PaneBlock {

    protected HungryLionmetalBars() {
        super(Block.Properties.create(Material.IRON));
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

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new HungryLionMetalBarTile();
    }
}
