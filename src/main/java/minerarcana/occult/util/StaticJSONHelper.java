package minerarcana.occult.util;

import com.google.gson.*;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import minerarcana.occult.api.pressure.PressureType;
import minerarcana.occult.content.OccultRegistries;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static minerarcana.occult.content.OccultPressure.SPIRITUAL;

public class StaticJSONHelper {

    public static NonNullList<ItemStack> readItemList(JsonArray array) {
        NonNullList<ItemStack> nonnulllist = NonNullList.create();

        for (int i = 0; i < array.size(); ++i) {
            ItemStack stack = stackElementDeserialize(array.get(i));
            nonnulllist.add(stack);
        }

        return nonnulllist;
    }

    private static ItemStack stackElementDeserialize(@Nullable JsonElement json) {
        if (json != null && !json.isJsonNull()) {
            if (json.isJsonObject()) {
                JsonObject jsonObject = json.getAsJsonObject();
                return deserializeItem(jsonObject);
            } else {
                throw new JsonSyntaxException("Expected 1 item");
            }
        } else {
            throw new JsonSyntaxException("input cannot be null");
        }
    }

    private static ItemStack deserializeItem(JsonObject json) {
        if (json.has("item")) {
            ResourceLocation resourcelocation = new ResourceLocation(JSONUtils.getString(json, "item"));
            ItemStack stack = Registry.ITEM.getValue(resourcelocation).orElseThrow(() -> {
                return new JsonSyntaxException("Unknown item '" + resourcelocation + "'");
            }).getDefaultInstance();
            stack.setCount(Math.max(1, JSONUtils.getInt(json, "count")));
            return stack;
        } else {
            throw new JsonParseException("A Recipe entry needs an input");
        }
    }

    public static Object2IntMap<PressureType> readPressureList(JsonArray array) {
        Object2IntMap<PressureType> pressureMap = new Object2IntOpenHashMap<>();

        for (int i = 0; i < array.size(); ++i) {
            PressureType type = pressureElementDeserialize(array.get(i));
            int amount = amountElementDeserialize(array.get(i));
            pressureMap.put(type, amount);
        }

        return pressureMap;
    }

    private static PressureType pressureElementDeserialize(@Nullable JsonElement json) {
        if (json != null && !json.isJsonNull()) {
            if (json.isJsonObject()) {
                JsonObject jsonObject = json.getAsJsonObject();
                return deserializePressure(jsonObject);
            }
        }
        return SPIRITUAL.get();
    }

    private static PressureType deserializePressure(JsonObject json) {
        if (json.has("pressureType")) {
            ResourceLocation resourcelocation = new ResourceLocation(JSONUtils.getString(json, "pressureType"));
            return OccultRegistries.PRESSURE.getValue(resourcelocation);
        }
        return SPIRITUAL.get();
    }

    private static int amountElementDeserialize(@Nullable JsonElement json) {
        if (json != null && !json.isJsonNull()) {
            if (json.isJsonObject()) {
                JsonObject jsonObject = json.getAsJsonObject();
                return deserializePressureAmount(jsonObject);
            }
        }
        return 0;
    }

    private static int deserializePressureAmount(JsonObject json) {
        if (json.has("amount")) {
            return JSONUtils.getInt(json, "amount");
        }
        return 0;
    }

    public static FluidStack deserializeFluid(JsonObject object, String prefix) {
        String s = JSONUtils.getString(object, "fluid" + prefix);
        Fluid fluid = Registry.FLUID.getValue(new ResourceLocation(s)).orElseThrow(() -> {
            return new JsonSyntaxException("Unknown fluid '" + s + "'");
        });
        int i = JSONUtils.getInt(object, "amount" + prefix, 100);
        return new FluidStack(fluid, i);
    }

}
