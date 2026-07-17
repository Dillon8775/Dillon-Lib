[![DillonLib Logo.](https://github.com/Dillon8775/ImageGIFs/blob/universal/9i8dnw.gif?raw=true)](https://youtu.be/CurMwVkAqWo)

# Library for Dillon's mods.

---

## Developer Notes

Provides all information required to create a mod on multiple modding platforms (ex. Fabric, Forge and NeoForge).

Provides other miscellaneous resources, including:
- Memory-stored player data, based on UUID
- Tick calculators
- Easy-to-use mixin predicates
- Scheduled tasks that run within the Minecraft server tick
- A clean options system
- Common texts
- Common client-side operations

DillonLib does not require any other outside resources or mods, other than [Fabric API](https://modrinth.com/mod/fabric-api) for fabric users.

You can view the **[source code](https://github.com/Dillon8775/Dillon-Lib)** for reference.

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

Created and regularly maintained by: [Dillon8775](https://www.youtube.com/@dillon8775).

#### All rights reserved unless explicitly stated.