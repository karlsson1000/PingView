package org.karlssonsmp.pingview.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import org.karlssonsmp.pingview.PingViewHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerListHud.class)
public class PlayerListHudMixin {

    @Shadow @Final private MinecraftClient client;

    @ModifyConstant(method = "render", constant = @Constant(intValue = 13))
    private int modifyPingAreaWidth(int original) {
        return original + 23;
    }

    @Inject(method = "renderLatencyIcon(Lnet/minecraft/client/gui/DrawContext;IIILnet/minecraft/client/network/PlayerListEntry;)V",
            at = @At("HEAD"),
            cancellable = true)
    private void replacePingIconWithText(DrawContext context, int width, int x, int y, PlayerListEntry entry, CallbackInfo ci) {
        PingViewHud.renderPingText(client, context, width, x, y, entry);
        ci.cancel();
    }
}