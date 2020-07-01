package minerarcana.occult.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class PileBlock extends Block {

    public PileBlock() {
        super(Block.Properties.create(Material.SAND).notSolid().noDrops());
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        return Stream.of(Block.makeCuboidShape(5D, 0.0D, 5D, 11D, 1D, 11D),Block.makeCuboidShape(6D, 1D, 6D, 10D, 2D, 10D),Block.makeCuboidShape(7D, 2D, 7D, 9D, 3D, 9D)).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    }
}
