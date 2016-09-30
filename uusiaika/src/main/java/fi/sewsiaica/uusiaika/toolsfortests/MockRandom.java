/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
