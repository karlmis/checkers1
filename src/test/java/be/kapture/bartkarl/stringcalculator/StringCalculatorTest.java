package be.kapture.bartkarl.stringcalculator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class StringCalculatorTest {
    @Test
    public void add_BorderCase() {
        assertThatNullPointerException().isThrownBy(() -> StringCalculator.add(null));
        assertThat(StringCalculator.add("")).isEqualTo(0);
    }

    @Test
    public void add_OneElement() {
        assertThat(StringCalculator.add("1")).isEqualTo(1);
        assertThat(StringCalculator.add("12")).isEqualTo(12);
    }

    @Test
    public void add_MoreElement() {
        assertThat(StringCalculator.add("14,27,41,97")).isEqualTo(14 + 27 + 41 + 97);
    }

    @Test
    public void add_WithEnter() {
        assertThat(StringCalculator.add("14,27\n41,97")).isEqualTo(14 + 27 + 41 + 97);
    }

    @Test
    public void add_OtherDelimiter() {
        assertThat(StringCalculator.add("//;\n14;27\n41;97")).isEqualTo(14 + 27 + 41 + 97);
        assertThat(StringCalculator.add("//!\n14!27\n41!97")).isEqualTo(14 + 27 + 41 + 97);
    }

    @Test
    public void add_Negatives() {
        assertThatIllegalArgumentException().isThrownBy(() -> StringCalculator.add("//;\n14;-27\n41;-97"))
                .withMessageContaining("-27 -97");
    }
}