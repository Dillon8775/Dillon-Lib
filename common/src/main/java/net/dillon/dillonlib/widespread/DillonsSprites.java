package net.dillon.dillonlib.widespread;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.minecraft.resources.Identifier;

import static net.dillon.dillonlib.core.DillonLibMain.dillonLibIdentifier;

/**
 * Common sprites used throughout Dillon's mods.
 * @since 1.0
 */
@Dill(env = DillEnv.CLIENT)
public class DillonsSprites {
    public static final Identifier DILLONLIB_LOGO = dillonLibIdentifier("icon/logo");
    public static final Identifier UPDATE_AVAILABLE = Identifier.withDefaultNamespace("icon/trial_available");
    public static final Identifier CHECKMARK = Identifier.withDefaultNamespace("icon/checkmark");
}