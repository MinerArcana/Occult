package minerarcana.occult.content;

import minerarcana.occult.items.equipment.lionmetal.hungry.HungryLionMetalArmor;
import minerarcana.occult.items.equipment.lionmetal.SatiatedLionmetalArmor;
import minerarcana.occult.items.equipment.lionmetal.hungry.HungryLionmetalAxe;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
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

    public static RegistryObject<Item> HUNGRY_LIONMETAL_AXE;
    public static RegistryObject<Item> HUNGRY_LIONMETAL_SHOVEL;
    public static RegistryObject<Item> HUNGRY_LIONMETAL_HOE;
    public static RegistryObject<Item> HUNGRY_LIONMETAL_SWORD;
    public static RegistryObject<Item> HUNGRY_LIONMETAL_SHIELD;


    public static RegistryObject<Item> SATIATED_LIONMETAL_HELM;
    public static RegistryObject<Item> SATIATED_LIONMETAL_CHEST;
    public static RegistryObject<Item> SATIATED_LIONMETAL_LEGS;
    public static RegistryObject<Item> SATIATED_LIONMETAL_BOOTS;

    public static RegistryObject<Item> SATIATED_LIONMETAL_AXE;
    public static RegistryObject<Item> SATIATED_LIONMETAL_SHOVEL;
    public static RegistryObject<Item> SATIATED_LIONMETAL_HOE;
    public static RegistryObject<Item> SATIATED_LIONMETAL_SWORD;
    public static RegistryObject<Item> SATIATED_LIONMETAL_SHIELD;


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

        //Hungry Tools
        HUNGRY_LIONMETAL_AXE = addItem("hungry_lionmetal_axe",new HungryLionmetalAxe(ItemTier.IRON, 3, -2.4F));
        HUNGRY_LIONMETAL_SHOVEL = addItem("hungry_lionmetal_shovel",new HungryLionmetalAxe(ItemTier.IRON, 3, -2.4F));
        HUNGRY_LIONMETAL_HOE = addItem("hungry_lionmetal_hoe",new HungryLionmetalAxe(ItemTier.IRON, 3, -2.4F));
        HUNGRY_LIONMETAL_SWORD = addItem("hungry_lionmetal_sword",new HungryLionmetalAxe(ItemTier.IRON, 3, -2.4F));
        HUNGRY_LIONMETAL_SHIELD = addItem("hungry_lionmetal_shield",new HungryLionmetalAxe(ItemTier.IRON, 3, -2.4F));

        //Satiated Armor
        SATIATED_LIONMETAL_HELM = addItem("satiated_lionmetal_helm",new SatiatedLionmetalArmor(EquipmentSlotType.HEAD));
        SATIATED_LIONMETAL_CHEST = addItem("satiated_lionmetal_chest",new SatiatedLionmetalArmor(EquipmentSlotType.CHEST));
        SATIATED_LIONMETAL_LEGS = addItem("satiated_lionmetal_legs",new SatiatedLionmetalArmor(EquipmentSlotType.LEGS));
        SATIATED_LIONMETAL_BOOTS = addItem("satiated_lionmetal_boots",new SatiatedLionmetalArmor(EquipmentSlotType.FEET));

        //Satiated Tools
        SATIATED_LIONMETAL_AXE = addItem("satiated_lionmetal_axe",new HungryLionmetalAxe(ItemTier.IRON, 3, -2.4F));
        SATIATED_LIONMETAL_SHOVEL = addItem("satiated_lionmetal_shovel",new HungryLionmetalAxe(ItemTier.IRON, 3, -2.4F));
        SATIATED_LIONMETAL_HOE = addItem("satiated_lionmetal_hoe",new HungryLionmetalAxe(ItemTier.IRON, 3, -2.4F));
        SATIATED_LIONMETAL_SWORD = addItem("satiated_lionmetal_sword",new HungryLionmetalAxe(ItemTier.IRON, 3, -2.4F));
        SATIATED_LIONMETAL_SHIELD = addItem("satiated_lionmetal_shield",new HungryLionmetalAxe(ItemTier.IRON, 3, -2.4F));


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
