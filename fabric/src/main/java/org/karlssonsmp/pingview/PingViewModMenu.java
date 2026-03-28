package org.karlssonsmp.pingview;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import org.karlssonsmp.pingview.config.PingViewConfigScreen;

public class PingViewModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return PingViewConfigScreen::create;
    }
}