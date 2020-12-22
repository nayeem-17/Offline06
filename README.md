## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

## Dependency Management

The `JAVA DEPENDENCIES` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-pack/blob/master/release-notes/v0.9.0.md#work-with-jar-files-directly).

## How to run

First you have to add modules from javafx-sdk-11.02 (not included in lib directory)

To run this application on vs code, first, you have to run server.Main and then run client.Main.

If you are using another ide, first you have to add the jar files from lib/ directory to your project.          
Then you have to add the following line to your vm options or vmargs          
`--module-path LIB_PATH --add-modules javafx.controls,javafx.fxml`          
here, LIB_PATH is the path of your javafx lib modules. Now first run server.Main and then run client.Main.