package minerarcana.occult.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

import static minerarcana.occult.content.OccultParticles.COLOR_BUBBLE_PARTICLE;

public class ColoredBubbleData implements IParticleData {

    private final int r;
    private final int g;
    private final int b;
    private final int maxAge;

    public static ColoredBubbleData coloredBubbleData(int r, int g, int b, int maxAge) {
        return new ColoredBubbleData(r,g,b, maxAge);
    }

    public ColoredBubbleData(int r, int g, int b, int maxAge) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.maxAge = maxAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int getRed() {
        return r;
    }

    public int getBlue() {
        return b;
    }

    public int getGreen() {
        return g;
    }

    @Override
    public ParticleType<?> getType() {
        return COLOR_BUBBLE_PARTICLE;
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeInt(getRed());
        buffer.writeInt(getGreen());
        buffer.writeInt(getBlue());
        buffer.writeInt(getMaxAge());
    }

    @Override
    public String getParameters() {
        return null;
    }

    public static final IDeserializer<ColoredBubbleData> DESERIALIZER = new IDeserializer<ColoredBubbleData>() {

        @Override
        public ColoredBubbleData deserialize(ParticleType<ColoredBubbleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            int r = reader.readInt();
            reader.expect(' ');
            int g = reader.readInt();
            reader.expect(' ');
            int b = reader.readInt();
            reader.expect(' ');
            int maxAge = reader.readInt();
            return coloredBubbleData(r,g,b,maxAge);
        }

        @Override
        public ColoredBubbleData read(ParticleType<ColoredBubbleData> particleTypeIn, PacketBuffer buf) {
            int r = buf.readInt();
            int g = buf.readInt();
            int b = buf.readInt();
            int maxAge = buf.readInt();
            return coloredBubbleData(r,g,b,maxAge);
        }
    };


}
