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
        String[] split;

        while (scanner.hasNextLine() && (scanner.nextLine().isBlank())) {

            split = scanner.nextLine().split("\s*,\s*");

            if (split.length > 0) {

                String id = split[ID];
                UHFFrequency uhfFrequency = new UHFFrequency(Integer.parseInt(split[UHF_FREQ]));
                Latitude latitude = readLatitude(split[LAT_DEG],split[LAT_MIN],split[LAT_SEC]);
                Longitude longitude = readLongitude(split[LON_DEG],split[LON_MIN],split[LON_SEC]);
                Altitude altitude = readAltitude(split[ALT]);
                CoordinateWorld3D postion = new CoordinateWorld3D(latitude, longitude, altitude);

                ComponentNavaidNDB navaidNDB = new ComponentNavaidNDB(id,postion,uhfFrequency);
                overlay.addNavaid(navaidNDB);
                navaids.put(id, navaidNDB);
            }
        }
    }

}
