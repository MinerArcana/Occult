package minerarcana.occult.content;

import minerarcana.occult.api.pressure.FluidRegistryObjectGroup;
import minerarcana.occult.blocks.fluid.QuicksilverFlowingFluidBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import static minerarcana.occult.Occult.MOD_ID;
import static minerarcana.occult.content.OccultRegistryHandler.*;

public class OccultFluids {


    public static FluidRegistryObjectGroup<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing> QUICKSILVER;
    public static ForgeFlowingFluid.Properties QUICKSILVER_PROPERTIES;

    public static FluidRegistryObjectGroup<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing> AMALGAM;
    public static ForgeFlowingFluid.Properties AMALGAM_PROPERTIES;

    public static void init(){
        QUICKSILVER = new FluidRegistryObjectGroup<>("molten_quicksilver", () ->
                new ForgeFlowingFluid.Source(QUICKSILVER_PROPERTIES), () ->
                new ForgeFlowingFluid.Flowing(QUICKSILVER_PROPERTIES),
                () -> new QuicksilverFlowingFluidBlock(QUICKSILVER)
        ).register(FLUIDS, BLOCKS, ITEMS);

        QUICKSILVER_PROPERTIES = new ForgeFlowingFluid.Properties(QUICKSILVER,
                QUICKSILVER::getFlowing, FluidAttributes.builder(new ResourceLocation(MOD_ID, "blocks/mercury_still"),
                new ResourceLocation(MOD_ID, "blocks/mercury_flow")).luminosity(900).density(2).viscosity(2).temperature(700)
                .color(fromHex("d0d7db")))
                .block(QUICKSILVER::getBlock)
                .bucket(QUICKSILVER::getBucket);

        AMALGAM = new FluidRegistryObjectGroup<>("molten_amalgam", () ->
                new ForgeFlowingFluid.Source(AMALGAM_PROPERTIES), () ->
                new ForgeFlowingFluid.Flowing(AMALGAM_PROPERTIES)
        ).register(FLUIDS, BLOCKS, ITEMS);

        AMALGAM_PROPERTIES = new ForgeFlowingFluid.Properties(AMALGAM,
                AMALGAM::getFlowing, FluidAttributes.builder(new ResourceLocation(MOD_ID, "blocks/amalgam_flow"),
                new ResourceLocation(MOD_ID, "blocks/amalgam_still"))
                .color(fromHex("eda31a")))
                .block(AMALGAM::getBlock)
                .bucket(AMALGAM::getBucket);
    }

    public static int fromHex(String text) {
        if (text.length() == 6) {
            text = "FF" + text;
        }
        return (int) Long.parseLong(text, 16);
    }

}