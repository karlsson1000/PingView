package org.karlssonsmp.pingview.platform;

public interface PlatformHelper {
    <T, U> void renderPingText(T client, U context, int width, int x, int y, Object entry);
}