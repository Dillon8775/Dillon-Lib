package net.dillon.dillonlib.main;

import net.dillon.dillonlib.*;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.client.ModernWidgetOptions;
import net.dillon.dillonlib.platform.Statics;
import net.dillon.dillonlib.client.ModernListWidget;

import java.util.List;

/**
 * {@code Client} initialization entrypoint for DillonLib.
 */
@Dill(DillType.CLIENT)
public class ClientMain {

    /**
     * Initializes the {@code client-side} of DillonLib.
     */
    public static void cInitialize() {
        clientInitializers().forEach(Runnable::run);

        Statics.info("Client-side for DillonLib has successfully initialized.");
    }

    /**
     * @return all client-side initializer methods for {@code client-side only classes.}
     */
    private static List<Runnable> clientInitializers() {
        return List.of(
                ModernListWidget::i_,
                ModernWidgetOptions::i_,
                ClientChores::i_
        );
    }
}