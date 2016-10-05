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

import fi.sewsiaica.uusiaika.config.Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This is used in tests, instead of the real Config, due to easier setting of
 * values.
 *
 * @author iah1016
 */
public class MockConfig extends Config {

    private Map<String, Integer> intValues;
    private List<String> vilNames;
    private List<String> professions;

    public MockConfig() {
        super();
    }
    
    @Override
    public List<String> loadVilNames(String configID) {
        return this.vilNames;
    }

    @Override
    public List<String> loadProfessions(String configID) {
        return this.professions;
    }

    public void setVilNames(List<String> vilNames) {
        this.vilNames = vilNames;
    }

    public void setProfessions(List<String> professions) {
        this.professions = professions;
    }
}
