package domain.model;

import domain.model.mow.Coordinates;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GrassTest {

    @Test
    public void should_create_grass_with_valid_dimensions() {
        var dimensions = new Coordinates(5, 5);

        var grass = new Grass(dimensions);

        assertThat(grass).isNotNull();
        assertThat(grass.getDimension()).isEqualTo(dimensions);
    }

    @Test
    public void should_throw_exception_when_creating_grass_with_null_dimensions() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Grass(null);
        });
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, true",     // Bottom-left corner
        "5, 5, true",     // Top-right corner (within bounds)
        "0, 5, true",     // Top-left corner
        "5, 0, true",     // Bottom-right corner
        "2, 3, true",     // Middle position
        "3, 2, true"      // Another middle position
    })
    public void should_return_true_when_coordinates_are_within_bounds(int x, int y, boolean expected) {
        var grass = new Grass(new Coordinates(5, 5));
        var coordinates = new Coordinates(x, y);

        boolean result = grass.isWithinBounds(coordinates);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "-1, 0",    // Negative X
        "0, -1",    // Negative Y
        "-1, -1",   // Both negative
        "6, 5",     // X out of bounds (dimension is 5, so max valid is 5)
        "5, 6",     // Y out of bounds
        "6, 6",     // Both out of bounds
        "10, 10"    // Far out of bounds
    })
    public void should_return_false_when_coordinates_are_out_of_bounds(int x, int y) {
        var grass = new Grass(new Coordinates(5, 5));
        var coordinates = new Coordinates(x, y);

        boolean result = grass.isWithinBounds(coordinates);

        assertThat(result).isFalse();
    }

    @Test
    public void should_throw_exception_when_checking_bounds_with_null_coordinates() {
        var grass = new Grass(new Coordinates(5, 5));

        assertThrows(NullPointerException.class, () -> {
            grass.isWithinBounds(null);
        });
    }

    @Test
    public void should_handle_zero_dimension_grass() {
        var grass = new Grass(new Coordinates(0, 0));

        // Only (0,0) should be valid
        assertThat(grass.isWithinBounds(new Coordinates(0, 0))).isTrue();
        assertThat(grass.isWithinBounds(new Coordinates(1, 0))).isFalse();
        assertThat(grass.isWithinBounds(new Coordinates(0, 1))).isFalse();
    }

    @Test
    public void should_return_correct_string_representation() {
        var grass = new Grass(new Coordinates(5, 5));

        assertThat(grass.toString()).isEqualTo("5 5");
    }
}
