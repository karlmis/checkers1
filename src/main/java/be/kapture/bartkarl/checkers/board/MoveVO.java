package be.kapture.bartkarl.checkers.board;

import be.kapture.bartkarl.checkers.Coordinates;
import be.kapture.bartkarl.checkers.Direction;

import static java.util.Objects.requireNonNull;

public class MoveVO {
    private Coordinates origin, destination;
    private Direction direction;

    private MoveVO(Coordinates origin, Direction direction, Coordinates destination) {
        this.origin = origin;
        this.direction = direction;
        this.destination = destination;
    }

    static MoveVO create(Coordinates origin, Direction direction) {
        requireNonNull(origin);
        requireNonNull(direction);

        return origin.getNeighbour(direction)
                .map(destination -> new MoveVO(origin, direction, destination))
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
}
