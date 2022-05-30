package com.league.challenge.services;

import com.league.challenge.utils.CSVUtil;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class CSVFileService implements ICSVFileService {

    /**
     * Method returns the matrix as a string in matrix format
     *
     * @param file Multipart File
     * @return matrix as a string
     * @throws IOException
     */
    @Override
    public String echo(MultipartFile file) throws IOException {
        //builds the CSV Parser object with properties
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(CSVUtil.csvDelimiter.charAt(0))
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        //try-with-resources automatically closes each IO resource after execution
        try (InputStream inputStream = file.getInputStream();
             BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, CSVUtil.encodingScheme));
             CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(csvParser).build();) {

            //read all matrix values
            //stream operation joins the inner column with the delimiter "," and rows are separated by a newline
            return csvReader.readAll()
                    .stream()
                    .map(column -> String.join(CSVUtil.csvDelimiter, column))
                    .collect(Collectors.joining(CSVUtil.newLine));
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Method returns the matrix as a string in matrix format where the columns and rows are inverted
     *
     * @param file Multipart File
     * @return matrix as a string
     * @throws IOException
     */
    @Override
    public String invert(MultipartFile file) throws IOException {

        //builds the CSV Parser object with properties
        List<String[]> matrixList;
        //Builder to set up the CSV Parser object with relevant properties
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(CSVUtil.csvDelimiter.charAt(0))
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        //try-with-resources automatically closes each IO resource after execution
        try (InputStream inputStream = file.getInputStream();
             BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, CSVUtil.encodingScheme));
             CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(csvParser).build();) {

            //reads all values in the matrix
            matrixList = csvReader.readAll();

            //creates a list of iterators for all rows in the array
            List<Iterator<String>> iterators = matrixList
                    .stream()
                    .map(it-> Arrays.stream(it).iterator())
                    .collect(Collectors.toList());

            //loops through each iterator while transposing the rows and columns
            List<List<String>> result = IntStream.range(0, matrixList.size())
                    .mapToObj(n -> iterators.stream()
                            .filter(it -> it.hasNext())
                            .map(m -> m.next())
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());

            //joins inner column with the delimiter "," and rows are separated by a newline
            return result
                    .stream()
                    .map(column -> String.join(CSVUtil.csvDelimiter, column))
                    .collect(Collectors.joining(CSVUtil.newLine));
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method returns the matrix as a 1 line string, with values separated by commas
     *
     * @param file Multipart File
     * @return matrix as a string
     * @throws IOException
     */
    @Override
    public String flatten(MultipartFile file) throws IOException {
        //builds the CSV Parser object with relevant properties
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(CSVUtil.csvDelimiter.charAt(0))
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        //try-with-resources statement automatically closes each IO resource after execution
        try (InputStream inputStream = file.getInputStream();
             BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, CSVUtil.encodingScheme));
             CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(csvParser).build();) {

            //reads all matrix values
            //stream operation flattens matrix into one line with no new line separating rows
            return csvReader.readAll()
                    .stream()
                    .flatMap(Stream::of)
                    .collect(Collectors.joining(CSVUtil.csvDelimiter));
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method returns the sum of the integers in the matrix
     *
     * @param file Multipart File
     * @return matrix as a string
     * @throws IOException
     */
    @Override
    public Integer sum(MultipartFile file) throws IOException {
        //builds the CSV Parser object with relevant properties
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(CSVUtil.csvDelimiter.charAt(0))
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        //Try-with-resources statement which automatically closes each IO resource declared after execution
        try (InputStream inputStream = file.getInputStream();
             BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, CSVUtil.encodingScheme));
             CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(csvParser).build();) {

            //reads all matrix values
            //stream operation sums all the integers
            return csvReader.readAll()
                    .stream()
                    .flatMap(Stream::of)
                    .mapToInt(Integer::valueOf).sum();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method returns the product of the integers in the matrix
     *
     * @param file Multipart File
     * @return matrix as a string
     * @throws IOException
     */
    @Override
    public Integer multiply(MultipartFile file) throws IOException {
        //builds the CSV Parser object with relevant properties
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(CSVUtil.csvDelimiter.charAt(0))
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        //try-with-resources automatically closes each IO resource after execution
        try (InputStream inputStream = file.getInputStream();
             BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, CSVUtil.encodingScheme));
             CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(csvParser).build();) {

            //read all values in matrix
            //stream operation calculates the product of the integers
            return csvReader.readAll()
                    .stream()
                    .flatMap(Stream::of)
                    .mapToInt(Integer::valueOf)
                    .reduce(1, (a, b) -> a * b);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
