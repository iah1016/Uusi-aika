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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class WriteFromOutputStreamTest {

    private WriteFromOutputStream writer;
    private ReadFromInputStream reader;
    private String content;
    private File file;

    public WriteFromOutputStreamTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        writer = new WriteFromOutputStream();
        reader = new ReadFromInputStream();
        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n"
                + "Vestibulum quis ornare lorem, vel maximus sapien.\n"
                + "Mauris gravida est ut cursus laoreet. "
                + "Pellentesque a justo purus.";
        file = new File("src/test/filesfortests/writer_text.txt");
        file.delete();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void dumpStringToTextFileFunctionsAsExpected() {
        assertEquals(false, file.exists());

        boolean result = writer.dumpStringToTextFile(file, content);
        assertEquals(true, result);
        assertEquals(true, file.exists());

        List<String> resList = new ArrayList<>();
        try {
            resList = reader.yankTextFromFile(new FileInputStream(file));
        } catch (Exception e) {
            result = false;
        }
        assertEquals(true, result);

        StringBuilder sb = new StringBuilder();
        sb.append(resList.get(0)).append("\n");
        sb.append(resList.get(1)).append("\n");
        sb.append(resList.get(2));
        assertEquals(content, sb.toString());
    }

    @Test
    public void dumpStringToTextFileReturnsFalseIfInvalidParameters() {
        assertEquals(false, writer.dumpStringToTextFile(null, content));
        assertEquals(false, writer.dumpStringToTextFile(file, null));
    }
    
    @Test
    public void createNewFileIfFileDoesNotExistFunctionsAsExpected()
            throws IOException {
        writer.dumpStringToTextFile(file, content);
        assertEquals(false, writer.createNewFileIfFileDoesNotExist(file));
        file.delete();
        assertEquals(true, writer.createNewFileIfFileDoesNotExist(file));
    }
}
