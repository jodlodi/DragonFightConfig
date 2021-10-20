package io.github.jodlodi.mixin;

import io.github.jodlodi.Config;
import net.minecraft.world.end.DragonFightManager;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DragonFightManager.class)
public abstract class MixinDragonFightManager {
    @Shadow protected abstract void spawnExitPortal(boolean p_186094_1_);
    @Shadow protected abstract void spawnNewGateway();

    @Redirect(method = "scanState", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/end/DragonFightManager;spawnExitPortal(Z)V"))
    private void spawnExitPortalMixinRedirect(DragonFightManager instance, boolean p_186094_1_) {
        if (Config.portalState) {
            spawnExitPortal(Config.portalOpen);
        }
        if (Config.gatewayState) {
            spawnNewGateway();
        }
    }

    @Redirect(method = "scanState", at = @At(value = "FIELD", target = "Lnet/minecraft/world/end/DragonFightManager;previouslyKilled:Z", opcode = Opcodes.GETFIELD))
    private boolean previouslyKilledMixin(DragonFightManager instance) {
        if (Config.naturalState) {
            return instance.hasPreviouslyKilledDragon();
        } else {
            return true;
        }
    }

    @Inject(method = "tryRespawn", at = @At(value = "HEAD"), cancellable = true)
    private void tryRespawnMixin(CallbackInfo ci) {
        if (!Config.dragonState){
            ci.cancel();
        }
    }

    @Redirect(method = "setDragonKilled", at = @At(value = "FIELD", target = "Lnet/minecraft/world/end/DragonFightManager;previouslyKilled:Z", opcode = Opcodes.GETFIELD))
    private boolean previouslyKilledEggMixin(DragonFightManager instance) {
        if (Config.eggState) {
            return false;
        } else {
            return instance.hasPreviouslyKilledDragon();
        }
    }
}