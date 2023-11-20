# Mancala

A text based game of mancala

## Description

A text based game of mancala. Features are demonstrated using a TextUI class

## Getting Started

### Dependencies

* JDK
* Gradle



### Executing program

* Run the following:
```
gradle build
java -cp build/classes/java/main ui.TextUI
```

## Limitations

The mancala game does everything as outlined by the assignment pdf

## Author Information

Daniel Xiao dxiao@uoguelph.ca

## Development History
Differences between the AI mancala and the real mancala:
* AI mancala does not actually end the game (its logic only ends the game if _both_ sides are empty)
* AI mancala implements the board as an array of 14 ints (no pit or store classes)
* AI mancala does not implement the "extra turn" rule
* AI mancala erroneously displays the valid pits for both players as 0-5
* AI mancala determines the winner by only comparing the stones in the stores, not the sum of stones in the stores and pits at the end of the game

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)



