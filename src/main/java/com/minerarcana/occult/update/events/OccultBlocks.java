package com.minerarcana.occult.update.events;

import com.minerarcana.occult.common.blocks.featureblocks.eldritchstone.EldritchStone;
import com.minerarcana.occult.common.blocks.flowers.BleachedFlower;
import com.minerarcana.occult.common.blocks.flowers.DeepFlower;
import com.minerarcana.occult.common.blocks.flowers.InfernalFlower;
import com.minerarcana.occult.common.blocks.normal.OccultStairs;
import com.minerarcana.occult.common.blocks.vegetation.EchocryBushes;
import com.minerarcana.occult.common.blocks.vegetation.PoisonIvy;
import com.minerarcana.occult.common.blocks.vegetation.SlickMoss;
import com.minerarcana.occult.common.blocks.vegetation.StrangleGrass;
import com.minerarcana.occult.common.blocks.worldblocks.Brimstone;
import com.minerarcana.occult.common.blocks.worldblocks.DeepGrass;
import com.minerarcana.occult.common.tileentity.crucible.CrucibleBlock;
import com.minerarcana.occult.common.tileentity.ritualfire.RitualBase;
import com.minerarcana.occult.common.tileentity.ritualfire.RitualFire;
import com.minerarcana.occult.update.util.itemgroup.OccultGroup;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.occult.update.util.lib.OccultNameLib.*;
import static com.minerarcana.occult.update.util.lib.OccultPropertyLib.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class OccultBlocks {




    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        //Deep Forest Biome
        event.getRegistry().register(OCCULTLOG.setRegistryName("deathcherrylog"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "deathcherrywood"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "deathcherryplanks"));
        event.getRegistry().register(new OccultStairs(deathcherryplanks.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deathcherrystairs");
        event.getRegistry().register(OCCULTLEAVES.setRegistryName( "deathcherryleaves"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "pinkcherryplanks"));

        event.getRegistry().register(OCCULTLOG.setRegistryName( "deeplog1"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "deepwood1"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "deepplanks1"));
        deepstairs1 = registerBlock(new OccultStairs(deepplanks1.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepstairs1");
        event.getRegistry().register(OCCULTLEAVES.setRegistryName( "deepleaves1"));

        event.getRegistry().register(OCCULTLOG.setRegistryName( "deeplog2"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "deepwood2"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "deepplanks2"));
        deepstairs2 = registerBlock(new OccultStairs(deepplanks2.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepstairs2");
        event.getRegistry().register(OCCULTLEAVES.setRegistryName( "deepleaves2"));

        event.getRegistry().register(OCCULTLOG.setRegistryName( "deeplog3"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "deepwood3"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "deepplanks3"));
        deepstairs3 = registerBlock(new OccultStairs(deepplanks2.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepstairs3");
        event.getRegistry().register(OCCULTLEAVES.setRegistryName( "deepleaves3"));

        //Charred Woodlands Biome
        event.getRegistry().register(OCCULTLOG.setRegistryName( "infernallog1"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "infernalwood1"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "infernalplanks1"));
        infernalstairs1 = registerBlock(new OccultStairs(infernalplanks1.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalstairs1");
        event.getRegistry().register(OCCULTLEAVES.setRegistryName( "infernalleaves1"));

        event.getRegistry().register(OCCULTLOG.setRegistryName( "infernallog2"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "infernalwood2"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "infernalplanks2"));
        infernalstairs2 = registerBlock(new OccultStairs(infernalplanks2.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalstairs2");
        event.getRegistry().register(OCCULTLEAVES.setRegistryName( "infernalleaves2"));

        event.getRegistry().register(OCCULTLOG.setRegistryName( "infernallog3"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "infernalwood3"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName( "infernalplanks3"));
        event.getRegistry().register(new OccultStairs(infernalplanks2.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalstairs3");
        event.getRegistry().register(OCCULTLEAVES.setRegistryName("infernalleaves3"));

        //Bleached Wood Biome
        event.getRegistry().register(OCCULTLOG.setRegistryName("bleachedlog1"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName("bleachedwood1"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName("bleachedplanks1"));
        event.getRegistry().register(new OccultStairs(bleachedplanks1.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedstairs1");
        event.getRegistry().register(OCCULTLEAVES.setRegistryName("bleachedleaves1"));

        event.getRegistry().register(OCCULTLOG.setRegistryName("bleachedlog2"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName("bleachedwood2"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName("bleachedplanks2"));
        bleachedstairs2 = registerBlock(new OccultStairs(bleachedplanks2.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedstairs2");
        event.getRegistry().register(OCCULTLEAVES.setRegistryName("bleachedleaves2"));

        event.getRegistry().register(OCCULTLOG.setRegistryName("bleachedlog3"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName("bleachedwood3"));
        event.getRegistry().register(OCCULTWOOD.setRegistryName("bleachedplanks3"));
        bleachedstairs3 = registerBlock(new OccultStairs(bleachedplanks2.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedstairs3");
        event.getRegistry().register(OCCULTLEAVES.setRegistryName("bleachedleaves3"));

        //Deep Flowers
        event.getRegistry().register(DEEPFLOWER.setRegistryName("deepflower1"));
        event.getRegistry().register(DEEPFLOWER.setRegistryName("deepflower2"));
        event.getRegistry().register(DEEPFLOWER.setRegistryName("deepflower3"));
        event.getRegistry().register(DEEPFLOWER.setRegistryName("deepflower4"));
        event.getRegistry().register(DEEPFLOWER.setRegistryName("deepflower5"));
        event.getRegistry().register(DEEPFLOWER.setRegistryName("deepflower6"));

        //Infernal Flowers
        event.getRegistry().register(INFERNALFLOWER.setRegistryName("infernalflower1"));
        event.getRegistry().register(INFERNALFLOWER.setRegistryName("infernalflower2"));
        event.getRegistry().register(INFERNALFLOWER.setRegistryName("infernalflower3"));
        event.getRegistry().register(INFERNALFLOWER.setRegistryName("infernalflower4"));
        event.getRegistry().register(INFERNALFLOWER.setRegistryName("infernalflower5"));
        event.getRegistry().register(INFERNALFLOWER.setRegistryName("infernalflower6"));

        //BleachedFlowers
        event.getRegistry().register(BLEACHEDFLOWER.setRegistryName("bleachedflower1"));
        event.getRegistry().register(BLEACHEDFLOWER.setRegistryName("bleachedflower2"));
        event.getRegistry().register(BLEACHEDFLOWER.setRegistryName("bleachedflower3"));
        event.getRegistry().register(BLEACHEDFLOWER.setRegistryName("bleachedflower4"));
        event.getRegistry().register(BLEACHEDFLOWER.setRegistryName("bleachedflower5"));
        event.getRegistry().register(BLEACHEDFLOWER.setRegistryName("bleachedflower6"));

        //Moving Features
        eldritchstone = registerBlock(new EldritchStone((Block.Properties.create(Material.ROCK).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.STONE))), "eldritchstone");
        movinggrass = registerBlock(new DeepGrass((Block.Properties.create(Material.PLANTS).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "movinggrass");
        rootedsoil = registerBlock(new DeepGrass((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "rootedsoil");
        infestedmushroom = registerBlock(new DeepGrass((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).lightValue(8).sound(SoundType.PLANT))), "infestedmushroom");

        //Magic Machines
        ritualfire = registerBlock(new RitualFire((Block.Properties.create(Material.FIRE).doesNotBlockMovement().hardnessAndResistance(-0.1F, 33333338).lightValue(8).sound(SoundType.SNOW))), "ritualfire");
        ritualbase = registerBlock(new RitualBase((Block.Properties.create(Material.ROCK).hardnessAndResistance(1.7f).sound(SoundType.STONE))), "ritualbase");
        crucible = registerBlock(new CrucibleBlock((Block.Properties.create(Material.ROCK).hardnessAndResistance(1.7f).sound(SoundType.STONE))), "crucible");

        //Vegetation/Ingredients
        poisonivy = registerBlock(new PoisonIvy((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "poisonivy");
        stranglegrass = registerBlock(new StrangleGrass((Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "stranglegrass");
        slickmoss = registerBlock(new SlickMoss((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "slickmoss");
        phantombush = registerBlock(new DeepGrass((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "phantombush");
        faslesod = registerBlock(new DeepGrass((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "faslesod");
        echobush1 = registerBlock(new EchocryBushes((Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "echobush1");
        echobush2 = registerBlock(new EchocryBushes((Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "echobush2");

        //Deco
        smalldriedanimalbones = registerBlock(new Brimstone((Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE))), "smalldriedanimalbones");
        bigdiedanimalbones = registerBlock(new Brimstone((Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE))), "bigdiedanimalbones");
        skull = registerBlock(new Brimstone((Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE))), "skull");

        //WorldBlocks
        deepgrass = registerBlock(new DeepGrass((Block.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "deepgrass");
        halfgrass = registerBlock(new DeepGrass((Block.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "halfgrass");
        brimstone = registerBlock(new Brimstone((Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE))), "brimstone");
        bonesand = registerBlock(new DeepGrass((Block.Properties.create(Material.SAND).hardnessAndResistance(0.6F).sound(SoundType.SAND))), "bonesand");
        rockytrails = registerBlock(new Brimstone((Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE))), "rockytrails");
        cinnabarore = registerBlock(new Brimstone((Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE))), "cinnabarore");
        saltore = registerBlock(new Brimstone((Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE))), "saltore");
        saltblock = registerBlock(new Brimstone((Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE))), "saltblock");
        sacredsaltblock = registerBlock(new Brimstone((Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE))), "sacredsaltblock");
        sulphurblock = registerBlock(new Brimstone((Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE))), "sulphurblock");

    }

    public static Block registerBlock(Block block, String name) {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(OccultGroup.instance));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }


}
