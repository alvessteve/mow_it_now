package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringValidatorTest {

    @Test
    public void empty_string(){
        assertTrue(StringValidator.isNullOrEmpty(""));
    }

    @Test
    public void null_string(){
        assertTrue(StringValidator.isNullOrEmpty(null));
    }

    @Test
    public void non_empty_string(){
        assertFalse(StringValidator.isNullOrEmpty("astring"));
    }

}