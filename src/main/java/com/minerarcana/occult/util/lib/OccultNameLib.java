package com.minerarcana.occult.util.lib;


import com.minerarcana.occult.blocks.*;
import com.minerarcana.occult.blocks.tileentity.*;
import com.minerarcana.occult.blocks.tileentity.tilecontainer.RitualBaseContainer;
import com.minerarcana.occult.world.feature.BushFeature;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.registries.ObjectHolder;

import static com.minerarcana.occult.Occult.MOD_ID;

@ObjectHolder(MOD_ID)
public class OccultNameLib
{

    // BlOCKS
    //-------------------------------------------------------------------------------------
    //Feature Blocks
    @ObjectHolder("occult:deepgrass")
    public static Block deepgrass;

    @ObjectHolder("occult:halfgrass")
    public static Block halfgrass;

    @ObjectHolder("occult:poisonivy")
    public static Block poisonivy;

    @ObjectHolder("occult:eldritchstone")
    public static EldritchStone eldritchstone;

    @ObjectHolder("occult:transformedstone")
    public static TransformedStone transformedstone;

    @ObjectHolder("occult:movinggrass")
    public static Block movinggrass;

    @ObjectHolder("occult:bonesand")
    public static Block bonesand;

    @ObjectHolder("occult:rockytrails")
    public static Block rockytrails;


    //Feature Deco
    @ObjectHolder("occult:smalldriedanimalbones")
    public static Block smalldriedanimalbones;

    @ObjectHolder("occult:bigdriedanimalbones")
    public static Block bigdriedanimalbones;

    @ObjectHolder("occult:skull")
    public static Block skull;


    //WorldSpawn

    @ObjectHolder("occult:brimstone")
    public static Block brimstone;

    @ObjectHolder("occult:cinnabarore")
    public static Block cinnabarore;

    @ObjectHolder("occult:saltore")
    public static Block saltore;

    @ObjectHolder("occult:saltblock")
    public static Block saltblock;

    @ObjectHolder("occult:sacredsaltblock")
    public static Block sacredsaltblock;

    @ObjectHolder("occult:sulphurblock")
    public static Block sulphurblock;

    //Deep Forest Tree's
    @ObjectHolder("occult:deathcherrylog")
    public static Block deathcherrylog;

    @ObjectHolder("occult:deathcherrywood")
    public static Block deathcherrywood;

    @ObjectHolder("occult:deathcherryplanks")
    public static Block deathcherryplanks;

    @ObjectHolder("occult:deathcherrystairs")
    public static Block deathcherrystairs;

    @ObjectHolder("occult:deathcherryleaves")
    public static Block deathcherryleaves;

    @ObjectHolder("occult:pinkcherryplanks")
    public static Block pinkcherryplanks;


    //Deep Wood
    @ObjectHolder("occult:deeplog1")
    public static Block deeplog1;

    @ObjectHolder("occult:deepwood1")
    public static Block deepwood1;

    @ObjectHolder("occult:deepplanks1")
    public static Block deepplanks1;

    @ObjectHolder("occult:deepstairs1")
    public static Block deepstairs1;

    @ObjectHolder("occult:deepleaves1")
    public static Block deepleaves1;

    @ObjectHolder("occult:deeplog2")
    public static Block deeplog2;

    @ObjectHolder("occult:deepwood2")
    public static Block deepwood2;

    @ObjectHolder("occult:deepplanks2")
    public static Block deepplanks2;

    @ObjectHolder("occult:deepstairs2")
    public static Block deepstairs2;

    @ObjectHolder("occult:deepleaves2")
    public static Block deepleaves2;

    @ObjectHolder("occult:deeplog3")
    public static Block deeplog3;

    @ObjectHolder("occult:deepwood3")
    public static Block deepwood3;

    @ObjectHolder("occult:deepplanks3")
    public static Block deepplanks3;

    @ObjectHolder("occult:deepstairs3")
    public static Block deepstairs3;

    @ObjectHolder("occult:deepleaves3")
    public static Block deepleaves3;


    //Infernal Forest Tree's
    @ObjectHolder("occult:infernallog1")
    public static Block infernallog1;

