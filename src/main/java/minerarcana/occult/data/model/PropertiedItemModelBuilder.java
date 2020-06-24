package minerarcana.occult.data.model;

import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

public class PropertiedItemModelBuilder extends ModelBuilder<PropertiedItemModelBuilder> {
    private final Map<String, String> properties = new LinkedHashMap<>();

    public PropertiedItemModelBuilder(ResourceLocation outputLocation, ExistingFileHelper existingFileHelper) {
        super(outputLocation, existingFileHelper);
    }

    public PropertiedItemModelBuilder property(String key, String value) {
        properties.put(key, value);
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject root = super.toJson();
        if (!properties.isEmpty()) {
            properties.forEach((key, value) -> root.addProperty(key, value));
        }
        return root;
    }
}
