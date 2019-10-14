package com.minerarcana.occult.common.blocks;

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

import static com.minerarcana.occult.update.util.lib.OccultLib.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class OccultBlocks {


    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        //Deep Forest Biome
        deathcherrylog = registerBlock(new LogBlock(MaterialColor.BROWN, (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deathcherrylog");
        deathcherrywood = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deathcherrywood");
        deathcherryplanks = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deathcherryplanks");
        deathcherrystairs = registerBlock(new OccultStairs(deathcherryplanks.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deathcherrystairs");
        deathcherryleaves = registerBlock(new LeavesBlock((Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "deathcherryleaves");
        pinkcherryplanks = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "pinkcherryplanks");

        deeplog1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deeplog1");
        deepwood1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepwood1");
        deepplanks1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepplanks1");
        deepstairs1 = registerBlock(new OccultStairs(deepplanks1.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepstairs1");
        deepleaves1 = registerBlock(new LeavesBlock((Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "deepleaves1");

        deeplog2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deeplog2");
        deepwood2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepwood2");
        deepplanks2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepplanks2");
        deepstairs2 = registerBlock(new OccultStairs(deepplanks2.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepstairs2");
        deepleaves2 = registerBlock(new LeavesBlock((Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "deepleaves2");

        deeplog3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deeplog3");
        deepwood3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepwood3");
        deepplanks3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepplanks3");
        deepstairs3 = registerBlock(new OccultStairs(deepplanks2.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "deepstairs3");
        deepleaves3 = registerBlock(new LeavesBlock((Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "deepleaves3");

        //Charred Woodlands Biome
        infernallog1 = registerBlock(new LogBlock(MaterialColor.BROWN, (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernallog1");
        infernalwood1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalwood1");
        infernalplanks1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalplanks1");
        infernalstairs1 = registerBlock(new OccultStairs(infernalplanks1.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalstairs1");
        infernalleaves1 = registerBlock(new LeavesBlock((Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "infernalleaves1");

        infernallog2 = registerBlock(new LogBlock(MaterialColor.BROWN, (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernallog2");
        infernalwood2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalwood2");
        infernalplanks2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalplanks2");
        infernalstairs2 = registerBlock(new OccultStairs(infernalplanks2.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalstairs2");
        infernalleaves2 = registerBlock(new LeavesBlock((Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "infernalleaves2");

        infernallog3 = registerBlock(new LogBlock(MaterialColor.BROWN, (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernallog3");
        infernalwood3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalwood3");
        infernalplanks3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalplanks3");
        infernalstairs3 = registerBlock(new OccultStairs(infernalplanks2.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "infernalstairs3");
        infernalleaves3 = registerBlock(new LeavesBlock((Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "infernalleaves3");

        //Bleached Wood Biome
        bleachedlog1 = registerBlock(new LogBlock(MaterialColor.BROWN, (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedlog1");
        bleachedwood1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedwood1");
        bleachedplanks1 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedplanks1");
        bleachedstairs1 = registerBlock(new OccultStairs(bleachedplanks1.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedstairs1");
        bleachedleaves1 = registerBlock(new LeavesBlock((Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "bleachedleaves1");

        bleachedlog2 = registerBlock(new LogBlock(MaterialColor.BROWN, (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedlog2");
        bleachedwood2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedwood2");
        bleachedplanks2 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedplanks2");
        bleachedstairs2 = registerBlock(new OccultStairs(bleachedplanks2.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedstairs2");
        bleachedleaves2 = registerBlock(new LeavesBlock((Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "bleachedleaves2");

        bleachedlog3 = registerBlock(new LogBlock(MaterialColor.BROWN, (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedlog3");
        bleachedwood3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedwood3");
        bleachedplanks3 = registerBlock(new Block((Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedplanks3");
        bleachedstairs3 = registerBlock(new OccultStairs(bleachedplanks2.getDefaultState(), (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))), "bleachedstairs3");
        bleachedleaves3 = registerBlock(new LeavesBlock((Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))), "bleachedleaves3");

        //Deep Flowers
        deepflower1 = registerBlock(new DeepFlower((Block.Properties.create(Material.PLANTS).lightValue(100).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "deepflower1");
        deepflower2 = registerBlock(new DeepFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "deepflower2");
        deepflower3 = registerBlock(new DeepFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "deepflower3");
        deepflower4 = registerBlock(new DeepFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "deepflower4");
        deepflower5 = registerBlock(new DeepFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "deepflower5");
        deepflower6 = registerBlock(new DeepFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "deepflower6");

        //Infernal Flowers
        infernalflower1 = registerBlock(new InfernalFlower((Block.Properties.create(Material.PLANTS).lightValue(10).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "infernalflower1");
        infernalflower2 = registerBlock(new InfernalFlower((Block.Properties.create(Material.PLANTS).lightValue(10).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "infernalflower2");
        infernalflower3 = registerBlock(new InfernalFlower((Block.Properties.create(Material.PLANTS).lightValue(10).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "infernalflower3");
        infernalflower4 = registerBlock(new InfernalFlower((Block.Properties.create(Material.PLANTS).lightValue(10).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "infernalflower4");
        infernalflower5 = registerBlock(new InfernalFlower((Block.Properties.create(Material.PLANTS).lightValue(10).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "infernalflower5");
        infernalflower6 = registerBlock(new InfernalFlower((Block.Properties.create(Material.PLANTS).lightValue(10).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "infernalflower6");

        //BleachedFlowers
        bleachedflower1 = registerBlock(new BleachedFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "bleachedflower1");
        bleachedflower2 = registerBlock(new BleachedFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "bleachedflower2");
        bleachedflower3 = registerBlock(new BleachedFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "bleachedflower3");
        bleachedflower4 = registerBlock(new BleachedFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "bleachedflower4");
        bleachedflower5 = registerBlock(new BleachedFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "bleachedflower5");
        bleachedflower6 = registerBlock(new BleachedFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "bleachedflower6");

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
