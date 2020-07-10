package minerarcana.occult.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import static minerarcana.occult.content.OccultBlocks.HUNGRY_LIONMETA_BLOCK;
import static minerarcana.occult.content.OccultBlocks.SATIATED_LIONMETAL_BLOCK;
import static minerarcana.occult.util.TagHelper.LIONMETALFOOD;

public class HungryLionmetalTile extends TileEntity implements ITickableTileEntity {

    private int fireConsumed = 0;

    public HungryLionmetalTile() {
        super(HUNGRY_LIONMETA_BLOCK.getTileEntityType());
    }

    @Override
    public void tick() {
        if (world.getGameTime() % 20 == 0) {
            lionMetalFireManager();
        }
    }


    private boolean isHungry() {
        return fireConsumed < 20;
    }

    public void lionMetalFireManager() {
        if (world != null) {
            if (!this.isHungry()) {
                world.setBlockState(pos, SATIATED_LIONMETAL_BLOCK.get().getDefaultState());
            } else {
                if (world.isBlockPowered(pos)) {
                    for (int x = -3; x <= 3; ++x) {
                        for (int y = -3; y <= 3; ++y) {
                            for (int z = -3; z <= 3; ++z) {
                                BlockPos targetPos = pos.add(x, y, z);
                                BlockState targetBlock = world.getBlockState(targetPos);
                                if (targetBlock.isIn(LIONMETALFOOD)) {
                                    world.setBlockState(targetPos, Blocks.AIR.getDefaultState());
                                    fireConsumed++;
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void read(CompoundNBT compound) {
        fireConsumed = compound.getInt("fireConsumed");
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("fireConsumed", fireConsumed);
        return super.write(compound);
    }
}
