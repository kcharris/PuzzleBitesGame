# PuzzleBitesGame

### Introduction  
Puzzle Bites is an android based puzzle game were users use directional buttons to move pieces around a 9x9 board and try to fill in all of the goal locations in the fewest number of moves. There are also a few mechanics tied to this: multiple pieces can move at once, pieces can be activated by pressing switches, and different pieces have different speeds to which they move.

#### Notes on running:  
In order for the app to work we currently recommend testing the application using a larger screen or high pixel density. Android Studio with the built in emulator running the Google Pixel, or any of the Pixel phone is recommended. The target sdk is 33, and the minSDK is 24. When running the app it is recommended to go to the settings first to turn off the sound and increase the movement speed. For testing, the solution to the first puzzle, the one at the bottom left, is "u, l, l, l, d, r, r, u."

#### Game Made By
* Braeden
* Luke
* Dan
* Kevin Harris

#### Completion Status
##### Note - Functionality has been added and removed since the project proposal. Notable changes include defining a Main page, including a settings page, removing fractional movement, removing solid walls to prevent movement, and updating the "change background" and "dark theme" settings into a "change theme" switch. Of the updated functionality the only thing that is not completely working due to bugs is the undo button. The rest of the app is fully functional.

Activities:
* Main Page - Done
* Puzzle level select Page - Done
* Puzzle Page - Done
* Trophy Page - Done
* Score Page - Done
* Settings Page - Done

Functionality:
* Puzzle and Level Select
  * Puzzle reset - Done
  * 5 Puzzles available - Done
  * Pieces animate to different locations when moving - Done
  * Undo button - *Implemented but buggy*
  * Can move pieces by directional buttons - Done
  * When pieces fill the end location the puzzle finishes and moves to score page - Done
  * When the bagel piece lands on a level or trophy symbol, the game navigates to the correct page - Done
* Navigation to go back a page or retry puzzle - Done
* Settings
  * Sound toggle for piece movement - Done
  * Animation speed toggle - Done
  * Four different themees have been added and can be switched between - Done
  * Reset button to reset all scores in memory - Done
* Pieces
  * Have different speeds - Done
  * Can be activated or deactivated by switches - Done
  * Have decent and different images - Done
* Score is updated and saved in memory on completing a puzzle - Done
* Score and stars earned from score can be seen in app - Done
