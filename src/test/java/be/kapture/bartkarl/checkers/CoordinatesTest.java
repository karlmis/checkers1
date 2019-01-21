package be.kapture.bartkarl.checkers;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class CoordinatesTest {

    @Test
    public void create() {
        assertThatIllegalArgumentException().isThrownBy(() -> Coordinates.create(-1, 0));
        assertThatIllegalArgumentException().isThrownBy(() -> Coordinates.create(0, -1));

        assertThatIllegalArgumentException().isThrownBy(() -> Coordinates.create(0, 10));
        assertThatIllegalArgumentException().isThrownBy(() -> Coordinates.create(10, 0));

        Coordinates coordinates = Coordinates.create(2, 3);
        assertThat(coordinates.getX()).isEqualTo(2);
        assertThat(coordinates.getY()).isEqualTo(3);
    }

    @Test
    public void getNeighbour() {
        assertThat(Coordinates.create(2, 2).getNeighbour(Direction.LOWER_LEFT)).contains(Coordinates.create(2 - 1, 2 - 1));
        assertThat(Coordinates.create(2, 2).getNeighbour(Direction.LOWER_RIGHT)).contains(Coordinates.create(2 + 1, 2 - 1));
        assertThat(Coordinates.create(2, 2).getNeighbour(Direction.UPPER_LEFT)).contains(Coordinates.create(2 - 1, 2 + 1));
        assertThat(Coordinates.create(2, 2).getNeighbour(Direction.UPPER_RIGHT)).contains(Coordinates.create(2 + 1, 2 + 1));

    }

    @Test
    public void getNeighbour_InvalidInput() {
        assertThatNullPointerException().isThrownBy(() -> Coordinates.create(2, 2).getNeighbour(null));
    }

    @Test
    public void getNeighbour_Border() {
        assertThat(Coordinates.create(0, 0).getNeighbour(Direction.LOWER_LEFT)).isEmpty();
        assertThat(Coordinates.create(0, 0).getNeighbour(Direction.LOWER_RIGHT)).isEmpty();
        assertThat(Coordinates.create(0, 0).getNeighbour(Direction.UPPER_LEFT)).isEmpty();

        assertThat(Coordinates.create(9, 9).getNeighbour(Direction.LOWER_RIGHT)).isEmpty();
        assertThat(Coordinates.create(9, 9).getNeighbour(Direction.UPPER_LEFT)).isEmpty();
        assertThat(Coordinates.create(9, 9).getNeighbour(Direction.UPPER_RIGHT)).isEmpty();
    }

    @Test
    public void getNextNeighbour() {
        assertThat(Coordinates.create(3, 3).getNextNeighbour(Direction.LOWER_LEFT)).contains(Coordinates.create(3 - 2, 3 - 2));
        assertThat(Coordinates.create(3, 3).getNextNeighbour(Direction.LOWER_RIGHT)).contains(Coordinates.create(3 + 2, 3 - 2));
        assertThat(Coordinates.create(3, 3).getNextNeighbour(Direction.UPPER_LEFT)).contains(Coordinates.create(3 - 2, 3 + 2));
        assertThat(Coordinates.create(3, 3).getNextNeighbour(Direction.UPPER_RIGHT)).contains(Coordinates.create(3 + 2, 3 + 2));

        assertThat(Coordinates.create(0, 0).getNextNeighbour(Direction.LOWER_LEFT)).isEmpty();
        assertThat(Coordinates.create(1, 1).getNextNeighbour(Direction.LOWER_LEFT)).isEmpty();
    }

    @Test
    public void getNextNeighbour_NoNull() {
        assertThatNullPointerException().isThrownBy(() -> Coordinates.create(2, 2).getNextNeighbour(null));
    }

}