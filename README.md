# PR-05 · Finite-state automaton

The automaton reads a line one character at a time and looks for the case-sensitive substring `TEST`. It returns the state reached after the last character.

| Label | State | Meaning |
| --- | --- | --- |
| S | START | No matching prefix at the end of the input |
| 1 | T | The input ends in the prefix `T` |
| 2 | TE | The input ends in the prefix `TE` |
| 3 | TES | The input ends in the prefix `TES` |
| F | FOUND | `TEST` occurred anywhere in the input |

An empty line yields `S`. Spaces count as input characters. Each call to `recognize` starts at `S` and requires a non-null string. Once the automaton reaches `F`, it stays there for the rest of the line.

## Optional task

In the assignment diagram, a mismatch from state `1` or `2` sends the automaton back to `S`. If the mismatching character is `T`, it can start a fresh match. The corrected automaton transitions to `1` on `T` from either state. For example, `TTEST` and `TETEST` now both yield `F`. The parameterized tests cover both transitions.

## Run

Open the project in IntelliJ IDEA as a Maven project with JDK 25 and run `lesson_5.Main`. Type one line and press Enter to see its final state.

From a terminal with Maven and JDK 25 available, run:

```text
mvn clean test
java -cp target/classes lesson_5.Main
```
