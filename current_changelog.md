# Version 1.0 | Initial Release

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

---
# API Documentation

---

# Platform Abstraction
This API provides a versatile way to store your mod's basic information with the help of a built-in Java feature called Service Loaders.

You can think of a service loader as your mod's base entrypoint, or table of contents. The service loader contains information like your mod version, mod ID, platform name (fabric, neoforge, forge, or other), platform release (stable, beta or alpha), and other useful utilities.

DillonLib provides three different service loaders for you to create. One for your base entrypoint ```(called ModPlatform.class)```, one specifically designed for mixins ```(called MixinModPlatform.class)```, and one for client-side operations ```(called ClientModPlatform)```.

You technically do not need to create all platforms. However, you should. Each platform contains necessary information required to register certain things.

Here is a look at ```ModPlatform.class```, the base service loader for your mod:

```
// A platform helper class. Can be used for various different calls and functions, and new methods can easily be added in the mod's specified platform.
// Each platform for your mod (ex. fabric and neoforge) should contain {@code three service loaders}, one for this class, one for ClientModPlatform (for client-side only access code), and one for the MixinModPlatform, which is a mixin-safe class for methods that can be used in conditional mixin plugins.
public abstract class ModPlatform implements Loadable {

    // Returns the mod id for this platform. Should return your mod id from your common mod class.
    @Override
    public abstract String modId();

    // Returns the logger for your mod.
    public abstract @NotNull Logger logger();

    // Returns your mod version.
    public abstract String modVersion();

    // Returns the PlatformName (fabric, neoforge, forge, etc.)
    public abstract @NotNull PlatformName platformName();

    // Returns the PlatformRelease (stable/release, beta, or alpha).
    public abstract @NotNull PlatformRelease platformRelease();

    // Returns the LogoWidth. Used specifically in Dillon's mods, but you can branch your own logic with this if you'd like. Otherwise this doesn't do much.
    public abstract @NotNull LogoWidth logoWidth();

    // Returns if a packet in your mod can be sent. It's helpful to check if a packet can be sent when joining a server that doesn't have your mod installed, which can inform the user of mod incompatibility. If you don't have a reason to check if a packet can be sent in your mod, you can just return false here.
    public abstract boolean canSendPacket(LocalPlayer localPlayer);

    // Registers events, that should be on all environments. Be careful with this method, because you don't want to register events in the wrong place.
    public void registerEvents() {
    }

    // Registers commands that should be on all environments.
    public void registerCommonCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
    }

    // Registers client-side only commands.
    public void registerClientCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
    }

    // Registers server-side only commands.
    public void registerServerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
    }
}
```

```ClientModPlatform.class```, the client-side entrypoint for your mod:

```
// An instance of Loadable. Similar to ModPlatform, all methods used inside this class should be client-side only methods, meaning they should not interfere with the common code.
@Dill(DillType.CLIENT)
public abstract class ClientModPlatform implements Loadable {

    // Returns the mod id for this client platform. Should return your mod id from your common mod class.
    @Override
    public abstract String modId();

    // Registers a KeyMapping into the game. This should point to your platform's way of registering a keybind.
    public abstract KeyMapping createKeyMapping(String name, InputConstants.Type type, KeyMapping.Category category, int value);
}
```

```MixinModPlatform.class```, the mixin platform for your mod:

```
// A separate service loader for mixin-safe classes and plugins, to prevent unknown and unexpected crashes when reading other Minecraft files during game initialization and checking mixins. This class should only be used within mixin plugins to ensure stability.
public abstract class MixinModPlatform implements Loadable {

    // Returns the mod id for this platform. Should return your mod id from your common mod class.
    @Override
    public abstract String modId();

    // Returns if a mod is loaded on a specific platform. You can use this in {@link MixinPluginUtil}s.
    public abstract boolean isModLoaded(ModReference mod);

    // Specifies if factories should be applied. If any instance of {@link MixinModPlatform} returns {@code true} here, unless a mixin is manually disabled, factories will be applied.
    // Returns false by default because not all of Dillon's mods use factories. This returns false by default as a safety feature in order to prevent mod incompatibility with other mods when factories aren't even being used.
    // You must override this to return true if you plan on using Factories!
    public boolean shouldApplyFactories() {
        return false;
    }
}
```

These platforms are designed to work on projects that are coded on multiple mod loaders, so each mod loader should have its own platform classes.

To properly create your service loader, simply create instances of these platform classes, and fill in your information.

Then, in your mods ```resources``` folder, create a directory called `META-INF/services`, and then create the following three files, with these exact names:

```
net.dillon.dillonlib.platform.client.ClientModPlatform
net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform
net.dillon.dillonlib.platform.ModPlatform
```

In each of these files, you should enter in the path to *your* platform. For example, inside of ```net.dillon.dillonlib.platform.ModPlatform``` should be:
```
net.dillon.quesoexample.platform.QuesoExamplePlatformImpl
```

Then you should create getter classes to get each of your platforms. For example:

