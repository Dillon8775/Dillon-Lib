package net.dillon.dillonlib.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

/**
 * Sounds for DillonLib.
 */
public class DillonLibSounds {
    public static final Identifier FORTNITE_BATTLE_PASS_ID = Identifier.fromNamespaceAndPath(DillonLibMain.MOD_ID, "fortnite_battle_pass");
    public static SoundEvent FORTNITE_BATTLE_PASS = SoundEvent.createVariableRangeEvent(FORTNITE_BATTLE_PASS_ID);

    /**
     * Binds the Fortnite battle pass sound to the sound event.
     */
    public static void bindSounds(SoundEvent soundEvent) {
        FORTNITE_BATTLE_PASS = soundEvent;
    }

    /**
     * Registers the Fortnite battle pass sound at the correct time, so NeoForged doesn't crash.
     */
    public static void registerBoundSoundEvents() {
        FORTNITE_BATTLE_PASS = Registry.register(BuiltInRegistries.SOUND_EVENT, FORTNITE_BATTLE_PASS_ID, FORTNITE_BATTLE_PASS);
    }
}