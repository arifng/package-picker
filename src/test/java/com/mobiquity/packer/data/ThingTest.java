package com.mobiquity.packer.data;

import com.mobiquity.data.Thing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Rana on 13/11/2021.
 */
public class ThingTest {
    private Thing thing;

    @BeforeEach
    public void setUp() {
        thing = new Thing();
        thing.setIndex(1);
        thing.setWeight(5);
        thing.setCost(2);
    }

    @Test
    public void isValidBeFalseWhenWeightIsNegative() {
        thing.setWeight(-3);

        Assertions.assertFalse(thing.isValid());
    }

    @Test
    public void isValidBeFalseWhenWeightIsGreaterThanMaxWeight() {
        thing.setWeight(105 * 100);

        Assertions.assertFalse(thing.isValid());
    }

    @Test
    public void isValidBeFalseWhenCostIsNegative() {
        thing.setCost(-3);

        Assertions.assertFalse(thing.isValid());
    }

    @Test
    public void isValidBeFalseWhenCostIsGreaterThanMaxWeight() {
        thing.setCost(105);

        Assertions.assertFalse(thing.isValid());
    }

    @Test
    public void isValidBeTrueWhenCostAndWeightIsGreaterThanZeroAndLessOrEqualToMaxValue() {
        Assertions.assertTrue(thing.isValid());

        // set weight to max value
        thing.setWeight(100 * 100);
        Assertions.assertTrue(thing.isValid());

        // set cost to max
        thing.setCost(100);
        Assertions.assertTrue(thing.isValid());
    }
}
