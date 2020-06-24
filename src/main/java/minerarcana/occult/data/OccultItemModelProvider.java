package minerarcana.occult.data;

import minerarcana.occult.data.model.PropertiedItemModelBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Function;

import static minerarcana.occult.Occult.MOD_ID;
import static minerarcana.occult.content.OccultRegistryHandler.ITEMLIST;

public class OccultItemModelProvider extends ModelProvider<PropertiedItemModelBuilder> {

    public OccultItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MOD_ID, ITEM_FOLDER, PropertiedItemModelBuilder::new, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(RegistryObject<Item> item: ITEMLIST){
            if(item != null){
                this.singleTexture(item.getId().getPath(), mcLoc("item/generated"), new ResourceLocation(MOD_ID, "items/" + item.getId().getPath()));
            }
        }
    }

    @Override
    public String getName() {
        return "Occult Item Model Provider";
    }
}
