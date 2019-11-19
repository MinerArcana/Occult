package com.minerarcana.occult.content;

import com.minerarcana.occult.util.itemgroup.OccultGroup;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static com.minerarcana.occult.Occult.MOD_ID;
import static net.minecraft.block.material.Material.*;


public class Blocks {

    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MOD_ID);

    //World Generated Blocks
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Block> AKJAR = BLOCKS.register("akjar", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(2.0F, 2.0F)
    ));
    public static final RegistryObject<Block> CINNABARORE = BLOCKS.register("cinnabarore", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(3)
            .sound(SoundType.STONE)
            .hardnessAndResistance(5.0F, 6.0F)
    ));
    public static final RegistryObject<Block> SALTORE = BLOCKS.register("saltore", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(2.0F, 2.0F)
    ));

    //MachineBlocks
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Block> CRUCIBLE = BLOCKS.register("crucible", () -> new Block(Block.Properties.create(Material.IRON)
            .harvestLevel(2)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 6.0F)
    ));

    //Crafted Blocks
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Block> SALTBLOCK = BLOCKS.register("saltblock", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(1.0F, 1.0F)
    ));
    public static final RegistryObject<Block> CHALKBLOCK = BLOCKS.register("chalkblock", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(1.0F, 1.0F)
    ));
    public static final RegistryObject<Block> SACREDSALTBLOCK = BLOCKS.register("sacredsaltblock", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(1.0F, 1.0F)
    ));
    public static final RegistryObject<Block> SULPHURBLOCK = BLOCKS.register("sulphurblock", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(1.0F, 1.0F)
    ));

    // Metal Blocks
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Block> AMALGAMBLOCK = BLOCKS.register("amalgamblock", () -> new Block(Block.Properties.create(Material.IRON)
            .harvestLevel(2)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 6.0F)
    ));
    public static final RegistryObject<Block> LIONMETALBLOCK = BLOCKS.register("lionmetalblock", () -> new Block(Block.Properties.create(Material.IRON)
            .harvestLevel(2)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 6.0F)
    ));


    //-------------------------------------------------------------------------------------------------------------------------------

    //ItemBlocks

    //-------------------------------------------------------------------------------------------------------------------------------

    //World Generated Blocks
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> AKJAR_ITEM = ITEMS.register("akjar",
            () -> new BlockItem(Objects.requireNonNull(CRUCIBLE.get()), new Item.Properties()
                    .group(OccultGroup.instance))
    );
    public static final RegistryObject<Item> CINNABARORE_ITEM = ITEMS.register("cinnabarore",
            () -> new BlockItem(Objects.requireNonNull(CRUCIBLE.get()), new Item.Properties()
                    .group(OccultGroup.instance))
    );
    public static final RegistryObject<Item> SALTORE_ITEM = ITEMS.register("saltore",
            () -> new BlockItem(Objects.requireNonNull(CRUCIBLE.get()), new Item.Properties()
                    .group(OccultGroup.instance))
    );

    //MachineBlocks
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> CRUCIBLE_ITEM = ITEMS.register("crucible",
            () -> new BlockItem(Objects.requireNonNull(CRUCIBLE.get()), new Item.Properties()
                    .group(OccultGroup.instance))
    );

    //Crafted
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> SALTBLOCK_ITEM = ITEMS.register("saltblock",
            () -> new BlockItem(Objects.requireNonNull(SALTBLOCK.get()), new Item.Properties()
                    .group(OccultGroup.instance))
    );
    public static final RegistryObject<Item> CHALKBLOCK_ITEM = ITEMS.register("chalkblock",
            () -> new BlockItem(Objects.requireNonNull(CHALKBLOCK.get()), new Item.Properties()
                    .group(OccultGroup.instance))
    );
    public static final RegistryObject<Item> SACREDSALTBLOCK_ITEM = ITEMS.register("sacredsaltblock",
            () -> new BlockItem(Objects.requireNonNull(SACREDSALTBLOCK.get()), new Item.Properties()
                    .group(OccultGroup.instance))
    );
    public static final RegistryObject<Item> SULPHURBLOCK_ITEM = ITEMS.register("sulphurblock",
            () -> new BlockItem(Objects.requireNonNull(SULPHURBLOCK.get()), new Item.Properties()
                    .group(OccultGroup.instance))
    );

    //Metals
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> AMALGAMBLOCK_ITEM = ITEMS.register("amalgamblock",
            () -> new BlockItem(Objects.requireNonNull(AMALGAMBLOCK.get()), new Item.Properties()
                    .group(OccultGroup.instance))
    );
    public static final RegistryObject<Item> LIONMETALBLOCK_ITEM = ITEMS.register("lionmetalblock",
            () -> new BlockItem(Objects.requireNonNull(LIONMETALBLOCK.get()), new Item.Properties()
                    .group(OccultGroup.instance))
    );

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
}
