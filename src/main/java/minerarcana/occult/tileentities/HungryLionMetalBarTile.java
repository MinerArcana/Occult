package minerarcana.occult.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static minerarcana.occult.content.OccultBlocks.HUNGRY_LIONMETA_BLOCK;
import static minerarcana.occult.util.TagHelper.LIONMETALFOOD;

public class HungryLionMetalBarTile extends TileEntity implements ITickableTileEntity {

    public HungryLionMetalBarTile() {
        super(HUNGRY_LIONMETA_BLOCK.getTileEntityType());
    }

    @Override
    public void tick() {
        if (world.getGameTime() % 20 == 0) {
            lionMetalFireManager();
        }
    }

    public void lionMetalFireManager() {
        if (world != null) {
            if (world.isBlockPowered(pos)) {
                for (int x = -1; x <= 1; ++x) {
                    for (int y = -1; y <= 1; ++y) {
                        for (int z = -1; z <= 1; ++z) {
                            BlockPos targetPos = pos.add(x, y, z);
                            BlockState targetBlock = world.getBlockState(targetPos);
                            if (targetBlock.isIn(LIONMETALFOOD)) {
                                world.setBlockState(targetPos, Blocks.AIR.getDefaultState());
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public int getPowerLevelFromBlock() {
        int redstonePower = 0;
        if (world.getRedstonePower(pos.down(), Direction.DOWN) > redstonePower) {
            redstonePower = world.getRedstonePower(pos.down(), Direction.DOWN);
        } else if (world.getRedstonePower(pos.up(), Direction.UP) > redstonePower) {
            redstonePower = world.getRedstonePower(pos.up(), Direction.UP);
        } else if (world.getRedstonePower(pos.north(), Direction.NORTH) > redstonePower) {
            redstonePower = world.getRedstonePower(pos.north(), Direction.NORTH);
        } else if (world.getRedstonePower(pos.south(), Direction.SOUTH) > redstonePower) {
            redstonePower = world.getRedstonePower(pos.south(), Direction.SOUTH);
        } else if (world.getRedstonePower(pos.west(), Direction.WEST) > redstonePower) {
            redstonePower = world.getRedstonePower(pos.west(), Direction.WEST);
        } else if(world.getRedstonePower(pos.east(), Direction.EAST) > redstonePower){
            redstonePower = world.getRedstonePower(pos.east(), Direction.EAST);
        }
        return redstonePower;
    }

}
