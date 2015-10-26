package com.kiro.projchaos.methods;

import java.util.Random;


public class Utils
{

    private static final Random rand = new Random();

    public static double getRandom(double range)
    {
        return (rand.nextDouble() - 0.5) * range;
    }

    public static float getRandom(float range)
    {
        return (rand.nextFloat() - 0.5f) * range;
    }

    public static double getRandom(double center, double range)
    {
        return center + getRandom(range);
    }

}