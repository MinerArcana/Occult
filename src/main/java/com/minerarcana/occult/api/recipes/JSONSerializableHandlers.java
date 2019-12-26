package com.minerarcana.occult.api.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.minerarcana.occult.api.pressure.PressureType;
import net.minecraft.network.PacketBuffer;

public class JSONSerializableHandlers {

    public static PressureType pressureFromString(PacketBuffer buf) {
        PressureType type = null;
        return type.getTypeFromName(buf.readString());
    }

    public static void writePressure(PacketBuffer buf, PressureType type) {
        buf.writeString(type.toString());
    }

    public static JsonObject writePressureType(PressureType type) {
        JsonObject object = new JsonObject();
        object.addProperty("pressureType", type.toString());
        return object;
    }

    public static PressureType readPressureType(JsonElement object){
        PressureType pressure = null;
        return pressure.getTypeFromName(object.getAsString());
    }


}
