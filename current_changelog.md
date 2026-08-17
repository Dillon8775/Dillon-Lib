# Version 1.1

- Update to 26.3.
- Add PlatformMenuButton: Easily add your mod menu button to the title and pause screen under certain conditions.
- Add UpdatableSpriteButton.
- Add DillonLibModReferences.
- Add KeybindScrollHelper (+ KeybindsScreenAccessor).
- Add DebugScreenEntriesInvoker.
- ClientModPlatform:
    - Add overridable "menuButtons" method to configure PlatformMenuButtons.
- ClientTasks:
    - Add "drawModInfo" - draws mod information in a specified menu screen.
    - Add "tryOpenYaclScreen" - tries to open a [YetAnotherConfigLib](https://modrinth.com/mod/yacl) configuration screen, and warns the user if the mod is not installed