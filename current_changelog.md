# Version 1.3

## Menu Button Changes
- `PlatformMenuButton` does not have pause or title conditions anymore.
- All `PlatformMenuButton`s are now placed inside a new screen called `Dillon's Mods`.
- The `Dillon's Screen` mod is accessible via the title and pause screen through a button with a purple cape. This button can be disabled via the DillonLib config.

### Removed ScreenBuilder, use Minecraft's built-in GridLayout.

### New Class Locations and Names
- *Renamed* Texts class -> `DillonsTexts`, CommonSprites -> `DillonsSprites`.
- *Renamed* DillType class -> `DillEnv` (`DillType` is now a new `@Dill` annotation).
- `DillType` now specifies whether the annotated classes should only be used for DillonLib or if can be used elsewhere. Generally, you would never change this value.
- Moved `DillonsSprites` and `DillonsTexts` to a new directory called `widespread`.
    - (See below) `DillonsWidgets` is also located in this directory.
- Moved `UpdatableSpriteButton` to the `screen` directory.
- Moved all screen classes to a `client.screen` directory.
- Added multiple `builder` classes for creating various types of buttons.
- *Removed* all `blit` and `draw` methods in `ClientTasks`, replaced with `TextureBuilder`.

### Sprites
- Updated the `Update Available` sprite to the new vanilla texture.
- *Removed* container sprites (only used by the now discontinued [Speedrunner Mod](https://modrinth.com/mod/speedrunner-mod)).

### Other Notes
- *Deprecated* most factories.
- `ModPlatform.modVersion` now returns `CommonModPlatform's common mod version`, so it does not need to be overriden by default.
- `ClientModPlatform.canSendPacket` now returns `false` by default and does not need to be overriden.
- Updated `ClientTasks` menu button methods, and moved them to a new class called `DillonsWidgets`.
- Updated default `UpdatableSpriteButton` constructor, added options for custom button size and sprite size.
- *Removed* built-in `UpdatableSpriteButton` builder methods, you must either use its constructor (now public), or `ClientTask`s methods.
- All of Dillon's mods will need to be updated when installing this version.
- Added `getSoundManager` method to `ClientTasks`.
- Added `isCurrentLevelNull` method to `ClientTasks`.
- Added several methods for playing `SimpleUISounds` in `ClientTasks`.

### Bugs Fixed
- Fixed several issues with new update checker introduced in a previous version.