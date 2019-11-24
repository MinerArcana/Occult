package com.minerarcana.occult.fluids;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.StateContainer;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class QuickSilverFluid extends ForgeFlowingFluid {

    protected QuickSilverFluid(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSource(IFluidState state) {
        return false;
    }

    @Override
    public int getLevel(IFluidState p_207192_1_) {
        return 0;
    }


    public static class Flowing extends QuickSilverFluid {

        public Flowing(Properties properties) {
            super(properties);
            this.setDefaultState(this.getStateContainer().getBaseState().with(LEVEL_1_8, 7));
        }

        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        @Override
        public int getLevel(IFluidState state) {
            return state.get(LEVEL_1_8);
        }

        @Override
        public boolean isSource(IFluidState state) {
            return false;
        }
    }

    public static class Source extends QuickSilverFluid {

        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public int getLevel(IFluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(IFluidState state) {
            return true;
        }
    }
}
