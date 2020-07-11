package minerarcana.occult.blocks.metals;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SatiatedLionmetalBars extends PaneBlock {

    protected SatiatedLionmetalBars() {
        super(Block.Properties.create(Material.IRON).lightValue(12));
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.setFire(120);
        entityIn.setFireTimer(120);
        super.onEntityCollision(state, worldIn, pos, entityIn);
    }
}
