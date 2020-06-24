package minerarcana.occult.blocks.crucible;

import minerarcana.occult.blocks.base.RotatableBlock;
import minerarcana.occult.tileentities.CrucibleTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static minerarcana.occult.content.OccultBlocks.CRUCIBLETOP;

public class CrucibleMainBlock extends RotatableBlock {


    public CrucibleMainBlock() {
        super(Block.Properties.create(Material.ANVIL).notSolid());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof CrucibleTile) {
            CrucibleTile crucible = (CrucibleTile) tile;
            return crucible.insertExtractItem(player, handIn);
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        //TODO make an air like block place on top so that people can interact with the machine
        BlockState above = worldIn.getBlockState(pos.up());
        if (above.isAir()) {
            worldIn.setBlockState(pos.up(), CRUCIBLETOP.get().getDefaultState());
        } else {
            worldIn.destroyBlock(pos, true);
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
        BlockState under = world.getBlockState(pos.up());
        if (under.getBlock() instanceof CrucibleTopBlock) {

        } else {
            World newWorld = world.getChunk(pos).getWorldForge().getWorld();
            newWorld.destroyBlock(pos, true);
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
