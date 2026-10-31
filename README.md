# SimpleCivGame
Spring Boot project inspired by game Sid Meier's Civilization V.

## Requirements
Java Development Kit (version 21 or later) needs to be installed. You can download for example [Adoptium Temurin JDK](https://adoptium.net/temurin/releases/?version=21).

## Tech stack
- **Backend:** Java 21, Spring Boot, Spring Data JPA
- **Database:** H2
- **Frontend:** JavaScript, HTML, CSS, Thymeleaf

## Running
1. In command line clone repository ([Git](https://git-scm.com/install/) required)
```bat
git clone https://github.com/grzeg2010/SimpleCivGame.git
```
Eventualy you can download copy of this project as [ZIP file](https://github.com/grzeg2010/SimpleCivGame/archive/refs/heads/main.zip). You can find it by pressing green _Code_ button and choosing _Download ZIP_.

2. Run _run.bat_
```bat
.\run.bat
```

3. Script should open browser automatically. User should be forwarded to site: http://localhost:9999

## Functionality
### Game interface
http://localhost:9999/ shows a playable area. You can select units or cities.  
After selecting unit or city, in the bottom left you will be able to choose action.
Units have smaller selection area than tiles. It allows you to select the city which is occupied by unit.

#### Movement
You can select where a unit will move in the next turn. First select unit and in control panel in bottom left press Move. You will be able to select one of dark blue tiles. Selected tile changes color to _steelblue_ to indicate that one of the units will move there.  
To finally move the units, you have to press _NEXT TURN_ button in the bottom left corner.

#### Units summary
You can see what type of units you currently have. To do this press _Units_ button in the top right.
You will see the table with units':
- id
- type
- position
- max travel distance (_move_)
- ability to travel on land (_walk_)
- ability to travel on water (_swim_)

### Cheat console
Thymeleaf cheat console is available under address http://localhost:9999/cheat-console.  
In cheat console you can spawn units or tile upgrades in any type of terrain. You can also delete the units or upgrades.  
At the bottom is a summary of terrain tiles:
- x position
- y position
- tile type (land, sea, mountain)
- tile upgrade (farm, mill, fish boat)

## Reseting game
To reset game state remove **.db** files from ```<PROJECT_ROOT>/data/```