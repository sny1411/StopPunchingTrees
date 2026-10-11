# StopPunchingTrees

A small Hytale server plugin: players can no longer break wood with their bare
hands. They need a tool, like a hatchet, to harvest it.

Which blocks need a tool is configurable, so the same rule can be applied to
stone, ores or any other kind of block.

## Compatibility

| Plugin | Hytale server |
|--------|---------------|
| 1.1.0  | 0.6.8 (Update 6) and later, also compiles against the 0.7.0 pre-releases |
| 1.0    | Built for servers before Update 6 |

## Installation

1. Download `StopPunchingTrees-<version>.jar`.
2. Put it in the `mods` folder of your server or world.
3. Restart the server.

## Configuration

On first start the plugin writes
`mods/sny1411_StopPunchingTrees/config.json`:

```json
{
  "ToolRequiredGatherTypes": ["Woods"]
}
```

`ToolRequiredGatherTypes` lists the gather types of the blocks that cannot be
broken without a tool. The gather type is the `Gathering.Breaking.GatherType`
field of a block asset. Holding any item that is a tool lets the player hit the
block; the game then decides as usual whether that tool can actually break it.

Some gather types used by the base game:

| Gather type | Blocks |
|-------------|--------|
| `Woods` | Logs and other wooden blocks |
| `Rocks`, `VolcanicRocks` | Stone |
| `Soils` | Dirt and similar blocks |
| `OreCopper`, `OreIron`, `OreSilver`, `OreGold`, `OreThorium`, `OreCobalt`, `OreAdamantite`, `OreMithril` | Ores |

For example, to also require a pickaxe for stone and ores:

```json
{
  "ToolRequiredGatherTypes": [
    "Woods",
    "Rocks", "VolcanicRocks",
    "OreCopper", "OreIron", "OreSilver", "OreGold",
    "OreThorium", "OreCobalt", "OreAdamantite", "OreMithril"
  ]
}
```

Restart the server after editing the file.

## Building

Requires Java 25. The Hytale server is downloaded from the official Maven
repository, no game installation is needed:

```sh
./gradlew build
```

The plugin jar is written to `build/libs/`.

The targeted server version is set in `gradle.properties`: `hytale_version` is
the server the plugin is compiled against, `patchline` selects the `release` or
`pre-release` repository, and `server_version` is the compatible range written
to the plugin manifest.

To run a development server from IntelliJ IDEA with the `HytaleServer` run
configuration, the game assets of a local Hytale installation are needed. The
default location is the Windows launcher folder; elsewhere set its path with
the `hytale_home` Gradle property, for example in
`~/.gradle/gradle.properties`.

## License

[MIT](LICENSE)
