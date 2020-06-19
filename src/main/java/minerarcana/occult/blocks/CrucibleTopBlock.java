package minerarcana.occult.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrucibleTopBlock extends Block {

    public CrucibleTopBlock() {
        super(Block.Properties.create(Material.ANVIL).noDrops().notSolid());
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        BlockState under = worldIn.getBlockState(pos.down());
        if(under.getBlock() instanceof CrucibleMainBlock){
            worldIn.destroyBlock(pos.down(),true);
        }
    }
}
