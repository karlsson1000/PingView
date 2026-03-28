package org.karlssonsmp.pingview.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.karlssonsmp.pingview.PingViewConfig;

public class PingViewConfigScreen {

    public static Screen create(Screen parent) {
        PingViewConfig cfg = PingViewConfig.get();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("PingView Settings"))
                .setSavingRunnable(PingViewConfig::save)
                .setDoesConfirmSave(false);

        ConfigEntryBuilder eb = builder.entryBuilder();
        ConfigCategory cat = builder.getOrCreateCategory(Component.literal("Options"));

        cat.addEntry(eb.startColorField(Component.literal("Good"), parseColor(cfg.pingColorGood))
                .setDefaultValue(parseColor("#1EFF00"))
                .setSaveConsumer(v -> cfg.pingColorGood = toHex(v))
                .build());

        cat.addEntry(eb.startColorField(Component.literal("Ok"), parseColor(cfg.pingColorOk))
                .setDefaultValue(parseColor("#FFF100"))
                .setSaveConsumer(v -> cfg.pingColorOk = toHex(v))
                .build());

        cat.addEntry(eb.startColorField(Component.literal("Bad"), parseColor(cfg.pingColorBad))
                .setDefaultValue(parseColor("#FF9500"))
                .setSaveConsumer(v -> cfg.pingColorBad = toHex(v))
                .build());

        cat.addEntry(eb.startColorField(Component.literal("Terrible"), parseColor(cfg.pingColorTerrible))
                .setDefaultValue(parseColor("#FF3B3B"))
                .setSaveConsumer(v -> cfg.pingColorTerrible = toHex(v))
                .build());

        cat.addEntry(eb.startColorField(Component.literal("Unknown"), parseColor(cfg.pingColorUnknown))
                .setDefaultValue(parseColor("#555555"))
                .setSaveConsumer(v -> cfg.pingColorUnknown = toHex(v))
                .build());

        cat.addEntry(eb.startBooleanToggle(Component.literal("Show 'ms' suffix"), cfg.showMs)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.showMs = v)
                .build());

        cat.addEntry(eb.startBooleanToggle(Component.literal("Show ping on nametags"), cfg.showNametagPing)
                .setDefaultValue(false)
                .setSaveConsumer(v -> cfg.showNametagPing = v)
                .build());

        cat.addEntry(eb.startBooleanToggle(Component.literal("Text shadow"), cfg.textShadow)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.textShadow = v)
                .build());

        return builder.build();
    }

    private static int parseColor(String hex) {
        String clean = hex.startsWith("#") ? hex.substring(1) : hex;
        try {
            return Integer.parseUnsignedInt(clean, 16);
        } catch (NumberFormatException e) {
            return 0xFFFFFF;
        }
    }

    private static String toHex(int color) {
        return String.format("#%06X", color & 0xFFFFFF);
    }
}