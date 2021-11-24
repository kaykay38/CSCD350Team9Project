package atcsim.loader.navaid;

import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.world.navigation.A_ComponentNavaid;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoaderNDBTests {

    OverlayNavigation overlay = new OverlayNavigation("1");
    HashMap<String, A_ComponentNavaid<?>> navaids = new HashMap<>();

    @Test
    public void testLoaderNDBConstructor() {
        LoaderNDB loaderNDB = new LoaderNDB(navaids,overlay);
        System.out.println("testLoaderNDBConstructor: "+ loaderNDB);
        assertNotNull(loaderNDB);
    }

    @Test
    public void testLoad() throws IOException {
        String resourceName = "definition1.txt";
        Scanner scanner = new Scanner(new FileInputStream(resourceName));
        String line = scanner.nextLine();
        while(!line.matches("\\[NAVAID:NDB]") && scanner.hasNextLine()) {
            line = scanner.nextLine();
        }

        /* definition.txt
        [NAVAID:NDB]
        ndb1, 320, 49,39,32, 117,25,30, 1950
        ndb2, 322, 50,40,33, 118,26,31, 1951
         */
        LoaderNDB loaderNDB = new LoaderNDB(navaids, overlay);
        String[] ids = {"ndb1", "ndb2"};
        loaderNDB.load(scanner);
        System.out.println("testNDBLoad: " + navaids.get(ids[0]));
        System.out.println("testNDBLoad: " + navaids.get(ids[1]));
        assertNotNull(navaids.get(ids[0]));
        assertNotNull(navaids.get(ids[1]));
    }
}
