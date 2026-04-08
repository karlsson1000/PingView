package org.karlssonsmp.pingview;

import net.minecraftforge.fml.common.Mod;

@Mod("pingview")
public class PingViewForge {

    public PingViewForge() {
        PingViewConfig.load();
    }
}