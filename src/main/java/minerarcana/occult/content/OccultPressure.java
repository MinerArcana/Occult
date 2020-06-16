package minerarcana.occult.content;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import static minerarcana.occult.Occult.OG;
import static minerarcana.occult.content.OccultRegistryHandler.*;

public class OccultItems {

    public static RegistryObject<Item> LIONMETAL_INGOT;

    public static void init(){
        LIONMETAL_INGOT = addItem("lionmetal_ingot");
        addItem("pressureChecker");
        addItem("pressureAdd");
        addItem("pressureRemove");
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
