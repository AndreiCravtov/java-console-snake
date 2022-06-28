
# Java console snake game

## Learning summary

While learning the Java programming language, I have produced a console rendition of the *Snake* game as one of my projects. This project implements a responsive main menu and a game loop that features collision detection between the snake head, it's body, the food and the borders.

* Java programming language: I have  learned  the syntax of Java  as well  as learning how to use useful utility functionality provided by either Java itself of external libraries like Apache Commons.

* Object-oriented programming: while working on this project, I have learned the rudimentary elements of object-oriented programming. For instance, I learned the benefits of using abstraction in my design, making the attributes in my `Snake` and `Food` classes private (accessible via get/set methods which have inbuilt validation). I also learned how these concepts apply to the Java programming language. For example, learning about static methods or  Enum  constructors in Java expanded my horizon as to what OOP could look like.

* Game design: since this project was a game, I had to learn what it takes to make a basic game. For example, I had to set up a game loop, handle continuous user input, create a render pipeline, and other fundamental aspects of game-making.

* Problem-solving using maths: since this project is a game, I had to devise a method to display an image to the console. To make this work, I had to employ co-ordinates and vector math in my algorithms to be able to calculate the movement of the `Snake`, if it eats the `Food` or not, and so on. Furthermore, I had to learn the math behind datastructures, so I could efficiently represent all this co-ordinate data as a single Java Array that I could display as an image.

## How to operate this project

### How to run the project

1. Download the `.jar` file from [here](https://github.com/AndreiCravtov/java-console-snake/releases/tag/release) or [here](https://github.com/AndreiCravtov/java-console-snake/tree/main/release).

2. Run it in a Linux terminal using something like `java -jar JavaSnake.jar`.

### Game controls

The game is controlled with  WASD+Enter keys

## Viewing and  modifying  the project

This repository is an  Intellij IDEA  project. It can be opened and edited by cloning this repo and opening the repo folder in Intellij IDEA  like any other project. From there, the source code can be viewed and modified.
