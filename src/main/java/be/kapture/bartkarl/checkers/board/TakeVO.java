package be.kapture.bartkarl.checkers.board;

import be.kapture.bartkarl.checkers.Coordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

public class TakeVO {
    private Coordinates origin, destination;
    private List<SingleTakeVO> steps;

    private TakeVO(Coordinates origin, Coordinates destination, List<SingleTakeVO> steps) {
        this.origin = origin;
        this.destination = destination;
        this.steps = steps;
    }

    public static TakeVO create(Coordinates origin, List<SingleTakeVO> steps) {
        requireNonNull(origin);
        checkArgument(steps != null && !steps.isEmpty());

        Coordinates previousDestionation= origin;
        for (SingleTakeVO step : steps) {
            if (!step.getOrigin().equals(previousDestionation)) {
                throw new IllegalArgumentException();
            }
            previousDestionation= step.getDestination();
        }
        return new TakeVO(origin, previousDestionation, new ArrayList<>(steps));
    }

    public Coordinates getOrigin() {
        return origin;
    }

    public Coordinates getDestination() {
        return destination;
    }

    public List<SingleTakeVO> getSteps() {
        return new ArrayList<>(steps);
    }
}
