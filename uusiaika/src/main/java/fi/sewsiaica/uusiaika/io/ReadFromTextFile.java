/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author iah1016
 */
public class ReadFromTextFile {

    private Scanner fileReader;

    public ReadFromTextFile(Scanner reader) {
        this.fileReader = reader;
    }

    public List<String> yankTextFromFile(File textfile) throws
            FileNotFoundException {
        fileReader = new Scanner(textfile);
        List<String> newLines = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            if (!isIgnorableLine(line)) {
                newLines.add(line);
            }
        }
        fileReader.close();
        return returnNewLineIffReaderIsClosed(newLines);
    }

    public boolean isIgnorableLine(String line) {
        if (line.isEmpty()) {
            return true;
        }
        return line.charAt(0) == ' ' || line.charAt(0) == '#';
    }

    public List<String> returnNewLineIffReaderIsClosed(
            List<String> list) {
        try {
            fileReader.hasNext();
        } catch (Exception e) {
            return list;
        }
        return null;
    }
}
