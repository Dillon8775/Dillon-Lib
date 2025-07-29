package net.dillon.lib.option;

public class TestModOptions {
    public static final TestModOptions.Handler INSTANCE = new TestModOptions.Handler();

    public OptionValue<Boolean> testBoolean = new OptionValue<>(true, false);

    public static class Handler extends BaseOptions<TestModOptions> {
        protected Handler() {
            super("testmod.json");
        }

        @Override
        protected TestModOptions createDefault() {
            return new TestModOptions();
        }

        @Override
        protected Class<TestModOptions> getConfigClass() {
            return TestModOptions.class;
        }

        @Override
        protected void safeCheck() {
        }
    }
}