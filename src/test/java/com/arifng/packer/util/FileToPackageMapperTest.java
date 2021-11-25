package com.arifng.packer.util;

import com.arifng.data.PackageOfThing;
import com.arifng.exception.APIException;
import com.arifng.util.FileToPackageMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Rana on 13/11/2021.
 */
public class FileToPackageMapperTest {
    private String filePath;
    private FileToPackageMapper fileToPackageMapper;

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        URL resource = getClass().getClassLoader().getResource("example_input");
        assert resource != null;
        filePath = Path.of(resource.toURI()).toString();
        fileToPackageMapper = new FileToPackageMapper();
    }

    @Test
    public void mapFileToPackageShouldNotReturnNullList() {
        List<PackageOfThing> packageOfThings = null;
        try {
            packageOfThings = fileToPackageMapper.mapFileToPackage(filePath);
        } catch (APIException e) {
            e.printStackTrace();
        }

        Assertions.assertNotNull(packageOfThings);
    }

    @Test
    public void mapFileToPackageShouldReturnPackageList() {
        List<PackageOfThing> packageOfThings = null;
        try {
            packageOfThings = fileToPackageMapper.mapFileToPackage(filePath);
        } catch (APIException e) {
            e.printStackTrace();
        }

        Assertions.assertFalse(packageOfThings.isEmpty());
    }

    @Test
    public void mapFileToPackageShouldReturnExpectedSizeOfPackageList() {
        List<PackageOfThing> packageOfThings = null;
        try {
            packageOfThings = fileToPackageMapper.mapFileToPackage(filePath);
        } catch (APIException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(packageOfThings.size(), 4);
    }

    @Test
    public void mapFileToPackageShouldThrowsExceptionWhenPathIsEmpty() {
        Exception exception = Assertions.assertThrows(APIException.class, () -> {
            fileToPackageMapper.mapFileToPackage("");
        });

        Assertions.assertEquals("Filepath is not provided!", exception.getMessage());
    }
}
