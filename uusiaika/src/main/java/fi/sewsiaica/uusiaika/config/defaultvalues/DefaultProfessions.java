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
package fi.sewsiaica.uusiaika.config.defaultvalues;

/**
 * This enum class contains the default list of professions. This list will soon
 * be much greater.
 *
 * @author iah1016
 */
public enum DefaultProfessions {
    PROF01("Kauppias"),
    PROF02("Leipuri"),
    PROF03("Opettaja"),
    PROF04("Postinjakaja"),
    PROF05("Lääkäri"),
    PROF06("Radiojuontaja"),
    PROF07("Poliisi"),
    PROF08("Bussikuski"),
    PROF09("Putkimies"),
    PROF10("Poliitikko"),
    PROF11("Tutkija"),
    PROF12("Apteekkari"),
    PROF13("AD"),
    PROF14("Toimitusjohtaja");
    private final String name;

    private DefaultProfessions(String name) {
        this.name = name;
    }

    public String profName() {
        return name;
    }
}
