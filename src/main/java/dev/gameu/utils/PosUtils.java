package dev.gameu.utils;

import org.bukkit.Location;
import org.bukkit.World;

public class PosUtils {

    public static Location eventloc = null;

    public static Location getEventLocation(){
        return eventloc;
    }

    public static void setEventLocation(Location loc){
        eventloc = loc;
    }

}
