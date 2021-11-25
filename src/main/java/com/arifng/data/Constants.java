package com.arifng.data;

/**
 * Created by Rana on 13/11/2021.
 */
public interface Constants {
    //weight in float number with two digit fraction, so defined some constants to make it integer.
    // assume two decimal point
    int DECIMAL_POINT_MAX_LEN = 2;
    // make integer from two decimal point number
    int DOUBLE_TO_INTEGER_MULTIPLIER = 100;
    // as we make decimal to int by multiple 100, so maximum allowed weight also need to multiply with 100
    int MAXIMUM_ALLOWED_WEIGHT = 100 * DOUBLE_TO_INTEGER_MULTIPLIER;

    int MAXIMUM_ALLOWED_COST = 100;

    String CURRENCY = "€";
}
