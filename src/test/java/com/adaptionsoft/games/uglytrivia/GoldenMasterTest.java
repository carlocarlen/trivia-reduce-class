package com.adaptionsoft.games.uglytrivia;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoldenMasterTest {

    // Picking some random seed and the corresponding result, always with 3 players
    @ParameterizedTest
    @MethodSource("seedsAndOutput")
    void goldenMasterFirst(int randomSeed, String expectedOutput) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Game aGame = new Game(new PrintStream(outputStream));

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random(randomSeed);

        boolean notAWinner;

        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);

        String actualOutput = outputStream.toString().replace("\r\n", "\n");

        assertEquals(expectedOutput, actualOutput);

    }

    private static Stream<Arguments> seedsAndOutput() {
        return Stream.of(
                Arguments.of(1, expectedOutput1),
                Arguments.of(2, expectedOutput2),
                Arguments.of(3, expectedOutput3)
        );
    }

    private static final String expectedOutput1 = """
Chet was added
They are player number 1
Pat was added
They are player number 2
Sue was added
They are player number 3
Chet is the current player
They have rolled a 1
Chet's new location is 1
The category is Science
Science Question 0
Answer was correct!!!!
Chet now has 1 Gold Coins.
Pat is the current player
They have rolled a 3
Pat's new location is 3
The category is Rock
Rock Question 0
Answer was correct!!!!
Pat now has 1 Gold Coins.
Sue is the current player
They have rolled a 5
Sue's new location is 5
The category is Science
Science Question 1
Answer was correct!!!!
Sue now has 1 Gold Coins.
Chet is the current player
They have rolled a 5
Chet's new location is 6
The category is Sports
Sports Question 0
Answer was correct!!!!
Chet now has 2 Gold Coins.
Pat is the current player
They have rolled a 4
Pat's new location is 7
The category is Rock
Rock Question 1
Answer was correct!!!!
Pat now has 2 Gold Coins.
Sue is the current player
They have rolled a 5
Sue's new location is 10
The category is Sports
Sports Question 1
Question was incorrectly answered
Sue was sent to the penalty box
Chet is the current player
They have rolled a 3
Chet's new location is 9
The category is Science
Science Question 2
Answer was correct!!!!
Chet now has 3 Gold Coins.
Pat is the current player
They have rolled a 3
Pat's new location is 10
The category is Sports
Sports Question 2
Question was incorrectly answered
Pat was sent to the penalty box
Sue is the current player
They have rolled a 3
Sue is getting out of the penalty box
Sue's new location is 1
The category is Science
Science Question 3
Answer was correct!!!!
Sue now has 2 Gold Coins.
Chet is the current player
They have rolled a 2
Chet's new location is 11
The category is Rock
Rock Question 2
Answer was correct!!!!
Chet now has 4 Gold Coins.
Pat is the current player
They have rolled a 2
Pat is not getting out of the penalty box
Sue is the current player
They have rolled a 1
Sue is getting out of the penalty box
Sue's new location is 2
The category is Sports
Sports Question 3
Answer was correct!!!!
Sue now has 3 Gold Coins.
Chet is the current player
They have rolled a 5
Chet's new location is 4
The category is Pop
Pop Question 0
Answer was correct!!!!
Chet now has 5 Gold Coins.
Pat is the current player
They have rolled a 4
Pat is not getting out of the penalty box
Sue is the current player
They have rolled a 3
Sue is getting out of the penalty box
Sue's new location is 5
The category is Science
Science Question 4
Answer was correct!!!!
Sue now has 4 Gold Coins.
Chet is the current player
They have rolled a 1
Chet's new location is 5
The category is Science
Science Question 5
Answer was correct!!!!
Chet now has 6 Gold Coins.
""";

    private static final String expectedOutput2 = """
Chet was added
They are player number 1
Pat was added
They are player number 2
Sue was added
They are player number 3
Chet is the current player
They have rolled a 4
Chet's new location is 4
The category is Pop
Pop Question 0
Answer was correct!!!!
Chet now has 1 Gold Coins.
Pat is the current player
They have rolled a 1
Pat's new location is 1
The category is Science
Science Question 0
Answer was correct!!!!
Pat now has 1 Gold Coins.
Sue is the current player
They have rolled a 5
Sue's new location is 5
The category is Science
Science Question 1
Answer was correct!!!!
Sue now has 1 Gold Coins.
Chet is the current player
They have rolled a 2
Chet's new location is 6
The category is Sports
Sports Question 0
Answer was correct!!!!
Chet now has 2 Gold Coins.
Pat is the current player
They have rolled a 3
Pat's new location is 4
The category is Pop
Pop Question 1
Answer was correct!!!!
Pat now has 2 Gold Coins.
Sue is the current player
They have rolled a 5
Sue's new location is 10
The category is Sports
Sports Question 1
Answer was correct!!!!
Sue now has 2 Gold Coins.
Chet is the current player
They have rolled a 5
Chet's new location is 11
The category is Rock
Rock Question 0
Answer was correct!!!!
Chet now has 3 Gold Coins.
Pat is the current player
They have rolled a 5
Pat's new location is 9
The category is Science
Science Question 2
Answer was correct!!!!
Pat now has 3 Gold Coins.
Sue is the current player
They have rolled a 3
Sue's new location is 1
The category is Science
Science Question 3
Answer was correct!!!!
Sue now has 3 Gold Coins.
Chet is the current player
They have rolled a 1
Chet's new location is 0
The category is Pop
Pop Question 2
Answer was correct!!!!
Chet now has 4 Gold Coins.
Pat is the current player
They have rolled a 4
Pat's new location is 1
The category is Science
Science Question 4
Answer was correct!!!!
Pat now has 4 Gold Coins.
Sue is the current player
They have rolled a 5
Sue's new location is 6
The category is Sports
Sports Question 2
Question was incorrectly answered
Sue was sent to the penalty box
Chet is the current player
They have rolled a 2
Chet's new location is 2
The category is Sports
Sports Question 3
Answer was correct!!!!
Chet now has 5 Gold Coins.
Pat is the current player
They have rolled a 3
Pat's new location is 4
The category is Pop
Pop Question 3
Answer was correct!!!!
Pat now has 5 Gold Coins.
Sue is the current player
They have rolled a 4
Sue is not getting out of the penalty box
Chet is the current player
They have rolled a 5
Chet's new location is 7
The category is Rock
Rock Question 1
Answer was correct!!!!
Chet now has 6 Gold Coins.
""";

    private static final String expectedOutput3 = """
Chet was added
They are player number 1
Pat was added
They are player number 2
Sue was added
They are player number 3
Chet is the current player
They have rolled a 5
Chet's new location is 5
The category is Science
Science Question 0
Answer was correct!!!!
Chet now has 1 Gold Coins.
Pat is the current player
They have rolled a 1
Pat's new location is 1
The category is Science
Science Question 1
Question was incorrectly answered
Pat was sent to the penalty box
Sue is the current player
They have rolled a 4
Sue's new location is 4
The category is Pop
Pop Question 0
Answer was correct!!!!
Sue now has 1 Gold Coins.
Chet is the current player
They have rolled a 5
Chet's new location is 10
The category is Sports
Sports Question 0
Question was incorrectly answered
Chet was sent to the penalty box
Pat is the current player
They have rolled a 5
Pat is getting out of the penalty box
Pat's new location is 6
The category is Sports
Sports Question 1
Answer was correct!!!!
Pat now has 1 Gold Coins.
Sue is the current player
They have rolled a 1
Sue's new location is 5
The category is Science
Science Question 2
Answer was correct!!!!
Sue now has 2 Gold Coins.
Chet is the current player
They have rolled a 3
Chet is getting out of the penalty box
Chet's new location is 1
The category is Science
Science Question 3
Answer was correct!!!!
Chet now has 2 Gold Coins.
Pat is the current player
They have rolled a 2
Pat is not getting out of the penalty box
Sue is the current player
They have rolled a 2
Sue's new location is 7
The category is Rock
Rock Question 0
Answer was correct!!!!
Sue now has 3 Gold Coins.
Chet is the current player
They have rolled a 2
Chet is not getting out of the penalty box
Pat is the current player
They have rolled a 2
Pat is not getting out of the penalty box
Sue is the current player
They have rolled a 1
Sue's new location is 8
The category is Pop
Pop Question 1
Answer was correct!!!!
Sue now has 4 Gold Coins.
Chet is the current player
They have rolled a 4
Chet is not getting out of the penalty box
Pat is the current player
They have rolled a 3
Pat is getting out of the penalty box
Pat's new location is 9
The category is Science
Science Question 4
Answer was correct!!!!
Pat now has 2 Gold Coins.
Sue is the current player
They have rolled a 2
Sue's new location is 10
The category is Sports
Sports Question 2
Answer was correct!!!!
Sue now has 5 Gold Coins.
Chet is the current player
They have rolled a 4
Chet is not getting out of the penalty box
Pat is the current player
They have rolled a 3
Pat is getting out of the penalty box
Pat's new location is 0
The category is Pop
Pop Question 2
Answer was correct!!!!
Pat now has 3 Gold Coins.
Sue is the current player
They have rolled a 3
Sue's new location is 1
The category is Science
Science Question 5
Answer was correct!!!!
Sue now has 6 Gold Coins.
""";
}
