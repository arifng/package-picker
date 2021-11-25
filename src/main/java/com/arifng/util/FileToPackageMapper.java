package com.arifng.util;

import com.arifng.data.PackageOfThing;
import com.arifng.exception.APIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rana on 13/11/2021.
 */
public class FileToPackageMapper {
    /**
     * This method will read the file as line by line
     * and convert it to list of package through package populator
     * @param filePath
     * @return
     * @throws APIException
     */
    public List<PackageOfThing> mapFileToPackage(String filePath) throws APIException {
        List<String> lines = readContents(filePath);

        List<PackageOfThing> packageOfThings = new ArrayList<>();
        PackagePopulator packagePopulator = new PackagePopulator();
        for (String line : lines) {
            // hide complexities of populating and validating packages
            packageOfThings.add(packagePopulator.populatePackageOfThing(line));
        }

        return packageOfThings;
    }

    /**
     * Read file, if path not provided or file isn't readable then
     * this method will throw APIException
     * @param filePath
     * @return
     * @throws APIException
     */
    private List<String> readContents(String filePath) throws APIException {
        if(filePath == null || filePath.trim().isEmpty()) {
            throw new APIException("Filepath is not provided!");
        }

        Path path = Paths.get(filePath);
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new APIException("File is not readable!");
        }
        return lines;
    }
}
