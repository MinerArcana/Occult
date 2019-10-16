package com.minerarcana.occult.events;


import com.minerarcana.occult.blocks.*;
import com.minerarcana.occult.blocks.flowers.BleachedFlower;
import com.minerarcana.occult.blocks.flowers.DeepFlower;
import com.minerarcana.occult.blocks.flowers.InfernalFlower;
import com.minerarcana.occult.blocks.tileentity.*;
import com.minerarcana.occult.blocks.vegetation.*;
import com.minerarcana.occult.items.SacredSalt;
import com.minerarcana.occult.util.itemgroup.OccultGroup;
import com.minerarcana.occult.world.biome.overworld.TheBleachedWood;
import com.minerarcana.occult.world.biome.overworld.TheCharredWoodlands;
import com.minerarcana.occult.world.biome.overworld.TheDeepForest;
import com.minerarcana.occult.world.biome.surfaces.overworld.DeepSpookySurface;
import com.minerarcana.occult.world.feature.BushFeature;
import com.minerarcana.occult.world.feature.EldritchStoneFeature;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.minerarcana.occult.Occult.MOD_ID;
import static com.minerarcana.occult.util.lib.OccultNameLib.*;
import static net.minecraft.block.material.Material.*;
import static net.minecraft.block.material.MaterialColor.BROWN;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OccultRegistryEvents {


    @SubscribeEvent
    public static void onRegisterSurfaces(RegistryEvent.Register<SurfaceBuilder<?>> event) {
        event.getRegistry().register(new DeepSpookySurface().setRegistryName("deepspookysurface"));
    }

    //Feature Registry
    //============================================================
    @SubscribeEvent
    public static void onRegisterFeature(RegistryEvent.Register<Feature<?>> event) {

        event.getRegistry().register(new BushFeature(NoFeatureConfig::deserialize).setRegistryName("ivyfeature"));
        event.getRegistry().register(new EldritchStoneFeature(NoFeatureConfig::deserialize).setRegistryName("eldritchstonefeature"));
        event.getRegistry().register(new BushFeature(NoFeatureConfig::deserialize).setRegistryName("phantombushfeature"));
        event.getRegistry().register(new BushFeature(NoFeatureConfig::deserialize).setRegistryName("echobushfeature"));
        event.getRegistry().register(new BushFeature(NoFeatureConfig::deserialize).setRegistryName("stranglegrassfeature"));

    }

    //Biome Registry
    //============================================================
    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
        event.getRegistry().register(new TheDeepForest().setRegistryName("spookyforest"));
        event.getRegistry().register(new TheCharredWoodlands().setRegistryName("infernalforest"));
        event.getRegistry().register(new TheBleachedWood().setRegistryName("bleachedforest"));

    }

    //TileEntity Registry
    //============================================================
    @SubscribeEvent
    public static void onRegisterTileEntity(final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().register(TileEntityType.Builder.create(CrucibleTile::new, crucible).build(null).setRegistryName("crucible"));
        event.getRegistry().register(TileEntityType.Builder.create(RitualBaseTile::new, ritualbase).build(null).setRegistryName("ritualbase"));
        event.getRegistry().register(TileEntityType.Builder.create(RitualFireTile::new, ritualfire).build(null).setRegistryName("ritualfire"));
        event.getRegistry().register(TileEntityType.Builder.create(EldritchStoneTile::new, eldritchstone).build(null).setRegistryName("eldritchstone"));
        event.getRegistry().register(TileEntityType.Builder.create(TransformedStoneTile::new, transformedstone).build(null).setRegistryName("transformedstone"));

    }

    //Item Registry
    //============================================================
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        //Items
        //=============================================================
        event.getRegistry().register(new Item(new Item.Properties().group(OccultGroup.instance)).setRegistryName("ivy"));
        event.getRegistry().register(new Item(new Item.Properties().group(OccultGroup.instance)).setRegistryName("amalgam"));
        event.getRegistry().register(new SacredSalt(new Item.Properties().group(OccultGroup.instance)).setRegistryName("sacredsalt"));
        event.getRegistry().register(new Item(new Item.Properties().group(OccultGroup.instance)).setRegistryName("greenvitriol"));
        event.getRegistry().register(new Item(new Item.Properties().group(OccultGroup.instance)).setRegistryName("pyrite"));
        event.getRegistry().register(new Item(new Item.Properties().group(OccultGroup.instance)).setRegistryName("lionmetal"));
        event.getRegistry().register(new Item(new Item.Properties().group(OccultGroup.instance)).setRegistryName("chalk"));
        event.getRegistry().register(new Item(new Item.Properties().group(OccultGroup.instance)).setRegistryName("mercury"));
        event.getRegistry().register(new Item(new Item.Properties().group(OccultGroup.instance)).setRegistryName("sulphur"));
        event.getRegistry().register(new Item(new Item.Properties().group(OccultGroup.instance)).setRegistryName("salt"));


        //ItemBlocks
        //=================================================================
        event.getRegistry().register(new BlockItem(deathcherrylog, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deathcherrylog"));
        event.getRegistry().register(new BlockItem(deathcherrywood, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deathcherrywood"));
        event.getRegistry().register(new BlockItem(deathcherryplanks, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deathcherryplanks"));
        event.getRegistry().register(new BlockItem(deathcherryleaves, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deathcherryleaves"));
        event.getRegistry().register(new BlockItem(pinkcherryplanks, new Item.Properties().group(OccultGroup.instance)).setRegistryName("pinkcherryplanks"));

        event.getRegistry().register(new BlockItem(deeplog1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deeplog1"));
        event.getRegistry().register(new BlockItem(deeplog2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deeplog2"));
        event.getRegistry().register(new BlockItem(deeplog3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deeplog3"));
        event.getRegistry().register(new BlockItem(infernallog1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernallog1"));
        event.getRegistry().register(new BlockItem(infernallog2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernallog2"));
        event.getRegistry().register(new BlockItem(infernallog3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernallog3"));
        event.getRegistry().register(new BlockItem(bleachedlog1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedlog1"));
        event.getRegistry().register(new BlockItem(bleachedlog2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedlog2"));
        event.getRegistry().register(new BlockItem(bleachedlog3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedlog3"));

        event.getRegistry().register(new BlockItem(deepleaves1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepleaves1"));
        event.getRegistry().register(new BlockItem(deepleaves2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepleaves2"));
        event.getRegistry().register(new BlockItem(deepleaves3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepleaves3"));
        event.getRegistry().register(new BlockItem(infernalleaves1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalleaves1"));
        event.getRegistry().register(new BlockItem(infernalleaves2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalleaves2"));
        event.getRegistry().register(new BlockItem(infernalleaves3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalleaves3"));
        event.getRegistry().register(new BlockItem(bleachedleaves1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedleaves1"));
        event.getRegistry().register(new BlockItem(bleachedleaves2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedleaves2"));
        event.getRegistry().register(new BlockItem(bleachedleaves3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedleaves3"));

        event.getRegistry().register(new BlockItem(deepwood1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepwood1"));
        event.getRegistry().register(new BlockItem(deepwood2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepwood2"));
        event.getRegistry().register(new BlockItem(deepwood3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepwood3"));
        event.getRegistry().register(new BlockItem(infernalwood1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalwood1"));
        event.getRegistry().register(new BlockItem(infernalwood2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalwood2"));
        event.getRegistry().register(new BlockItem(infernalwood3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalwood3"));
        event.getRegistry().register(new BlockItem(bleachedwood1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedwood1"));
        event.getRegistry().register(new BlockItem(bleachedwood2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedwood2"));
        event.getRegistry().register(new BlockItem(bleachedwood3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedwood3"));

        event.getRegistry().register(new BlockItem(deepplanks1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepplanks1"));
        event.getRegistry().register(new BlockItem(deepplanks2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepplanks2"));
        event.getRegistry().register(new BlockItem(deepplanks3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepplanks3"));
        event.getRegistry().register(new BlockItem(infernalplanks1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalplanks1"));
        event.getRegistry().register(new BlockItem(infernalplanks2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalplanks2"));
        event.getRegistry().register(new BlockItem(infernalplanks3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalplanks3"));
        event.getRegistry().register(new BlockItem(bleachedplanks1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedplanks1"));
        event.getRegistry().register(new BlockItem(bleachedplanks2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedplanks2"));
        event.getRegistry().register(new BlockItem(bleachedplanks3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedplanks3"));

        event.getRegistry().register(new BlockItem(deepflower1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepflower1"));
        event.getRegistry().register(new BlockItem(deepflower2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepflower2"));
        event.getRegistry().register(new BlockItem(deepflower3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepflower3"));
        event.getRegistry().register(new BlockItem(deepflower4, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepflower4"));
        event.getRegistry().register(new BlockItem(deepflower5, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepflower5"));
        event.getRegistry().register(new BlockItem(deepflower6, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepflower6"));

        event.getRegistry().register(new BlockItem(infernalflower1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalflower1"));
        event.getRegistry().register(new BlockItem(infernalflower2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalflower2"));
        event.getRegistry().register(new BlockItem(infernalflower3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalflower3"));
        event.getRegistry().register(new BlockItem(infernalflower4, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalflower4"));
        event.getRegistry().register(new BlockItem(infernalflower5, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalflower5"));
        event.getRegistry().register(new BlockItem(infernalflower6, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infernalflower6"));

        event.getRegistry().register(new BlockItem(bleachedflower1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedflower1"));
        event.getRegistry().register(new BlockItem(bleachedflower2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedflower2"));
        event.getRegistry().register(new BlockItem(bleachedflower3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedflower3"));
        event.getRegistry().register(new BlockItem(bleachedflower4, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedflower4"));
        event.getRegistry().register(new BlockItem(bleachedflower5, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedflower5"));
        event.getRegistry().register(new BlockItem(bleachedflower6, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bleachedflower6"));

        event.getRegistry().register(new BlockItem(bleachedflower1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("deepgrass"));
        event.getRegistry().register(new BlockItem(bleachedflower2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("halfgrass"));
        event.getRegistry().register(new BlockItem(bleachedflower3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("brimstone"));
        event.getRegistry().register(new BlockItem(bleachedflower4, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bonesand"));
        event.getRegistry().register(new BlockItem(bleachedflower5, new Item.Properties().group(OccultGroup.instance)).setRegistryName("rockytrails"));
        event.getRegistry().register(new BlockItem(bleachedflower6, new Item.Properties().group(OccultGroup.instance)).setRegistryName("cinnabarore"));
        event.getRegistry().register(new BlockItem(bleachedflower3, new Item.Properties().group(OccultGroup.instance)).setRegistryName("saltore"));
        event.getRegistry().register(new BlockItem(bleachedflower4, new Item.Properties().group(OccultGroup.instance)).setRegistryName("saltblock"));
        event.getRegistry().register(new BlockItem(bleachedflower5, new Item.Properties().group(OccultGroup.instance)).setRegistryName("sacredsaltblock"));
        event.getRegistry().register(new BlockItem(bleachedflower6, new Item.Properties().group(OccultGroup.instance)).setRegistryName("sulphurblock"));

        event.getRegistry().register(new BlockItem(poisonivy, new Item.Properties().group(OccultGroup.instance)).setRegistryName("poisonivy"));
        event.getRegistry().register(new BlockItem(stranglegrass, new Item.Properties().group(OccultGroup.instance)).setRegistryName("stranglegrass"));
        event.getRegistry().register(new BlockItem(slickmoss, new Item.Properties().group(OccultGroup.instance)).setRegistryName("slickmoss"));
        event.getRegistry().register(new BlockItem(phantombush, new Item.Properties().group(OccultGroup.instance)).setRegistryName("phantombush"));
        event.getRegistry().register(new BlockItem(falsesod, new Item.Properties().group(OccultGroup.instance)).setRegistryName("falsesod"));
        event.getRegistry().register(new BlockItem(echobush1, new Item.Properties().group(OccultGroup.instance)).setRegistryName("echobush1"));
        event.getRegistry().register(new BlockItem(echobush2, new Item.Properties().group(OccultGroup.instance)).setRegistryName("echobush2"));
        event.getRegistry().register(new BlockItem(smalldriedanimalbones, new Item.Properties().group(OccultGroup.instance)).setRegistryName("smalldriedanimalbones"));
        event.getRegistry().register(new BlockItem(bigdriedanimalbones, new Item.Properties().group(OccultGroup.instance)).setRegistryName("bigdriedanimalbones"));
        event.getRegistry().register(new BlockItem(skull, new Item.Properties().group(OccultGroup.instance)).setRegistryName("skull"));

        event.getRegistry().register(new BlockItem(transformedstone, new Item.Properties().group(OccultGroup.instance)).setRegistryName("transformedstone"));
        event.getRegistry().register(new BlockItem(eldritchstone, new Item.Properties().group(OccultGroup.instance)).setRegistryName("eldritchstone"));
        event.getRegistry().register(new BlockItem(movinggrass, new Item.Properties().group(OccultGroup.instance)).setRegistryName("movinggrass"));
        event.getRegistry().register(new BlockItem(rootedsoil, new Item.Properties().group(OccultGroup.instance)).setRegistryName("rootedsoil"));
        event.getRegistry().register(new BlockItem(infestedmushroom, new Item.Properties().group(OccultGroup.instance)).setRegistryName("infestedmushroom"));
        event.getRegistry().register(new BlockItem(ritualfire, new Item.Properties().group(OccultGroup.instance)).setRegistryName("ritualfire"));
        event.getRegistry().register(new BlockItem(ritualbase, new Item.Properties().group(OccultGroup.instance)).setRegistryName("ritualbase"));
        event.getRegistry().register(new BlockItem(crucible, new Item.Properties().group(OccultGroup.instance)).setRegistryName("crucible"));

    }

    //Block Registry
    //============================================================
    @SubscribeEvent
    public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
        //Deep Forest Biome
        event.getRegistry().register(new LogBlock(BROWN, (Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))).setRegistryName("deathcherrylog"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("deathcherrywood"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("deathcherryplanks"));
        event.getRegistry().register(new LeavesBlock(Block.Properties.create(LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("deathcherryleaves"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("pinkcherryplanks"));

        event.getRegistry().register(new LogBlock(BROWN, (Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))).setRegistryName("deeplog1"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("deepwood1"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("deepplanks1"));
        event.getRegistry().register(new LeavesBlock(Block.Properties.create(LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("deepleaves1"));

        event.getRegistry().register(new LogBlock(BROWN, (Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))).setRegistryName("deeplog2"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("deepwood2"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("deepplanks2"));
        event.getRegistry().register(new LeavesBlock(Block.Properties.create(LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("deepleaves2"));

        event.getRegistry().register(new LogBlock(BROWN, (Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))).setRegistryName("deeplog3"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("deepwood3"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("deepplanks3"));
        event.getRegistry().register(new LeavesBlock(Block.Properties.create(LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("deepleaves3"));

        //Charred Woodlands Biome
        event.getRegistry().register(new LogBlock(BROWN, (Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))).setRegistryName("infernallog1"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("infernalwood1"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("infernalplanks1"));
        event.getRegistry().register(new LeavesBlock(Block.Properties.create(LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("infernalleaves1"));

        event.getRegistry().register(new LogBlock(BROWN, (Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))).setRegistryName("infernallog2"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("infernalwood2"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("infernalplanks2"));
        event.getRegistry().register(new LeavesBlock(Block.Properties.create(LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("infernalleaves2"));

        event.getRegistry().register(new LogBlock(BROWN, (Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))).setRegistryName("infernallog3"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("infernalwood3"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("infernalplanks3"));
        event.getRegistry().register(new LeavesBlock(Block.Properties.create(LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("infernalleaves3"));

        //Bleached Wood Biome
        event.getRegistry().register(new LogBlock(BROWN, (Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))).setRegistryName("bleachedlog1"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("bleachedwood1"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("bleachedplanks1"));
        event.getRegistry().register(new LeavesBlock(Block.Properties.create(LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("bleachedleaves1"));

        event.getRegistry().register(new LogBlock(BROWN, (Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))).setRegistryName("bleachedlog2"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("bleachedwood2"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("bleachedplanks2"));
        event.getRegistry().register(new LeavesBlock(Block.Properties.create(LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("bleachedleaves2"));

        event.getRegistry().register(new LogBlock(BROWN, (Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))).setRegistryName("bleachedlog3"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("bleachedwood3"));
        event.getRegistry().register(new Block(Block.Properties.create(WOOD, BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("bleachedplanks3"));
        event.getRegistry().register(new LeavesBlock(Block.Properties.create(LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("bleachedleaves3"));

        //Deep Flowers
        event.getRegistry().register(new DeepFlower(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("deepflower1"));
        event.getRegistry().register(new DeepFlower(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("deepflower2"));
        event.getRegistry().register(new DeepFlower(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("deepflower3"));
        event.getRegistry().register(new DeepFlower(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("deepflower4"));
        event.getRegistry().register(new DeepFlower(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("deepflower5"));
        event.getRegistry().register(new DeepFlower(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("deepflower6"));

        //Infernal Flowers
        event.getRegistry().register(new InfernalFlower(Block.Properties.create(PLANTS).lightValue(10).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("infernalflower1"));
        event.getRegistry().register(new InfernalFlower(Block.Properties.create(PLANTS).lightValue(10).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("infernalflower2"));
        event.getRegistry().register(new InfernalFlower(Block.Properties.create(PLANTS).lightValue(10).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("infernalflower3"));
        event.getRegistry().register(new InfernalFlower(Block.Properties.create(PLANTS).lightValue(10).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("infernalflower4"));
        event.getRegistry().register(new InfernalFlower(Block.Properties.create(PLANTS).lightValue(10).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("infernalflower5"));
        event.getRegistry().register(new InfernalFlower(Block.Properties.create(PLANTS).lightValue(10).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("infernalflower6"));

        //BleachedFlowers
        event.getRegistry().register(new BleachedFlower(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("bleachedflower1"));
        event.getRegistry().register(new BleachedFlower(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("bleachedflower2"));
        event.getRegistry().register(new BleachedFlower(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("bleachedflower3"));
        event.getRegistry().register(new BleachedFlower(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("bleachedflower4"));
        event.getRegistry().register(new BleachedFlower(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("bleachedflower5"));
        event.getRegistry().register(new BleachedFlower(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("bleachedflower6"));

        //Moving Features
        event.getRegistry().register(new TransformedStone(Block.Properties.create(ROCK).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.STONE)).setRegistryName("transformedstone"));

        event.getRegistry().register(new EldritchStone(Block.Properties.create(ROCK).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.STONE)));
        event.getRegistry().register(new MovingGrass(Block.Properties.create(PLANTS).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("movinggrass"));
        event.getRegistry().register(new RoottrackedSoil(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("rootedsoil"));
        event.getRegistry().register(new RadiantMushroom(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).lightValue(8).sound(SoundType.PLANT)).setRegistryName("infestedmushroom"));

        //Magic Machines
        event.getRegistry().register(new RitualFire((Block.Properties.create(FIRE).doesNotBlockMovement().hardnessAndResistance(-0.1F, 33333338).lightValue(8).sound(SoundType.SNOW))).setRegistryName("ritualfire"));
        event.getRegistry().register(new RitualBase((Block.Properties.create(ROCK).hardnessAndResistance(1.7f).sound(SoundType.STONE))).setRegistryName("ritualbase"));
        event.getRegistry().register(new CrucibleBlock((Block.Properties.create(ROCK).hardnessAndResistance(1.7f).sound(SoundType.STONE))).setRegistryName("crucible"));

        //Vegetation/Ingredients
        event.getRegistry().register(new PoisonIvy(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("poisonivy"));
        event.getRegistry().register(new StrangleGrass(Block.Properties.create(TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("stranglegrass"));
        event.getRegistry().register(new SlickMoss(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("slickmoss"));
        event.getRegistry().register(new PhatomBushes(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("phantombush"));
        event.getRegistry().register(new FalseSod(Block.Properties.create(PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("falsesod"));
        event.getRegistry().register(new EchocryBushes(Block.Properties.create(PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("echobush1"));
        event.getRegistry().register(new EchocryBushes(Block.Properties.create(PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName("echobush2"));

        //Deco
        event.getRegistry().register(new Brimstone(Block.Properties.create(ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE)).setRegistryName("smalldriedanimalbones"));
        event.getRegistry().register(new Brimstone(Block.Properties.create(ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE)).setRegistryName("bigdriedanimalbones"));
        event.getRegistry().register(new Brimstone(Block.Properties.create(ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE)).setRegistryName("skull"));

        //WorldBlocks
        //TODO remove copy pastes
        event.getRegistry().register(new DeepGrass(Block.Properties.create(ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("deepgrass"));
        event.getRegistry().register(new DeepGrass(Block.Properties.create(ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("halfgrass"));
        event.getRegistry().register(new DeepGrass(Block.Properties.create(SAND).hardnessAndResistance(0.6F).sound(SoundType.SAND)).setRegistryName("bonesand"));
        event.getRegistry().register(new Brimstone(Block.Properties.create(ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE)).setRegistryName("rockytrails"));
        event.getRegistry().register(new Brimstone(Block.Properties.create(ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE)).setRegistryName("sacredsaltblock"));
        event.getRegistry().register(new Brimstone(Block.Properties.create(ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE)).setRegistryName("sulphurblock"));

        //Ores
        event.getRegistry().register(new Brimstone(Block.Properties.create(ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE)).setRegistryName("brimstone"));
        event.getRegistry().register(new Block(Block.Properties.create(ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE)).setRegistryName("cinnabarore"));
        event.getRegistry().register(new Block(Block.Properties.create(ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE)).setRegistryName("saltore"));
        event.getRegistry().register(new Brimstone(Block.Properties.create(ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE)).setRegistryName("saltblock"));
    }

}
