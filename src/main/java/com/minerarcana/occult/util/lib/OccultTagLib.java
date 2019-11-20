package com.minerarcana.occult.util.lib;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

import static com.minerarcana.occult.Occult.MOD_ID;

public class OccultTagLib {

    public static class Items {
        public static final Tag<Item> CRUCIBLE = tag("crucible");
        public static final Tag<Item> AKJAR = tag("akjar");

        private static Tag<Item> tag(String name) {
            return new ItemTags.Wrapper(new ResourceLocation(MOD_ID, name));
        }
    }

    public static class Blocks {

        //Heat Sources
        public static final Tag<Block> LOWHEATSOURCE = tag("heat/lowheatsource");
        public static final Tag<Block> MEDIUMLOWHEATSOURCE = tag("heat/mediumlowheatsource");
        public static final Tag<Block> MEDIUMHEATSOURCE = tag("heat/mediumheatsource");
        public static final Tag<Block> MEDIUMHIGHHEATSOURCE = tag("heat/mediumhighheatsource");
        public static final Tag<Block> HIGHHEATSOURCE = tag("heat/highheatsource");
        public static final Tag<Block> EXTREMELYHIGHHEATSOURCE = tag("heat/extremelyhighheatsource");

        //LionMetalFood
        public static final Tag<Block> LIONMETALFOOD = tag("lionmetalfood");

        private static Tag<Block> tag(String name) {
            return new BlockTags.Wrapper(new ResourceLocation(MOD_ID, name));
        }
    }


}
