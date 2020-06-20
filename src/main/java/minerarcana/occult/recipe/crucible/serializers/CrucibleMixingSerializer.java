package minerarcana.occult.recipe.crucible.serializers;

import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import minerarcana.occult.api.pressure.PressureType;
import minerarcana.occult.content.OccultRegistries;
import minerarcana.occult.recipe.crucible.recipe.CrucibleMixingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

import static minerarcana.occult.util.StaticJSONHelper.*;

public class CrucibleMixingSerializer  extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CrucibleMixingRecipe> {

    @Override
    public CrucibleMixingRecipe read(ResourceLocation recipeId, JsonObject json) {
        Object2IntMap<PressureType> pressureMap = readPressureList(JSONUtils.getJsonArray(json, "pressureReleased"));
        NonNullList<ItemStack> itemsIn = readItemList(JSONUtils.getJsonArray(json, "itemInputs"));
        FluidStack fluidOut = deserializeFluid(json, "Out");
        FluidStack fluidIn = deserializeFluid(json, "In");
        int experience = JSONUtils.getInt(json,"experience",0);
        int maxTemp = JSONUtils.getInt(json,"maxTemp",1500);
        int minTemp = JSONUtils.getInt(json,"minTemp",250);
        int cookTime = JSONUtils.getInt(json,"cookTime",100);
        return new CrucibleMixingRecipe(recipeId,itemsIn,fluidIn,fluidOut,maxTemp,minTemp,cookTime,pressureMap,experience);
    }

    @Nullable
    @Override
    public CrucibleMixingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {

        int inSize = buffer.readInt();
        NonNullList<ItemStack> itemsIn = NonNullList.create();
        ;
        for (int x = 0; x < inSize; ++x) {
            itemsIn.add(buffer.readItemStack());
        }

        FluidStack fluidIn = buffer.readFluidStack();

        FluidStack fluidOut = buffer.readFluidStack();

        int maxTemp = buffer.readInt();
        int minTemp = buffer.readInt();
        int cookTime = buffer.readInt();
        float experience = buffer.readFloat();

        Object2IntMap<PressureType> pressureMap = new Object2IntOpenHashMap<>();
        int pressureSize = buffer.readInt();
        for (int x = 0; x < pressureSize; ++x) {
            PressureType type = OccultRegistries.PRESSURE.getValue(new ResourceLocation(buffer.readString()));
            pressureMap.put(type, buffer.readInt());
        }
        return new CrucibleMixingRecipe(recipeId,itemsIn,fluidIn,fluidOut,maxTemp,minTemp,cookTime,pressureMap,experience);
    }

    @Override
    public void write(PacketBuffer buffer, CrucibleMixingRecipe recipe) {
        buffer.writeInt(recipe.getItemsIn().size());
        for (ItemStack stack : recipe.getItemsIn()) {
            buffer.writeItemStack(stack);
        }

        buffer.writeFluidStack(recipe.getFluidIn());

        buffer.writeFluidStack(recipe.getFluidOut());

        buffer.writeInt(recipe.getMaxTemp());
        buffer.writeInt(recipe.getMinTemp());
        buffer.writeInt(recipe.getCookTime());
        buffer.writeFloat(recipe.getExperience());

        buffer.writeInt(recipe.getPressureCreated().size());
        for (PressureType type : recipe.getPressureCreated().keySet()) {
            buffer.writeString(type.getRegistryName().toString());
            buffer.writeInt(recipe.getPressureCreated().get(type));
        }
    }

}
