package net.zogtt.blockanimations.mixin.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Timer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {
    // Inject into
    // net.minecraft.client.renderer.renderItemInFirstPerson(F)V
    //  -> net.minecraft.client.renderer.func_178103_d()V
    // (case net.minecraft.item.EnumAction BLOCK)
    @Inject(method = "func_178103_d", at = @At("RETURN"), cancellable = false)
    private void func_178103_d(CallbackInfo ci)
    {
        Minecraft mc = Minecraft.getMinecraft();

        // Minecraft.timer
        Timer timer = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, mc, "field_71428_T");
        float partialTicks = timer.renderPartialTicks;

        AbstractClientPlayer acp = mc.thePlayer;
        float swingProgress = acp.getSwingProgress(partialTicks);

        // Angle stuff... I don't really know, I just messed
        //  with these until it started working in a way I liked.
        float f = MathHelper.sin(swingProgress * (float)Math.PI);
        GlStateManager.rotate(f * -80.0F, 1.0F, 0.0F, 0.0F);
    }
}
