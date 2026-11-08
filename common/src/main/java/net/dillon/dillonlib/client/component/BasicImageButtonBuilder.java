package net.dillon.dillonlib.client.component;

import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.widespread.DillonsTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

/**
 * A builder class for creating basic {@link ImageButton}s.
 * @since 1.3
 */
@Dill(env = DillEnv.CLIENT)
public class BasicImageButtonBuilder implements ComponentBuilder<ImageButton>, TooltipBuilder<BasicImageButtonBuilder> {
    private final WidgetSprites sprites;
    private final Button.OnPress onPress;
    private Supplier<Component> tooltip = Component::empty;
    private int width = 20;
    private int height = 20;

    private BasicImageButtonBuilder(WidgetSprites sprites, Button.OnPress onPress) {
        this.sprites = sprites;
        this.onPress = onPress;
    }

    public static BasicImageButtonBuilder builder(WidgetSprites sprites, Button.OnPress onPress) {
        return new BasicImageButtonBuilder(sprites, onPress);
    }

    @Override
    public BasicImageButtonBuilder tooltip(Component tooltip) {
        return tooltip(() -> tooltip);
    }

    @Override
    public BasicImageButtonBuilder tooltip(Supplier<Component> tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public BasicImageButtonBuilder width(int width) {
        this.width = width;
        return this;
    }

    public BasicImageButtonBuilder height(int height) {
        this.height = height;
        return this;
    }

    @Override
    public ImageButton build() {
        ImageButton imageButton = new ImageButton(
                width,
                height,
                sprites,
                onPress,
                DillonsTexts.BLANK
        );
        imageButton.setTooltip(Tooltip.create(tooltip.get()));
        return imageButton;
    }
}