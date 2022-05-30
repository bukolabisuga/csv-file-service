package com.league.challenge;

import com.league.challenge.services.CSVFileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.io.IOException;

@SpringBootTest("Tests the CSVFileService class")
@RunWith(SpringJUnit4ClassRunner.class)
class CSVFileServiceTests {

    @Autowired
    private CSVFileService csvFileService;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertNotNull(csvFileService);
    }

    /**
     * Test echo method
     *
     * @throws IOException
     */
    @Test
    public void testEcho() throws IOException {
        String result = csvFileService.echo(TestData.getValidMultipartCsvFile());
        Assert.notNull(result, "echo result should not be null");
        Assert.isTrue(result.trim().equals(TestData.expectedResultForEcho().trim()), "expected and actual results for echoString method should match");
    }

    /**
     * Test invert method
     *
     * @throws IOException
     */
    @Test
    public void testInvert() throws IOException {
        String result = csvFileService.invert(TestData.getValidMultipartCsvFile());
        Assert.notNull(result, "invert result should not be null");
        Assert.isTrue(result.trim().equals(TestData.expectedResultForInvert().trim()), "expected and actual results for invertString method should match");
    }

    /**
     * Test flatten method
     *
     * @throws IOException
     */
    @Test
    public void testFlatten() throws IOException {
        String result = csvFileService.flatten(TestData.getValidMultipartCsvFile());
        Assert.notNull(result, "flatten result should not be null");
        Assert.isTrue(result.trim().equals(TestData.expectedResultForFlatten().trim()), "expected and actual results for flattenString method should match");
    }

    /**
     * Test sum method
     *
     * @throws IOException
     */
    @Test
    public void testSum() throws IOException {
        Integer result = csvFileService.sum(TestData.getValidMultipartCsvFile());
        Assert.notNull(result, "sum result should not be null");
        Assert.isTrue(result.equals(TestData.expectedResultForSum()), "expected and actual results for sumString method should match");
    }

    /**
     * Test multiply method
     *
     * @throws IOException
     */
    @Test
    public void testMultiply() throws IOException {
        Integer result = csvFileService.multiply(TestData.getValidMultipartCsvFile());
        Assert.notNull(result, "multiply result should not be null");
        Assert.isTrue(result.equals(TestData.expectedResultForMultiply()), "expected and actual results for multiplyString method should match");
    }

}
