package minerarcana.occult.content;

import com.hrznstudio.titanium.registry.BlockRegistryObjectGroup;
import minerarcana.occult.Occult;
import minerarcana.occult.blocks.BrimstoneBlock;
import minerarcana.occult.blocks.SulfurBlock;
import minerarcana.occult.blocks.crucible.CrucibleMainBlock;
import minerarcana.occult.blocks.crucible.CrucibleTopBlock;
import minerarcana.occult.blocks.metals.LionMetalBlock;
import minerarcana.occult.tileentities.CrucibleTile;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

import static minerarcana.occult.content.OccultRegistryHandler.*;

public class OccultBlocks {

    public static BlockRegistryObjectGroup<CrucibleMainBlock,BlockItem,CrucibleTile> CRUCIBLE;
    public static RegistryObject<Block> CRUCIBLETOP;

    public static void init(){
        CRUCIBLE = new BlockRegistryObjectGroup<>("crucible",CrucibleMainBlock::new, tileItemCreator(1),CrucibleTile::new).register(BLOCKS,ITEMS,TILE_ENTITIES);
        CRUCIBLETOP = BLOCKS.register("crucible_top", CrucibleTopBlock::new);
        addCubedBlock("akjar", new BrimstoneBlock());
        addCubedBlock("sated_lionmetal_block", new LionMetalBlock());
        addCubedBlock("hungry_lionmetal_block", new LionMetalBlock());
        addCubedBlock("amalgam_block", Block.Properties.create(Material.ANVIL));
        addCubedBlock("sulfur_block", new SulfurBlock());

    }

    public static BlockRegistryObjectGroup<?, ?, ?> addCubedBlock(String name, Block.Properties properties) {
       BLOCK_INT++;
        return CUBEDBLOCKS[BLOCK_INT - 1] = new BlockRegistryObjectGroup<>(name,
                () -> new Block(properties), blockItemCreator())
                .register(BLOCKS, ITEMS);
    }

    public static BlockRegistryObjectGroup<?, ?, ?> addCubedBlock(String name, Block block) {
        BLOCK_INT++;
        return CUBEDBLOCKS[BLOCK_INT - 1] = new BlockRegistryObjectGroup<>(name,
                () -> block, blockItemCreator())
                .register(BLOCKS, ITEMS);
    }

    private static <B extends Block> Function<B, BlockItem> blockItemCreator() {
        return block -> new BlockItem(block, new Item.Properties().group(Occult.OG));
    }

    public static BlockRegistryObjectGroup<?,?, ?> addTileEntity(String name, Supplier<Block> blockSupplier, Supplier<TileEntity> tileSupplier){
        TILE_INT = ++TILE_INT;
        return TILES[TILE_INT - 1] = new BlockRegistryObjectGroup<>(name,blockSupplier,tileItemCreator(1),tileSupplier).register(BLOCKS,ITEMS,TILE_ENTITIES);
    }

    private static <B extends Block> Function<B, BlockItem> tileItemCreator(int size) {
        return block -> new BlockItem(block, new Item.Properties().maxStackSize(size).group(Occult.OG));
    }


}
