package be.kapture.bartkarl.checkers;

public enum Direction {
    UPPER_LEFT(-1, 1), UPPER_RIGHT(1, 1), LOWER_RIGHT(1, -1), LOWER_LEFT(-1, -1);

    private final int xDifference;
    private final int yDifference;

    Direction(int xDifference, int yDifference) {
        this.xDifference = xDifference;
        this.yDifference = yDifference;
    }

    public int getXDifference() {
        return xDifference;
    }

    public int getYDifference() {
        return yDifference;
    }
}
