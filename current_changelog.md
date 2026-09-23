# Version 1.2

- Introduce ```DillonLibScreen``` interface, a basic interface which can be implemented on new screen classes to make creating screens easier. Acts like a normal options/menu screen class, with more functionality and expansion with the use of ```ScreenBuilder```.
- Introduce ```BasicDillonLibScreen``` (an instance of Screen), and ```DillonLibMenuScreen``` (an instance of OptionsSubScreen). Acts like a normal screen class, with more functionality and expansion with the use of ```ScreenBuilder```.
- Introduce ```ScreenBuilder```, a utility class, which must be constructed in ```BasicDillonLibScreen``` and ```DillonLibMenuScreen```, and can be used as a literal screen builder.
- Introduce ```WidgetData```, which stores custom widget data like if the widget should be active, an active/inactive tooltip, and a custom x/y pos.

---

- ```ClientTasks.drawModInfo``` now calculates the size of your mod version text and automatically determines how to place the text and logo on a screen.
- Renamed ```ClientTasks.tryOpenYaclScreen``` to ```ClientTasks.tryOpenConfigScreen``` and added a ```ModConfigLib``` parameter, to include which mod needs to be loaded to open a config screen.

---
- Added ```ModConfigLib``` record, which takes in a Component ```libName``` as the literal mod's library name to display, and a ```ModReference``` to check if the library is loaded from mod id.
- ```ModReference```s and ```ModConfigLib```s are now created using ```of``` methods, and constructors are now private.
- ```PlatformMenuButton```s constructor is now also private, and new buttons are creating using ```of``` methods, as well as additional methods added into the class, to make creating buttons easier.
- Moved ```ClientTasks.createSpriteIconButton``` to ```UpdatableSpriteButton.createSpriteIconButton```, UpdatableSpriteButton constructors are now private.
- ```PredicateEntry``` constructor is now private, now created with ```of``` methods, and added more methods to make creating predicates easier.

---
- Removed ```LogoWidth``` enum, no need for it anymore.
- Remove ```logoWidth``` method from ModPlatform.
- Remove ```logger``` method from ModPlatform.
- Rename ```@NotNull``` annotations from object-returned methods.
- Rename PlatformRelease to ```Release```, and rename PlatformName to ```Platform```.
  - "platformRelease()" method in ModPlatform is now named ```release()```.
  - "platformName()" method in ModPlatform is now named ```platform`()``.
- Reorder methods in ModPlatform.

---
- Move DillonLib core config to utilize Balm's config system.
- Add ```Links``` class.
- Add doc comment to ```modVersion``` method in ModPlatform to use ```CommonModPlatform.commonModVersion()```.
- Rename ```PredicateSigned``` annotation to ```Predicated```.
- Removed deprecated ```RecipeSerializerFactory```.

---
- Added a screen to directly open the config file.
- Fixed DillonLib config not loading upon game launch.