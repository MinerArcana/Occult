package minerarcana.occult.data;

import minerarcana.occult.data.lang.OccultUSLangProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import java.util.Collections;

import static minerarcana.occult.Occult.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OccultDataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        final DataGenerator dataGenerator = event.getGenerator();
        final ExistingFileHelper existingFileHelper = new ExistingFileHelper(Collections.emptyList(), false);

        if (event.includeClient()) {
            dataGenerator.addProvider(new OccultUSLangProvider(dataGenerator));
            dataGenerator.addProvider(new OccultBlockModelProvider(dataGenerator,existingFileHelper));
            dataGenerator.addProvider(new OccultBlockStateProvider(dataGenerator,existingFileHelper));
            dataGenerator.addProvider(new OccultItemModelProvider(dataGenerator, existingFileHelper));
        }

    }





}
