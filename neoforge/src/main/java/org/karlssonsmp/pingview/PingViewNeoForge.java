package org.karlssonsmp.pingview;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.karlssonsmp.pingview.config.PingViewConfigScreen;

@Mod("pingview")
public class PingViewNeoForge {

    public PingViewNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class,
                (mc, parent) -> PingViewConfigScreen.create(parent));
    }
}