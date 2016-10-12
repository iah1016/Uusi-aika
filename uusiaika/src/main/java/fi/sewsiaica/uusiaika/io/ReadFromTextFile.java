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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * The method yanks lines from a text file. The yankees are transferred out
     * in a String-List. Hashed or spaced out (comment) lines are excluded.
     *
     * @param textfile The yanking target.
     * @return The yanked lines of goodness.
     * @throws FileNotFoundException Throws the FileNotFoundException.
     */
    public List<String> yankTextFromFile(File textfile) throws
            FileNotFoundException {
        Scanner fileReader = new Scanner(textfile, "UTF-8");
        List<String> newLines = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            if (!isIgnorableLine(line)) {
                newLines.add(line);
            }
        }
        fileReader.close();
        return returnNewLineIffReaderIsClosed(newLines, fileReader);
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
     * @param reader Scanner that should closed already.
     * @return If everything goes as expected (ie you are not an evil pit
     * mutant), the method returns the list. If you are an evil pit mutant, null
     * is all you get here.
     */
    public List<String> returnNewLineIffReaderIsClosed(
            List<String> list, Scanner reader) {
        try {
            reader.hasNext();
        } catch (Exception e) {
            return list;
        }
        return null;
    }
}
