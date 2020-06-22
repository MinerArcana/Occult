package minerarcana.occult.content;

import com.hrznstudio.titanium.registry.BlockRegistryObjectGroup;
import minerarcana.occult.Occult;
import minerarcana.occult.api.pressure.PressureType;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static minerarcana.occult.Occult.MOD_ID;

public class OccultRegistryHandler {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<PressureType> PRESSURE = DeferredRegister.create(OccultRegistries.PRESSURE, MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MOD_ID);
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID);

    public static int ITEMLIST_INT = 0;
    public static int BLOCK_INT = 0;
    public static int PRESSURE_INT = 0;
    public static int TILE_INT = 0;

    public static final RegistryObject<Item>[] ITEMLIST = new RegistryObject[200];
    public static final BlockRegistryObjectGroup<Block, BlockItem, ?>[] BLOCKLIST = new BlockRegistryObjectGroup[100];
    public static final RegistryObject<PressureType>[] PRESSURELIST = new RegistryObject[100];
    public static final BlockRegistryObjectGroup<Block, BlockItem, TileEntity>[] TILES = new BlockRegistryObjectGroup[100];


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        TILE_ENTITIES.register(eventBus);
        FLUIDS.register(eventBus);
        RECIPE_SERIALIZER.register(eventBus);
        PRESSURE.register(eventBus);
    }

    public static void init(IEventBus eventBus) {
        OccultBlocks.init();
        OccultItems.init();
        OccultFluids.init();
        OccultPressure.init();
        OccultRecipeSerializers.init();
        //Must be last
        register(eventBus);
    }

}
