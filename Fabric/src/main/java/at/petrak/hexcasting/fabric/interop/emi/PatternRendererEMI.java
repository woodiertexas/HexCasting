package at.petrak.hexcasting.fabric.interop.emi;

import at.petrak.hexcasting.api.PatternRegistryBak;
import at.petrak.hexcasting.api.spell.math.HexCoord;
import at.petrak.hexcasting.interop.utils.PatternDrawingUtil;
import at.petrak.hexcasting.interop.utils.PatternEntry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import dev.emi.emi.api.render.EmiRenderable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;

import java.util.List;

public class PatternRendererEMI implements EmiRenderable {

    private final long startTime = System.currentTimeMillis();

    private final int width;
    private final int height;

    private final boolean strokeOrder;

    private final List<PatternEntry> patterns;
    private final List<Vec2> pathfinderDots;

    public PatternRendererEMI(ResourceLocation pattern, int w, int h) {
        var entry = PatternRegistryBak.lookupPattern(pattern);
        this.strokeOrder = !entry.isPerWorld();
        var data = PatternDrawingUtil.loadPatterns(List.of(new Pair<>(entry.getPrototype(), HexCoord.getOrigin())));
        this.patterns = data.patterns();
        this.pathfinderDots = data.pathfinderDots();
        this.width = w;
        this.height = h;
    }

    @Override
    public void render(PoseStack poseStack, int x, int y, float delta) {
        long time = (System.currentTimeMillis() - startTime) / 50;
        poseStack.pushPose();
        poseStack.translate(x - 0.5f + width / 2f, y + 1 + height / 2f, 0);
        poseStack.scale(width / 64f, height / 64f, 1f);
        PatternDrawingUtil.drawPattern(poseStack, 0, 0, this.patterns, this.pathfinderDots, this.strokeOrder, time,
            0xff_333030, 0xff_191818, 0xc8_0c0a0c, 0x80_666363);
        poseStack.popPose();
    }
}