```
public class QuesoExamplePlatformGetter {
    private static final ModPlatform PLATFORM = PlatformLoader.load(ModPlatform.class, QuesoExampleMod.MOD_ID);

    // This will return your ModPlatform. You will be accessing this a lot if you plan to use DillonLib.
    public static ModPlatform get() {
        return PLATFORM;
    }
}
```

These platforms are split into three to ensure that Minecraft launches safely. We don't want client-side interfering with common code, and we also don't want classes to be loaded too early during mixin initialization, which is why we have a separate platform called ```MixinModPlatform```.

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
  - In *1.21.11 and below,* dispenser behavior must be done *manually* for this factory.
- Any "ignitable" item, which ignites or creates fire on certain blocks
- Bows and crossbows
- Boats *(1.21.11 and above only)*
- Shields
- Totems of Undying
- Keybinds

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

## Totem Factories
Totems are hard to duplicate in modding. Totem factories make this process a lot easier. All you have to do is create an instance of TotemFactory, and your totem will work as a normal totem of undying, like normal.

Additionally, you can create custom totem logic that is applied when your totem is used. For example:

```
public class QuesoTotemFactory extends TotemFactory {

    public QuesoTotemFactory(Properties properties, DeathProtection protection, ParticleOptions particle, int particleLifeTime) {
        super(properties, protection, particle, particleLifeTime);
    }

    // This method is called when your totem is used. See the original TotemFactory for the super method
    // If you don't want custom logic, you don't have to override this method
    @Override
    public void invokeTotemUse(LivingEntity living, ItemStack stack, DamageSource source) {
        /* your custom logic goes here */
    }
}

// QuesoItems.class (create the totem factory)
 public static final Item QUESO_TOTEM = ItemsInvoker.registerModItem(QuesoItemIds.QUESO_TOTEM, properties -> new QuesoTotemFactory(properties, DeathProtection.TOTEM_OF_UNDYING, ParticleTypes.ANGRY_VILLAGER, 30));
```

As you can see, you can set the particles that appear when using the totem, and set the particle lifetime (the last parameter).

There are two constructors for TotemFactories:

```
// Default factory, which uses default particles and particle lifetime
public TotemFactory(Properties properties, DeathProtection protection)

// Custom factory
public TotemFactory(Properties properties, DeathProtection protection, ParticleOptions particle, int particleLifeTime)
```

