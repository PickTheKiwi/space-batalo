# space-batalo

This is a small game I decided to make for my year 12 class in Java.

## I'm remaking this in V, find it [here](https://github.com/PickJ888/batalo-v)

## Dependencies
- A JDK (If you're compiling on your own computer, you can use the JDK you have installed on your computer. If you're compiling on a computer with a JDK installed, you can use the JDK you have installed on that computer.)
- A JVM (version 17 if you're running the version I compiled, otherwise use the version you have installed on your computer)


## Installation
You can either download the already compiled version from the releases page or compile with the instructions below.

## Compiling from source
### Command line (I have no clue why you would want to do this)
Run the following set of commands:
```
cd path/to/space-batalo/src
javac -d . Main.java
jar cvmf ./META-INF/MANIFEST.MF SpaceBatalo.jar Main.class Player.class
```
> You will now have compiled the game to a jar file

### BlueJ
1. Open src folder in BlueJ
2. Click "Compile in the side menu"
3. Click on the Project menu and select "Create Jar File..."
4. Select "Main" as the main class
5. Click "Continue"
6. Select output folder and click "Create"
> You will now have compiled the game to a jar file

### IntelliJ
1. Open main folder in IntelliJ
2. Select File in the top bar then Project Structure (or use Ctrl+Alt+Shift+S)
3. Click on the Artifacts tab (if it's not already the tab you're on).
4. Click on the "+" (or use Alt + Insert) button then click on "From modules with dependencies..."
5. Select "Main" as the main class
6. Click "OK" and close the menu (go back to the main menu)
7. Select "Build" then "Build Artifacts"
8. Select build from the build Artifact menu.

### Other
Look it up, I don't know, so I can't tell you how to compile in other programs.

### Running
1. Open your shell of choice
2. Go do your folder of choice using `cd path/to/jar/folder`
3. Open the jar with `java -jar space-batalo.jar` (or whatever you named the jar when compiling it)
> The game should run within your shell
