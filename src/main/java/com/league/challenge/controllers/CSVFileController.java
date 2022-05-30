
package com.league.challenge.controllers;

import com.league.challenge.services.CSVFileService;
import com.league.challenge.utils.CSVValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
@RestController
public class CSVFileController {

    @Autowired
    CSVFileService csvFileService;

    /**
     * Exposes (http://localhost:8080/echo) endpoint with HTTP POST method
     *
     * @param file Multipart File
     * @return Returns the matrix as a string in matrix format
     * @throws IOException
     */
    @PostMapping("/echo")
    @ResponseStatus(HttpStatus.OK)
    public String echoMatrix(@RequestParam("file") MultipartFile file) throws IOException {
        CSVValidator.validateCSVFile(file);
        return csvFileService.echo(file);
    }

    /**
     * Exposes (http://localhost:8080/invert) endpoint with HTTP POST method
     *
     * @param file Multipart File
     * @return Returns the matrix as a string in matrix format where the columns and rows are inverted
     * @throws IOException
     */
    @PostMapping("/invert")
    @ResponseStatus(HttpStatus.OK)
    public String invertMatrix(@RequestParam("file") MultipartFile file) throws IOException {
        CSVValidator.validateCSVFile(file);
        return csvFileService.invert(file);
    }

    /**
     * Exposes (http://localhost:8080/flatten) endpoint with HTTP POST method
     *
     * @param file Multipart File
     * @return Returns the matrix as a 1 line string in matrix format where the columns and rows are inverted
     * @throws IOException
     */
    @PostMapping("/flatten")
    @ResponseStatus(HttpStatus.OK)
    public String flattenMatrix(@RequestParam("file") MultipartFile file) throws IOException {

        CSVValidator.validateCSVFile(file);
        return csvFileService.flatten(file);
    }

    /**
     * Exposes (http://localhost:8080/sum) endpoint with HTTP POST method
     *
     * @param file Multipart File
     * @return Returns the sum of the integers in the matrix
     * @throws IOException
     */
    @PostMapping("/sum")
    @ResponseStatus(HttpStatus.OK)
    public Integer getSum(@RequestParam("file") MultipartFile file) throws IOException {
        CSVValidator.validateCSVFile(file);
        return csvFileService.sum(file);
    }

    /**
     * Exposes (http://localhost:8080/multiply) endpoint with HTTP POST method
     *
     * @param file Multipart File
     * @return Returns the product of the integers in the matrix
     * @throws IOException
     */
    @PostMapping("/multiply")
    @ResponseStatus(HttpStatus.OK)
    public Integer getProduct(@RequestParam("file") MultipartFile file) throws IOException {
        CSVValidator.validateCSVFile(file);
        return csvFileService.multiply(file);
    }

    /**
     * Controller class exception handler
     * Handles exceptions thrown by request mapping methods
     * @param exception Exception
     * @param response HttpServletResponse
     * @return String
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String errorHandler(Exception exception, HttpServletResponse response){
        return exception.getMessage();
    }

}
