package com.kiro.projchaos.methods;

import org.bukkit.util.Vector;

public final class VecUtils
{

    private VecUtils()
    {

    }

    public static Vector getRandomLocation(double px, double py, double pz, double range)
    {
        return new Vector(Utils.getRandom(px, range), Utils.getRandom(py, range), Utils.getRandom(pz, range));
    }


    public static double getDistanceSquared(double x1, double y1, double x2, double y2)
    {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

}