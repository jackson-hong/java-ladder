package ladder.domain;

import ladder.ladderexceptions.InvalidLayoutException;

import java.util.Objects;

public class Point {
    private final boolean leftSideStatus;
    private final boolean atStatus;

    public static Point first(boolean atPointStatus) {
        return custom(false, atPointStatus);
    }

    public Point next(boolean atStatus) {
        return Point.custom(this.atStatus, atStatus);
    }

    public Point last() {
        return custom(this.atStatus, atStatus);
    }

    public static Point custom(boolean leftSideStatus, boolean atPointStatus) {
        return new Point(leftSideStatus, atPointStatus);
    }

    private Point(boolean leftSideStatus, boolean atPointStatus) {
        validateStatus(leftSideStatus, atPointStatus);
        this.leftSideStatus = leftSideStatus;
        this.atStatus = atPointStatus;
    }

    private void validateStatus(boolean leftSideStatus, boolean atPointStatus) {
        if (leftSideStatus && atPointStatus) {
            throw new InvalidLayoutException();
        }
    }

    public int nextIndex(int startingIndex) {
        if (!leftSideStatus && atStatus) {
            return startingIndex + 1;
        }
        if (leftSideStatus && !atStatus) {
            return startingIndex - 1;
        }

        return startingIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return leftSideStatus == point.leftSideStatus && atStatus == point.atStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftSideStatus, atStatus);
    }
}
