package atcsim.loader.navaid;

import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.world.navigation.A_ComponentNavaid;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoaderVORTests {

    OverlayNavigation overlay = new OverlayNavigation("1");
    HashMap<String, A_ComponentNavaid<?>> navaids = new HashMap<>();

    @Test
    public void testLoaderVORConstructor() {
        LoaderVOR loaderVOR = new LoaderVOR(navaids,overlay);
        System.out.println("testLoaderVORConstructor: "+ loaderVOR);
        assertNotNull(loaderVOR);
    }

    @Test
    public void testLoad() throws IOException {
        String resourceName = "definition1.txt";
        Scanner scanner = new Scanner(new FileInputStream(resourceName));
        String line = scanner.nextLine();
        while(!line.matches("\\[NAVAID:VOR]") && scanner.hasNextLine()) {
            line = scanner.nextLine();
        }

        /* definition.txt
         [NAVAID:VOR]
         vor1, 118,1, 51,41,34, 119,27,32, 1952
         vor2, 115,325, 52,42,35, 120,28,33, 1953
         */
        LoaderVOR loaderVOR = new LoaderVOR(navaids, overlay);
        String[] ids = {"vor1", "vor2"};
        loaderVOR.load(scanner);
        System.out.println("testVORLoad: " + navaids.get(ids[0]));
        System.out.println("testVORLoad: " + navaids.get(ids[1]));
        assertNotNull(navaids.get(ids[0]));
        assertNotNull(navaids.get(ids[1]));
    }
}
