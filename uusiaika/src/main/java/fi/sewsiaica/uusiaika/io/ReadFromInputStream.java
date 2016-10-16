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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will replace the ReadFromTextFile class.
 *
 * @author iah1016
 */
public class ReadFromInputStream {

    /**
     * The method yanks lines from the inputStream. The yankees are transferred
     * out in a String-List. Hashed or spaced out (comment) lines are excluded.
     *
     * @param inputStream InputStream.
     * @return The yanked lines of goodness.
     * @throws IOException Throws the IOException.
     * @throws NullPointerException Throws the NullPointerException.
     */
    public List<String> yankTextFromFile(InputStream inputStream)
            throws IOException, NullPointerException {
        List<String> newLines = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();

        while (line != null) {
            if (!isIgnorableLine(line)) {
                newLines.add(line);
            }
            line = bufferedReader.readLine();
        }
        return newLines;
    }

    /**
     * Checking if the line is empty or has a space or a hash sign in the
     * beginning.
     *
     * @param line A line that has been yanked from a text file.
     * @return If one of the mentioned conditions is met, the line is ignored.
     */
    protected boolean isIgnorableLine(String line) {
        if (line.isEmpty()) {
            return true;
        }
        return line.charAt(0) == ' ' || line.charAt(0) == '#';
    }
}
