package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

import static net.dillon.dillonlib.task.ClientTasks.getSoundManager;

/**
 * A builder class for playing sounds.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT)
public class SoundBuilder {
    private final Identifier sound;
    private float pitch = 1.0F;
    private float volume = 0.25F;

    private SoundBuilder(Identifier sound) {
        this.sound = sound;
    }

    public static SoundBuilder create(SoundEvent sound) {
        return create(sound.location());
    }

    public static SoundBuilder create(Identifier sound) {
        return new SoundBuilder(sound);
    }

    public SoundBuilder pitch(float pitch) {
        this.pitch = pitch;
        return this;
    }

    public SoundBuilder volume(float volume) {
        this.volume = volume;
        return this;
    }

    public void buildAndPlay() {
        getSoundManager().play(
                new SimpleSoundInstance(
                        sound,
                        SoundSource.UI,
                        volume,
                        pitch,
                        SoundInstance.createUnseededRandom(),
                        false,
                        0,
                        SoundInstance.Attenuation.NONE,
                        0.0,
                        0.0,
                        0.0,
                        true
                )
        );
    }
}