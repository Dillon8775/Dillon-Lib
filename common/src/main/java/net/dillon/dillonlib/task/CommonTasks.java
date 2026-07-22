package net.dillon.dillonlib.task;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Common tasks that can be used in your mod.
 * @since 1.0
 */
public class CommonTasks {
    private static final List<ScheduledTask> TASKS = new ArrayList<>();

    /**
     * Schedules a task (executes after {@code delayTicks}).
     */
    public static void schedule(int delayTicks, Runnable task) {
        TASKS.add(new ScheduledTask(delayTicks, task));
    }

    /**
     * Tick the task delay, and then execute.
     */
    public static void tickTasks(MinecraftServer server) {
        Iterator<ScheduledTask> iterator = TASKS.iterator();

        while (iterator.hasNext()) {
            ScheduledTask task = iterator.next();
            task.ticks--;

            if (task.ticks <= 0) {
                task.task.run();
                iterator.remove();
            }
        }
    }

    /**
     * Sends an overlay message to a {@link Player}.
     */
    public void sendOverlayMessage(Player player, Component message) {
        player.displayClientMessage(message, true);
    }

    /**
     * Sends a system message to a {@link Player}.
     */
    public void sendSystemMessage(Player player, Component message) {
        player.displayClientMessage(message, false);
    }

    /**
     * Plays a sound to a {@link LivingEntity}.
     */
    public static void playSoundFromLiving(LivingEntity living, SoundEvent sound, SoundSource source) {
        playSoundFromLiving(living, sound, source, 1.0F, 1.0F);
    }

    /**
     * Plays a sound to a {@link LivingEntity}, with a custom volume and pitch.
     */
    public static void playSoundFromLiving(LivingEntity living, SoundEvent sound, SoundSource source, float volume, float pitch) {
        Level level = living.level();

        if (level != null) {
            level.playSound(null, living.getOnPos(), sound, source, volume, pitch);
        }
    }

    /**
     * Executes a task for a {@link Player} if they aren't null.
     */
    public static void executeIfPlayer(Player player, Consumer<Player> playerConsumer) {
        if (player != null) {
            playerConsumer.accept(player);
        }
    }

    /**
     * Executes a task of the current {@link Level} if it isn't null.
     */
    public static void executeIftLevel(Level l, Consumer<Level> levelConsumer) {
        if (l != null) {
            levelConsumer.accept(l);
        }
    }

    /**
     * @return the player's main hand stack.
     */
    public static ItemStack getMainHandStack(Player player) {
        return player != null ? player.getMainHandItem() : ItemStack.EMPTY;
    }

    /**
     * @return the player's offhand stack.
     */
    public static ItemStack getOffHandStack(Player player) {
        return player != null ? player.getOffhandItem() : ItemStack.EMPTY;
    }

    /**
     * @return the item in the given slot.
     */
    public static ItemStack getItemBySlot(Player player, EquipmentSlot slot) {
        return player != null ? player.getItemBySlot(slot) : ItemStack.EMPTY;
    }

    /**
     * @return if the stack has an enchantment.
     */
    public static boolean hasEnchantment(Predicate<ItemStack> validStack, ResourceKey<Enchantment> enchantment, ItemStack stack) {
        if (!validStack.test(stack)) {
            return false;
        }

        ItemEnchantments itemEnchantmentsComponent = EnchantmentHelper.getEnchantmentsForCrafting(stack);

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : itemEnchantmentsComponent.entrySet()) {
            if (entry.getKey().is(enchantment)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return if the stack has a potion component.
     */
    public static boolean hasEffect(ItemStack stack, String potionName) {
        if (stack.get(DataComponents.POTION_CONTENTS) == null) {
            return false;
        }

        for (MobEffectInstance slotEffect : stack.get(DataComponents.POTION_CONTENTS).getAllEffects()) {
            if (slotEffect.getEffect().is(Identifier.parse(potionName))) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return a raw {@link List} of entities (excluding {@code named entities}) within a specified range, and a custom predicate.
     * @param world level reference
     * @param entityListOf the list of entities to return, by class
     * @param startingPoint the entity that the game should start searching from
     * @param xyz an array of the maximum {@code x, y, and z} search radius
     */
    public static List getEntitiesWithinRange(Level world, Class<? extends LivingEntity> entityListOf, LivingEntity startingPoint, List<Integer> xyz, Predicate<? super LivingEntity> selector) {
        return world.getEntitiesOfClass(entityListOf, startingPoint.getBoundingBox().inflate(
                        xyz.getFirst(),
                        xyz.get(1),
                        xyz.get(2)),
                selector);
    }

    /**
     * An alternative to {@link TimerTask}, which allows execution of code after a set amount of seconds, running within the Minecraft server tick.
     */
    private static class ScheduledTask {
        int ticks;
        Runnable task;

        ScheduledTask(int ticks, Runnable task) {
            this.ticks = ticks;
            this.task = task;
        }
    }
}