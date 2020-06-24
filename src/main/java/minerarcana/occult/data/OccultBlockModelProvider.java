package minerarcana.occult.data;

import com.hrznstudio.titanium.registry.BlockRegistryObjectGroup;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelProvider;

import java.util.function.Function;

import static minerarcana.occult.Occult.MOD_ID;
import static minerarcana.occult.content.OccultRegistryHandler.CUBEDBLOCKS;

public class OccultBlockModelProvider extends ModelProvider<BlockModelBuilder> {

    public OccultBlockModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MOD_ID, BLOCK_FOLDER, BlockModelBuilder::new, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(BlockRegistryObjectGroup block : CUBEDBLOCKS) {
            if (block != null) {
                this.cubeAll(block.getName(), new ResourceLocation(MOD_ID, BLOCK_FOLDER + "s/"+ block.getName()));
            }
        }
    }

    @Override
    public String getName() {
        return null;
    }
}
