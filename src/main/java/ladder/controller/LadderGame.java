package ladder.controller;

import ladder.domain.LadderHeight;
import ladder.domain.Line;
import ladder.domain.Participant;
import ladder.strategy.LineGenerateStrategy;
import ladder.view.InputView;
import ladder.view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LadderGame {
    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final Random rd = new Random();

    public void start() {
        List<Participant> participants = inputView.getParticipantNames();
        LadderHeight ladderHeight = inputView.getLadderHeight();

        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < ladderHeight.getHeight(); i++) {
            lines.add(new Line(participants.size(), lineGenerateStrategy()));
        }

        resultView.printParticipants(participants);
        resultView.printLadder(lines);
    }

    private LineGenerateStrategy lineGenerateStrategy() {
        return (size) -> {
            List<Boolean> points = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                points.add(getBooleanPoint(points, i));
            }
            return points;
        };
    }

    private boolean getBooleanPoint(List<Boolean> points, int index) {
        if (index == 0 || points.get(index - 1)) {
            return false;
        }
        return rd.nextBoolean();
    }

}