    @ObjectHolder("occult:infernalwood1")
    public static Block infernalwood1;

    @ObjectHolder("occult:infernalplanks1")
    public static Block infernalplanks1;

    @ObjectHolder("occult:infernalstairs1")
    public static Block infernalstairs1;

    @ObjectHolder("occult:infernalleaves1")
    public static Block infernalleaves1;


    @ObjectHolder("occult:infernallog2")
    public static Block infernallog2;

    @ObjectHolder("occult:infernalwood2")
    public static Block infernalwood2;

    @ObjectHolder("occult:infernalplanks2")
    public static Block infernalplanks2;

    @ObjectHolder("occult:infernalstairs2")
    public static Block infernalstairs2;

    @ObjectHolder("occult:infernalleaves2")
    public static Block infernalleaves2;

    @ObjectHolder("occult:infernallog3")
    public static Block infernallog3;

    @ObjectHolder("occult:infernalwood3")
    public static Block infernalwood3;

    @ObjectHolder("occult:infernalplanks3")
    public static Block infernalplanks3;

    @ObjectHolder("occult:infernalstairs3")
    public static Block infernalstairs3;

    @ObjectHolder("occult:infernalleaves3")
    public static Block infernalleaves3;


    // Bleached Forest Tree's'
    @ObjectHolder("occult:bleachedlog1")
    public static Block bleachedlog1;

    @ObjectHolder("occult:bleachedwood1")
    public static Block bleachedwood1;

    @ObjectHolder("occult:bleachedplanks1")
    public static Block bleachedplanks1;

    @ObjectHolder("occult:bleachedstairs1")
    public static Block bleachedstairs1;

    @ObjectHolder("occult:bleachedleaves1")
    public static Block bleachedleaves1;

    @ObjectHolder("occult:bleachedlog2")
    public static Block bleachedlog2;

    @ObjectHolder("occult:bleachedwood2")
    public static Block bleachedwood2;

    @ObjectHolder("occult:bleachedplanks2")
    public static Block bleachedplanks2;

    @ObjectHolder("occult:bleachedstairs2")
    public static Block bleachedstairs2;

    @ObjectHolder("occult:bleachedleaves2")
    public static Block bleachedleaves2;

    @ObjectHolder("occult:bleachedlog3")
    public static Block bleachedlog3;

    @ObjectHolder("occult:bleachedwood3")
    public static Block bleachedwood3;

    @ObjectHolder("occult:bleachedplanks3")
    public static Block bleachedplanks3;

    @ObjectHolder("occult:bleachedstairs3")
    public static Block bleachedstairs3;

    @ObjectHolder("occult:bleachedleaves3")
    public static Block bleachedleaves3;


    //Infernal Flowers
    @ObjectHolder("occult:infernalflower1")
    public static Block infernalflower1;

    @ObjectHolder("occult:infernalflower2")
    public static Block infernalflower2;

    @ObjectHolder("occult:infernalflower3")
    public static Block infernalflower3;

    @ObjectHolder("occult:infernalflower4")
    public static Block infernalflower4;

    @ObjectHolder("occult:infernalflower5")
    public static Block infernalflower5;

    @ObjectHolder("occult:infernalflower6")
    public static Block infernalflower6;

    //Deep Flowers
    @ObjectHolder("occult:deepflower1")
    public static Block deepflower1;

    @ObjectHolder("occult:deepflower2")
    public static Block deepflower2;

    @ObjectHolder("occult:deepflower3")
    public static Block deepflower3;

    @ObjectHolder("occult:deepflower4")
    public static Block deepflower4;

    @ObjectHolder("occult:deepflower5")
    public static Block deepflower5;

    @ObjectHolder("occult:deepflower6")
    public static Block deepflower6;

    //Bleached Flowers
    @ObjectHolder("occult:bleachedflower1")
    public static Block bleachedflower1;

    @ObjectHolder("occult:bleachedflower2")
    public static Block bleachedflower2;

    @ObjectHolder("occult:bleachedflower3")
    public static Block bleachedflower3;

    @ObjectHolder("occult:bleachedflower4")
    public static Block bleachedflower4;

