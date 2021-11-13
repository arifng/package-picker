package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Path;

/**
 * Created by Rana on 13/11/2021.
 */
public class PackerTest {
    String filePath;

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        URL resource = getClass().getClassLoader().getResource("example_input");
        assert resource != null;
        filePath = Path.of(resource.toURI()).toString();
    }
    @Test
    public void packShouldReturnDesiredResult() {
        try {
            String result = Packer.pack(filePath);

            StringBuilder sb = new StringBuilder();
            sb.append(4).append("\n");
            sb.append("-").append("\n");
            sb.append("2,7").append("\n");
            sb.append("8,9");

            Assertions.assertEquals(result, sb.toString());
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}
