/*
 * Copyright (C) 2016 Ilja Häkkinen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fi.sewsiaica.uusiaika.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
public class ReadFromInputStreamTest {

    private ReadFromInputStream readFromInputStream;
    private InputStream testInputStream;
    private final int linesInTestFile = 32;
    private final int ignorableLinesInTestFile = 3;

    public ReadFromInputStreamTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        String fileName = "src/test/filesfortests/testfile.txt";
        try {
            testInputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        readFromInputStream = new ReadFromInputStream();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void yankTextFromFileThrowsNullPointerExceptionIfFileNotFound()
            throws IOException {
        boolean throwEx = false;
        try {
            InputStream testInput
                    = this.getClass().getClassLoader()
                            .getResourceAsStream("foo");

            readFromInputStream.yankTextFromFile(testInput);
        } catch (NullPointerException e) {
            throwEx = true;
        }
        assertEquals(true, throwEx);
    }
    
    @Test
    public void yankTextFromFileOpensTestFile() throws IOException {
        boolean throwsEx = false;
        try {
            readFromInputStream.yankTextFromFile(testInputStream);
        } catch (NullPointerException e) {
            throwsEx = true;
        }
        assertEquals(false, throwsEx);
    }
    
    @Test
    public void yankTextFromFileReturnsArrayList() throws
            NullPointerException, IOException {
        Object resObj = readFromInputStream.yankTextFromFile(testInputStream);
        Object expectedObj = new ArrayList<>();
        assertEquals(expectedObj.getClass(), resObj.getClass());
    }

    @Test
    public void yankTextFromFileReturnsCorrectNumberOfLines() throws
            NullPointerException, IOException {
        int expected = linesInTestFile - ignorableLinesInTestFile;
        List<String> list = readFromInputStream.yankTextFromFile(
                testInputStream);
        assertEquals(expected, list.size());
    }
    
    @Test
    public void isIgnorableLineReturnsTrueIfLineIsEmpty() {
        String line = "";
        assertEquals(true, readFromInputStream.isIgnorableLine(line));
    }

    @Test
    public void isIgnorableLineReturnsTrueIfCharAtZeroIsHashOrWhiteSpace() {
        String line = "#";
        assertEquals(true, readFromInputStream.isIgnorableLine(line));
        line = " ";
        assertEquals(true, readFromInputStream.isIgnorableLine(line));
    }

    @Test
    public void isIgnorableReturnsFalseWithAnotherChar() {
        String line = "c";
        assertEquals(false, readFromInputStream.isIgnorableLine(line));
    }
}
