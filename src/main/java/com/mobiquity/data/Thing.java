package com.mobiquity.data;

import lombok.Data;

/**
 * Created by Rana on 13/11/2021.
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

    @Override
    public int compareTo(Thing o) {
        return Double.compare(this.getWeight(), o.getWeight());
    }
}
