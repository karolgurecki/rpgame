##### Requirements
 * installed JDK 12
 * JAVA_HOME env variable set to dir when JDK 12 is installed
 * internet connection do maven central to download needed libs
 
##### Build
Using command line run from main project directory (where this file is located) given command:
* on Windows - `mvnw.cmd clean package`
* on Unix - `mvnw clean package`

##### Run the game
When you built this project, you can run the game using command line (from main project directory where this file is located)<br>
`java -jar runner/target/eu.kgorecki.rpgame.jar` 