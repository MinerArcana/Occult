package minerarcana.occult.recipe.crucible.serializers;

import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import minerarcana.occult.api.pressure.PressureType;
import minerarcana.occult.content.OccultRegistries;
import minerarcana.occult.recipe.crucible.recipe.CrucibleCoolingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

import static minerarcana.occult.util.StaticJSONHelper.deserializeFluid;
import static minerarcana.occult.util.StaticJSONHelper.readPressureList;

public class CrucibleCoolingSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CrucibleCoolingRecipe> {

    @Override
    public CrucibleCoolingRecipe read(ResourceLocation recipeId, JsonObject json) {
        Object2IntMap<PressureType> pressureMap = readPressureList(JSONUtils.getJsonArray(json, "pressureReleased"));
        FluidStack fluidIn = deserializeFluid(json,"In");
        ItemStack itemOut = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json,"result"));
        int experience = JSONUtils.getInt(json,"experience",0);
        int maxTemp = JSONUtils.getInt(json,"maxTemp",1500);
        int minTemp = JSONUtils.getInt(json,"minTemp",250);
        int cookTime = JSONUtils.getInt(json,"coolTime",100);
        return new CrucibleCoolingRecipe(recipeId,fluidIn,itemOut,maxTemp,minTemp,cookTime,pressureMap,experience);
    }

    @Nullable
    @Override
    public CrucibleCoolingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {

        FluidStack fluidIn = buffer.readFluidStack();

        ItemStack itemOut = buffer.readItemStack();

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
        return new CrucibleCoolingRecipe(recipeId,fluidIn,itemOut,maxTemp,minTemp,cookTime,pressureMap,experience);
    }

    @Override
    public void write(PacketBuffer buffer, CrucibleCoolingRecipe recipe) {

        buffer.writeFluidStack(recipe.getFluidIn());

        buffer.writeItemStack(recipe.getItemOut());

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
