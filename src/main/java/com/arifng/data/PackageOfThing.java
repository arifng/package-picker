package com.arifng.data;

import lombok.Data;

import java.util.List;

/**
 * Created by Rana on 13/11/2021.
 *
 * This class holds package data ie maximum allowed weight and list of things
 */
@Data
public class PackageOfThing {
    private int allowedWeight;
    private List<Thing> things;

    public boolean isValid() {
        return allowedWeight > 0 && allowedWeight <= Constants.MAXIMUM_ALLOWED_WEIGHT &&
                things != null && !things.isEmpty();
    }
}
