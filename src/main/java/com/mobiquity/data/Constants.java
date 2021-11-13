package com.mobiquity.data;

/**
 * Created by Rana on 13/11/2021.
 */
public interface Constants {
    //weight in float number with two digit fraction, so defined some constants to make it integer.
    int DECIMAL_POINT_MAX_LEN = 2;
    int DOUBLE_TO_INTEGER_MULTIPLIER = 100;
    int MAXIMUM_ALLOWED_WEIGHT = 100 * DOUBLE_TO_INTEGER_MULTIPLIER;

    int MAXIMUM_ALLOWED_COST = 100;

    String CURRENCY = "€";
}
