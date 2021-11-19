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

    private final int ID = 0, VHF_FREQ_MAJ = 1, VHF_FREQ_MIN = 2, LAT_DEG = 3, LAT_MIN = 4, LAT_SEC = 5,
        LON_DEG = 6, LON_MIN = 7, LON_SEC = 8, ALT = 9;

    public LoaderVOR(Map<String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay) {
        super(navaids, overlay);
    }

    public void load(Scanner scanner) throws IOException {
        String[] splitStr;

        while (scanner.hasNextLine()) {
            splitStr = scanner.nextLine().split("\s*,\s*");    

            if (splitStr.length > 0) {
                for (String str : splitStr) {
                    str.trim();
                }

                String id = splitStr[ID];
                VHFFrequency vhfFrequency = new VHFFrequency(Integer.parseInt(splitStr[VHF_FREQ_MAJ]), Integer.parseInt(splitStr[VHF_FREQ_MIN]));
                Latitude latitude = readLatitude(splitStr[LAT_DEG],splitStr[LAT_MIN],splitStr[LAT_SEC]);
                Longitude longitude = readLongitude(splitStr[LON_DEG],splitStr[LON_MIN],splitStr[LON_SEC]);
                Altitude altitude = readAltitude(splitStr[ALT]);
                CoordinateWorld3D postion = new CoordinateWorld3D(latitude, longitude, altitude);

                this.overlay.addNavaid(new ComponentNavaidVOR(id,postion,vhfFrequency));
            }
        }
    }

}
