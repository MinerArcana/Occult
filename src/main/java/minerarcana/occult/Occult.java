package minerarcana.occult;

import minerarcana.occult.content.OccultRegistryHandler;
import minerarcana.occult.util.BlockRenderHandler;
import minerarcana.occult.util.OccultGroup;
import minerarcana.occult.util.TileEntityRender;
import minerarcana.occult.util.init.CapabilitySetup;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static minerarcana.occult.Occult.MOD_ID;

@Mod(MOD_ID)
public class Occult
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "occult";
    public static final OccultGroup OG = new OccultGroup(ItemGroup.GROUPS.length, "occult");

    public Occult() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetupEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetupEvent);
        OccultRegistryHandler.init(bus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetupEvent(final FMLCommonSetupEvent event) {

        CapabilitySetup.init();
    }
    private void clientSetupEvent(final FMLClientSetupEvent event) {
        BlockRenderHandler.setRenderLayers();
        TileEntityRender.init();
    }

}
