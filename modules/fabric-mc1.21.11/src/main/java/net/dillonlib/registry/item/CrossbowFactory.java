package net.dillonlib.registry.item;

import net.dillonlib.annotation.GlobalUse;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * A crossbow item factory.
 */
@GlobalUse
public class CrossbowFactory extends CrossbowItem {
    private final float baseChargeTime;
    private final float defaultChargeSpeed;
    private final float fireworkChargeSpeed;

    public CrossbowFactory(Settings settings, float baseChargeTime, float defaultChargeSpeed, float fireworkChargeSpeed) {
        super(settings);
        this.baseChargeTime = baseChargeTime;
        this.defaultChargeSpeed = defaultChargeSpeed;
        this.fireworkChargeSpeed = fireworkChargeSpeed;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        ChargedProjectilesComponent chargedProjectilesComponent = itemStack.get(DataComponentTypes.CHARGED_PROJECTILES);
        if (chargedProjectilesComponent != null && !chargedProjectilesComponent.isEmpty()) {
            this.shootAll(world, user, hand, itemStack, this.getSpeed(chargedProjectilesComponent), 1.0F, null);
            return ActionResult.CONSUME;
        }
        if (!user.getProjectileType(itemStack).isEmpty()) {
            this.charged = false;
            this.loaded = false;
            user.setCurrentHand(hand);
            return ActionResult.CONSUME;
        }
        return ActionResult.FAIL;
    }

    /**
     * The maximum use time for the crossbow.
     */
    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return this.getNewPullTime(stack, user) + 3;
    }

    /**
     * Modified charging speed.
     */
    private float getSpeed(ChargedProjectilesComponent stack) {
        return stack.contains(Items.FIREWORK_ROCKET) ? this.fireworkChargeSpeed : this.defaultChargeSpeed;
    }

    /**
     * Modified pull time.
     */
    public int getNewPullTime(ItemStack stack, LivingEntity user) {
        float f = EnchantmentHelper.getCrossbowChargeTime(stack, user, this.baseChargeTime);
        return MathHelper.floor(f * 20.0F);
    }
}