package minerarcana.occult.blocks;

import minerarcana.occult.tileentities.CrucibleTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static minerarcana.occult.content.OccultBlocks.CRUCIBLETOP;

public class CrucibleMainBlock extends RotatableBlock {


    public CrucibleMainBlock() {
        super(Block.Properties.create(Material.ANVIL).notSolid());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        //TODO make an air like block place on top so that people can interact with the machine
        BlockState above = worldIn.getBlockState(pos.up());
        if(above.isAir()){
            worldIn.setBlockState(pos.up(),CRUCIBLETOP.get().getDefaultState());
        }else{
            worldIn.destroyBlock(pos,true);
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        BlockState above = worldIn.getBlockState(pos.up());
        if(above.getBlock() instanceof CrucibleTopBlock){
            worldIn.destroyBlock(pos.up(),false);
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CrucibleTile();
    }
}
