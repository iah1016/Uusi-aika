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
package fi.sewsiaica.uusiaika.ui.subpanels;

import fi.sewsiaica.uusiaika.domain.Villager;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 * This class extends DefaultListCellRenderer with a minor modification: the
 * sect members are coloured in magenta in the villager list panel.
 *
 * @author iah1016
 */
public class CustomCellRendererForVillagerListPanel
        extends DefaultListCellRenderer {

    private final List<Villager> allVillagers;

    /**
     * The constructor is given the list of all villagers which it sets as its
     * object variable.
     *
     * @param allVillagers The list of all villagers.
     */
    public CustomCellRendererForVillagerListPanel(List<Villager> allVillagers) {
        this.allVillagers = allVillagers;
    }

    @Override
    public Component getListCellRendererComponent(
            JList<?> jlist, Object o, int idx, boolean bln, boolean bln1) {
        Component component
                = super.getListCellRendererComponent(jlist, o, idx, bln, bln1);

        boolean[] inSectArray = updateinSectArray();
        if (inSectArray[idx]) {
            component.setForeground(Color.decode("#993366"));
        }
        return component;
    }

    private boolean[] updateinSectArray() {
        boolean[] inSectArray = new boolean[allVillagers.size()];
        for (int i = 0; i < inSectArray.length; i++) {
            inSectArray[i] = allVillagers.get(i).isInSect();
        }
        return inSectArray;
    }
}
