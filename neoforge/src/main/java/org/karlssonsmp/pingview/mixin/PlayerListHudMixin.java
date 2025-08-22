package org.karlssonsmp.pingview.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.client.multiplayer.PlayerInfo;
import org.karlssonsmp.pingview.PingViewHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerTabOverlay.class)
public class PlayerListHudMixin {

    @Shadow @Final private Minecraft minecraft;

    @ModifyConstant(method = "render", constant = @Constant(intValue = 13))
    private int modifyPingAreaWidth(int original) {
        return original + 23;
    }

    @Inject(method = "renderPingIcon(Lnet/minecraft/client/gui/GuiGraphics;IIILnet/minecraft/client/multiplayer/PlayerInfo;)V",
            at = @At("HEAD"),
            cancellable = true)
    private void replacePingIconWithText(GuiGraphics context, int width, int x, int y, PlayerInfo entry, CallbackInfo ci) {
        PingViewHud.renderPingText(minecraft, context, width, x, y, entry);
        ci.cancel();
    }
}