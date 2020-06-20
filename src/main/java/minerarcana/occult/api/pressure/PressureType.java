package minerarcana.occult.api.pressure;

import net.minecraftforge.registries.ForgeRegistryEntry;

public class PressureType extends ForgeRegistryEntry<PressureType> {

    private final int color;
    public PressureType(int color){
        this.color = color;
    }

    public int getColor() {
        return color;
    }

}
