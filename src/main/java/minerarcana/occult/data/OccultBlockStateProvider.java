package minerarcana.occult.data;

import com.hrznstudio.titanium.registry.BlockRegistryObjectGroup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;

import static minerarcana.occult.Occult.MOD_ID;
import static minerarcana.occult.content.OccultRegistryHandler.CUBEDBLOCKS;
import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class OccultBlockStateProvider extends BlockStateProvider {

    private final ExistingFileHelper helper;

    public OccultBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MOD_ID, exFileHelper);
        this.helper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        for(BlockRegistryObjectGroup block : CUBEDBLOCKS){
            if(block != null){
                this.simpleBlock(block.getBlock(), new ModelFile.ExistingModelFile(modLoc(BLOCK_FOLDER + "/" + block.getName()), helper));
            }
        }
    }
}
