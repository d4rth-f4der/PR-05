package lesson_5;

public enum State {
    START("S"),
    T("1"),
    TE("2"),
    TES("3"),
    FOUND("F");

    private final String label;

    State(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
