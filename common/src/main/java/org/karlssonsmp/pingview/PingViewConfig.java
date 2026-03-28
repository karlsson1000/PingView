package org.karlssonsmp.pingview;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.*;

public class PingViewConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = Path.of("config", "pingview.json");

    private static PingViewConfig instance;

    public String pingColorGood     = "#1EFF00";
    public String pingColorOk       = "#FFF100";
    public String pingColorBad      = "#FF9500";
    public String pingColorTerrible = "#FF3B3B";
    public String pingColorUnknown  = "#555555";
    public boolean showMs           = true;
    public boolean showNametagPing  = false;
    public boolean textShadow = true;

    public static PingViewConfig get() {
        if (instance == null) load();
        return instance;
    }

    public static void load() {
        instance = new PingViewConfig();
        if (!Files.exists(CONFIG_PATH)) {
            save();
            return;
        }
        try (Reader r = Files.newBufferedReader(CONFIG_PATH)) {
            instance = GSON.fromJson(r, PingViewConfig.class);
        } catch (IOException e) {
            System.err.println("[PingView] Failed to read config, using defaults: " + e.getMessage());
        }
    }

    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (Writer w = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(instance, w);
            }
        } catch (IOException e) {
            System.err.println("[PingView] Failed to save config: " + e.getMessage());
        }
    }

    public static int parseColor(String hex) {
        String clean = hex.startsWith("#") ? hex.substring(1) : hex;
        if (clean.length() != 6) {
            return 0xFFFFFFFF;
        }
        try {
            return 0xFF000000 | Integer.parseUnsignedInt(clean, 16);
        } catch (NumberFormatException e) {
            return 0xFFFFFFFF;
        }
    }
}