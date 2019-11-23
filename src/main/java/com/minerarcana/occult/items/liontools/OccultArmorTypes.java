package com.minerarcana.occult.items.liontools;


import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

import static com.minerarcana.occult.content.OccultItems.HUNGRY_LIONMETAL_INGOT;
import static com.minerarcana.occult.content.OccultItems.SATED_LIONMETAL_INGOT;

public enum OccultArmorTypes implements IItemTier {

    sated_lion_tools(10.0f, 9.0f, 800, 3, 25, SATED_LIONMETAL_INGOT.get()),
    hungry_lion_tools(10.0f, 9.0f, 800, 3, 25, HUNGRY_LIONMETAL_INGOT.get());

    private float attackDamage, efficiency;
    private int durability, harvestLevel, enchantability;
    private Item repairMaterial;

    private OccultArmorTypes(float attackDamage, float efficiency, int durability, int harvestLevel, int enchantability, Item repairMaterial)
    {
        this.attackDamage = attackDamage;
        this.efficiency = efficiency;
        this.durability = durability;
        this.harvestLevel = harvestLevel;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public float getAttackDamage()
    {
        return this.attackDamage;
    }

    @Override
    public float getEfficiency()
    {
        return this.efficiency;
    }

    @Override
    public int getEnchantability()
    {
        return this.enchantability;
    }

    @Override
    public int getHarvestLevel()
    {
        return this.harvestLevel;
    }

    @Override
    public int getMaxUses()
    {
        return this.durability;
    }

    @Override
    public Ingredient getRepairMaterial()
    {
        return Ingredient.fromItems(this.repairMaterial);
    }
}
