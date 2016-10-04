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
package fi.sewsiaica.uusiaika.toolsfortests;

import java.util.Random;

/**
 * This is used in tests, instead of the real Random, for all that is Holy shall
 * be filled with number two.
 *
 * @author iah1016
 */
public class MockRandom extends Random {

    public MockRandom() {
        super();
    }

    @Override
    public int nextInt(int bound) {
        return 2;
    }

}
