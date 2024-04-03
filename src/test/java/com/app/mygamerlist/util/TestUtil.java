package com.app.mygamerlist.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

public class TestUtil {

    public static String readJsonFile(ResourceLoader resourceLoader, String file) throws IOException {

        return new String(
                readAllBytes(
                        resourceLoader
                                .getResource(file)
                                .getFile()
                                .toPath()
                )
        );
    }
}
