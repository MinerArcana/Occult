package minerarcana.occult.api.worldpressure;

import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;

public class WorldPressureCap {

    @CapabilityInject(IWorldPressure.class)
    public static Capability<IWorldPressure> WORLD_PRESSURE_CAPABILITY;

    public static final Direction DEFAULT_FACING = null;

    public static LazyOptional<IWorldPressure> getWorldPressure(final World world) {
        return world.getCapability(WORLD_PRESSURE_CAPABILITY, DEFAULT_FACING);
    }

}