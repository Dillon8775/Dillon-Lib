package net.dillonlib.registry.item;

import net.dillonlib.annotation.GlobalUse;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

import java.util.List;

/**
 * A bow item factory.
 */
@GlobalUse
public class BowFactory extends BowItem {
    private final float pullSpeed;
    private final float power;

    public BowFactory(Settings settings, float pullSpeed, float power) {
        super(settings.maxCount(1));
        this.pullSpeed = pullSpeed;
        this.power = power;
    }

    /**
     * Changes bow pull speed.
     */
    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity playerEntity)) {
            return false;
        } else {
            ItemStack itemStack = playerEntity.getProjectileType(stack);
            if (itemStack.isEmpty()) {
                return false;
            } else {
                int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
                float f = this.getNewPullProgress(i);
                if ((double)f < 0.1) {
                    return false;
                } else {
                    List<ItemStack> list = load(stack, itemStack, playerEntity);
                    if (world instanceof ServerWorld serverWorld && !list.isEmpty()) {
                        this.shootAll(serverWorld, playerEntity, playerEntity.getActiveHand(), stack, list, f * pullSpeed, 1.0F, f == 1.0F, null);
                    }

                    world.playSound(
                            null,
                            playerEntity.getX(),
                            playerEntity.getY(),
                            playerEntity.getZ(),
                            SoundEvents.ENTITY_ARROW_SHOOT,
                            SoundCategory.PLAYERS,
                            1.0F,
                            1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                    );
                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                    return true;
                }
            }
        }
    }

    @Override
    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        Item item = projectileStack.getItem();
        ArrowItem arrowItem2 = item instanceof ArrowItem ? (ArrowItem)item : (ArrowItem) Items.ARROW;
        PersistentProjectileEntity persistentProjectileEntity = arrowItem2.createArrow(world, projectileStack, shooter, weaponStack);
        persistentProjectileEntity.applyDamageModifier(power); // Added to increase/decrease power of bow
        if (critical) {
            persistentProjectileEntity.setCritical(true);
        }
        return persistentProjectileEntity;
    }

    /**
     * Renderers the pull progress of the speedrunner bow at a faster rate.
     */
    public float getNewPullProgress(int useTicks) {
        float f = (float)useTicks / pullSpeed;
        if ((f = (f * f + f * (pullSpeed / 10.0F)) / Math.round((pullSpeed / 6))) > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    /**
     * I honestly don't know what this does.
     */
    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return (int)pullSpeed * 3600;
    }

    /**
     * @return the pull speed for this bow.

     */
    public float getPullSpeed() {
        return this.pullSpeed;
    }
}
