package org.karlssonsmp.pingview.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import org.karlssonsmp.pingview.PingViewConfig;
import org.karlssonsmp.pingview.render.PingViewRender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private <T extends Entity, S extends EntityRenderState> void appendPingToNametag(
            T entity, S state, float partialTicks, CallbackInfo ci) {

        PingViewConfig cfg = PingViewConfig.get();
        if (!cfg.showNametagPing) return;
        if (!(entity instanceof AbstractClientPlayer player)) return;
        if (state.nameTag == null) return;

        var connection = Minecraft.getInstance().getConnection();
        if (connection == null) return;

        PlayerInfo info = connection.getPlayerInfo(player.getUUID());
        if (info == null) return;

        int ping = info.getLatency();
        String pingText = PingViewRender.getPingText(cfg, ping);
        int color = PingViewRender.getPingColor(cfg, ping);

        state.nameTag = Component.literal(state.nameTag.getString())
                .append(Component.literal(" (").withStyle(style -> style.withColor(0xAAAAAA)))
                .append(Component.literal(pingText).withStyle(style -> style.withColor(color)))
                .append(Component.literal(")").withStyle(style -> style.withColor(0xAAAAAA)));
    }
}