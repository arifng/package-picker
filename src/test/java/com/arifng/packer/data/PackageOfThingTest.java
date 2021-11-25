package com.arifng.packer.data;

import com.arifng.data.PackageOfThing;
import com.arifng.data.Thing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Rana on 13/11/2021.
 */
public class PackageOfThingTest {
    private PackageOfThing packageOfThing;

    @BeforeEach
    public void setUp() {
        packageOfThing = new PackageOfThing();
        packageOfThing.setAllowedWeight(120);
        packageOfThing.setThings(Arrays.asList(new Thing()));
    }

    @Test
    public void isValidBeFalseWhenAllowedWeightIsNegative() {
        packageOfThing.setAllowedWeight(-1);
        Assertions.assertFalse(packageOfThing.isValid());
    }

    @Test
    public void isValidBeFalseWhenAllowedWeightIsZero() {
        packageOfThing.setAllowedWeight(0);
        Assertions.assertFalse(packageOfThing.isValid());
    }

    @Test
    public void isValidBeFalseWhenAllowedWeightIsGreaterThanMaxWeight() {
        packageOfThing.setAllowedWeight(105 * 100);
        Assertions.assertFalse(packageOfThing.isValid());
    }

    @Test
    public void isValidBeFalseWhenThingsIsNull() {
        packageOfThing.setThings(null);
        Assertions.assertFalse(packageOfThing.isValid());
    }

    @Test
    public void isValidBeFalseWhenThingsIsEmpty() {
        packageOfThing.setThings(Collections.emptyList());
        Assertions.assertFalse(packageOfThing.isValid());
    }

    @Test
    public void isValidBeTrueWhenWeighWithinRangeAndThingsNotEmpty() {
        Assertions.assertTrue(packageOfThing.isValid());
    }
}
