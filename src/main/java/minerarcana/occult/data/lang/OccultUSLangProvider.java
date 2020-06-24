package minerarcana.occult.data.lang;

import com.hrznstudio.titanium.registry.BlockRegistryObjectGroup;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;
import org.apache.commons.lang3.text.WordUtils;
import org.codehaus.plexus.util.StringUtils;

import static minerarcana.occult.Occult.MOD_ID;
import static minerarcana.occult.content.OccultBlocks.CRUCIBLE;
import static minerarcana.occult.content.OccultRegistryHandler.CUBEDBLOCKS;
import static minerarcana.occult.content.OccultRegistryHandler.ITEMLIST;

public class OccultUSLangProvider extends LanguageProvider {

    public OccultUSLangProvider(DataGenerator gen) {
        super(gen, MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        //Add Translation for Cubed Blocks
        for(BlockRegistryObjectGroup block: CUBEDBLOCKS){
            if(block != null){
                this.addItem(block::getItem, WordUtils.capitalizeFully(StringUtils.capitalise(block.getName().replace("_", " ").replace("1", "").replace("2", "").replace("3", ""))));
            }
        }
        //Add Translation for Items
        for(RegistryObject<Item> item: ITEMLIST){
            if(item != null){
                this.addItem(item, WordUtils.capitalizeFully(item.getId().getPath().replace("_", " ")));
            }
        }

        this.addItem(CRUCIBLE::getItem, WordUtils.capitalizeFully(CRUCIBLE.getName()));


    }
}
