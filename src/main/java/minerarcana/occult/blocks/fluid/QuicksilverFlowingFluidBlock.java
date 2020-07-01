package minerarcana.occult.blocks.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;

import java.util.function.Supplier;

public class QuicksilverFlowingFluidBlock extends FlowingFluidBlock {


    public QuicksilverFlowingFluidBlock(Supplier<? extends FlowingFluid> supplier) {
        super(supplier, Block.Properties.from(Blocks.LAVA).noDrops());
    }

}
