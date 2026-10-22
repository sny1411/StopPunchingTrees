# Changelog

## Unreleased

### Changed
- Built against the Hytale server 0.7.3. The 0.7.0 server is no longer on the
  Hytale Maven repository, so the build failed on a clean machine.

## 1.1.2 - 2026-10-13

Requires a Hytale server 0.6.8 (Update 6) or later, including 0.7.0.

### Changed
- Built against the Hytale server 0.7.0. Still works on 0.6.8.

## 1.1.1 - 2026-10-11

### Changed
- The plugin group is now `sny1411`, so the config file is
  `mods/sny1411_StopPunchingTrees/config.json`. If you edited it with 1.1.0,
  move it from `mods/StopPunchingTrees_StopPunchingTrees/`.

## 1.1.0 - 2026-10-11

Requires a Hytale server 0.6.8 (Update 6) or later.

### Added
- Configurable blocks: `ToolRequiredGatherTypes` in `config.json` lists the
  gather types that need a tool. Only `Woods` by default, like before.
- Stone and ores can require a tool too by adding `Rocks` and the ore gather
  types (`OreCopper`, `OreIron`, ...) to the config (#1).
- Hitting a protected block with bare hands plays the game's unbreakable block
  sound.

### Changed
- Rebuilt for Update 6.
- Only real tools work. Before, any item with durability, such as a sword,
  could chop trees.

### Fixed
- The startup message now shows the plugin name and version.

## 1.0 - 2026-01-18

- First release: players cannot break wood with their bare hands.
