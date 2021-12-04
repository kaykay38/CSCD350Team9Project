package atcsim.loader.navaid;

import atcsim.datatype.Altitude;
import atcsim.datatype.CoordinateWorld3D;
import atcsim.datatype.Latitude;
import atcsim.datatype.Longitude;
import atcsim.datatype.VHFFrequency;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.A_ComponentNavaid;
import atcsim.world.navigation.ComponentNavaidVOR;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class LoaderVOR extends A_Loader {

    private final int ID = 0, VHF_MAJ = 1, VHF_MIN = 2, LAT_DEG = 3, LAT_MIN = 4, LAT_SEC = 5,
        LON_DEG = 6, LON_MIN = 7, LON_SEC = 8, ALT = 9;

    public LoaderVOR(Map<String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay) {
        super(navaids, overlay);
    }

    public void load(Scanner scanner) throws IOException {
        String line = scanner.nextLine();
        String[] split;

        while (line.matches("\\[NAVAID:.+") && scanner.hasNextLine()) {
            line = scanner.nextLine();
        }

        while (!line.isBlank()) {
            split = line.split("\\s*,\\s*");

            if (split.length >= 9) {
                String id = split[ID];

                VHFFrequency vhfFrequency = new VHFFrequency(Integer.parseInt(split[VHF_MAJ]), Integer.parseInt(split[VHF_MIN]));
                Latitude latitude = readLatitude(split[LAT_DEG],split[LAT_MIN],split[LAT_SEC]);
                Longitude longitude = readLongitude(split[LON_DEG],split[LON_MIN],split[LON_SEC]);
                Altitude altitude = readAltitude(split[ALT]);
                CoordinateWorld3D postion = new CoordinateWorld3D(latitude, longitude, altitude);

                ComponentNavaidVOR navaidVOR = new ComponentNavaidVOR(id,postion,vhfFrequency);
                overlay.addNavaid(navaidVOR);
                navaids.put(id, navaidVOR);
            }

            if(scanner.hasNextLine())
                line = scanner.nextLine();
            else
                line = "";
        }
    }

}
