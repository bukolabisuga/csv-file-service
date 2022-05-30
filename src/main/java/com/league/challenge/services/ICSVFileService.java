package com.league.challenge.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ICSVFileService {
    String echo(MultipartFile file) throws IOException;
    String invert(MultipartFile file) throws IOException;
    String flatten(MultipartFile file) throws IOException;
    Integer sum(MultipartFile file) throws IOException;
    Integer multiply(MultipartFile file) throws IOException;
}
