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
 * This is a basic file reader that reads every line from a text file but
 * discards the empty lines and the lines that contain a space or a hash sign as
 * the first character. All the other lines are returned in an ArrayList of
 * Strings.
 *
 * @author iah1016
 */
public class ReadFromTextFile {

    private Scanner fileReader;

    /**
     * Scanner object is brought from the outside for easier testing.
     *
     * @param reader
     */
    public ReadFromTextFile(Scanner reader) {
        this.fileReader = reader;
    }

    /**
     * Yankee, go elsewhere on a ArrayList with the rest of your lot. Hashed or
     * spaced out (comment) lines are excluded.
     *
     * @param textfile
     * @return
     * @throws FileNotFoundException
     */
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

    /**
     * Checking if the line is empty or has a space or a hash sign in the
     * beginning.
     *
     * @param line A line that has been yanked from a text file.
     * @return If one of the mentioned conditions is met, the line is ignored.
     */
    public boolean isIgnorableLine(String line) {
        if (line.isEmpty()) {
            return true;
        }
        return line.charAt(0) == ' ' || line.charAt(0) == '#';
    }

    /**
     * A fairly superfluous method for an earnest antimutator. Its purpose is to
     * confirm that the scanner has been closed.
     *
     * @param list All them beautiful yankees.
     * @return If everything goes as expected (ie. you are not an evil pit
     * mutant), the method returns the list. If you are an evil pit mutant, null
     * is all you get here.
     */
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
