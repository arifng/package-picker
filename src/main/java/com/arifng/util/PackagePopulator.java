package com.arifng.util;

import com.arifng.data.Constants;
import com.arifng.data.PackageOfThing;
import com.arifng.data.Thing;
import com.arifng.exception.APIException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rana on 13/11/2021.
 */
public class PackagePopulator {
    // common message for invalid content
    private String contentInvalidMsg = "File content is invalid!";

    /**
     * Generate Package object from string input, also validate package.
     * if package is invalid, then this method throw exception
     * @param line
     * @return
     * @throws APIException
     */
    public PackageOfThing populatePackageOfThing(String line) throws APIException {
        String[] splitStr = line.split(":");

        if(splitStr.length != 2) {
            throw new APIException(contentInvalidMsg);
        }

        PackageOfThing packageOfThing = new PackageOfThing();
        try {
            packageOfThing.setAllowedWeight(Integer.parseInt(splitStr[0].trim()) * 100);
            packageOfThing.setThings(populateThings(splitStr[1]));
        } catch (NumberFormatException exception) {
            throw new APIException(contentInvalidMsg);
        }

        if(!packageOfThing.isValid()) {
            throw new APIException(contentInvalidMsg);
        }

        return packageOfThing;
    }

    /**
     * Generate list of things, using regex to extract data by ()
     * Only get all data which is enclosed by ()
     * @param thingsStr
     * @return
     * @throws APIException
     */
    private List<Thing> populateThings(String thingsStr) throws APIException {
        Matcher matcher = Pattern.compile("\\((.*?)\\)").matcher(thingsStr);

        List<Thing> things = new ArrayList<>();
        while (matcher.find()) {
            String group = matcher.group(1);
            things.add(populateThing(group));
        }
        return things;
    }

    /**
     * Generate Thing object
     *
     * @param thingStr
     * @return
     * @throws APIException
     */
    private Thing populateThing(String thingStr) throws APIException {
        String[] splitStr = thingStr.split(",");
        Thing thing = new Thing();
        thing.setIndex(Integer.parseInt(splitStr[0].trim()));
        thing.setWeight(getWeightAsInt(splitStr[1].trim()));
        thing.setCost(Integer.parseInt(splitStr[2].trim().replace(Constants.CURRENCY, "")));

        if (!thing.isValid()) {
            throw new APIException(contentInvalidMsg);
        }

        return thing;
    }

    /**
     * Handle two decimal point number, assume that maximum decimal point is two.
     * It still valid for number 12, 12.0, 12.1, 12.12, but no for 12.252
     * Also make it to int, as thingChooser algorithm can't handle double value.
     * @param weightStr
     * @return
     * @throws APIException
     */
    private int getWeightAsInt(String weightStr) throws APIException {
        if(weightStr.contains(".") &&
                weightStr.substring(weightStr.indexOf(".") + 1).length() > Constants.DECIMAL_POINT_MAX_LEN) {
            throw new APIException(contentInvalidMsg);
        }

        weightStr = String.format("%." + Constants.DECIMAL_POINT_MAX_LEN + "f", Double.parseDouble(weightStr));

        return (int) (Double.parseDouble(weightStr) * Constants.DOUBLE_TO_INTEGER_MULTIPLIER);
    }
}
