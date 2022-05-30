package com.league.challenge;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestData {
    public static MultipartFile getValidMultipartCsvFile() throws IOException {
        return new MockMultipartFile(
                "file",
                "matrix.csv",
                "text/csv",
                Files.readAllBytes(Paths.get("src/test/resources/matrix.csv"))
        );
    }

    public static MultipartFile getInvalidMultipartCsvFile() throws IOException {
        return new MockMultipartFile(
                "file",
                "league.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "League, Challenge".getBytes(StandardCharsets.UTF_8)
        );
    }

    public static MultipartFile getEmptyMultipartCsvFile() throws IOException {
        return new MockMultipartFile(
                "file",
                "".getBytes(StandardCharsets.UTF_8)
        );
    }

    public static String expectedResultForEcho(){
        return "1,2,3\r\n4,5,6\r\n7,8,9";
    }

    public static String expectedResultForInvert(){
        return "1,4,7\r\n2,5,8\r\n3,6,9";
    }

    public static String expectedResultForFlatten(){
        return "1,2,3,4,5,6,7,8,9";
    }

    public static Integer expectedResultForSum(){
        return 45;
    }

    public static Integer expectedResultForMultiply(){
        return 362880;
    }

}
