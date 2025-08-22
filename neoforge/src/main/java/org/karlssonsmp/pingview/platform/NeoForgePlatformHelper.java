package org.karlssonsmp.pingview.platform;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.PlayerInfo;

public class NeoForgePlatformHelper implements PlatformHelper {

    @Override
    public <T, U> void renderPingText(T client, U context, int width, int x, int y, Object entry) {
        Minecraft mc = (Minecraft) client;
        GuiGraphics guiGraphics = (GuiGraphics) context;
        PlayerInfo playerInfo = (PlayerInfo) entry;

        int ping = playerInfo.getLatency();

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

        Font font = mc.font;
        int textWidth = font.width(pingText);
        int textX = x + width - textWidth;

        guiGraphics.drawString(font, pingText, textX, y, color, true);
    }
}