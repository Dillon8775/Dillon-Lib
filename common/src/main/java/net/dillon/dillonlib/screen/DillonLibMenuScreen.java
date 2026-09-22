package net.dillon.dillonlib.screen;

import com.mojang.blaze3d.Blaze3D;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillType;
import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.util.KeybindScrollHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * A common {@code menu} screen that contains basic mod information and access to configurations, resources and other things.
 * @since 1.2
 * @see ScreenBuilder
 * @see BasicDillonLibScreen
 */
@Dill(DillType.CLIENT)
public abstract class DillonLibMenuScreen extends OptionsSubScreen implements DillonLibScreen {
    private Button doneButton;
    private List<WidgetData> widgets;
    private final ScreenBuilder screenBuilder;

    public DillonLibMenuScreen(Screen lastScreen, Component title, Function<Screen, ScreenBuilder> builderFactory) {
        super(lastScreen, Minecraft.getInstance().options, title);
        this.screenBuilder = builderFactory.apply(this);
    }

    @Override
    public List<WidgetData> widgetData() {
        return this.widgets;
    }

    /**
     * @return the {@code done button}, which can be used as a reference for other buttons.
     */
    public AbstractWidget getDoneButton() {
        return this.doneButton;
    }

    /**
     * @return the screen builder.
     */
    public ScreenBuilder builder() {
        return this.screenBuilder;
    }

    @Override
    protected void addFooter() {
        this.doneButton = this.layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, button -> this.onClose()).width(200).build());
    }

    @Override
    public void widgets() {
    }

    @Override
    protected void init() {
        this.widgets = new ArrayList<>();

        super.init();

        this.widgets();

        for (WidgetData data : widgetData()) {
            setWidgetPositions(data);
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        drawWidgetData(graphics, mouseX, mouseY);
        renderModInfo(graphics);
    }

    /**
     * @return a keybinds button to display on your screen.
     */
    public AbstractWidget createOpenKeybindsButton(Component text) {
        return Button.builder(text, button -> {
            this.openKeybinds();
        }).build();
    }

    /**
     * Creates a header.
     */
    public void addHeader(Component header) {
        this.list.addHeader(header);
    }

    /**
     * Adds a list of buttons to the screen.
     */
    public void addButtons(AbstractWidget... buttons) {
        this.list.addSmall(
                Arrays.stream(buttons).toList()
        );
    }

    /**
     * Creates a header with buttons.
     */
    public void createHeader(Component header, AbstractWidget... buttons) {
        addHeader(header);
        addButtons(buttons);
    }

    /**
     * Creates a header a big button at the top, and then a list of regular buttons below it.
     */
    public void createHeaderWithBig(Component header, AbstractWidget big, AbstractWidget... buttons) {
        createHeader(header);
        this.list.addBig(big);
        addButtons(buttons);
    }

    /**
     * A method to open your keybinds screen.
     * @see KeybindScrollHelper#request(KeyMapping.Category)
     */
    protected void openKeybinds() {
    }

    /**
     * Renders basic mod information on the screen.
     * <pre> {@code
     * ClientTasks.drawModInfo(
     *                 graphics,
     *                 this,
     *                 VERSION,
     *                 qoqIdentifier(CHEESE_WHEEL_TEXTURE),
     *                 HAS_UPDATE
     *         );
     * }
     * </pre>
     * @see ClientTasks#drawModInfo(GuiGraphicsExtractor, Screen, Component, Identifier, boolean)
     */
    protected abstract void renderModInfo(GuiGraphicsExtractor graphics);

    /**
     * Opens the default configuration directory.
     */
    public void openConfigDirectory() {
        Blaze3D.openPath(
                Platforms.getCommonPlatform().configDir()
                        .toFile().toPath()
        );
    }

    /**
     * Required method to override.
     */
    @Override
    protected void addOptions() {
    }
}