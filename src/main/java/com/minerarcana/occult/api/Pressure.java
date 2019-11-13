package com.minerarcana.occult.api;

import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Objects;
import java.util.function.Function;

public class Pressure extends ForgeRegistryEntry<Pressure> {
    private final Function<Pressure, PressureType> pressureInstance;
    private final LazyLoadBase<ResourceLocation> id;
    private final LazyLoadBase<String> translationKey;
    private final LazyLoadBase<ITextComponent> name;

    public Pressure() {
        this(PressureType::new);
    }

    public Pressure(Function<Pressure, PressureType> createPressureType) {
        this.pressureInstance = createPressureType;
        this.id = new LazyLoadBase<>(() -> {
            ResourceLocation registryName = Objects.requireNonNull(this.getRegistryName());
            return new ResourceLocation(registryName.getNamespace(), "pressure/" + registryName.getPath());
        });
        this.translationKey = new LazyLoadBase<>(() -> {
            ResourceLocation registryName = Objects.requireNonNull(this.getRegistryName());
            return "pressure." + registryName.getNamespace() + "." + registryName.getPath();
        });
        this.name = new LazyLoadBase<>(() -> new TranslationTextComponent(this.translationKey.getValue()));
    }

    public PressureType createInstance() {
        return pressureInstance.apply(this);
    }

    public ITextComponent getName() {
        return this.name.getValue();
    }

    public String getTranslationKey() {
        return this.translationKey.getValue();
    }

    public ResourceLocation getID() {
        return id.getValue();
    }


}