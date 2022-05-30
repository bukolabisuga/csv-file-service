package com.league.challenge.utils;

import com.google.common.base.Preconditions;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class CSVValidator {


    /**
     * Method validates multipart csv file
     *
     * @param file Multipart File
     * @return void
     * @throws IllegalArgumentException
     */
    public static void validateCSVFile(MultipartFile file){
        Preconditions.checkArgument(!file.isEmpty(), "Unable to upload empty CSV file");
        Preconditions.checkArgument(file.getOriginalFilename().endsWith(CSVUtil.fileType), "Please upload a valid CSV file");
    }
}
