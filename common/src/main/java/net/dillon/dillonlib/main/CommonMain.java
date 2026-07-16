package net.dillon.dillonlib.main;

import net.dillon.dillonlib.utility.*;

/**
 * {@code Main} entrypoint initialization for DillonLib.
 */
public class CommonMain {

    public static void initialize() {
        Arithmetics.i_();
        BaseOptions.i_();
        ClientChores.i_();
        TaskScheduler.i_();
        Texts.i_();
    }
}