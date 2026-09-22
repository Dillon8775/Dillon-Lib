package net.dillon.dillonlib.registry;

import net.dillon.dillonlib.core.DillonLibMain;
import net.dillon.dillonlib.core.DillonLibSounds;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeSoundEvents {
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, DillonLibMain.MOD_ID);
    private static final Supplier<SoundEvent> FORTNITE_BATTLE_PASS = SOUND_EVENTS.register(
            "fortnite_battle_pass",
            () -> SoundEvent.createVariableRangeEvent(DillonLibSounds.FORTNITE_BATTLE_PASS_ID)
    );

    public static void register(IEventBus modEventBus) {
        SOUND_EVENTS.register(modEventBus);
    }

    public static void bindCommonReferences() {
        DillonLibSounds.bindSounds(FORTNITE_BATTLE_PASS.get());
    }
}