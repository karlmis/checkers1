package be.kapture.bartkarl.checkers.board;

import be.kapture.bartkarl.checkers.Coordinates;
import be.kapture.bartkarl.checkers.Direction;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class SingleTakeVOTest {

    @Test
    public void create() {
        SingleTakeVO moveVO = SingleTakeVO.create(Coordinates.create(2, 2), Direction.UPPER_LEFT);

        assertThat(moveVO.getOrigin()).isEqualTo(Coordinates.create(2, 2));
        assertThat(moveVO.getDirection()).isEqualTo(Direction.UPPER_LEFT);
        assertThat(moveVO.getDestination()).isEqualTo(Coordinates.create(2, 2).getNextNeighbour(Direction.UPPER_LEFT).get());
    }

    @Test
    public void create_IllegalInput() {
        assertThatNullPointerException().isThrownBy(() -> SingleTakeVO.create(Coordinates.create(2, 2), null));
        assertThatNullPointerException().isThrownBy(() -> SingleTakeVO.create(null, Direction.UPPER_LEFT));

        assertThatIllegalArgumentException().isThrownBy(() -> SingleTakeVO.create(Coordinates.create(0, 0), Direction.LOWER_LEFT));
        assertThatIllegalArgumentException().isThrownBy(() -> SingleTakeVO.create(Coordinates.create(1, 1), Direction.LOWER_LEFT));

    }
}