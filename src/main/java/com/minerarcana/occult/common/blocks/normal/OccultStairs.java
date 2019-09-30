package com.minerarcana.occult.common.blocks.normal;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;
import java.util.stream.IntStream;

public class OccultStairs extends Block implements IWaterLoggable {
    public static final DirectionProperty FACING;
    public static final EnumProperty<Half> HALF;
    public static final EnumProperty<StairsShape> SHAPE;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape AABB_SLAB_TOP;
    protected static final VoxelShape AABB_SLAB_BOTTOM;
    protected static final VoxelShape NWD_CORNER;
    protected static final VoxelShape SWD_CORNER;
    protected static final VoxelShape NWU_CORNER;
    protected static final VoxelShape SWU_CORNER;
    protected static final VoxelShape NED_CORNER;
    protected static final VoxelShape SED_CORNER;
    protected static final VoxelShape NEU_CORNER;
    protected static final VoxelShape SEU_CORNER;
    protected static final VoxelShape[] SLAB_TOP_SHAPES;
    protected static final VoxelShape[] SLAB_BOTTOM_SHAPES;
    private static final int[] field_196522_K;
    private Block modelBlock;
    private BlockState modelState;

    private static VoxelShape[] makeShapes(VoxelShape p_199779_0_, VoxelShape p_199779_1_, VoxelShape p_199779_2_, VoxelShape p_199779_3_, VoxelShape p_199779_4_) {
        return (VoxelShape[]) IntStream.range(0, 16).mapToObj((p_199780_5_) -> {
            return combineShapes(p_199780_5_, p_199779_0_, p_199779_1_, p_199779_2_, p_199779_3_, p_199779_4_);
        }).toArray((p_199778_0_) -> {
            return new VoxelShape[p_199778_0_];
        });
    }

    private static VoxelShape combineShapes(int p_199781_0_, VoxelShape p_199781_1_, VoxelShape p_199781_2_, VoxelShape p_199781_3_, VoxelShape p_199781_4_, VoxelShape p_199781_5_) {
        VoxelShape lvt_6_1_ = p_199781_1_;
        if ((p_199781_0_ & 1) != 0) {
            lvt_6_1_ = VoxelShapes.or(p_199781_1_, p_199781_2_);
        }

        if ((p_199781_0_ & 2) != 0) {
            lvt_6_1_ = VoxelShapes.or(lvt_6_1_, p_199781_3_);
        }

        if ((p_199781_0_ & 4) != 0) {
            lvt_6_1_ = VoxelShapes.or(lvt_6_1_, p_199781_4_);
        }

        if ((p_199781_0_ & 8) != 0) {
            lvt_6_1_ = VoxelShapes.or(lvt_6_1_, p_199781_5_);
        }

        return lvt_6_1_;
    }

