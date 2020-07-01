package minerarcana.occult.util;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

import static minerarcana.occult.Occult.MOD_ID;

public class TagHelper {

    //Heat Sources
    public static final Tag<Block> LOW_HEAT = tag("heat/low_heat");
    public static final Tag<Block> MEDIUM_HEAT = tag("heat/medium_heat");
    public static final Tag<Block> HIGH_HEAT = tag("heat/high_heat");
    public static final Tag<Block> EXTREME_HEAT = tag("heat/extreme_heat");
    public static final Tag<Block> INFERNAL_HEAT = tag("heat/internal_heat");
    public static final Tag<Block> LIONMETALFOOD = tag("lionmetalfood");

    private static Tag<Block> tag(String name) {
        return new BlockTags.Wrapper(new ResourceLocation(MOD_ID, name));
    }
}
