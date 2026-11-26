package net.dillon.dillonlib.client.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.core.DillonLibOptions;
import net.dillon.dillonlib.util.BaseOptions;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * A command to reload an instance of a {@link BaseOptions} instance.
 */
@Dill(env = DillEnv.CLIENT, type = DillType.UNIVERSAL)
public class ReloadConfigCommand {
    private static final List<Reloadable> reloadables = new ArrayList<>();

    /**
     * Creates a reloadable config argument.
     * Call this in your mod's main entrypoint, not a loadable instance.
     */
    public static void createReloadable(String argument, BaseOptions<?> instance) {
        createReloadable(argument, List.of(instance));
    }

    /**
     * Creates a reloadable config argument.
     * Call this in your mod's main entrypoint, not a loadable instance.
     */
    public static void createReloadable(String argument, List<BaseOptions<?>> instances) {
        reloadables.add(new Reloadable(argument, instances));
    }

    /**
     * Reloads a config instance.
     */
    private static <S> int executeForClient(S source, Function<S, ClientCommandFeedback> feedback, List<BaseOptions<?>> instances) {
        final int reloaded = reloadConfigs(instances);
        feedback.apply(source).sendSuccess(reloadedMessage(reloaded));
        return 1;
    }

    /**
     * Reloads a list of configs.
     */
    private static int reloadConfigs(List<BaseOptions<?>> instances) {
        int i = 0;

        for (BaseOptions<?> instance : instances) {
            instance.load();
            i++;
        }

        return i;
    }

    /**
     * @return the loaded message.
     */
    private static Component reloadedMessage(int reloaded) {
        return Component.translatable("dillonlib.command.config_reloaded", reloaded);
    }

    /**
     * Initializes the {@code DillonLib /dillonlibconfig reload} command.
     */
    public static <S> LiteralArgumentBuilder<S> i_(Function<S, ClientCommandFeedback> feedback) {
        LiteralArgumentBuilder<S> reload = LiteralArgumentBuilder.<S>literal("reload")
                .executes(context -> executeForClient(
                                context.getSource(),
                                feedback,
                                List.of(DillonLibOptions.INSTANCE)
                        )
                );

        reloadables.forEach(reloadable -> {
            String argumentName = reloadable.argumentName();
            List<BaseOptions<?>> instances = reloadable.instances();

            reload.then(
                    LiteralArgumentBuilder.<S>literal(argumentName)
                            .executes(context -> executeForClient(
                                            context.getSource(),
                                            feedback,
                                            instances
                                    )
                            )
            );
        });

        return LiteralArgumentBuilder.<S>literal("dillonlibconfig")
                .then(reload);
    }

    /**
     * Stores a reloadable.
     */
    record Reloadable(String argumentName, List<BaseOptions<?>> instances) {
    }
}