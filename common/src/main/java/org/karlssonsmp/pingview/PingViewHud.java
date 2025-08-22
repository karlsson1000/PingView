package org.karlssonsmp.pingview;

import org.karlssonsmp.pingview.platform.Services;

public class PingViewHud {

	public static <T, U> void renderPingText(T client, U context, int width, int x, int y, Object entry) {
		Services.PLATFORM.renderPingText(client, context, width, x, y, entry);
	}
}