package com.mobiquity.packer;

import com.mobiquity.data.PackageOfThing;
import com.mobiquity.exception.APIException;
import com.mobiquity.util.FileToPackageMapper;
import com.mobiquity.util.ThingsChooser;

import java.util.ArrayList;
import java.util.List;

public class Packer {
    private Packer() {
    }

    public static String pack(String filePath) throws APIException {
        FileToPackageMapper fileToPackageMapper = new FileToPackageMapper();
        List<PackageOfThing> packageOfThings = fileToPackageMapper.mapFileToPackage(filePath);

        ThingsChooser thingsChooser = new ThingsChooser();
        List<String> results = new ArrayList<>();

        for (PackageOfThing packageOfThing : packageOfThings) {
          results.add(thingsChooser.chooseThings(packageOfThing));
        }

        return String.join("\n", results);
    }
}
