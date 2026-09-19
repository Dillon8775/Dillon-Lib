# Version 1.2

- Add ```Links``` class.
- ```ClientTasks.drawModInfo``` now calculates the size of your mod version text and automatically determines how to place the text and logo on a screen.
- Renamed ```ClientTasks.tryOpenYaclScreen``` to ```ClientTasks.tryOpenConfigScreen``` and added a ```ModConfigLib``` parameter, to include which mod needs to be loaded to open a config screen.
- Added ```ModConfigLib``` record, which takes in a Component ```libName``` as the literal mod's library name to display, and a ```ModReference``` to check if the library is loaded from mod id.
- Removed ```LogoWidth``` enum, no need for it anymore.
- Remove ```logoWidth``` method from ModPlatform.
- Remove ```logger``` method from ModPlatform.
- Rename ```@NotNull``` annotations from object-returned methods.
- Rename PlatformRelease to ```Release```, and rename PlatformName to ```Platform```.
  - "platformRelease()" method in ModPlatform is now named ```release()```.
  - "platformName()" method in ModPlatform is now named ```platform`()``.
- Reorder methods in ModPlatform.
- Add doc comment to ```modVersion``` method in ModPlatform to use ```CommonModPlatform.commonModVersion()```.
- Removed deprecated ```RecipeSerializerFactory```.