package net.dillon.dillonlib.core;

import net.dillon.dillonlib.util.BaseOptions;

public class DillonLibOptions {
    public static final DillonLibOptionsHandler INSTANCE = new DillonLibOptionsHandler();
    public boolean applyAbstractClientPlayerFix = true;
    public boolean applyAvaterRendererFix = true;
    public boolean applyItemInHandRendererFix = true;
    public boolean applyShearFactories = true;
    public boolean applyIgnitableFactories = true;
    public boolean applyShieldFactories = true;

    /**
     * @return the dillonlib options instance.
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