package ladder.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LadderGameTest {
    private LadderLine firstLadderLine;
    private LadderLine secondLadderLine;
    private LadderLine thirdLadderLine;
    private Players players;

    @Before
    public void setUp() {
        Direction direction1 = Direction.of(false, true);
        Direction direction2 = Direction.of(true, false);
        Direction direction3 = Direction.of(false, true);
        Direction direction4 = Direction.of(true, false);
        List<Point> points = Arrays.asList(Point.of(0, direction1),
                Point.of(1, direction2),
                Point.of(2, direction3),
                Point.of(3, direction4));
        firstLadderLine = new LadderLine(points);

        Player player1 = new Player("test1", 0);
        Player player2 = new Player("test2", 1);
        Player player3 = new Player("test3", 2);
        Player player4 = new Player("test4", 3);
        players = new Players(Arrays.asList(player1, player2, player3, player4));
    }

    @Test
    public void 첫번째줄사다리결과() {
        Ladder ladder = new Ladder(Arrays.asList(firstLadderLine));
        Winnings winnings = new Winnings("1, 2, 3, 4", players.size());
        LadderGame ladderGame = new LadderGame(ladder);
        Results results = ladderGame.playLadder(players, winnings);
        Result result = results.findResult("test1");
        System.out.println(result);
        assertThat(result.getWinning().trim()).isEqualTo("2");
    }
}