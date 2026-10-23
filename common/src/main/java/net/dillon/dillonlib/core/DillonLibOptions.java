package net.dillon.dillonlib.core;

import net.dillon.dillonlib.util.BaseOptions;

public class DillonLibOptions {
    public static final DillonLibOptionsHandler INSTANCE = new DillonLibOptionsHandler();
    private final Core core = new Core();
    private final Factories factories = new Factories();

    public Core core() {
        return this.core;
    }

    public Factories factories() {
        return this.factories;
    }

    public static class Core {
        public boolean checkForUpdates = true;
        public boolean applyFullBrightIfRequired = true;
    }

    public static class Factories {
        public boolean applyDebugOptionsScreenFix = true;
        public boolean applyAbstractClientPlayerFix = true;
        public boolean applyAvatarRendererFix = true;
        public boolean applyPersonHandsAndItemsRendererFix = true;
        public boolean applyShearFactories = true;
        public boolean applyIgnitableFactories = true;
    }

    /**
     * @return the DillonLib options instance.
     */
    public static DillonLibOptions getLibInstance() {
        return DillonLibOptions.INSTANCE.getInstance();
    }

    public static class DillonLibOptionsHandler extends BaseOptions<DillonLibOptions> {

        public DillonLibOptionsHandler() {
            super("dillonlib.json");
        }

        @Override
        protected DillonLibOptions createDefault() {
            return new DillonLibOptions();
        }

        @Override
        protected Class<DillonLibOptions> getConfigClass() {
            return DillonLibOptions.class;
        }
    }
}