    @ObjectHolder("occult:bleachedflower5")
    public static Block bleachedflower5;

    @ObjectHolder("occult:bleachedflower6")
    public static Block bleachedflower6;


    // Infesting Blocks

    @ObjectHolder("occult:rootedsoil")
    public static Block rootedsoil;

    @ObjectHolder("occult:stranglegrass")
    public static Block stranglegrass;

    @ObjectHolder("occult:slickmoss")
    public static Block slickmoss;

    @ObjectHolder("occult:infestedmushroom")
    public static Block infestedmushroom;

    @ObjectHolder("occult:phantombush")
    public static Block phantombush;

    @ObjectHolder("occult:falsesod")
    public static Block falsesod;

    @ObjectHolder("occult:echobush1")
    public static Block echobush1;

    @ObjectHolder("occult:echobush2")
    public static Block echobush2;

    //Magic Machines

    @ObjectHolder("occult:ritualfire")
    public static RitualFire ritualfire;

    @ObjectHolder("occult:ritualbase")
    public static RitualBase ritualbase;

    @ObjectHolder("occult:crucible")
    public static CrucibleBlock crucible;


    // ITEMS
    //-------------------------------------------------------------------------------------

    @ObjectHolder("occult:ivyleaf")
    public static Item ivyleaf;

    @ObjectHolder("occult:amalgam")
    public static Item amalgam;

    @ObjectHolder("occult:sacredsalt")
    public static Item sacredsalt;

    @ObjectHolder("occult:greenvitriol")
    public static Item greenvitriol;

    @ObjectHolder("occult:pyrite")
    public static Item pyrite;

    @ObjectHolder("occult:lionmetal")
    public static Item lionmetal;

    @ObjectHolder("occult:chalk")
    public static Item chalk;

    @ObjectHolder("occult:mercury")
    public static Item mercury;

    @ObjectHolder("occult:sulphur")
    public static Item sulphur;

    @ObjectHolder("occult:salt")
    public static Item salt;


    @ObjectHolder("occult:occult_icon")
    public static Item occult_icon;



    //TileEntities
    //-------------------------------------------------------------------------------------

    @ObjectHolder("occult:transformedstone")
    public static TileEntityType<TransformedStoneTile> TRANSFORMEDSTONETILEENTITY;

    @ObjectHolder("occult:crucible")
    public static TileEntityType<CrucibleTile> CRUCIBLETILE;

    @ObjectHolder("occult:eldritchstone")
    public static TileEntityType<EldritchStoneTile> ELDRITCHSTONETILEENTITY;

    @ObjectHolder("occult:ritualfire")
    public static TileEntityType<RitualFireTile> RITUALFIRETILE;

    @ObjectHolder("occult:ritualbase")
    public static TileEntityType<RitualBaseTile> RITUALBASETILE;




    //Containers
    //-------------------------------------------------------------------------------------

    @ObjectHolder("occult:ritualbase")
    public static ContainerType<RitualBaseContainer> RITUALBASECONTAINER;



    //Biomes
    //-------------------------------------------------------------------------------------

    @ObjectHolder("occult:spookyforest")
    public static Biome spookyforest;

    @ObjectHolder("occult:infernalforest")
    public static Biome infernalforest;

    @ObjectHolder("occult:bleachedforest")
    public static Biome bleachedforest;


    //Features
    //-------------------------------------------------------------------------------------
    @ObjectHolder("occult:stranglegrassfeature")
    public static BushFeature stranglegrassfeature;

    @ObjectHolder("occult:echobushfeature")
    public static BushFeature echobushfeature;

    @ObjectHolder("occult:phantombushfeature")
    public static BushFeature phantombushfeature;

    @ObjectHolder("occult:ivyfeature")
    public static BushFeature ivyfeature;

    @ObjectHolder("occult:eldritchstonefeature")
    public static Feature<NoFeatureConfig> eldritchstonefeature;

    //Surface Builders
    //-------------------------------------------------------------------------------------
    @ObjectHolder("occult:deepspookysurface")
    public static SurfaceBuilder<SurfaceBuilderConfig> DEEPSPOOKYSURFACE;


}
