package minerarcana.occult.content;

import minerarcana.occult.items.PressureChecker;
import minerarcana.occult.items.PressureMinus;
import minerarcana.occult.items.PressurePlus;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import static minerarcana.occult.Occult.OG;
import static minerarcana.occult.content.OccultRegistryHandler.*;

public class OccultItems {

    public static RegistryObject<Item> LIONMETAL_INGOT;

    public static void init(){
        LIONMETAL_INGOT = addItem("lionmetal_ingot");
        //addItem("pressure_checker", new PressureChecker());
        //addItem("pressure_add", new PressurePlus());
        //addItem("pressure_remove", new PressureMinus());
        addItem("quicksilver");
        addItem("sated_lionmetal_ingot");
        addItem("sated_lionmetal_nugget");
        addItem("hungry_lionmetal_ingot");
        addItem("hungry_lionmeta_nugget");
        addItem("amalgam_nugget");
        addItem("amalgam_ingot");

    }

    public static RegistryObject<Item> addItem(String name){
        ITEMLIST_INT++;
        return ITEMLIST[ITEMLIST_INT - 1] = ITEMS.register(name, ()-> new Item(new Item.Properties().group(OG)));
    }

    public static RegistryObject<Item> addItem(String name, Item item){
        ITEMLIST_INT++;
        return ITEMLIST[ITEMLIST_INT - 1] = ITEMS.register(name, ()-> item);
    }

}
