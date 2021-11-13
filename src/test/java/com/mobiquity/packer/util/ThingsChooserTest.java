package com.mobiquity.packer.util;

import com.mobiquity.data.PackageOfThing;
import com.mobiquity.data.Thing;
import com.mobiquity.util.ThingsChooser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by Rana on 13/11/2021.
 */
public class ThingsChooserTest {
    private PackageOfThing packageOfThing;
    private ThingsChooser thingsChooser;

    @BeforeEach
    public void setUp() {
        packageOfThing = new PackageOfThing();
        packageOfThing.setAllowedWeight(81*100);
        packageOfThing.setThings(Arrays.asList(createThing(1, 5338, 45),
                createThing(2, 8862, 98), createThing(3, 7848, 3),
                createThing(4, 7230, 76), createThing(5, 3018, 9),
                createThing(6, 4634, 48)));
        thingsChooser = new ThingsChooser();
    }

    private Thing createThing(int index, int weight, int cost) {
        Thing thing = new Thing();
        thing.setIndex(index);
        thing.setWeight(weight);
        thing.setCost(cost);
        return thing;
    }

    @SneakyThrows
    @Test
    public void chooseThingsShouldReturnIndexAsString() {
        String result  = thingsChooser.chooseThings(packageOfThing);
        Assertions.assertEquals("4", result);
    }
}
