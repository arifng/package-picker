package com.mobiquity.util;

import com.mobiquity.data.PackageOfThing;
import com.mobiquity.data.Thing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rana on 13/11/2021.
 */
public class ThingsChooser {
    public String chooseThings(PackageOfThing packageOfThing) {

        List<Thing> desiredThings = getDesiredThings(packageOfThing.getAllowedWeight(), packageOfThing.getThings());

        List<Integer> indexes = desiredThings.stream()
                .map(Thing::getIndex)
                .sorted()
                .collect(Collectors.toList());

        return indexes.size() > 0 ? indexes.stream()
                .map(i -> i + "")
                .collect(Collectors.joining(",")) :
                "-";
    }

    private List<Thing> getDesiredThings(int allowedWeight,
                                         List<Thing> things) {
        Collections.sort(things);
        int[][] maxThingMatrix = generateMaximumThingsMatrix(allowedWeight, things);

        int i = things.size();
        int j = allowedWeight;
        List<Thing> desiredThings = new ArrayList<>();
        while (i > 0 && j > 0) {
            if(maxThingMatrix[i][j] != maxThingMatrix[i-1][j]) {
                desiredThings.add(things.get(i - 1));
                j = j - things.get(i - 1).getWeight();
                i--;
            } else {
                i--;
            }
        }
        return desiredThings;
    }

    private int[][] generateMaximumThingsMatrix(int maxWeight,
                                                List<Thing> things) {
        int[][] matrix = new int[things.size() + 1][maxWeight + 1];

        for(int thingIndex = 1; thingIndex <= things.size(); thingIndex++) {
            for(int weight = 1; weight <= maxWeight; weight++) {
                Thing currentThing = things.get(thingIndex - 1);
                if (currentThing.getWeight() <= weight) {
                    matrix[thingIndex][weight] = Math.max(currentThing.getCost() +
                            matrix[thingIndex - 1][weight - currentThing.getWeight()],
                            matrix[thingIndex - 1][weight]);
                } else {
                    matrix[thingIndex][weight] = matrix[thingIndex - 1][weight];
                }
            }
        }

        return matrix;
    }
}
