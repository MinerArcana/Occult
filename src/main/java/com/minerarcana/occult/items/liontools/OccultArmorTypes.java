package com.minerarcana.occult.items.liontools;


import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import static com.minerarcana.occult.Occult.MOD_ID;
import static com.minerarcana.occult.content.OccultItems.HUNGRY_LIONMETAL_INGOT;
import static com.minerarcana.occult.content.OccultItems.SATED_LIONMETAL_INGOT;

public enum OccultArmorTypes implements IArmorMaterial {

    satedlionarmortype("sated_lion_armor", new int[] {2, 2, 2, 2}, 50, SATED_LIONMETAL_INGOT.get(), "entity.ender_dragon.growl", 0.0f),
    hungrylionarmortype("hungry_lion_armor", new int[] {2, 2, 2, 2}, 25, HUNGRY_LIONMETAL_INGOT.get(), "entity.ender_dragon.growl", 0.0f);

    private String name, equipSound;
    private int enchantability;
    private Item repairItem;
    private int[] damageReductionAmounts;
    private float toughness;

    private OccultArmorTypes(String name, int[] damageReductionAmounts, int enchantability, Item repairItem, String equipSound, float toughness)
    {
        this.name = name;
        this.equipSound = equipSound;
        this.enchantability = enchantability;
        this.repairItem = repairItem;
        this.damageReductionAmounts = damageReductionAmounts;
        this.toughness = toughness;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        int durability = 0;
        if (slotIn.equals(EquipmentSlotType.HEAD)) {
            durability = 200;
        } else if (slotIn.equals(EquipmentSlotType.CHEST)) {
            durability = 200;
        } else if (slotIn.equals(EquipmentSlotType.LEGS)) {
            durability = 200;
        } else if (slotIn.equals(EquipmentSlotType.FEET)) {
            durability = 200;
        }
        return durability;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmounts[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return new SoundEvent(new ResourceLocation(equipSound));
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(this.repairItem);
    }

    @Override
    public String getName() {
        return MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return 0;
    }
}
