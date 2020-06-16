package minerarcana.occult.content;

import minerarcana.occult.api.PressureType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static minerarcana.occult.Occult.MOD_ID;

public class OccultRegistryHandler {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<PressureType> PRESSURE = new DeferredRegister<>(OccultRegistries.PRESSURE, MOD_ID);

    public static int ITEMLIST_INT = 0;
    public static int PRESSURE_INT = 0;
    public static final RegistryObject<Item>[] ITEMLIST = new RegistryObject[1000];
    public static final RegistryObject<PressureType>[] PRESSURELIST = new RegistryObject[100];




    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        PRESSURE.register(eventBus);
    }

    public static void init(IEventBus eventBus) {
        OccultItems.init();
        OccultPressure.init();

        //Must be last
        register(eventBus);
    }

}
