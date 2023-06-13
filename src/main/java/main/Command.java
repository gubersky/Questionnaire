package main;

public enum Command {

    GET_RANDOM_QUESTION_BY_TOPIC("1. Get random question by topic"),
    GET_RANDOM_QUESTION("2. Get random question"),
    ADD_QUESTION("3. Add question"),
    REMOVE_QUESTION("4. Remove question"),
    GET_TOPIC("5. Get topic"),
    ADD_TOPIC("6. Add topic");

    final String command;

    Command(String command) {
        this.command = command;
    }
}
