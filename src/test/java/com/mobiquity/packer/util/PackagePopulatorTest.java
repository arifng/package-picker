package com.mobiquity.packer.util;

import com.mobiquity.data.PackageOfThing;
import com.mobiquity.exception.APIException;
import com.mobiquity.util.PackagePopulator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Rana on 13/11/2021.
 */
public class PackagePopulatorTest {
    private String contentInvalidMsg = "File content is invalid!";
    private PackagePopulator packagePopulator;
    private String line;

    @BeforeEach
    public void setUp() {
        packagePopulator = new PackagePopulator();
        line = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
    }

    @Test
    public void populatePackageOfThingShouldReturnObject() {
        PackageOfThing packageOfThing = null;
        try {
            packageOfThing = packagePopulator.populatePackageOfThing(line);
        } catch (APIException e) {
            e.printStackTrace();
        }

        Assertions.assertNotNull(packageOfThing);
    }

    @Test
    public void populatePackageOfThingShouldSetAllowedWet() {
        PackageOfThing packageOfThing = null;
        try {
            packageOfThing = packagePopulator.populatePackageOfThing(line);
        } catch (APIException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(8100, packageOfThing.getAllowedWeight());
    }

    @Test
    public void populatePackageOfThingShouldSetThings() {
        PackageOfThing packageOfThing = null;
        try {
            packageOfThing = packagePopulator.populatePackageOfThing(line);
        } catch (APIException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(6, packageOfThing.getThings().size());
    }

    @Test
    public void populatePackageOfThingShouldConsiderWeightAsTwoDigitDecimalNumber() {
        PackageOfThing packageOfThing = null;
        try {
            packageOfThing = packagePopulator.populatePackageOfThing("81 : (1,53,€45) (2,12.1,€45)");
        } catch (APIException e) {
            e.printStackTrace();
        }

        String firstData = String.valueOf(packageOfThing.getThings().get(0).getWeight());
        String secondData = String.valueOf(packageOfThing.getThings().get(1).getWeight());
        Assertions.assertEquals(4, firstData.length());
        Assertions.assertEquals(4, secondData.length());
    }

    @Test
    public void populatePackageOfThingShouldThrowsExceptionWhenMultipleColonFound() {
        Exception exception = Assertions.assertThrows(APIException.class, () -> {
            packagePopulator.populatePackageOfThing("81.0 : (1,53,€45) :");
        });

        Assertions.assertEquals(contentInvalidMsg, exception.getMessage());
    }

    @Test
    public void populatePackageOfThingShouldThrowsExceptionWhenAllowedWeighIsInteger() {
        Exception exception = Assertions.assertThrows(APIException.class, () -> {
            packagePopulator.populatePackageOfThing("81.0 : (1,53,€45)");
        });

        Assertions.assertEquals(contentInvalidMsg, exception.getMessage());
    }

    @Test
    public void populatePackageOfThingShouldThrowsExceptionWhenItIsNotValid() {
        Exception exception = Assertions.assertThrows(APIException.class, () -> {
            packagePopulator.populatePackageOfThing("105 : (1,53,€45)");
        });

        Assertions.assertEquals(contentInvalidMsg, exception.getMessage());
    }

    @Test
    public void populatePackageOfThingShouldThrowsExceptionWhenAnyOfThingsWeightIsNotValid() {
        Exception exception = Assertions.assertThrows(APIException.class, () -> {
            packagePopulator.populatePackageOfThing("81 : (1,105.25,€45)");
        });

        Assertions.assertEquals(contentInvalidMsg, exception.getMessage());
    }

    @Test
    public void populatePackageOfThingShouldThrowsExceptionWhenAnyOfThingsCostIsNotValid() {
        Exception exception = Assertions.assertThrows(APIException.class, () -> {
            packagePopulator.populatePackageOfThing("81 : (1,65.52,€105)");
        });

        Assertions.assertEquals(contentInvalidMsg, exception.getMessage());
    }
}
