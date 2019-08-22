package com.minerarcana.occult.blocks;

import com.minerarcana.occult.blocks.flowers.BleachedFlower;
import com.minerarcana.occult.blocks.flowers.DeepFlower;
import com.minerarcana.occult.blocks.flowers.InfernalFlower;
import com.minerarcana.occult.blocks.normal.OccultStairs;
import com.minerarcana.occult.blocks.vegetation.StrangleGrass;
import com.minerarcana.occult.blocks.worldblocks.DeepGrass;
import com.minerarcana.occult.util.OccultItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class OccultBlocks
{
    public static Block deepgrass;
    public static Block halfgrass;
    public static Block poisonivy;
    public static Block eldritchstone;
    public static Block movinggrass;
    public static Block bonesand;
    public static Block brimstone;

    //Deep Forest Tree's
    public static Block deathcherrylog;
    public static Block deathcherrywood;
    public static Block deathcherryplanks;
    public static Block deathcherrystairs;
    public static Block deathcherryleaves;
    public static Block pinkcherryplanks;

    public static Block deeplog1;
    public static Block deepwood1;
    public static Block deepplanks1;
    public static Block deepstairs1;
    public static Block deepleaves1;

    public static Block deeplog2;
    public static Block deepwood2;
    public static Block deepplanks2;
    public static Block deepstairs2;
    public static Block deepleaves2;

    public static Block deeplog3;
    public static Block deepwood3;
    public static Block deepplanks3;
    public static Block deepstairs3;
    public static Block deepleaves3;

    //Infernal Forest Tree's

    public static Block infernallog1;
    public static Block infernalwood1;
    public static Block infernalplanks1;
    public static Block infernalstairs1;
    public static Block infernalleaves1;

    public static Block infernallog2;
    public static Block infernalwood2;
    public static Block infernalplanks2;
    public static Block infernalstairs2;
    public static Block infernalleaves2;

    public static Block infernallog3;
    public static Block infernalwood3;
    public static Block infernalplanks3;
    public static Block infernalstairs3;
    public static Block infernalleaves3;

   // Bleached Forest Tree's'

    public static Block bleachedlog1;
    public static Block bleachedwood1;
    public static Block bleachedplanks1;
    public static Block bleachedstairs1;
    public static Block bleachedleaves1;

    public static Block bleachedlog2;
    public static Block bleachedwood2;
    public static Block bleachedplanks2;
    public static Block bleachedstairs2;
    public static Block bleachedleaves2;

    public static Block bleachedlog3;
    public static Block bleachedwood3;
    public static Block bleachedplanks3;
    public static Block bleachedstairs3;
    public static Block bleachedleaves3;

    //Infernal Flowers
    public static Block infernalflower1;
    public static Block infernalflower2;
    public static Block infernalflower3;
    public static Block infernalflower4;
    public static Block infernalflower5;
    public static Block infernalflower6;

    //Deep Flowers
    public static Block deepflower1;
    public static Block deepflower2;
    public static Block deepflower3;
    public static Block deepflower4;
    public static Block deepflower5;
    public static Block deepflower6;

    //Bleached Flowers
    public static Block bleachedflower1;
    public static Block bleachedflower2;
    public static Block bleachedflower3;
    public static Block bleachedflower4;
    public static Block bleachedflower5;
    public static Block bleachedflower6;

    // Infesting Blocks
    public static Block rootedsoil;
    public static Block stranglegrass;
    public static Block slickmoss;
    public static Block infestedmushroom;
    public static Block phantombush;
    public static Block faslesod;
    public static Block echobush;


    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event)
    {
        //Deep Forest Biome
        deathcherrylog = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deathcherrylog");
        deathcherrywood = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deathcherrywood");
        deathcherryplanks = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deathcherryplanks");
        deathcherrystairs = registerBlock(new OccultStairs(deathcherryplanks.getDefaultState(),(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deathcherrystairs");
        deathcherryleaves = registerBlock(new Block((Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "deathcherryleaves");
        pinkcherryplanks = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "pinkcherryplanks");

        deeplog1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deeplog1");
        deepwood1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepwood1");
        deepplanks1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepplanks1");
        deepstairs1 = registerBlock(new OccultStairs(deepplanks1.getDefaultState(),(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepstairs1");
        deepleaves1 = registerBlock(new Block((Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "deepleaves1");

        deeplog2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deeplog2");
        deepwood2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepwood2");
        deepplanks2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepplanks2");
        deepstairs2 = registerBlock(new OccultStairs(deepplanks2.getDefaultState(),(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepstairs2");
        deepleaves2 = registerBlock(new Block((Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "deepleaves2");

        deeplog3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deeplog3");
        deepwood3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepwood3");
        deepplanks3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepplanks3");
        deepstairs3 = registerBlock(new OccultStairs(deepplanks2.getDefaultState(),(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepstairs3");
        deepleaves3 = registerBlock(new Block((Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "deepleaves3");

        //Charred Woodlands Biome

        infernallog1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernallog1");
        infernalwood1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalwood1");
        infernalplanks1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalplanks1");
        infernalstairs1 = registerBlock(new OccultStairs(infernalplanks1.getDefaultState(),(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalstairs1");
        infernalleaves1 = registerBlock(new Block((Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "infernalleaves1");

        infernallog2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernallog2");
        infernalwood2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalwood2");
        infernalplanks2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalplanks2");
        infernalstairs2 = registerBlock(new OccultStairs(infernalplanks2.getDefaultState(),(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalstairs2");
        infernalleaves2 = registerBlock(new Block((Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "infernalleaves2");

        infernallog3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernallog3");
        infernalwood3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalwood3");
        infernalplanks3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalplanks3");
        infernalstairs3 = registerBlock(new OccultStairs(infernalplanks2.getDefaultState(),(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalstairs3");
        infernalleaves3 = registerBlock(new Block((Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "infernalleaves3");

        //Bleached Wood Biome

        bleachedlog1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedlog1");
        bleachedwood1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedwood1");
        bleachedplanks1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedplanks1");
        bleachedstairs1 = registerBlock(new OccultStairs(bleachedplanks1.getDefaultState(),(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedstairs1");
        bleachedleaves1 = registerBlock(new Block((Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "bleachedleaves1");

        bleachedlog2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedlog2");
        bleachedwood2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedwood2");
        bleachedplanks2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedplanks2");
        bleachedstairs2 = registerBlock(new OccultStairs(bleachedplanks2.getDefaultState(),(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedstairs2");
        bleachedleaves2 = registerBlock(new Block((Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "bleachedleaves2");

        bleachedlog3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedlog3");
        bleachedwood3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedwood3");
        bleachedplanks3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedplanks3");
        bleachedstairs3 = registerBlock(new OccultStairs(bleachedplanks2.getDefaultState(),(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedstairs3");
        bleachedleaves3 = registerBlock(new Block((Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "bleachedleaves3");


        //Deep Flowers
        deepflower1 = registerBlock(new DeepFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "deepflower1");
        deepflower2 = registerBlock(new DeepFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "deepflower2");
        deepflower3 = registerBlock(new DeepFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "deepflower3");
        deepflower4 = registerBlock(new DeepFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "deepflower4");
        deepflower5 = registerBlock(new DeepFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "deepflower5");
        deepflower6 = registerBlock(new DeepFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "deepflower6");

        //Infernal Flowers
        infernalflower1 = registerBlock(new InfernalFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "infernalflower1");
        infernalflower2 = registerBlock(new InfernalFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "infernalflower2");
        infernalflower3 = registerBlock(new InfernalFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "infernalflower3");
        infernalflower4 = registerBlock(new InfernalFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "infernalflower4");
        infernalflower5 = registerBlock(new InfernalFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "infernalflower5");
        infernalflower6 = registerBlock(new InfernalFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "infernalflower6");

        //BleachedFlowers
        bleachedflower1 = registerBlock(new BleachedFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "bleachedflower1");
        bleachedflower2 = registerBlock(new BleachedFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "bleachedflower2");
        bleachedflower3 = registerBlock(new BleachedFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "bleachedflower3");
        bleachedflower4 = registerBlock(new BleachedFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "bleachedflower4");
        bleachedflower5 = registerBlock(new BleachedFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "bleachedflower5");
        bleachedflower6 = registerBlock(new BleachedFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "bleachedflower6");


        //Moving Features
        eldritchstone = registerBlock(new DeepGrass((Block.Properties.create(Material.ROCK).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.STONE))), "eldritchstone");
        movinggrass = registerBlock(new DeepGrass((Block.Properties.create(Material.PLANTS).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "movinggrass");
        rootedsoil = registerBlock(new DeepGrass((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "rootedsoil");
        infestedmushroom = registerBlock(new DeepGrass((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "infestedmushroom");



        //Vegetation/Ingredients

        poisonivy = registerBlock(new DeepGrass((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "poisonivy");
        stranglegrass = registerBlock(new StrangleGrass((Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "stranglegrass");
        slickmoss = registerBlock(new DeepGrass((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "slickmoss");
        phantombush = registerBlock(new DeepGrass((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "phantombush");
        faslesod = registerBlock(new DeepGrass((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "faslesod");
        echobush = registerBlock(new DeepGrass((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "echobush");

        //Deco



        //WorldBlocks
        deepgrass = registerBlock(new DeepGrass((Block.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "deepgrass");
        halfgrass = registerBlock(new DeepGrass((Block.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "halfgrass");
        brimstone = registerBlock(new DeepGrass((Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE))), "brimstone");
        bonesand = registerBlock(new DeepGrass((Block.Properties.create(Material.SAND).hardnessAndResistance(0.6F).sound(SoundType.SAND))), "bonesand");

    }

    public static Block registerBlock(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(OccultItemGroup.instance));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }













}
