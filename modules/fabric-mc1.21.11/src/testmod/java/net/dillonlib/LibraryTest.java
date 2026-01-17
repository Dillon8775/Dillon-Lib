package net.dillonlib;

import net.dillonlib.main.LibTest;
import net.dillonlib.main.TestModOptions;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class LibraryTest implements ModInitializer {

    @Override
    public void onInitialize() {
        LibTest.init();
        TestModOptions.INSTANCE.load();
    }

    public static Identifier id(String path) {
        return Identifier.of("dillonlibtest", path);
    }
}