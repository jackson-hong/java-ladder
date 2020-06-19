package ladder.domain;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ladder {
    private final List<Line> ladder;

    private Ladder(int ladderHeight, int countOfPerson) {
        checkLadderHeight(ladderHeight);
        Random random = new Random();
        this.ladder = Stream.generate(() -> Line.valueOf(countOfPerson, random::nextBoolean))
                .limit(ladderHeight)
                .collect(Collectors.toList());
    }

    public static Ladder valueOf(int ladderHeight, int countOfPerson) {
        return new Ladder(ladderHeight, countOfPerson);
    }

    public int run(int personIndex) {
        int finalPoint = personIndex;
        for (Line line : ladder) {
            finalPoint += line.runLine(finalPoint);
        }
        return finalPoint;
    }

    public List<Line> getLadder() {
        return Collections.unmodifiableList(ladder);
    }

    private void checkLadderHeight(int ladderHeight) {
        if (ladderHeight < 1) {
            throw new IllegalArgumentException("사다리 길이가 0 이하 입니다.");
        }
    }


}