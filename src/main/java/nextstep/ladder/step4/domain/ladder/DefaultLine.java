package nextstep.ladder.step4.domain.ladder;

import static nextstep.ladder.step4.exception.LineMinimumLengthException.MINIMUM_LENGTH;

import java.util.List;
import nextstep.ladder.step4.domain.ladder.engine.Line;
import nextstep.ladder.step4.exception.LineMinimumLengthException;

public class DefaultLine implements Line {

    private final List<Point> points;

    public DefaultLine(List<Point> points) {
        valid(points);
        this.points = points;
    }

    private void valid(List<Point> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("사다리가 존재하지 않습니다.");
        }

        if (points.size() < MINIMUM_LENGTH) {
            throw new LineMinimumLengthException();
        }
    }

    @Override
    public int move(int index) {
        return 0;
    }
}
