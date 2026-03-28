package org.karlssonsmp.pingview;

import net.fabricmc.api.ClientModInitializer;

public class PingViewClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PingViewConfig.load();
    }
}