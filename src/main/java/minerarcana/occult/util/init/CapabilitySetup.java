package minerarcana.occult.util.init;

import minerarcana.occult.api.chunkpressure.IChunkPressure;
import minerarcana.occult.api.worldpressure.IWorldPressure;
import minerarcana.occult.api.worldpressure.WorldPressureStorage;
import minerarcana.occult.util.EmptyNBTStorage;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilitySetup {

    public static void init() {
        //Pressure Cap
        CapabilityManager.INSTANCE.register(IChunkPressure.class, new EmptyNBTStorage<>(), () -> null);
        CapabilityManager.INSTANCE.register(IWorldPressure.class, new Capability.IStorage<IWorldPressure>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IWorldPressure> capability, IWorldPressure instance, Direction side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<IWorldPressure> capability, IWorldPressure instance, Direction side, INBT nbt) {
                instance.deserializeNBT((CompoundNBT) nbt);
            }
        }, WorldPressureStorage::new);

    }

}
