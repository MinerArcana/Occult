package minerarcana.occult.data;

import minerarcana.occult.data.model.PropertiedItemModelBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelProvider;

import java.util.function.Function;

import static minerarcana.occult.Occult.MOD_ID;

public class OccultItemModelProvider extends ModelProvider<PropertiedItemModelBuilder> {

    public OccultItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MOD_ID, ITEM_FOLDER, PropertiedItemModelBuilder::new, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }

    @Override
    public String getName() {
        return "Occult Item Model Provider";
    }
}
