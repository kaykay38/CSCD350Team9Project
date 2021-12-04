package atcsim.loader.navaid;

import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.world.navigation.A_ComponentNavaid;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class LoaderAirwayTests {
    OverlayNavigation overlay = new OverlayNavigation("1");
    HashMap<String, A_ComponentNavaid<?>> navaids = new HashMap<>();

    @Test
    public void testLoaderAirwayConstructor() {
        LoaderAirway loaderAirway = new LoaderAirway(navaids,overlay);
        System.out.println("testLoaderAirwayConstructor: " + loaderAirway);
        assertNotNull(loaderAirway);
    }

    @Test
    public void testLoad() throws IOException {
        String resourceName = "definition1.txt";
        Scanner scanner = new Scanner(new FileInputStream(resourceName));
        String line = scanner.nextLine();
        while(!line.matches("\\[NAVAID:AIRWAY]") && scanner.hasNextLine()) {
            line = scanner.nextLine();
        }

        /* definition.txt
         [NAVAID:AIRWAY]
         v1, CC, 49,39,32, 117,25,30, 1950, 50,40,33, 118,26,31, 1951
         v2, NC, vor1, 50,40,33, 118,26,31, 1951
         v3, NN, ndb1, ils1
         v4, NN, v1, v2
         */
        String[] ids = {"v1", "v2","v3","v4"};
        LoaderAirway loaderAirway = new LoaderAirway(navaids, overlay);
        loaderAirway.load(scanner);
        System.out.println("testAirwayLoad: " + navaids.get(ids[0]));
        System.out.println("testAirwayLoad: " + navaids.get(ids[1]));
        System.out.println("testAirwayLoad: " + navaids.get(ids[2]));
        System.out.println("testAirwayLoad: " + navaids.get(ids[3]));
        assertNotNull(navaids.get(ids[0]));
        assertNotNull(navaids.get(ids[1]));
        assertNotNull(navaids.get(ids[2]));
        assertNotNull(navaids.get(ids[3]));
    }
}
