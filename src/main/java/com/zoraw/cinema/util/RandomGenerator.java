package com.zoraw.cinema.util;

import lombok.experimental.UtilityClass;

import java.util.Random;


//Jaki jest sens istnienia tej klasy?
@UtilityClass
public class RandomGenerator {

    private static final Random random = new Random();

    public static Random getRandom() {
        return random;
    }
}
