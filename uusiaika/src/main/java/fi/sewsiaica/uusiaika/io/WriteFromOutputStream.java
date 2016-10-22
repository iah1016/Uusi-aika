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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This class uses FileOutputStream to write binary data to a file.
 *
 * @author iah1016
 */
public class WriteFromOutputStream {

    /**
     * This method writes String content to a text file by using
     * FileOutputSteam. During the writing process the data is in binary form.
     * If the given file does not exist, it is created.
     *
     * @param saveFile The given destination.
     * @param content The content given as a string.
     * @return Returns true if the writing process is successful.
     */
    public boolean dumpStringToTextFile(File saveFile, String content) {
        try (OutputStream outputStream = new FileOutputStream(saveFile)) {
            createNewFileIfFileDoesNotExist(saveFile);

            byte[] contentInBytes = content.getBytes();
            outputStream.write(contentInBytes);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method creates a new file if the file does not exist.
     *
     * @param saveFile The save file.
     * @return Returns true if a new file is created and false otherwise.
     * @throws IOException Returns the IOException.
     */
    protected boolean createNewFileIfFileDoesNotExist(File saveFile)
            throws IOException {
        if (!saveFile.exists()) {
            saveFile.createNewFile();
            return true;
        }
        return false;
    }
}