    public OccultStairs(BlockState state,Block.Properties properties) {
        super(properties);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with(FACING, Direction.NORTH)).with(HALF, Half.BOTTOM)).with(SHAPE, StairsShape.STRAIGHT)).with(WATERLOGGED, false));
        this.modelBlock = state.getBlock();
        this.modelState = state;
    }

    public boolean func_220074_n(BlockState p_220074_1_) {
        return true;
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return (p_220053_1_.get(HALF) == Half.TOP ? SLAB_TOP_SHAPES : SLAB_BOTTOM_SHAPES)[field_196522_K[this.func_196511_x(p_220053_1_)]];
    }

    private int func_196511_x(BlockState p_196511_1_) {
        return ((StairsShape)p_196511_1_.get(SHAPE)).ordinal() * 4 + ((Direction)p_196511_1_.get(FACING)).getHorizontalIndex();
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
        this.modelBlock.animateTick(p_180655_1_, p_180655_2_, p_180655_3_, p_180655_4_);
    }

    public void onBlockClicked(BlockState p_196270_1_, World p_196270_2_, BlockPos p_196270_3_, PlayerEntity p_196270_4_) {
        this.modelState.onBlockClicked(p_196270_2_, p_196270_3_, p_196270_4_);
    }

    public void onPlayerDestroy(IWorld p_176206_1_, BlockPos p_176206_2_, BlockState p_176206_3_) {
        this.modelBlock.onPlayerDestroy(p_176206_1_, p_176206_2_, p_176206_3_);
    }

    public float getExplosionResistance() {
        return this.modelBlock.getExplosionResistance();
    }

    public BlockRenderLayer getRenderLayer() {
        return this.modelBlock.getRenderLayer();
    }

    public int tickRate(IWorldReader p_149738_1_) {
        return this.modelBlock.tickRate(p_149738_1_);
    }

    public void onBlockAdded(BlockState p_220082_1_, World p_220082_2_, BlockPos p_220082_3_, BlockState p_220082_4_, boolean p_220082_5_) {
        if (p_220082_1_.getBlock() != p_220082_1_.getBlock()) {
            this.modelState.neighborChanged(p_220082_2_, p_220082_3_, Blocks.AIR, p_220082_3_, false);
            this.modelBlock.onBlockAdded(this.modelState, p_220082_2_, p_220082_3_, p_220082_4_, false);
        }
    }

    public void onReplaced(BlockState p_196243_1_, World p_196243_2_, BlockPos p_196243_3_, BlockState p_196243_4_, boolean p_196243_5_) {
        if (p_196243_1_.getBlock() != p_196243_4_.getBlock()) {
            this.modelState.onReplaced(p_196243_2_, p_196243_3_, p_196243_4_, p_196243_5_);
        }
    }

    public void onEntityWalk(World p_176199_1_, BlockPos p_176199_2_, Entity p_176199_3_) {
        this.modelBlock.onEntityWalk(p_176199_1_, p_176199_2_, p_176199_3_);
    }

    public void tick(BlockState p_196267_1_, World p_196267_2_, BlockPos p_196267_3_, Random p_196267_4_) {
        this.modelBlock.tick(p_196267_1_, p_196267_2_, p_196267_3_, p_196267_4_);
    }

    public boolean onBlockActivated(BlockState p_220051_1_, World p_220051_2_, BlockPos p_220051_3_, PlayerEntity p_220051_4_, Hand p_220051_5_, BlockRayTraceResult p_220051_6_) {
        return this.modelState.onBlockActivated(p_220051_2_, p_220051_4_, p_220051_5_, p_220051_6_);
    }

    public void onExplosionDestroy(World p_180652_1_, BlockPos p_180652_2_, Explosion p_180652_3_) {
        this.modelBlock.onExplosionDestroy(p_180652_1_, p_180652_2_, p_180652_3_);
    }

    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        Direction lvt_2_1_ = p_196258_1_.getFace();
        BlockPos lvt_3_1_ = p_196258_1_.getPos();
        IFluidState lvt_4_1_ = p_196258_1_.getWorld().getFluidState(lvt_3_1_);
        BlockState lvt_5_1_ = (BlockState)((BlockState)((BlockState)this.getDefaultState().with(FACING, p_196258_1_.getPlacementHorizontalFacing())).with(HALF, lvt_2_1_ != Direction.DOWN && (lvt_2_1_ == Direction.UP || p_196258_1_.getHitVec().y - (double)lvt_3_1_.getY() <= 0.5D) ? Half.BOTTOM : Half.TOP)).with(WATERLOGGED, lvt_4_1_.getFluid() == Fluids.WATER);
        return (BlockState)lvt_5_1_.with(SHAPE, getShapeProperty(lvt_5_1_, p_196258_1_.getWorld(), lvt_3_1_));
    }

    public BlockState updatePostPlacement(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if ((Boolean)p_196271_1_.get(WATERLOGGED)) {
            p_196271_4_.getPendingFluidTicks().scheduleTick(p_196271_5_, Fluids.WATER, Fluids.WATER.getTickRate(p_196271_4_));
        }

        return p_196271_2_.getAxis().isHorizontal() ? (BlockState)p_196271_1_.with(SHAPE, getShapeProperty(p_196271_1_, p_196271_4_, p_196271_5_)) : super.updatePostPlacement(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }

    private static StairsShape getShapeProperty(BlockState p_208064_0_, IBlockReader p_208064_1_, BlockPos p_208064_2_) {
        Direction lvt_3_1_ = (Direction)p_208064_0_.get(FACING);
        BlockState lvt_4_1_ = p_208064_1_.getBlockState(p_208064_2_.offset(lvt_3_1_));
        if (isBlockStairs(lvt_4_1_) && p_208064_0_.get(HALF) == lvt_4_1_.get(HALF)) {
            Direction lvt_5_1_ = (Direction)lvt_4_1_.get(FACING);
            if (lvt_5_1_.getAxis() != ((Direction)p_208064_0_.get(FACING)).getAxis() && isDifferentStairs(p_208064_0_, p_208064_1_, p_208064_2_, lvt_5_1_.getOpposite())) {
                if (lvt_5_1_ == lvt_3_1_.rotateYCCW()) {
                    return StairsShape.OUTER_LEFT;
                }

                return StairsShape.OUTER_RIGHT;
            }
        }

        BlockState lvt_5_2_ = p_208064_1_.getBlockState(p_208064_2_.offset(lvt_3_1_.getOpposite()));
        if (isBlockStairs(lvt_5_2_) && p_208064_0_.get(HALF) == lvt_5_2_.get(HALF)) {
            Direction lvt_6_1_ = (Direction)lvt_5_2_.get(FACING);
            if (lvt_6_1_.getAxis() != ((Direction)p_208064_0_.get(FACING)).getAxis() && isDifferentStairs(p_208064_0_, p_208064_1_, p_208064_2_, lvt_6_1_)) {
                if (lvt_6_1_ == lvt_3_1_.rotateYCCW()) {
                    return StairsShape.INNER_LEFT;
                }

                return StairsShape.INNER_RIGHT;
            }
        }

        return StairsShape.STRAIGHT;
    }

    private static boolean isDifferentStairs(BlockState p_185704_0_, IBlockReader p_185704_1_, BlockPos p_185704_2_, Direction p_185704_3_) {
        BlockState lvt_4_1_ = p_185704_1_.getBlockState(p_185704_2_.offset(p_185704_3_));
        return !isBlockStairs(lvt_4_1_) || lvt_4_1_.get(FACING) != p_185704_0_.get(FACING) || lvt_4_1_.get(HALF) != p_185704_0_.get(HALF);
    }

    public static boolean isBlockStairs(BlockState state) {
        return state.getBlock() instanceof OccultStairs;
    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return (BlockState)p_185499_1_.with(FACING, p_185499_2_.rotate((Direction)p_185499_1_.get(FACING)));
    }

    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
        Direction lvt_3_1_ = (Direction)p_185471_1_.get(FACING);
        StairsShape lvt_4_1_ = (StairsShape)p_185471_1_.get(SHAPE);
        switch(p_185471_2_) {
            case LEFT_RIGHT:
                if (lvt_3_1_.getAxis() == Direction.Axis.Z) {
                    switch(lvt_4_1_) {
                        case INNER_LEFT:
                            return (BlockState)p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.INNER_RIGHT);
                        case INNER_RIGHT:
                            return (BlockState)p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.INNER_LEFT);
                        case OUTER_LEFT:
                            return (BlockState)p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return (BlockState)p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.OUTER_LEFT);
                        default:
                            return p_185471_1_.rotate(Rotation.CLOCKWISE_180);
                    }
                }
                break;
            case FRONT_BACK:
                if (lvt_3_1_.getAxis() == Direction.Axis.X) {
                    switch(lvt_4_1_) {
                        case INNER_LEFT:
                            return (BlockState)p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.INNER_LEFT);
                        case INNER_RIGHT:
                            return (BlockState)p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.INNER_RIGHT);
                        case OUTER_LEFT:
                            return (BlockState)p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return (BlockState)p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.OUTER_LEFT);
                        case STRAIGHT:
                            return p_185471_1_.rotate(Rotation.CLOCKWISE_180);
                    }
                }
        }

        return super.mirror(p_185471_1_, p_185471_2_);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new IProperty[]{FACING, HALF, SHAPE, WATERLOGGED});
    }

    public IFluidState getFluidState(BlockState p_204507_1_) {
        return (Boolean)p_204507_1_.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(p_204507_1_);
    }

    public boolean allowsMovement(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }

    static {
        FACING = HorizontalBlock.HORIZONTAL_FACING;
        HALF = BlockStateProperties.HALF;
        SHAPE = BlockStateProperties.STAIRS_SHAPE;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        AABB_SLAB_TOP = OccultSlab.TOP_SHAPE;
        AABB_SLAB_BOTTOM = OccultSlab.BOTTOM_SHAPE;
        NWD_CORNER = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 8.0D);
        SWD_CORNER = Block.makeCuboidShape(0.0D, 0.0D, 8.0D, 8.0D, 8.0D, 16.0D);
        NWU_CORNER = Block.makeCuboidShape(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 8.0D);
        SWU_CORNER = Block.makeCuboidShape(0.0D, 8.0D, 8.0D, 8.0D, 16.0D, 16.0D);
        NED_CORNER = Block.makeCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D);
        SED_CORNER = Block.makeCuboidShape(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);
        NEU_CORNER = Block.makeCuboidShape(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D);
        SEU_CORNER = Block.makeCuboidShape(8.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D);
        SLAB_TOP_SHAPES = makeShapes(AABB_SLAB_TOP, NWD_CORNER, NED_CORNER, SWD_CORNER, SED_CORNER);
        SLAB_BOTTOM_SHAPES = makeShapes(AABB_SLAB_BOTTOM, NWU_CORNER, NEU_CORNER, SWU_CORNER, SEU_CORNER);
        field_196522_K = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};
    }
}
