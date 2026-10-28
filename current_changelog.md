# Version 1.3

- Updated default `UpdatableSpriteButton` constructor and added options for custom button size.
- Updated `PlatformMenuButton` - does not have pause or title conditions anymore. All menu buttons are now placed inside a universal screen called `Dillon's Mods`.
- All registered `PlatformMenuButtons` will be placed inside the `Dillon's Mods` screen.
- Completely changed `ClientTasks` menu button methods.
- Removed `titleCondition` and `pauseCondition` from menu buttons, as they are never placed on the title or pause screen - only the new Dillon's Mods screen.
- Moved `ClientTasks` menu button methods to a new class called `CommonWidgets`, which contains helpful widgets.
- Removed `UpdatableSpriteButton` builder methods, you must either use 