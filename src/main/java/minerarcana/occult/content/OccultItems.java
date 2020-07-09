package minerarcana.occult.content;

import minerarcana.occult.items.PressureChecker;
import minerarcana.occult.items.PressureMinus;
import minerarcana.occult.items.PressurePlus;
import minerarcana.occult.items.equipment.HungryLionMetalArmor;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import static minerarcana.occult.Occult.OG;
import static minerarcana.occult.content.OccultRegistryHandler.*;

public class OccultItems {

    public static RegistryObject<Item> SATIATED_LIONMETAL_INGOT;
    public static RegistryObject<Item> HUNGRY_LIONMETAL_INGOT;
    public static RegistryObject<Item> AMALGAM_INGOT;

    public static RegistryObject<Item> HUNGRY_LIONMETAL_HELM;
    public static RegistryObject<Item> HUNGRY_LIONMETAL_LEGS;
    public static RegistryObject<Item> HUNGRY_LIONMETAL_BOOTS;
    public static RegistryObject<Item> HUNGRY_LIONMETAL_CHEST;

    public static RegistryObject<Item> SATIATED_LIONMETAL_HELM;
    public static RegistryObject<Item> SATIATED_LIONMETAL_CHEST;
    public static RegistryObject<Item> SATIATED_LIONMETAL_LEGS;
    public static RegistryObject<Item> SATIATED_LIONMETAL_BOOTS;


    public static void init(){
        SATIATED_LIONMETAL_INGOT = addItem("satiated_lionmetal_ingot");
        addItem("quicksilver");
        addItem("satiated_lionmetal_nugget");
        HUNGRY_LIONMETAL_INGOT = addItem("hungry_lionmetal_ingot");
        addItem("hungry_lionmetal_nugget");
        addItem("amalgam_nugget");
        AMALGAM_INGOT = addItem("amalgam_ingot");

        //Hungry Armor
        HUNGRY_LIONMETAL_HELM = addItem("hungry_lionmetal_helm",new HungryLionMetalArmor(EquipmentSlotType.HEAD));
        HUNGRY_LIONMETAL_CHEST = addItem("hungry_lionmetal_chest",new HungryLionMetalArmor(EquipmentSlotType.CHEST));
        HUNGRY_LIONMETAL_LEGS = addItem("hungry_lionmetal_legs",new HungryLionMetalArmor(EquipmentSlotType.LEGS));
        HUNGRY_LIONMETAL_BOOTS = addItem("hungry_lionmetal_boots",new HungryLionMetalArmor(EquipmentSlotType.FEET));

        //Satiated Armor
        SATIATED_LIONMETAL_HELM = addItem("satiated_lionmetal_helm",new HungryLionMetalArmor(EquipmentSlotType.HEAD));
        SATIATED_LIONMETAL_CHEST = addItem("satiated_lionmetal_chest",new HungryLionMetalArmor(EquipmentSlotType.CHEST));
        SATIATED_LIONMETAL_LEGS = addItem("satiated_lionmetal_legs",new HungryLionMetalArmor(EquipmentSlotType.LEGS));
        SATIATED_LIONMETAL_BOOTS = addItem("satiated_lionmetal_boots",new HungryLionMetalArmor(EquipmentSlotType.FEET));
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
