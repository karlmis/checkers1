package be.kapture.bartkarl.checkers.board;

import be.kapture.bartkarl.checkers.Coordinates;
import be.kapture.bartkarl.checkers.Direction;

import static java.util.Objects.requireNonNull;

public class SingleTakeVO {
    private Coordinates origin, destination;
    private Direction direction;

    private SingleTakeVO(Coordinates origin, Direction direction, Coordinates destination) {
        this.origin = origin;
        this.direction = direction;
        this.destination = destination;
    }

    static SingleTakeVO create(Coordinates origin, Direction direction) {
        requireNonNull(origin);
        requireNonNull(direction);

        return origin.getNextNeighbour(direction)
                .map(destination -> new SingleTakeVO(origin, direction, destination))
                .orElseThrow(IllegalArgumentException::new);
    }

    public Coordinates getOrigin() {
        return origin;
    }

    public Coordinates getDestination() {
        return destination;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "SingleTakeVO{" +
                "origin=" + origin +
                ", destination=" + destination +
                ", direction=" + direction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleTakeVO that = (SingleTakeVO) o;

        if (origin != null ? !origin.equals(that.origin) : that.origin != null) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        int result = origin != null ? origin.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }
}
