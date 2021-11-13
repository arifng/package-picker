package com.mobiquity.data;

import lombok.Data;

/**
 * Created by Rana on 13/11/2021.
 *
 * This class holds thing's data ie index, weight (converted from double to int) and cost
 */
@Data
public class Thing implements Comparable<Thing> {
    private int index;
    private int weight;
    private int cost;

    public boolean isValid() {
        return weight > 0 && weight <= Constants.MAXIMUM_ALLOWED_WEIGHT &&
                cost > 0 && cost <= Constants.MAXIMUM_ALLOWED_COST;
    }

    /**
     * This method sort things object based on weight in ascending order
     * as one of the requirements is choose lower weighted thing if cost is equal.
     * Algorithm will choose this from thing 1 to N i.e it choose lower weighted thing at first
     * @param o
     * @return
     */
    @Override
    public int compareTo(Thing o) {
        return Double.compare(this.getWeight(), o.getWeight());
    }
}
