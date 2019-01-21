package be.kapture.bartkarl.checkers;

import java.util.Optional;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Objects.requireNonNull;

public class Coordinates {

    private final int x;
    private final int y;

    private Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates create(int x, int y) {
        if (isInvalid(x, y)) {
            throw new IllegalArgumentException();
        }
        return new Coordinates(x, y);
    }

    private static boolean isInvalid(int x, int y) {
        return min(x, y) < 0 || max(x, y) > 9;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Optional<Coordinates> getNeighbour(Direction direction) {
        requireNonNull(direction);
        if (isInvalid(x + direction.getXDifference(), y + direction.getYDifference())) {
            return Optional.empty();
        }

        return Optional.of(Coordinates.create(
                x + direction.getXDifference(),
                y + direction.getYDifference()
        ));
    }

    public Optional<Coordinates> getNextNeighbour(Direction direction){
        requireNonNull(direction);
        return getNeighbour(direction)
                .map(n -> n.getNeighbour(direction).orElse(null));
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
