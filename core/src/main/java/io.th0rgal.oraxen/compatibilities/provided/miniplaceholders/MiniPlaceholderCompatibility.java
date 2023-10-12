package io.th0rgal.oraxen.compatibilities.provided.miniplaceholders;

import io.github.miniplaceholders.api.Expansion;
import io.th0rgal.oraxen.compatibilities.CompatibilityProvider;
import io.th0rgal.oraxen.font.GlyphTag;
import io.th0rgal.oraxen.font.ShiftTag;
import io.th0rgal.oraxen.utils.logs.Logs;
import org.bukkit.plugin.java.JavaPlugin;

public class MiniPlaceholderCompatibility extends CompatibilityProvider<JavaPlugin> {
    static Expansion expansion;

    public MiniPlaceholderCompatibility() {
        Logs.logSuccess("MiniPlaceholders found, registering expansions");
        registerExpansion();
    }
    public static void registerExpansion() {
        unregister();
        expansion = Expansion.builder("oraxen")
                .globalPlaceholder(GlyphTag.GLYPH, (queue, ctx) -> GlyphTag.glyphTag(null, queue))
                .globalPlaceholder(GlyphTag.GLYPH_SHORT, (queue, ctx) -> GlyphTag.glyphTag(null, queue))
                .globalPlaceholder(ShiftTag.SHIFT, (queue, ctx) -> ShiftTag.shiftTag(queue))
                .globalPlaceholder(ShiftTag.SHIFT_SHORT, (queue, ctx) -> ShiftTag.shiftTag(queue))
                .build();
        expansion.register();
        Logs.debug("Registered glyph expansion");
    }

    public static void unregister() {
        if (expansion != null && expansion.registered()) {
            expansion.unregister();
            expansion = null;
        }
    }
}
