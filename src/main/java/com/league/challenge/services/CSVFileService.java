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
import java.util.*;
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
//    public String echo(MultipartFile file) throws IOException {
//        String line = "";
//
//        StringBuilder finalMatrixString = new StringBuilder();
//
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//            while ((line = br.readLine()) != null)   //returns a Boolean value
//            {
//                finalMatrixString.append(line);
//                finalMatrixString.append("\n");
//            }
//        }
//
//        return finalMatrixString.toString().trim();
//    }
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
//        Collection<String[]> lines = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//            for (String line = br.readLine(); line != null; line = br.readLine()) {
//                lines.add(line.split(","));
//            }
//        }
//
//        String[][] originalArray = lines.toArray(new String[lines.size()][]);
//
//        int length = originalArray.length;
//
//        String[][] invertedArray = new String[length][length];
//
//        transpose(originalArray, invertedArray);
//
//        StringBuilder invertedMatrix = new StringBuilder();
//        for (int a = 0; a < length; a++) {
//            for (int b = 0; b < length; b++) {
//                invertedMatrix.append(invertedArray[a][b]);
//                invertedMatrix.append(",");
//            }
//            invertedMatrix.deleteCharAt(invertedMatrix.length() - 1);
//            invertedMatrix.append("\n");
//        }
//
//        return invertedMatrix.toString().trim();

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
//        String line = "";
//        StringBuilder finalMatrixString = new StringBuilder();
//
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//            while ((line = br.readLine()) != null)   //returns a Boolean value
//            {
//                finalMatrixString.append(line);
//                finalMatrixString.append(",");
//            }
//            finalMatrixString.deleteCharAt(finalMatrixString.length() - 1);
//        }
//
//        return finalMatrixString.toString().trim();

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
//        Collection<String[]> lines = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//            for (String line = br.readLine(); line != null; line = br.readLine()) {
//                lines.add(line.split(","));
//            }
//        }
//        String[][] matrixArray = lines.toArray(new String[lines.size()][]);
//
//        int[][] intArray = convertStringArrayToIntArray(matrixArray);
//
//        int add = 0;
//
//        for (int[] array : intArray) {
//            for (int a : array) {
//                add += a;
//            }
//        }
//
//        return add;

        //Builder to set up the CSV Parser object with relevant properties
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

    private void transpose(String[][] A, String[][] B) {
        int N = A.length;
        int i, j;
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                B[i][j] = A[j][i];
            }
        }
    }

    private int[][] convertStringArrayToIntArray(String[][] stringArray) {
        int[][] intArray = new int[stringArray.length][];
        for (int row = 0; row < stringArray.length; row++) {
            intArray[row] = new int[stringArray[row].length];
            for (int col = 0; col < stringArray[row].length; col++) {
                intArray[row][col] = Integer.parseInt(stringArray[row][col]);
            }
        }
        return intArray;
    }

}
