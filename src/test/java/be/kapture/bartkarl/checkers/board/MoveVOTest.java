package be.kapture.bartkarl.checkers.board;

import be.kapture.bartkarl.checkers.Coordinates;
import be.kapture.bartkarl.checkers.Direction;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class MoveVOTest {

    @Test
    public void create() {
        MoveVO moveVO = MoveVO.create(Coordinates.create(2, 2), Direction.UPPER_LEFT);

        assertThat(moveVO.getOrigin()).isEqualTo(Coordinates.create(2, 2));
        assertThat(moveVO.getDirection()).isEqualTo(Direction.UPPER_LEFT);
        assertThat(moveVO.getDestination()).isEqualTo(Coordinates.create(2, 2).getNeighbour(Direction.UPPER_LEFT).get());
    }

    @Test
    public void create_IllegalInput() {
        assertThatNullPointerException().isThrownBy(() -> MoveVO.create(Coordinates.create(2, 2), null));
        assertThatNullPointerException().isThrownBy(() -> MoveVO.create(null, Direction.UPPER_LEFT));

        assertThatIllegalArgumentException().isThrownBy(() -> MoveVO.create(Coordinates.create(0, 0), Direction.LOWER_LEFT));

    }
}