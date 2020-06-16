package minerarcana.occult.api;

import minerarcana.occult.content.OccultRegistries;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Map;
import java.util.Objects;

public class PressureType extends ForgeRegistryEntry<PressureType> {

    private final int color;
    public PressureType(int color){
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public PressureType getTypeFromName(String name) {
        PressureType pressureType = null;

        for(Map.Entry<ResourceLocation, PressureType> type: OccultRegistries.PRESSURE.getEntries()) {
            if (Objects.requireNonNull(type.getValue().getRegistryName()).toString().equals(name)) {
                pressureType = type.getValue();
                break;
            }
        }
        return pressureType;
    }
}
