# Version 1.3

- Updated default `UpdatableSpriteButton` constructor and added options for custom button size.
- Updated `PlatformMenuButton` - does not have pause or title conditions anymore.
- All `PlatformMenuButton`s are now placed inside a new screen called `Dillon's Mods`.
- The `Dillon's Screen` mod is accessible via the title and pause screen through a button with a purple cape. This button can be disabled via the DillonLib config.
- Updated the `Update Available` sprite to match Dillon's color theme.
- Updated `ClientTasks` menu button methods, and moved them to a new class called `CommonWidgets`.
- Removed `UpdatableSpriteButton` builder methods, you must either use its constructor (now public), or `ClientTask`s methods.