## Other Factories
For other factories, registering them is similar to these other factories. Take a deeper look at this [Example Items Class](https://github.com/Dillon8775/Dillon-Lib/blob/mc26.2/fabric/src/test/java/net/dillon/quesoexample/item/QuesoTestItems.java) for a better understanding of how to create your factories.

- For shear factories, dispenser behavior, block predicates and use-on entities is automatically registered.
- For flint and steel and ignitable factories, dispenser behavior and use-on entities is automatically registered.
- For bow and crossbow factories, player model and Fov modification is automatically registered.

---
# Mixin Plugin Util
Mixins are high in causing mod incompatibility, and that is what the ```MixinPluginUtil``` class is for. It allows you to easily control if mixins certain should be applied at initialization or not.

Here is a look at ```MixinPluginUtil.class```:

```
// Simple utility class for org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin. You can use this to easily disable mixins on certain conditions with the help of PredicateEntries
// logger - the logger to be used specifically for the mixin plugin.
// Do not use an outside logger, as you risk Minecraft crashing due to pre-mature load time.
// mixinDirectory - the mixin directory to be checked with the plugin. You can have the method return blank or "" if you want to specifically all mixin class names by their full package name. However, it's easier to specify the package name with this method, and then simply input the mixin classes names.
// entries - the list of PredicateEntries to check for each mixin loaded at load time. If the predicate entry boolean returns true, the list of mixins in the entry will be disabled at load time.
public abstract class MixinPluginUtil {

    // Returns the logger for a mixin plugin. It should only be used within the conditional mixin plugin.
    public abstract Logger logger();

    // Returns the mixin directory used to check for mixins.
    // Example: "net.dillon.dillonlib.mixin."
    public abstract String mixinDirectory();

    // Returns the list of predicates, mixin class names and message reasons for mixins that should be disabled.
    public abstract List<PredicateEntry> entries();

    // Returns false if mixin should not apply. It is not recommended to override this method, as this provides the full functionality for {@link PredicateEntry}.
    public boolean shouldNotApply(String targetClassName, String mixinClassName) {
        for (PredicateEntry entry : entries()) {
            if (entry.reason().isEmpty() || entry.reason().isBlank()) {
                throw new IllegalStateException("Mixin predicate entry reason cannot be blank!");
            }

            if (entry.condition()) {
                for (String s : entry.mixins()) {
                    String name = this.mixinDirectory() + s;
                    if (name.equals(mixinClassName)) {
                        message(entry, mixinClassName, targetClassName);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // The message that is sent to the console. This can be over-ridden if you wish to change the message.
    public void message(PredicateEntry entry, String mixinClassName, String targetClassName) {
        sendMessage(entry, "Skipping mixin {} for class {}: {}", mixinClassName,
                targetClassName,
                entry.reason());
    }

    // Sends a console message to the user, based on the {@link MessageType}.
    public void sendMessage(PredicateEntry entry, String message, Object... arguments) {
        if (CommonPlatformGetter.get().isDevelopmentEnvironment()) {
            logger().warn(message, arguments);
        } else {
            switch (entry.messageType()) {
                case WARN -> logger().warn(message, arguments);
                case DEBUG -> logger().debug(message, arguments);
                case ERROR -> logger().error(message, arguments);
                default -> logger().info(message, arguments);
            }
        }
    }
}
```

Here is an example of a ```MixinPluginUtil```:

```
public class DillonLibMixinPluginUtil extends MixinPluginUtil {

    @Override
    public Logger logger() {
        return LoggerFactory.getLogger("DillonLib/Mixin");
    }

    @Override
    public String mixinDirectory() {
        return "net.dillon.dillonlib.mixin.";
    }

    @Override
    public List<PredicateEntry> entries() {
        return List.of(
                new PredicateEntry(
                        new String[]{"client.fix.bow.AbstractClientPlayerFix"},
                        !DillonLibOptions.getLibInstance().applyAbstractClientPlayerFix || !MixinPlatformGetter.shouldApplyFactories(),
                        ofFactory("\"apply_abstract_client_player_fix\" is disabled."),
                        MessageType.DEBUG
                ),
                new PredicateEntry(
                        new String[]{"client.fix.bow.AvatarRendererFix"},
                        !DillonLibOptions.getLibInstance().applyAvaterRendererFix || !MixinPlatformGetter.shouldApplyFactories(),
                        ofFactory("\"apply_avatar_renderer_fix\" is disabled."),
                        MessageType.DEBUG
                ),
                new PredicateEntry(
                        new String[]{
                                "client.fix.bow.ItemInHandRendererFix",
                                "client.fix.bow.FabricItemInHandRendererFix"
                        },
                        !DillonLibOptions.getLibInstance().applyItemInHandRendererFix || !MixinPlatformGetter.shouldApplyFactories(),
                        ofFactory("\"apply_item_in_hand_renderer_fix\" is disabled."),
                        MessageType.DEBUG
                ),
                new PredicateEntry(
                        new String[]{
                                "ignitable.CandleCakeBlockFix",
                                "ignitable.TntBlockFix"
                        },
                        !DillonLibOptions.getLibInstance().applyIgnitableFactories || !MixinPlatformGetter.shouldApplyFactories(),
                        ofFactory("\"apply_ignitable_factories\" is disabled."),
                        MessageType.DEBUG
                ),
                new PredicateEntry(
                        new String[]{
                                "shear.BeehiveBlockFix",
                                "shear.CopperGolemFix",
                                "shear.MatchToolFix",
                                "shear.MushroomCowFix",
                                "shear.FabricBoggedFix",
                                "shear.FabricLeashFenceKnotEntityFix",
                                "shear.FabricPumpkinBlockFix",
                                "shear.FabricSheepFix",
                                "shear.FabricSnowGolemFix",
                                "shear.FabricTripWireBlockFix"
                        },
                        !DillonLibOptions.getLibInstance().applyShearFactories || !MixinPlatformGetter.shouldApplyFactories(),
                        ofFactory("\"apply_shear_factories\" is disabled."),
                        MessageType.DEBUG
                ),
                new PredicateEntry(
                        new String[]{"entity.fix.SulfurCubeFix"},
                        (!DillonLibOptions.getLibInstance().applyShearFactories && !DillonLibOptions.getLibInstance().applyIgnitableFactories) || !MixinPlatformGetter.shouldApplyFactories(),
                        ofFactory("\"apply_shear_factories\" and \"apply_ignitable_factories\" is disabled."),
                        MessageType.DEBUG
                ),
                new PredicateEntry(
                        new String[]{
                                "client.ClientPacketListenerMixin",
                                "entity.LivingEntityMixin"
                        },
                        !DillonLibOptions.getLibInstance().applyTotemFactories || !MixinPlatformGetter.shouldApplyFactories(),
                        ofFactory("\"apply_totem_factories\" is disabled."),
                        MessageType.DEBUG
                )
        );
    }

  
    private static String ofFactory(String s) {
        return "Factories are disabled, or " + s;
    }
```

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

# Learn More

**DillonLib has many other modding utilities at hand,** including simple math operations, modern list widgets, a clean configuration system, UUID-based player storage, task scheduling, and more! Please view the [source code](https://github.com/Dillon8775/Dillon-Lib) if you want to learn more.

Please make sure that your current version of DillonLib is kept up-to-date for all the latest features and utilities.

Report any issues you may find [here,](https://github.com/Dillon8775/Dillon-Lib/issues) such as game crashes, inconsistencies, or a feature request.

---

Created and regularly maintained by: [Dillon8775](https://www.youtube.com/@dillon8775).

#### All rights reserved unless explicitly stated.