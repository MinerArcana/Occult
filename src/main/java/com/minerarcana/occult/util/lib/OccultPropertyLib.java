package com.minerarcana.occult.util.lib;

import com.minerarcana.occult.blocks.flowers.BleachedFlower;
import com.minerarcana.occult.blocks.flowers.DeepFlower;
import com.minerarcana.occult.blocks.flowers.InfernalFlower;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.BooleanProperty;

public class OccultPropertyLib
{

    public static Block OCCULTLOG = new LogBlock(MaterialColor.BROWN, (Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static Block OCCULTWOOD = new Block(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));

    public static Block OCCULTLEAVES = new LeavesBlock((Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)));

   //Flower Properties
    public static Block DEEPFLOWER = new DeepFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)));
    public static Block INFERNALFLOWER = new InfernalFlower((Block.Properties.create(Material.PLANTS).lightValue(10).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)));
    public static Block BLEACHEDFLOWER = new BleachedFlower((Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)));

    //Eldritch Stone
    public static BooleanProperty TOP;
    public static BooleanProperty MIDDLE;
    public static BooleanProperty BOTTOM;
    public static final BooleanProperty BOTTOMPROPERTY = BooleanProperty.create("bottom");
    public static final BooleanProperty MIDDLEPROPERTY = BooleanProperty.create("middle");
    public static final BooleanProperty TOPPROPERTY = BooleanProperty.create("top");

}
