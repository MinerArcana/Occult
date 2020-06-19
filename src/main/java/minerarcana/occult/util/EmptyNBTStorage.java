package minerarcana.occult.util;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class EmptyNBTStorage<T> implements Capability.IStorage<T> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<T> capability, T instance, Direction side) {
        return null;
    }

    @Override
    public void readNBT(Capability<T> capability, T instance, Direction side, INBT nbt) {

    }
}