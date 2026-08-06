[![DillonLib Logo.](https://github.com/Dillon8775/ImageGIFs/blob/universal/9i8dnw.gif?raw=true)](https://youtu.be/CurMwVkAqWo)

# Base Library/API for Dillon's mods.

Cross-platform API providing resuable utilities, such as platform abstraction, mixin management, configuration systems, factories, and other common modding utilities.

---

## Developer Notes

Provides all information required to create a mod on multiple modding platforms (ex. Fabric, Forge and NeoForge).

Provides other resources, including:
- Factories, which auto-registers certain behaviors for certain types of objects
- Memory-stored player data, based on UUID
- Easy-to-use mixin predicates
- Scheduled tasks that run within the Minecraft server tick
- Simple permissions getter
- Tick calculators
- A clean options system
- Common texts
- Common client-side operations and modern-styled widget lists

DillonLib does not require any other outside resources or mods, other than [Fabric API](https://modrinth.com/mod/fabric-api) for fabric users.

You can view the **[source code](https://github.com/Dillon8775/Dillon-Lib)** for all features and utilities, as well as read documentation below.

### To use this library with your mod, add this to your `build.gradle` file:

```
repositories {
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = "https://api.modrinth.com/maven"
            }
        }
        filter {
            includeGroup "maven.modrinth"
        }
    }
}

dependencies {
    implementation "maven.modrinth:dillon-lib:*DL_version*"
    
    // Note: for *DL_version*, make sure you use the correct MC and platform (fabric/neoforge/forge) version.
    // You can get it by opening the specified version and copying the end of the modrinth link.
    // Example: https://modrinth.com/mod/dillon-lib/version/5voax3wu <- use "5voax3wu" as the *DL_version*!
}
```

### For multi-loader mods (fabric x (neo)forge), in your *common* directory, you must add this:
```
compileOnly "maven.modrinth:dillon-lib:${project.*DL_version* /*<- can either be Fabric or NeoForge, but I recommend fabric*/}"
```
Then, in your other modules (ex. fabric or neoforge), add the normal implementation block provided above for your specified module.

---
# API Documentation

---

# Platform Abstraction
This API provides a versatile way to store a mod's basic information with the help of a built-in Java feature called Service Loaders.

You can think of a service loader as your mod's base entrypoint, or table of contents. The service loader contains information like your mod version, mod ID, platform name (fabric, neoforge, forge, or other), platform release (stable, beta or alpha), and other useful utilities.

DillonLib provides three different service loaders for you to create. One for your base entrypoint ```(called ModPlatform.class)```, one specifically designed for mixins ```(called MixinModPlatform.class)```, and one for client-side operations ```(called ClientModPlatform)```.

You technically do not need to create all platforms. Each platform contains necessary information required to register certain things.

[Click here to view ModPlatform.class](https://github.com/Dillon8775/Dillon-Lib/blob/mc26.2/common/src/main/java/net/dillon/dillonlib/platform/ModPlatform.java).

[Click here to view ClientModPlatform.class](https://github.com/Dillon8775/Dillon-Lib/blob/mc26.2/common/src/main/java/net/dillon/dillonlib/platform/client/ClientModPlatform.java).

[Click here to view MixinModPlatform.class](https://github.com/Dillon8775/Dillon-Lib/blob/mc26.2/common/src/main/java/net/dillon/dillonlib/platform/mixinsafe/MixinModPlatform.java).

These platforms are designed to work on projects that are coded on multiple mod loaders, so each mod loader should have its own platform classes.

To properly create your service loader, simply create instances of these platform classes, and fill in your information.

Then, in your mods ```resources``` folder, create a directory called `META-INF/services`, and then create the following files (depending on which platforms you are using), with these exact names:

```
net.dillon.dillonlib.platform.client.ClientModPlatform
net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform
net.dillon.dillonlib.platform.ModPlatform
```

In each of these files, you should enter in the path to *your* platform. For example, inside of ```net.dillon.dillonlib.platform.ModPlatform``` should be:
```
net.dillon.quesoexample.platform.QuesoExamplePlatformImpl
```

If you are extending a platform with a custom platform, ex. MyModPlatform extends ModPlatform, if your custom platform *only* has methods that are inherited from an original platform class, you must specify your *extended platform* in the file. However, if you have a custom platform with custom methods that are not inherited from the original platform class, you must specify your extended platform in the file *and additionally* create a new file, pointing to your extended platform class, and specify it there too.

For example:
```
package com.me.mymod

public class MyPlatform extends ModPlatform {

  public void myCustomMethod(String args) {
  }
}
```
You must call *this class* in the file named ```net.dillon.dillonlib.platform.ModPlatform``` *and* a file called ```com.me.mymod.MyPlatform```

Then you should create getter methods to get each of your platforms. [View an example of this here.]([https://github.com/Dillon8775/Dillon-Lib/tree/mc26.2/fabric/src/test/java/net/dillon/quesoexample/platform](https://github.com/Dillon8775/Dillon-Lib/blob/mc26.2/fabric/src/main/java/net/dillon/dillonlib/platform/FabricPlatforms.java))

There are three different platforms to ensure that Minecraft launches safely. We don't want client-side interfering with common code, and we also don't want classes to be loaded too early during mixin initialization, which is why we have a separate platform called ```MixinModPlatform```.

---

# Mixin Plugin Util
Mixins are high in causing mod incompatibility, and that is what the ```MixinPluginUtil``` class is for. It allows you to easily control if mixins certain should be applied at initialization or not.

[Click here to view MixinPluginUtil.class](https://github.com/Dillon8775/Dillon-Lib/blob/mc26.2/common/src/main/java/net/dillon/dillonlib/mixinplugin/MixinPluginUtil.java).

[Example use of MixinPluginUtil](https://github.com/Dillon8775/Dillon-Lib/blob/mc26.2/common/src/main/java/net/dillon/dillonlib/core/DillonLibMixinPluginUtil.java).

Once you create your ```MixinPluginUtil```, you can use it like this in your ```IMixinConfigPlugin```:

```
public class ConditionalMixinPlugin implements IMixinConfigPlugin {

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        DillonLibMixinPluginUtil mixinPluginUtil = new DillonLibMixinPluginUtil();
        return !mixinPluginUtil.shouldNotApply(targetClassName, mixinClassName);
    }
```

---

# Update Checker
This mod comes with a built-in update checker, to easily add implementation to see if your mod has an update available for users. View the class [here.](https://github.com/Dillon8775/Dillon-Lib/blob/mc26.2/common/src/main/java/net/dillon/dillonlib/util/UpdateChecker.java)

All you have to do is create a simple static boolean expression somewhere in your mod's common code (or a method that returns a boolean). For example:

```
public static final boolean HAS_UPDATE = UpdateChecker.hasUpdate(UpdateChecker.checkForUpdate(
    "*your_modrinth-project-slug*", // ex. "dillonlib"
    "*your_current_mod_version_as_a_string" // ex. "ModConstants.CURRENT_MOD_VERSION"
))
```

Then, you can use this expression anywhere to run code if your mod has an update. The mod also comes with built-in update checker features for your convenience. For example, when a player joins a world/server, you can call this method from the ```CommonTasks class``` to inform the user that they should update their mod:

```
public static void sendUpdateMessage(Player player, Component modName, String linkToUpdate, int textColor)
```

This will send the message to the player, which is interactive.

```modName``` - how your mod name should be displayed in chat.

```linkToUpdate``` - where the user should be directed when they click the message in chat.

```textColor``` - the general text color of the entire chat message (does not affect the ```modName``` color)

Additionally, you can call these methods from the ```ClientTasks class``` to render an "update indicator" sprite when your mod has an update (this uses the same texture that [Mod Menu](https://modrinth.com/mod/modmenu) uses when it checks for mod updates):

```
// Draws the update sprite at a certain position on a screen
public static void drawUpdateSprite(GuiGraphicsExtractor graphics, int x, int y)

// OR:

// Draws the update sprite on a button with fixed position
// "hasUpdate" paramter is simply your HAS_UPDATE expression
public static void renderUpdateIconOnButton(GuiGraphicsExtractor graphics, SpriteIconButton button, boolean hasUpdate)
```

---

# Factories
Factories allow you to create certain objects, such as items, boats and keybinds without the headache of registering miscellaneous behaviors for those items.

As seen in the ```MixinModPlatform``` class, **in order to use factories, you must override the "shouldApplyFactories" method in your MixinModPlatformImpl!** Otherwise, the factories will not do what they are supposed to do.

Typically, for example, when you create a ShearsItem, you have to manually mixin certain classes to allow the shears to work properly on certain entities and blocks. Additionally, the shears don't work correctly as a shear predicate on certain loot tables, such as leaves and grass, and their dispenser behavior is never accounted for.

Factories handles this type of stuff for you. All you have to do is create an instance of, in this example, ShearsFactory, and the rest of the behavior will automatically be registered for you.

Here is an example:

```
public static final Item QUESO_SHEARS = ItemsInvoker.registerModItem(QuesoItemIds.QUESO_SHEARS, ShearsFactory::new, new Item.Properties()
            .component(DataComponents.TOOL, ShearsItem.createToolProperties())
            .rarity(Rarity.UNCOMMON)
            .durability(150)
    );
```

You don't have to do anything else with your shears after this. Everything else is automatically registered!

You can use factories with the following objects:
- Shears
- Flint and Steels
- Any "ignitable" item, which ignites or creates fire on certain blocks
- Bows and crossbows
- Boats
- Shields
- Totems of Undying
- Keybinds
- Item groups

## Boat Factories
Boat factories are a special type of factory that hold "BoatData", which contains all information required for the boat.

The special part about boat factories is that model registration is automatically created for you. All you have to do is create your boat texture and place it in the correct location!

Here is an example of two boat factories, one chest boat, and one non-chest boat:

```
// QuesoItems.class (boat items)
public static final Item QUESO_BOAT = ItemsInvoker.registerModItem(QuesoItemIds.QUESO_BOAT, properties -> new BoatItem(QuesoEntityTypes.QUESO_BOAT, properties));

public static final Item QUESO_CHEST_BOAT = ItemsInvoker.registerModItem(QuesoItemIds.QUESO_CHEST_BOAT, properties -> new BoatItem(QuesoEntityTypes.QUESO_CHEST_BOAT, properties));

// QuesoEntityTypes.class (boat entities)
public static final EntityType<Boat> QUESO_BOAT = Factories.registerBoatFactory(
            Identifier.fromNamespaceAndPath(QuesoExampleMod.MOD_ID, "queso_boat"), () -> QuesoTestItems.QUESO_BOAT, false);

    public static final EntityType<ChestBoat> QUESO_CHEST_BOAT = Factories.registerBoatFactory(
            Identifier.fromNamespaceAndPath(QuesoExampleMod.MOD_ID, "queso_chest_boat"), () -> QuesoTestItems.QUESO_CHEST_BOAT, true);
```

You should register the boat entities and items in different classes, failure to do so could result in a compilation error.

Method signature(s) for registering boat factories:
```
// Typically, you would always use this method
<T extends AbstractBoat> EntityType<T> registerBoatFactory(Identifier id, Supplier<Item> dropItem, boolean chest)

// You should only use this method if you want a custom EntityFactory for your boat (in most cases, you don't)
<T extends AbstractBoat> EntityType<T> registerBoatFactory(Identifier id, Supplier<Item> dropItem, EntityType.EntityFactory<T> factory, boolean chest)
```

## Other Factories
For other factories, registering them is similar to these other factories. Take a deeper look at this [Example Items Class](https://github.com/Dillon8775/Dillon-Lib/blob/mc26.2/fabric/src/test/java/net/dillon/quesoexample/item/QuesoTestItems.java) for a better understanding of how to create your factories.

- For shear factories, dispenser behavior, block predicates and use-on entities is automatically registered.
- For flint and steel and ignitable factories, dispenser behavior and use-on entities is automatically registered.
- For bow and crossbow factories, player model and Fov modification is automatically registered.

---

# Learn More

**DillonLib has many other modding utilities at hand,** including simple math operations, modern list widgets, a clean configuration system, UUID-based player storage, task scheduling, and more! Please view the [source code](https://github.com/Dillon8775/Dillon-Lib) if you want to learn more.

Please make sure that your current version of DillonLib is kept up-to-date for all the latest features and utilities.

Report any issues you may find [here,](https://github.com/Dillon8775/Dillon-Lib/issues) such as game crashes, inconsistencies, or a feature request.

---

Created and regularly maintained by: [Dillon8775](https://www.youtube.com/@dillon8775).

#### All rights reserved unless explicitly stated.