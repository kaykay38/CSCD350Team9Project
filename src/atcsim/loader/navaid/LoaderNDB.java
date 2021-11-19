package atcsim.loader.navaid;

import atcsim.datatype.Altitude;
import atcsim.datatype.CoordinateWorld3D;
import atcsim.datatype.Latitude;
import atcsim.datatype.Longitude;
import atcsim.datatype.UHFFrequency;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.A_ComponentNavaid;
import atcsim.world.navigation.ComponentNavaidNDB;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class LoaderNDB extends A_Loader {

    private final int ID = 0, UHF_FREQ = 1, LAT_DEG = 2, LAT_MIN = 3, LAT_SEC = 4,
        LON_DEG = 5, LON_MIN = 6, LON_SEC = 7, ALT = 8;

    public LoaderNDB(Map<String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay) {
        super(navaids, overlay);
    }

    public void load(Scanner scanner) throws IOException {
        String[] splitString;

        while (scanner.hasNextLine()) {
            splitString = scanner.nextLine().split("\s*,\s*");    

            if (splitString.length > 0) {
                for (String str : splitString) {
                    str.trim();
                }

                String id = splitString[ID];
                UHFFrequency uhfFrequency = new UHFFrequency(Integer.parseInt(splitString[UHF_FREQ]));
                Latitude latitude = readLatitude(splitString[LAT_DEG],splitString[LAT_MIN],splitString[LAT_SEC]);
                Longitude longitude = readLongitude(splitString[LON_DEG],splitString[LON_MIN],splitString[LON_SEC]);
                Altitude altitude = readAltitude(splitString[ALT]);
                CoordinateWorld3D postion = new CoordinateWorld3D(latitude, longitude, altitude);

                this.overlay.addNavaid(new ComponentNavaidNDB(id,postion,uhfFrequency));
            }
        }
    }

}
