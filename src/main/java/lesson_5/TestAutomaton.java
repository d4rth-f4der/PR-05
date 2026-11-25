package lesson_5;

import java.util.Objects;

public class TestAutomaton {
    public State recognize(String input) {
        Objects.requireNonNull(input, "input must not be null");
        State state = State.START;

        for (int i = 0; i < input.length(); i++) {
            state = transition(state, input.charAt(i));
        }

        return state;
    }

    private State transition(State state, char symbol) {
        return switch (state) {
            case START -> symbol == 'T' ? State.T : State.START;
            case T -> switch (symbol) {
                case 'E' -> State.TE;
                case 'T' -> State.T;
                default -> State.START;
            };
            case TE -> switch (symbol) {
                case 'S' -> State.TES;
                case 'T' -> State.T;
                default -> State.START;
            };
            case TES -> symbol == 'T' ? State.FOUND : State.START;
            case FOUND -> State.FOUND;
        };
    }
}
