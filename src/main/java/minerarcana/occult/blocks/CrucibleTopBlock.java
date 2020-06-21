package minerarcana.occult.blocks;

import minerarcana.occult.tileentities.CrucibleTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorldReader;
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
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tile = worldIn.getTileEntity(pos.down());
        if (tile instanceof CrucibleTile) {
            CrucibleTile crucible = (CrucibleTile) tile;
            return crucible.insertExtractItem(player, handIn);
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        BlockState under = worldIn.getBlockState(pos.down());
        if(under.getBlock() instanceof CrucibleMainBlock){
            worldIn.destroyBlock(pos.down(),true);
        }
    }

    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
        BlockState under = world.getBlockState(pos.down());
        if(under.getBlock() instanceof CrucibleMainBlock){

        }else{
            World newWorld = world.getChunk(pos).getWorldForge().getWorld();
            newWorld.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

}
