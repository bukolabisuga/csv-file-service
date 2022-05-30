package com.league.challenge;

import com.league.challenge.controllers.CSVFileController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;

@SpringBootTest("Tests the CSVFileController class")
public class CSVFileControllerTests {
    @Autowired
    CSVFileController controller;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertNotNull(controller);
    }

    /**
     * Test null file scenario for /echo
     *
     * @throws IOException
     */
    @Test
    public void testEchoController_nullFile() throws IOException {
        Assertions.assertThrows(NullPointerException.class, () -> controller.echoMatrix(null));
    }

    /**
     * Test empty csv file scenario for /echo
     *
     * @throws IOException
     */
    @Test
    public void testEchoController_emptyCSVFile() throws IOException {
        String expectedErrorMessage = "Unable to upload empty CSV file";
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> controller.echoMatrix(TestData.getEmptyMultipartCsvFile()));
        Assertions.assertEquals(expectedErrorMessage, illegalArgumentException.getMessage());
    }

    /**
     * Test invalid csv file scenario for /echo method
     *
     * @throws IOException
     */
    @Test
    public void testEchoController_invalidCSVFile() throws IOException {
        String expectedErrorMessage = "Please upload a valid CSV file";
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> controller.echoMatrix(TestData.getInvalidMultipartCsvFile()));
        Assertions.assertEquals(expectedErrorMessage, illegalArgumentException.getMessage());
    }

    /**
     * Test valid file scenario for /echo method
     *
     * @throws IOException
     */
    @Test
    public void testEchoController_validCSVFile() throws IOException {
        String result = controller.echoMatrix(TestData.getValidMultipartCsvFile());
        Assert.notNull(result, "echoMatrix result should not be null");
        Assert.isTrue(result.trim().equals(TestData.expectedResultForEcho().trim()), "expected and actual results for echoMatrix method should match");
    }

    /**
     * Test null file scenario for /invert
     *
     * @throws IOException
     */
    @Test
    public void testInvertController_nullFile() throws IOException {
        Assertions.assertThrows(NullPointerException.class, () -> controller.invertMatrix(null));
    }

    /**
     * Test empty file scenario for /invert method
     *
     * @throws IOException
     */
    @Test
    public void testInvertController_emptyCSVFile() throws IOException {
        String expectedErrorMessage = "Unable to upload empty CSV file";
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> controller.invertMatrix(TestData.getEmptyMultipartCsvFile()));
        Assertions.assertEquals(expectedErrorMessage, illegalArgumentException.getMessage());
    }

    /**
     * Test invalid file scenario for /invert
     *
     * @throws IOException
     */
    @Test
    public void testInvertController_invalidFile() throws IOException {
        String expectedErrorMessage = "Please upload a valid CSV file";
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> controller.invertMatrix(TestData.getInvalidMultipartCsvFile()));
        Assertions.assertEquals(expectedErrorMessage, illegalArgumentException.getMessage());
    }

    /**
     * Test valid file scenario for /invert
     *
     * @throws IOException
     */
    @Test
    public void testInvertController_validFile() throws IOException {
        String result = controller.invertMatrix(TestData.getValidMultipartCsvFile());
        Assert.notNull(result, "invertMatrix result should not be null");
        Assert.isTrue(result.trim().equals(TestData.expectedResultForInvert().trim()), "expected and actual results for invertMatrix method should match");
    }

    /**
     * Test null file scenario for /flatten
     *
     * @throws IOException
     */
    @Test
    public void testFlattenController_nullFile() throws IOException {
        Assertions.assertThrows(NullPointerException.class, () -> controller.flattenMatrix(null));
    }

    /**
     * Test empty file scenario for /flatten
     *
     * @throws IOException
     */
    @Test
    public void testFlattenController_emptyFile() throws IOException {
        String expectedErrorMessage = "Unable to upload empty CSV file";
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> controller.flattenMatrix(TestData.getEmptyMultipartCsvFile()));
        Assertions.assertEquals(expectedErrorMessage, illegalArgumentException.getMessage());
    }

    /**
     * Test invalid file scenario for /flatten
     *
     * @throws IOException
     */
    @Test
    public void testFlattenController_invalidFile() throws IOException {
        String expectedErrorMessage = "Please upload a valid CSV file";
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> controller.flattenMatrix(TestData.getInvalidMultipartCsvFile()));
        Assertions.assertEquals(expectedErrorMessage, illegalArgumentException.getMessage());
    }

    /**
     * Test valid file scenario for /flatten
     *
     * @throws IOException
     */
    @Test
    public void testFlattenController_validFile() throws IOException {
        String result = controller.flattenMatrix(TestData.getValidMultipartCsvFile());
        Assert.notNull(result, "flattenMatrix result should not be null");
        Assert.isTrue(result.trim().equals(TestData.expectedResultForFlatten().trim()), "expected and actual results for flattenMatrix method should match");
    }

    /**
     * Test null file scenario for /sum
     *
     * @throws IOException
     */
    @Test
    public void testSumController_nullFile() throws IOException {
        Assertions.assertThrows(NullPointerException.class, () -> controller.getSum(null));
    }

    /**
     * Test empty file scenario for /sum
     *
     * @throws IOException
     */
    @Test
    public void testSumController_emptyFile() throws IOException {
        String expectedErrorMessage = "Unable to upload empty CSV file";
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> controller.getSum(TestData.getEmptyMultipartCsvFile()));
        Assertions.assertEquals(expectedErrorMessage, illegalArgumentException.getMessage());
    }

    /**
     * Test invalid file scenario for /sum
     *
     * @throws IOException
     */
    @Test
    public void testSumController_invalidFile() throws IOException {
        String expectedErrorMessage = "Please upload a valid CSV file";
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> controller.getSum(TestData.getInvalidMultipartCsvFile()));
        Assertions.assertEquals(expectedErrorMessage, illegalArgumentException.getMessage());
    }

    /**
     * Test valid file scenario for /sum
     *
     * @throws IOException
     */
    @Test
    public void testSumController_validFile() throws IOException {
        Integer result = controller.getSum(TestData.getValidMultipartCsvFile());
        Assert.notNull(result, "getSum result should not be null");
        Assert.isTrue(result.equals(TestData.expectedResultForSum()), "expected and actual results for getSum method should match");
    }

    /**
     * Test null file scenario for /multiply
     *
     * @throws IOException
     */
    @Test
    public void testMultiplyController_nullFile() throws IOException {
        Assertions.assertThrows(NullPointerException.class, () -> controller.getProduct(null));
    }

    /**
     * Test empty file scenario for /multiply
     *
     * @throws IOException
     */
    @Test
    public void testMultiplyController_emptyFile() throws IOException {
        String expectedErrorMessage = "Unable to upload empty CSV file";
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> controller.getProduct(TestData.getEmptyMultipartCsvFile()));
        Assertions.assertEquals(expectedErrorMessage, illegalArgumentException.getMessage());
    }

    /**
     * Test invalid file scenario for /multiply
     *
     * @throws IOException
     */
    @Test
    public void testMultiplyController_invalidFile() throws IOException {
        String expectedErrorMessage = "Please upload a valid CSV file";
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> controller.getProduct(TestData.getInvalidMultipartCsvFile()));
        Assertions.assertEquals(expectedErrorMessage, illegalArgumentException.getMessage());
    }

    /**
     * Test valid file scenario for /multiply
     *
     * @throws IOException
     */
    @Test
    public void testMultiplyController_validFile() throws IOException {
        Integer result = controller.getProduct(TestData.getValidMultipartCsvFile());
        Assert.notNull(result, "getProduct result should not be null");
        Assert.isTrue(result.equals(TestData.expectedResultForMultiply()), "expected and actual results for getProduct method should match");
    }
}
