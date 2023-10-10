# Playwright demo
This is a demo project to show how to use [Playwright](https://playwright.dev/) to automate browser testing.

## Getting started
1. Install [OpenJDK 11](https://openjdk.java.net/projects/jdk/11/) or later.
2. Install [Maven](https://maven.apache.org/download.cgi).
3. Clone the project.

## Run the examples
1. In the project root directory, run each line:
```
mvn compile exec:java -D exec.mainClass="org.example.Main" # run the main class.
mvn compile exec:java -D exec.mainClass="org.example.FullTests" # run a complete example.
mvn compile exec:java -D exec.mainClass="org.example.TodoTests" # run a TODO MVC example.
```

2. To use mvnw, run each line:
```
./mvnw compile exec:java -D exec.mainClass="org.example.Main" # run the main class.
./mvnw compile exec:java -D exec.mainClass="org.example.FullTests" # run a complete example.
./mvnw compile exec:java -D exec.mainClass="org.example.TodoTests" # run a TODO MVC example.
```
