package domain.model.mow;

import domain.exceptions.OutsideGrassMoveException;
import domain.model.Grass;
import domain.model.mow.orientation.Orientation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MowTest {

    @Test
    public void should_create_mow_with_valid_position() {
        var position = new Position(new Coordinates(1, 2), Orientation.NORTH);

        var mow = new Mow(position);

        assertThat(mow).isNotNull();
        assertThat(mow.getPosition()).isEqualTo(position);
    }

    @Test
    public void should_throw_exception_when_creating_mow_with_null_position() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Mow(null);
        });
    }

    @Test
    public void should_rotate_clockwise() {
        var position = new Position(new Coordinates(1, 2), Orientation.NORTH);
        var mow = new Mow(position);

        mow.rotateClockwise();

        assertThat(mow.getPosition().getOrientation()).isEqualTo(Orientation.EAST);
    }

    @Test
    public void should_rotate_counterclockwise() {
        var position = new Position(new Coordinates(1, 2), Orientation.NORTH);
        var mow = new Mow(position);

        mow.rotateCounterclockwise();

        assertThat(mow.getPosition().getOrientation()).isEqualTo(Orientation.WEST);
    }

    @Test
    public void should_move_forward_when_within_bounds() {
        var position = new Position(new Coordinates(1, 2), Orientation.NORTH);
        var mow = new Mow(position);
        var grass = new Grass(new Coordinates(5, 5));

        mow.move(grass);

        assertThat(mow.getPosition().getCoordinates()).isEqualTo(new Coordinates(1, 3));
        assertThat(mow.getPosition().getOrientation()).isEqualTo(Orientation.NORTH);
    }

    @Test
    public void should_throw_exception_when_moving_out_of_bounds() {
        var position = new Position(new Coordinates(5, 5), Orientation.NORTH);
        var mow = new Mow(position);
        var grass = new Grass(new Coordinates(5, 5));

        assertThrows(OutsideGrassMoveException.class, () -> {
            mow.move(grass);
        });
    }

    @Test
    public void should_not_change_position_when_move_fails() {
        var initialPosition = new Position(new Coordinates(5, 5), Orientation.NORTH);
        var mow = new Mow(initialPosition);
        var grass = new Grass(new Coordinates(5, 5));

        try {
            mow.move(grass);
        } catch (OutsideGrassMoveException e) {
            // Expected exception
        }

        assertThat(mow.getPosition()).isEqualTo(initialPosition);
    }

    @Test
    public void should_return_correct_string_representation() {
        var position = new Position(new Coordinates(1, 2), Orientation.NORTH);
        var mow = new Mow(position);

        assertThat(mow.toString()).isEqualTo("1 2 N");
    }

    @Test
    public void should_handle_multiple_rotations() {
        var position = new Position(new Coordinates(1, 2), Orientation.NORTH);
        var mow = new Mow(position);

        mow.rotateClockwise();  // EAST
        mow.rotateClockwise();  // SOUTH
        mow.rotateClockwise();  // WEST
        mow.rotateClockwise();  // NORTH

        assertThat(mow.getPosition().getOrientation()).isEqualTo(Orientation.NORTH);
    }
}
