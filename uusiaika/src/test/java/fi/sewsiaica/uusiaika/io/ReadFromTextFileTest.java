/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iah1016
 */
public class ReadFromTextFileTest {

    private Scanner fileReader;
    private ReadFromTextFile readFromTextFile;
    private File testFile;
    private final int linesInTestFile = 32;
    private final int ignorableLinesInTestFile = 3;

    public ReadFromTextFileTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        testFile = new File("src/main/resources/testfile.txt");
        fileReader = new Scanner("foo");
        readFromTextFile = new ReadFromTextFile(fileReader);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void yankTextFromFileThrowsFileNotFoundExceptionIfFileNotFound() {
        File fauxFile = new File("diipadaapa.foo");
        boolean throwsEx = false;
        try {
            readFromTextFile.yankTextFromFile(fauxFile);
        } catch (FileNotFoundException e) {
            throwsEx = true;
        }
        assertEquals(true, throwsEx);
    }

    @Test
    public void yankTextFromFileOpensTestFile() {
        boolean throwsEx = false;
        try {
            readFromTextFile.yankTextFromFile(testFile);
        } catch (FileNotFoundException e) {
            throwsEx = true;
        }
        assertEquals(false, throwsEx);
    }

    @Test
    public void yankTextFromFileReturnsArrayList() throws
            FileNotFoundException {
        Object resObj = readFromTextFile.yankTextFromFile(testFile);
        Object expectedObj = new ArrayList<>();
        assertEquals(expectedObj.getClass(), resObj.getClass());
    }

    @Test
    public void yankTextFromFileReturnsCorrectNumberOfLines() throws
            FileNotFoundException {
        int expected = linesInTestFile - ignorableLinesInTestFile;
        List<String> list = readFromTextFile.yankTextFromFile(testFile);
        assertEquals(expected, list.size());
    }

    @Test
    public void isIgnorableLineReturnsTrueIfLineIsEmpty() {
        String line = "";
        assertEquals(true, readFromTextFile.isIgnorableLine(line));
    }

    @Test
    public void isIgnorableLineReturnsTrueIfCharAtZeroIsHashOrWhiteSpace() {
        String line = "#";
        assertEquals(true, readFromTextFile.isIgnorableLine(line));
        line = " ";
        assertEquals(true, readFromTextFile.isIgnorableLine(line));
    }

    @Test
    public void isIgnorableReturnsFalseWithAnotherChar() {
        String line = "c";
        assertEquals(false, readFromTextFile.isIgnorableLine(line));
    }

    @Test
    public void returnNewLineIffReaderIsClosedReturnsNullIfReaderOpen() {
        List<String> list = new ArrayList<>();
        list.add("some text");
        assertEquals(null,
                readFromTextFile.returnNewLineIffReaderIsClosed(list));
    }
}
