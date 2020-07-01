package minerarcana.occult.content;

import minerarcana.occult.api.pressure.PressureType;
import net.minecraftforge.fml.RegistryObject;

import static minerarcana.occult.content.OccultRegistryHandler.*;

public class OccultPressure {

    public static RegistryObject<PressureType> SPIRITUAL;
    public static RegistryObject<PressureType> INFERNAL;
    public static RegistryObject<PressureType> NATURAL;

    public static void init() {
        SPIRITUAL = addPressure("spiritual", 12408320);
        INFERNAL = addPressure("infernal", 16384);
        NATURAL = addPressure("natural", 14543359);
    }

    public static RegistryObject<PressureType> addPressure(String name, int color) {
        PRESSURE_INT++;
        return PRESSURELIST[PRESSURE_INT - 1] = PRESSURE.register(name, () -> new PressureType(color));
    }

}
