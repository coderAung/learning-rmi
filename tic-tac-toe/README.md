## Tic Tac Toe 2 Players Mini Game

### Tech Stack

- Java RMI for multiplayer (2 players) support
- JavaFX for UI
- Spring + Spring Boot for other spring's support

### Architecture

- JavaFX controller are declared as spring beans
- Use Spring's Event Handling support for Realtime UI update
- Instead of Central Server, each device can act as both server and client with the power of RMI

### Usage

1. Open / Run the app
2. Both players must be under same network such as wi-fi, hotspot or LAN.
3. A player can host as a server or join to a hosted player by entering host's ip address.
4. Play well.

### Remark

This is the first commit of the project, only ui update and page routing are implemented.
Business or Game logic are coming soon.