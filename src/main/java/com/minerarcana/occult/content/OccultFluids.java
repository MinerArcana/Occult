package com.minerarcana.occult.content;

import com.minerarcana.occult.fluids.QuickSilverFluid;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.occult.Occult.MOD_ID;
import static com.minerarcana.occult.content.OccultItems.QUICKSILVER_BUCKET;

public class OccultFluids {
    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MOD_ID);
    private static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, MOD_ID);

    public static final ResourceLocation FLUID_STILL = new ResourceLocation(MOD_ID + ":block/fluid/still");
    public static final ResourceLocation FLUID_FLOWING = new ResourceLocation(MOD_ID + ":block/fluid/flowing");

    public static RegistryObject<FlowingFluid> QUICKSILVER_FLUID = FLUIDS.register("quicksilver_fluid", () -> new QuickSilverFluid.Source(QUICKSILVER_PROPERTIES));
    public static RegistryObject<FlowingFluid> QUICKSILVER_FLUID_FLOWING = FLUIDS.register("quicksilver_fluid_flowing", () -> new QuickSilverFluid.Flowing(QUICKSILVER_PROPERTIES));
    public static RegistryObject<FlowingFluidBlock> QUICKSILVER_FLUID_BLOCK = BLOCKS.register("quicksilver_fluid_block", () -> new FlowingFluidBlock(QUICKSILVER_FLUID, Block.Properties.create(Material.WATER).hardnessAndResistance(100.0F).noDrops()));
    public static QuickSilverFluid.Properties QUICKSILVER_PROPERTIES = new QuickSilverFluid.Properties(QUICKSILVER_FLUID, QUICKSILVER_FLUID_FLOWING, FluidAttributes.builder(FLUID_STILL, FLUID_FLOWING).color(0xd5d2d1).density(1500).viscosity(1500).temperature(235)).explosionResistance(100.0F).bucket(QUICKSILVER_BUCKET).block(QUICKSILVER_FLUID_BLOCK);

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        FLUIDS.register(bus);
    }

}
