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

        private static Tag<Item> tag(String name) {
            return new ItemTags.Wrapper(new ResourceLocation(MOD_ID, name));
        }
    }

    public static class Blocks {

        //Heat Sources
        public static final Tag<Block> LOWHEATSOURCE = tag("lowheatsource");
        public static final Tag<Block> MEDIUMLOWHEATSOURCE = tag("mediumlowheatsource");
        public static final Tag<Block> MEDIUMHEATSOURCE = tag("mediumheatsource");
        public static final Tag<Block> MEDIUMHIGHHEATSOURCE = tag("mediumhighheatsource");
        public static final Tag<Block> HIGHHEATSOURCE = tag("highheatsource");
        public static final Tag<Block> EXTREMELYHIGHHEATSOURCE = tag("extremelyhighheatsource");

        //LogBlocks
        public static final Tag<Block> BLEACHEDLOGS = tag("bleachedlogs");
        public static final Tag<Block> DEEPLOGS = tag("deeplogs");
        public static final Tag<Block> INFERNALLOGS = tag("infernallogs");

        //Eldritch Stone
        public static final Tag<Block> ELDRITCH = tag("eldritch");
        //FalseSod
        public static final Tag<Block> FALSESOD = tag("falsesod");

        //UTILITY TAGS
        public static final Tag<Block> VALIDGROUND = tag("validground");
        public static final Tag<Block> VALIDNETHERGROUND = tag("validnetherground");
        public static final Tag<Block> OVERWORLD = tag("overworld");
        public static final Tag<Block> NETHER = tag("nether");

        private static Tag<Block> tag(String name) {
            return new BlockTags.Wrapper(new ResourceLocation(MOD_ID, name));
        }
    }


}
