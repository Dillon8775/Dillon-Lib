# Version 1.2.5

- All of Dillon's mods must be updated to their latest version because of this update, or your game will crash.


- Remove `ConditionalTooltip`, replace with a Component function as `Function<Boolean, Component>`, where the `boolean` is the button's active state.
- Remove `PlatformGetter` class, and move `getDillonLibPlatform` method to `Platforms` class.
- Remove all [Speedrunner Mod](https://modrinth.com/mod/speedrunner-mod) sound files, as this project has been discontinued and only works on DillonLib version 1.2.4.
- Split `DillonLibOptions` into different categories (`Core` and `Factories`).
- Added `LevelSelectorScreen`, with a consumable action to do something on world/server selection.
- Added `"Search..."`, `"Confirm"`, `"Open File"` and `"Open Directory"` texts.
- Make `UpdateChecker` allow parsing for `.`s and `-`s.