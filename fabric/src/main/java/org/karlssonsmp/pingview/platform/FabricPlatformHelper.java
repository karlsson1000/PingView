package org.karlssonsmp.pingview.platform;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;

public class FabricPlatformHelper implements PlatformHelper {

    @Override
    public <T, U> void renderPingText(T client, U context, int width, int x, int y, Object entry) {
        MinecraftClient mc = (MinecraftClient) client;
        DrawContext drawContext = (DrawContext) context;
        PlayerListEntry playerEntry = (PlayerListEntry) entry;

        int ping = playerEntry.getLatency();

        String pingText;
        int color;

        if (ping <= 0) {
            pingText = "N/A";
            color = 0xFF555555;
        } else {
            pingText = ping + "ms";

            if (ping < 50) {
                color = 0xFF1EFF00; // Green
            } else if (ping < 100) {
                color = 0xFFFFF100; // Yellow
            } else if (ping < 200) {
                color = 0xFFFF9500; // Orange
            } else {
                color = 0xFFFF3B3B; // Red
            }
        }

        TextRenderer textRenderer = mc.textRenderer;
        int textWidth = textRenderer.getWidth(pingText);
        int textX = x + width - textWidth;

        drawContext.drawText(textRenderer, pingText, textX, y, color, true);
    }
}