package minerarcana.occult.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static minerarcana.occult.Occult.MOD_ID;

public class OccultBlockStateProvider extends BlockStateProvider {

    public OccultBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
