package com.minerarcana.occult.content;

import com.minerarcana.occult.blocks.*;
import com.minerarcana.occult.items.BurnableBlockItem;
import com.minerarcana.occult.blocks.lionmetal.HungyLionMetalBlock;
import com.minerarcana.occult.blocks.lionmetal.SatedLionMetalBlock;
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
import static com.minerarcana.occult.OccultGroup.OCCULT_GROUP;
import static net.minecraft.block.material.Material.*;


public class OccultBlocks {

    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MOD_ID);

    //World Generated Blocks
    //-----------------------------------------------------------------------------------------------------
    //Akjar Variants
    public static final RegistryObject<Block> AKJAR = BLOCKS.register("akjar", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(2.0F, 2.0F)
    ));
    public static final RegistryObject<Block> AKJAR_BRICKS = BLOCKS.register("akjar_bricks", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(2.0F, 2.0F)
    ));
    public static final RegistryObject<Block> BEATING_AKJAR = BLOCKS.register("beating_akjar", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(2.0F, 2.0F)
    ));
    public static final RegistryObject<Block> BEATING_AKJAR_BRICKS = BLOCKS.register("beating_akjar_bricks", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(2.0F, 2.0F)
    ));

    //Ores
    public static final RegistryObject<Block> CINNABAR_ORE = BLOCKS.register("cinnabar_ore", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(3)
            .sound(SoundType.STONE)
            .hardnessAndResistance(5.0F, 6.0F)
    ));
    public static final RegistryObject<Block> SALT_ORE = BLOCKS.register("salt_ore", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(2.0F, 2.0F)
    ));

    //MachineBlocks
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Block> CRUCIBLE = BLOCKS.register("crucible", () -> new CrucibleBlock(Block.Properties.create(Material.IRON)
            .harvestLevel(2)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 6.0F)
    ));

    //Crafted Blocks
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Block> SALT_BLOCK = BLOCKS.register("salt_block", () -> new SaltBlock(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(1.0F, 1.0F)
    ));
    public static final RegistryObject<Block> CHALK_BLOCK = BLOCKS.register("chalk_block", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(1.0F, 1.0F)
    ));
    public static final RegistryObject<Block> SACRED_SALT_BLOCK = BLOCKS.register("sacred_salt_block", () -> new Block(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(1.0F, 1.0F)
    ));
    public static final RegistryObject<Block> SULPHUR_BLOCK = BLOCKS.register("sulphur_block", () -> new SulphurBlock(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(1.0F, 1.0F)
    ));
    public static final RegistryObject<Block> SULPHUR_PILE = BLOCKS.register("sulphur_pile", () -> new SulphurPile(Block.Properties.create(ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(1.0F, 1.0F)
    ));

    // Metal Blocks
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Block> AMALGAM_BLOCK = BLOCKS.register("amalgam_block", () -> new Block(Block.Properties.create(Material.IRON)
            .harvestLevel(2)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 6.0F)
    ));
    public static final RegistryObject<Block> SATED_LIONMETAL_BLOCK = BLOCKS.register("sated_lionmetal_block", () -> new SatedLionMetalBlock(Block.Properties.create(Material.IRON)
            .harvestLevel(2)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 6.0F).lightValue(25)
    ));
    public static final RegistryObject<Block> HUNGRY_LIONMETAL_BLOCK = BLOCKS.register("hungry_lionmetal_block", () -> new HungyLionMetalBlock(Block.Properties.create(Material.IRON)
            .harvestLevel(2)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 6.0F)
    ));

    // Bar Blocks
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Block> HUNGRY_LIONMETAL_BARS = BLOCKS.register("hungry_lionmetal_bars", () -> new OccultBars(Block.Properties.create(Material.IRON)
            .harvestLevel(2)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 6.0F)
    ));
    public static final RegistryObject<Block> SATED_LIONMETAL_BARS = BLOCKS.register("sated_lionmetal_bars", () -> new OccultBars(Block.Properties.create(Material.IRON)
            .harvestLevel(2)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 6.0F).lightValue(12)
    ));


    //-------------------------------------------------------------------------------------------------------------------------------

    //ItemBlocks

    //-------------------------------------------------------------------------------------------------------------------------------

    //World Generated Blocks
    //-----------------------------------------------------------------------------------------------------
    //Akjar Variants
    public static final RegistryObject<Item> AKJAR_ITEM = ITEMS.register("akjar",
            () -> new BlockItem(Objects.requireNonNull(AKJAR.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );
    public static final RegistryObject<Item> AKJAR_BRICKS_ITEM = ITEMS.register("akjar_bricks",
            () -> new BlockItem(Objects.requireNonNull(AKJAR_BRICKS.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );
    public static final RegistryObject<Item> BEATING_AKJAR_ITEM = ITEMS.register("beating_akjar",
            () -> new BlockItem(Objects.requireNonNull(BEATING_AKJAR.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );
    public static final RegistryObject<Item> BEATING_AKJAR_BRICKS_ITEM = ITEMS.register("beating_akjar_bricks",
            () -> new BlockItem(Objects.requireNonNull(BEATING_AKJAR_BRICKS.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );

    //Ores
    public static final RegistryObject<Item> CINNABAR_ORE_ITEM = ITEMS.register("cinnabar_ore",
            () -> new BlockItem(Objects.requireNonNull(CINNABAR_ORE.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );
    public static final RegistryObject<Item> SALT_ORE_ITEM = ITEMS.register("salt_ore",
            () -> new BlockItem(Objects.requireNonNull(SALT_ORE.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );

    //MachineBlocks
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> CRUCIBLE_ITEM = ITEMS.register("crucible",
            () -> new BlockItem(Objects.requireNonNull(CRUCIBLE.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );

    //Crafted
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> SALT_BLOCK_ITEM = ITEMS.register("salt_block",
            () -> new BlockItem(Objects.requireNonNull(SALT_BLOCK.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );
    public static final RegistryObject<Item> CHALK_BLOCK_ITEM = ITEMS.register("chalk_block",
            () -> new BlockItem(Objects.requireNonNull(CHALK_BLOCK.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );
    public static final RegistryObject<Item> SACRED_SALT_BLOCK_ITEM = ITEMS.register("sacred_salt_block",
            () -> new BlockItem(Objects.requireNonNull(SACRED_SALT_BLOCK.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );
    public static final RegistryObject<Item> SULPHUR_BLOCK_ITEM = ITEMS.register("sulphur_block",
            () -> new BurnableBlockItem(Objects.requireNonNull(SULPHUR_BLOCK.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );

    //Metals
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> AMALGAM_BLOCK_ITEM = ITEMS.register("amalgam_block",
            () -> new BlockItem(Objects.requireNonNull(AMALGAM_BLOCK.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );
    public static final RegistryObject<Item> SATED_LIONMETAL_BLOCK_ITEM = ITEMS.register("sated_lionmetal_block",
            () -> new BurnableBlockItem(Objects.requireNonNull(SATED_LIONMETAL_BLOCK.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );
    public static final RegistryObject<Item> HUNGRY_LIONMETAL_BLOCK_ITEM = ITEMS.register("hungry_lionmetal_block",
            () -> new BlockItem(Objects.requireNonNull(HUNGRY_LIONMETAL_BLOCK.get()), new Item.Properties()
                    .group(OCCULT_GROUP))
    );

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
}
