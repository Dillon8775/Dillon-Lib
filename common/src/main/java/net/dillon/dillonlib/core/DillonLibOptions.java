package net.dillon.dillonlib.core;

import net.dillon.dillonlib.util.BaseOptions;

public class DillonLibOptions {
    public static final DillonLibOptionsHandler INSTANCE = new DillonLibOptionsHandler();
    public boolean applyFullBrightIfRequired = true;
    public boolean applyDebugOptionsScreenFix = true;
    public boolean applyAbstractClientPlayerFix = true;
    public boolean applyAvatarRendererFix = true;
    public boolean applyPersonHandsAndItemsRendererFix = true;
    public boolean applyShearFactories = true;
    public boolean applyIgnitableFactories = true;
    public boolean fortniteBattlePass = false;

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