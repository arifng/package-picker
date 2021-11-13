package com.mobiquity.util;

import com.mobiquity.data.Constants;
import com.mobiquity.data.PackageOfThing;
import com.mobiquity.data.Thing;
import com.mobiquity.exception.APIException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rana on 13/11/2021.
 */
public class PackagePopulator {
    private String contentInvalidMsg = "File content is invalid!";

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

    private List<Thing> populateThings(String thingsStr) throws APIException {
        Matcher matcher = Pattern.compile("\\((.*?)\\)").matcher(thingsStr);

        List<Thing> things = new ArrayList<>();
        while (matcher.find()) {
            String group = matcher.group(1);
            things.add(populateThing(group));
        }
        return things;
    }

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

    private int getWeightAsInt(String weightStr) throws APIException {
        if(weightStr.contains(".") &&
                weightStr.substring(weightStr.indexOf(".") + 1).length() > Constants.DECIMAL_POINT_MAX_LEN) {
            throw new APIException(contentInvalidMsg);
        }

        weightStr = String.format("%." + Constants.DECIMAL_POINT_MAX_LEN + "f", Double.parseDouble(weightStr));

        return (int) (Double.parseDouble(weightStr) * Constants.DOUBLE_TO_INTEGER_MULTIPLIER);
    }
}
