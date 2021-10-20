package io.github.jodlodi.mixin;

import io.github.jodlodi.Config;
import net.minecraft.entity.item.EnderCrystalEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.EndSpikeFeature;
import net.minecraft.world.gen.feature.EndSpikeFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EndSpikeFeature.class)
public class MixinEndSpikeFeature {
    @Inject(method = "placeSpike", at = @At(value = "HEAD"), cancellable = true)
    private void mixinPlaceSpikeFullCancel(IServerWorld flag3, Random flag4, EndSpikeFeatureConfig blockstate, EndSpikeFeature.EndSpike flag, CallbackInfo ci) {
        if (flag3.getEntitiesOfClass(EnderCrystalEntity.class, new AxisAlignedBB(-2,0,-2,3,255,3)).isEmpty() & !Config.spikeState) {
            ci.cancel();
        }
    }

    @Inject(method = "placeSpike", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityType;create(Lnet/minecraft/world/World;)Lnet/minecraft/entity/Entity;"), cancellable = true)
    private void mixinPlaceSpikeCrystalCancel(IServerWorld flag3, Random flag4, EndSpikeFeatureConfig blockstate, EndSpikeFeature.EndSpike flag, CallbackInfo ci) {
        if (flag3.getEntitiesOfClass(EnderCrystalEntity.class, new AxisAlignedBB(-2,0,-2,3,255,3)).isEmpty() & !Config.naturalState) {
            ci.cancel();
        }
    }
}