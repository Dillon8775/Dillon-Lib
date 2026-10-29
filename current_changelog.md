# Version 1.3

## Menu Button Changes
- `PlatformMenuButton` does not have pause or title conditions anymore.
- All `PlatformMenuButton`s are now placed inside a new screen called `Dillon's Mods`.
- The `Dillon's Screen` mod is accessible via the title and pause screen through a button with a purple cape. This button can be disabled via the DillonLib config.

### New Class Locations and Names
- *Renamed* Texts class -> `WidespreadTexts`, CommonSprites -> `WidespreadSprites`.
- Moved `WidespreadSprites` and `WidespreadTexts` to a new directory called `widespread`.
    - (See below) `WidespreadWidgets` is also located in this directory.

### Sprites
- Updated the `Update Available` sprite to match the Dillon color theme.
- *Removed* container sprites (only used by the now discontinued [Speedrunner Mod](https://modrinth.com/mod/speedrunner-mod)).

### Miscellaneous
- Updated `ClientTasks` menu button methods, and moved them to a new class called `WidespreadWidgets`.
- Updated default `UpdatableSpriteButton` constructor, added options for custom button size and sprite size.
- *Removed* built-in `UpdatableSpriteButton` builder methods, you must either use its constructor (now public), or `ClientTask`s methods.
- All of Dillon's mods will need to be updated when installing this version.