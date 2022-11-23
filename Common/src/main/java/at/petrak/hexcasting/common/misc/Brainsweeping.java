package at.petrak.hexcasting.common.misc;

import at.petrak.hexcasting.xplat.IXplatAbstractions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public class Brainsweeping {
    public static InteractionResult interactWithBrainswept(Player player, Level world, InteractionHand hand,
        Entity entity, @Nullable EntityHitResult hitResult) {
        if (entity instanceof Mob mob && IXplatAbstractions.INSTANCE.isBrainswept(mob)) {
            return InteractionResult.FAIL;
        }

        return InteractionResult.PASS;
    }

    public static InteractionResult copyBrainsweepPostTransformation(LivingEntity original, LivingEntity outcome) {
        if (original instanceof Mob mOriginal && outcome instanceof Mob mOutcome
            && IXplatAbstractions.INSTANCE.isBrainswept(mOriginal)) {
            IXplatAbstractions.INSTANCE.brainsweep(mOutcome);
        }
        return InteractionResult.PASS;
    }
}
