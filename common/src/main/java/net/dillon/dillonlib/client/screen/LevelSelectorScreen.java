package net.dillon.dillonlib.client.screen;

import com.ibm.icu.text.Collator;
import net.dillon.dillonlib.annotation.Dill;
import net.dillon.dillonlib.annotation.DillEnv;
import net.dillon.dillonlib.widespread.DillonsTexts;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.storage.LevelSummary;
import org.jspecify.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A basic screen to select a world or server.
 */
@Dill(env = DillEnv.CLIENT)
public class LevelSelectorScreen extends DillonLibScreen {
    private final Screen parent;
    private final Consumer<String> onLevelSelected;
    private final HeaderAndFooterLayout layout;
    private final Set<String> listOfServers;
    private final Predicate<LevelSummary> validWorldPredicate;
    private LevelList levelList;
    private Button selectButton;

    public LevelSelectorScreen(Screen parent, Component title, Consumer<String> onLevelSelected) {
        this(parent, title, onLevelSelected, levelSummary -> true, new HashSet<>());
    }

    public LevelSelectorScreen(Screen parent, Component title, Consumer<String> onLevelSelected, Predicate<LevelSummary> validWorldPredicate, Set<String> servers) {
        super(title);
        this.parent = parent;
        this.onLevelSelected = onLevelSelected;
        this.layout = new HeaderAndFooterLayout(this, 40, 33);
        this.validWorldPredicate = validWorldPredicate;
        this.listOfServers = servers;
    }

    @Override
    public void onClose() {
        this.minecraft.gui.setScreen(this.parent);
    }

    @Override
    protected void repositionElements() {
        this.layout.arrangeElements();
        this.levelList.updateSize(this.width, this.layout);
    }

    /**
     * Ensures the selected button can be pressed if a world is selected.
     */
    private void updateButtonValidity() {
        this.selectButton.active = this.levelList.getSelected() != null;
    }

    @Override
    public void widgets() {
        LinearLayout header = this.layout.addToHeader(LinearLayout.vertical().spacing(3));

        header.defaultCellSetting().alignHorizontallyCenter();
        header.addChild(new StringWidget(this.getTitle(), this.font));

        EditBox search = header.addChild(new EditBox(this.font, 200, 15, Component.empty()));
        search.setHint(DillonsTexts.SEARCH);
        this.levelList = this.layout.addToContents(new LevelList(this.listOfServers, this.validWorldPredicate));
        search.setResponder(this.levelList::filterEntries);

        LinearLayout footer = this.layout.addToFooter(LinearLayout.horizontal().spacing(8));

        this.selectButton = footer.addChild(
                Button.builder(
                        Component.literal("Select"),
                        button -> {
                            LevelList.Entry selected = this.levelList.getSelected();

                            if (selected != null) {
                                this.onLevelSelected.accept(selected.getDisplayName());

                                this.onClose();
                            }
                        }
                ).build()
        );

        footer.addChild(
                Button.builder(
                        CommonComponents.GUI_CANCEL,
                        button -> this.onClose()
                ).build()
        );

        this.levelList.setSelected(null);

        this.layout.visitWidgets(this::addRenderableWidget);
        this.repositionElements();
    }

    private class LevelList extends ObjectSelectionList<LevelList.Entry> {
        private List<LevelSummary> worlds = List.of();
        private Set<String> servers = new HashSet<>();

        private LevelList(Set<String> listOfServers, Predicate<LevelSummary> acceptableWorld) {
            super(
                    Minecraft.getInstance(),
                    LevelSelectorScreen.this.width,
                    LevelSelectorScreen.this.layout.getContentHeight(),
                    LevelSelectorScreen.this.layout.getHeaderHeight(),
                    16
            );

            Minecraft.getInstance()
                    .getLevelSource()
                    .loadLevelSummaries(
                            Minecraft.getInstance()
                                    .getLevelSource()
                                    .findLevelCandidates()
                    )
                    .thenAccept(summaries ->
                            Minecraft.getInstance().execute(() -> {
                                this.worlds = summaries.stream()
                                        .filter(acceptableWorld)
                                        .toList();
                                this.servers = listOfServers;
                                this.filterEntries("");
                            })
                    );
        }

        @Override
        public void setSelected(@Nullable Entry selected) {
            super.setSelected(selected);
            LevelSelectorScreen.this.updateButtonValidity();
        }

        private void filterEntries(String filter) {
            Collator collator = Collator.getInstance(Locale.getDefault());
            String lowercaseFilter = filter.toLowerCase(Locale.ROOT);

            List<Entry> entries = new ArrayList<>();

            this.worlds.stream()
                    .filter(summary -> summary.getLevelId()
                            .toLowerCase(Locale.ROOT)
                            .contains(lowercaseFilter))
                    .map(Entry::new)
                    .forEach(entries::add);

            this.servers.stream()
                    .filter(server -> server.toLowerCase(Locale.ROOT)
                            .contains(lowercaseFilter))
                    .map(Entry::new)
                    .forEach(entries::add);

            entries.sort(Comparator.comparing(
                    Entry::getDisplayName,
                    collator
            ));

            Entry selected = this.getSelected();

            this.replaceEntries(entries);

            if (selected != null) {
                this.setSelected(this.children().stream()
                        .filter(entry -> entry.getDisplayName()
                                .equals(selected.getDisplayName()))
                        .findFirst()
                        .orElse(null));
            }

            this.refreshScrollAmount();
            LevelSelectorScreen.this.updateButtonValidity();
        }

        private class Entry extends ObjectSelectionList.Entry<Entry> {
            private final @Nullable LevelSummary summary;
            private final String displayName;

            private Entry(LevelSummary summary) {
                this.summary = summary;
                this.displayName = summary.getLevelId();
            }

            private Entry(String server) {
                this.summary = null;
                this.displayName = server;
            }

            private String getDisplayName() {
                return this.displayName;
            }

            @Override
            public Component getNarration() {
                return Component.translatable(
                        "narrator.select",
                        Component.literal(this.displayName)
                );
            }

            @Override
            public void extractContent(
                    GuiGraphicsExtractor graphics,
                    int mouseX,
                    int mouseY,
                    boolean hovered,
                    float partialTick
            ) {
                graphics.text(
                        LevelSelectorScreen.this.font,
                        this.displayName,
                        this.getContentX() + 5,
                        this.getContentY() + 2,
                        -1
                );
            }

            @Override
            public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
                LevelList.this.setSelected(this);
                return super.mouseClicked(event, doubleClick);
            }
        }
    }
}