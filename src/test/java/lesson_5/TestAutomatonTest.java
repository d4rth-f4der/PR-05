package lesson_5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static lesson_5.State.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestAutomatonTest {
    private final TestAutomaton automaton = new TestAutomaton();

    @ParameterizedTest
    @MethodSource("basicCases")
    void returnsFinalState(String input, State expected) {
        assertEquals(expected, automaton.recognize(input));
    }

    static Stream<Arguments> basicCases() {
        return Stream.of(
                Arguments.of("", START),
                Arguments.of("abc", START),
                Arguments.of("T", T),
                Arguments.of("TE", TE),
                Arguments.of("TES", TES),
                Arguments.of("TEST", FOUND),
                Arguments.of("abcTESTabc", FOUND),
                Arguments.of("abcTES", TES),
                Arguments.of("TX", START),
                Arguments.of("TEX", START),
                Arguments.of("TESX", START),
                Arguments.of("test", START),
                Arguments.of("Test", START),
                Arguments.of("TE ST", T),
                Arguments.of("T ", START),
                Arguments.of("TESTTEST", FOUND),
                Arguments.of("TEST ", FOUND)
        );
    }

    @ParameterizedTest
    @MethodSource("overlapCases")
    void reusesTAsTheStartOfANewMatch(String input, State expected) {
        assertEquals(expected, automaton.recognize(input));
    }

    static Stream<Arguments> overlapCases() {
        return Stream.of(
                Arguments.of("TT", T),
                Arguments.of("TET", T),
                Arguments.of("TTEST", FOUND),
                Arguments.of("TETEST", FOUND),
                Arguments.of("TTES", TES),
                Arguments.of("abTTESTcd", FOUND)
        );
    }

    @Test
    void startsOverForEveryInput() {
        assertEquals(FOUND, automaton.recognize("TEST"));
        assertEquals(START, automaton.recognize(""));
    }

    @Test
    void rejectsNullInput() {
        assertThrows(NullPointerException.class, () -> automaton.recognize(null));
    }
}
