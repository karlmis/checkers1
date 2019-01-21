package be.kapture.bartkarl.checkers.board;

import be.kapture.bartkarl.checkers.Coordinates;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static be.kapture.bartkarl.checkers.Direction.UPPER_LEFT;
import static be.kapture.bartkarl.checkers.Direction.UPPER_RIGHT;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.*;

public class TakeVOTest {

    private static final Coordinates COORDINATES_22 = Coordinates.create(2, 2);
    private static final Coordinates COORDINATES_44 = Coordinates.create(4, 4);

    @Test
    public void create_InvalidInput() {
        assertThatNullPointerException().isThrownBy(() ->
                TakeVO.create(null, singletonList(SingleTakeVO.create(COORDINATES_22, UPPER_LEFT))));

        assertThatIllegalArgumentException().isThrownBy(() -> TakeVO.create(COORDINATES_22, null));
        assertThatIllegalArgumentException().isThrownBy(() -> TakeVO.create(COORDINATES_22, emptyList()));
    }

    @Test
    public void create_IncorrectList() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                TakeVO.create(COORDINATES_22, singletonList(SingleTakeVO.create(COORDINATES_44, UPPER_RIGHT))));

        assertThatIllegalArgumentException().isThrownBy(() ->
                TakeVO.create(COORDINATES_22, asList(SingleTakeVO.create(COORDINATES_22, UPPER_RIGHT), SingleTakeVO.create(COORDINATES_22, UPPER_RIGHT))));
    }

    @Test
    public void create() {
        TakeVO takeVO = TakeVO.create(Coordinates.create(2, 2), singletonList(SingleTakeVO.create(Coordinates.create(2, 2), UPPER_RIGHT)));

        assertThat(takeVO.getOrigin()).isEqualTo(COORDINATES_22);
        assertThat(takeVO.getDestination()).isEqualTo(COORDINATES_44);
        assertThat(takeVO.getSteps()).containsExactly(SingleTakeVO.create(Coordinates.create(2, 2), UPPER_RIGHT));
    }

    @Test
    public void create_Multiple() {
        TakeVO takeVO = TakeVO.create(COORDINATES_22, asList(
                SingleTakeVO.create(COORDINATES_22, UPPER_RIGHT),
                SingleTakeVO.create(COORDINATES_44, UPPER_RIGHT)
        ));

        assertThat(takeVO.getOrigin()).isEqualTo(COORDINATES_22);
        assertThat(takeVO.getDestination()).isEqualTo(Coordinates.create(6, 6));
        assertThat(takeVO.getSteps()).containsExactly(
                SingleTakeVO.create(COORDINATES_22, UPPER_RIGHT),
                SingleTakeVO.create(COORDINATES_44, UPPER_RIGHT));
    }

    @Test
    public void create_StepsCanNotBeChangedAfterCreation() {
        List<SingleTakeVO> originalSteps = new ArrayList<>(asList(
                SingleTakeVO.create(COORDINATES_22, UPPER_RIGHT),
                SingleTakeVO.create(COORDINATES_44, UPPER_RIGHT))
        );
        TakeVO takeVO = TakeVO.create(COORDINATES_22, originalSteps);
        originalSteps.add(SingleTakeVO.create(Coordinates.create(6, 6), UPPER_RIGHT));


        List<SingleTakeVO> steps = takeVO.getSteps();
        assertThat(steps).containsExactly(
                SingleTakeVO.create(COORDINATES_22, UPPER_RIGHT),
                SingleTakeVO.create(COORDINATES_44, UPPER_RIGHT));

       // assertThatExceptionOfType(Exception.class).isThrownBy(() -> steps.add(SingleTakeVO.create(Coordinates.create(6, 6), UPPER_RIGHT)));

        assertThat(takeVO.getSteps()).hasSize(2);
        originalSteps.add(SingleTakeVO.create(Coordinates.create(6, 6), UPPER_RIGHT));
        assertThat(takeVO.getSteps()).hasSize(2);
    }

}