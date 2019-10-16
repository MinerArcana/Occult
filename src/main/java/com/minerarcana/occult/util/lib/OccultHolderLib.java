package com.minerarcana.occult.util.lib;

import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ObjectHolder;
@ObjectHolder("occult")
public class OccultHolderLib
{

    //Bush
    public static BlockState BUSH;

    //Eldritch Stone
    public static BooleanProperty TOP;
    public static BooleanProperty MIDDLE;
    public static BooleanProperty BOTTOM;
    public static final BooleanProperty BOTTOMPROPERTY = BooleanProperty.create("bottom");
    public static final BooleanProperty MIDDLEPROPERTY = BooleanProperty.create("middle");
    public static final BooleanProperty TOPPROPERTY = BooleanProperty.create("top");

}
