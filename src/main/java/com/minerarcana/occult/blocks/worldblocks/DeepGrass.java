package com.minerarcana.occult.blocks.worldblocks;

import com.minerarcana.occult.blocks.OccultBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;

import java.util.Random;

public class DeepGrass extends GrassBlock {

    private String dirt;
    public DeepGrass(Properties properties) {
        super(properties);
        this.dirt = dirt;
    }

    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return worldIn.getBlockState(pos.up()).isAir();
    }

    public static boolean getLightLevel(BlockState state, IWorldReader iworld, BlockPos pos) {
        BlockPos blockpos = pos.up();
        BlockState blockstate = iworld.getBlockState(blockpos);
        if (blockstate.getBlock() == Blocks.SNOW && blockstate.get(SnowBlock.LAYERS) == 1) {
            return true;
        } else {
            int i = LightEngine.func_215613_a(iworld, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(iworld, blockpos));
            return i < iworld.getMaxLightLevel();
        }
    }

    public static boolean getFluidState(BlockState state, IWorldReader iworld, BlockPos pos) {
        BlockPos blockpos = pos.up();
        return getLightLevel(state, iworld, pos) && !iworld.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return !this.getDefaultState().isValidPosition(context.getWorld(), context.getPos()) ? Block.nudgeEntitiesWithNewState(this.getDefaultState(), ((Block) Registry.BLOCK.getOrDefault(new ResourceLocation(this.dirt))).getDefaultState(), context.getWorld(), context.getPos()) : super.getStateForPlacement(context);
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }


        @Override
        public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
            BlockState plant = plantable.getPlant(world, pos.offset(facing));
            net.minecraftforge.common.PlantType type = plantable.getPlantType(world, pos.offset(facing));

            if (plant.getBlock() == Blocks.SUGAR_CANE && this == Blocks.SUGAR_CANE)
                return true;

            if (plantable instanceof BushBlock)
                return true;

            switch (type) {
                case Desert: return this.getBlock() == Blocks.SAND || this.getBlock() == Blocks.TERRACOTTA || this.getBlock() instanceof GlazedTerracottaBlock;
                case Nether: return this.getBlock() == Blocks.SOUL_SAND;
                case Crop:   return this.getBlock() == Blocks.FARMLAND;
                case Cave:   return Block.hasSolidSide(state, world, pos, Direction.UP);
                case Plains: return this.getBlock() == Blocks.GRASS_BLOCK || Block.isDirt(this) || this.getBlock() == Blocks.FARMLAND;
                case Water:  return state.getMaterial() == Material.WATER; //&& state.getValue(BlockLiquidWrapper)
                case Beach:
                    boolean isBeach = this.getBlock() == Blocks.GRASS_BLOCK || Block.isDirt(this) || this.getBlock() == Blocks.SAND;
                    boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                            world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                            world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                            world.getBlockState(pos.south()).getMaterial() == Material.WATER);
                    return isBeach && hasWater;
            }
            return false;
        }


    @Deprecated
    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (!worldIn.isRemote) {

            if (!worldIn.isAreaLoaded(pos, 3))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (!getLightLevel(state, worldIn, pos)) {
                worldIn.setBlockState(pos, OccultBlocks.deepgrass.getDefaultState());


            } else {
                if (worldIn.getLight(pos.up()) >= 0) {
                    BlockState blockstate = this.getDefaultState();
                    for (int i = 0; i < 4; ++i) {
                        BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                        if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && getFluidState(blockstate, worldIn, blockpos)) {
                            worldIn.setBlockState(blockpos, blockstate.with(SNOWY, Boolean.valueOf(worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.SNOW)));

                        }
                    }
                }

            }
        }

    }
}



