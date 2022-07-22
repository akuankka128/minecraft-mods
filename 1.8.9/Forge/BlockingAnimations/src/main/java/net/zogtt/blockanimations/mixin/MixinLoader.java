package net.zogtt.blockanimations.mixin;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.8.9")
@IFMLLoadingPlugin.Name("Blocking Animations")
public class MixinLoader {
    public MixinLoader() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.blockanimations.json");
    }
}
