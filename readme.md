# Janken

## How to build and run

```bash
./mvnw clean install
```

```bash
java -jar ./target/janken-0.0.1-SNAPSHOT.jar
```

## How to use

Janken shell supports several commands with `tab`-complition

- General commands
    - `clear` -  Clear the shell screen.
    - `exit`, `quit` - Exit the shell.
    - `help` - Display help about available commands.
- Game commands
    - `figures` - Display a list of available figures.
    - `stats` - Display current game stats.
    - `throw` - Make a move and throw your figure.