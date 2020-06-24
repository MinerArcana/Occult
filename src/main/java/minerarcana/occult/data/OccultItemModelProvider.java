package minerarcana.occult.data;

import com.hrznstudio.titanium.registry.BlockRegistryObjectGroup;
import minerarcana.occult.data.model.PropertiedItemModelBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Function;

import static minerarcana.occult.Occult.MOD_ID;
import static minerarcana.occult.content.OccultFluids.AMALGAM;
import static minerarcana.occult.content.OccultFluids.QUICKSILVER;
import static minerarcana.occult.content.OccultRegistryHandler.CUBEDBLOCKS;
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

        for(BlockRegistryObjectGroup block: CUBEDBLOCKS){
            if(block != null){
                this.withExistingParent(block.getName(),modLoc(BLOCK_FOLDER + "/" + block.getName()));
            }
        }

        bucket(new ResourceLocation(MOD_ID,AMALGAM.getName()));
        bucket(new ResourceLocation(MOD_ID,QUICKSILVER.getName()));

    }

    @Override
    public String getName() {
        return "Occult Item Model Provider";
    }

    private void bucket(ResourceLocation fluidName) {
        getBuilder(fluidName.toString() + "_bucket")
                .parent(new ModelFile.UncheckedModelFile("forge:" + ITEM_FOLDER + "/bucket_drip"))
                .property("loader", "forge:bucket")
                .property("fluid", fluidName.toString());
    }
}
