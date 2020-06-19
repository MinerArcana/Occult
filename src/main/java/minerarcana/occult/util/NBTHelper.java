package minerarcana.occult.util;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;

public class NBTHelper {

    public static <T, U extends INBT> Collection<T> fromListNBT(ListNBT listNBT, BiFunction<ListNBT, Integer, U> nbt,
                                                                Function<U, T> fromNBT) {
        Collection<T> items = Lists.newArrayList();
        for (int i = 0; i < listNBT.size(); i++) {
            T value = fromNBT.apply(nbt.apply(listNBT, i));
            if (value != null) {
                items.add(value);
            }
        }
        return items;
    }

    public static <T> Collection<T> fromListNBT(CompoundNBT compoundNBT, String name, Function<CompoundNBT, T> fromNBT) {
        return fromListNBT(compoundNBT.getList(name, Constants.NBT.TAG_COMPOUND), ListNBT::getCompound, fromNBT);
    }
}