package nextstep.ladder.domain;

public class LadderGame {

    private final Participants participants;

    private final Lines lines;

    private final LadderResults ladderResults;

    public LadderGame(String[] participants, String[] ladderResults, int height) {
        this.participants = new Participants(participants);
        this.ladderResults = new LadderResults(ladderResults, participants.length);
        this.lines = new Lines(height, participants.length);

        linkParticipantWithLadderResult();
    }

    public void linkParticipantWithLadderResult() {
        for (int participantIndex = 0; participantIndex < this.participants.numberOfParticipants(); participantIndex++) {
            int resultIndex = moveToLadderResult(participantIndex, this.lines.height());

            this.participants.match(participantIndex, this.ladderResults.getLadderResult(resultIndex));
        }
    }

    private int moveToLadderResult(int participantIndex, int heightOfLine) {
        int currentIndex = participantIndex;
        for (int height = 0; height < heightOfLine; height++) {
            currentIndex = move(currentIndex, height);
        }

        return currentIndex;
    }

    private int move(int index, int height) {
        int lastIndexOfLadder = this.participants.numberOfParticipants() - 1;

        if (isNotFirstIndex(index) && linkedLeft(height, index)) {
            return moveLeft(index);
        }
        if (isNotLastIndex(index, lastIndexOfLadder) && linkedRight(height, index)) {
            return moveRight(index);
        }
        return index;
    }

    private boolean isNotFirstIndex(int index) {
        return index > 0;
    }

    private boolean linkedLeft(int height, int index) {
        return this.lines.getLine(height).linkedLeft(index);
    }

    private boolean isNotLastIndex(int index, int lastIndexOfLadder) {
        return index < lastIndexOfLadder;
    }

    private boolean linkedRight(int height, int index) {
        return this.lines.getLine(height).linkedRight(index);
    }

    private int moveLeft(int currentIndex) {
        return currentIndex - 1;
    }

    private int moveRight(int currentIndex) {
        return currentIndex + 1;
    }

    public String resultOfParticipant(String name) {
        return this.participants.findByName(name);
    }

    public String view() {
        return this.lines.toLadderLines();
    }

    public Participants participants() {
        return participants;
    }

    public LadderResults ladderResults() {
        return ladderResults;
    }
}