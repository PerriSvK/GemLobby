# GemLobby

Spigot 1.12.2 plugin for lobbies

### Features

- disable all damage events
- disable join and quit message
- set `ADVENTURE` gamemode
- disable all inventory events
- disable fire spreading and leaves decay
- OP can modify world

### Installation

1. Put file `GemLobby.jar` into your spigot lobby server plugin folder.
2. Restart server to generate config file
3. Edit config file and save it
4. Start again lobby server

### Config

```yaml
spawn: # Where will player spawn after join
  world: world
  x: 10
  y: 65
  z: 10
  yaw: 0.0
  pitch: 0.0
join-msg: # This message will (only) player see in chat after join
  - ""
```