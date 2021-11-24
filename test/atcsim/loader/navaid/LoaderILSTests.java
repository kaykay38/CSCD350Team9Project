package atcsim.loader.navaid;

import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.world.navigation.A_ComponentNavaid;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoaderILSTests {
    OverlayNavigation overlay = new OverlayNavigation("1");
    HashMap<String, A_ComponentNavaid<?>> navaids = new HashMap<>();

    @Test
    public void testLoaderILSConstructor() {
        LoaderILS loaderILS = new LoaderILS(navaids,overlay);
        System.out.println("testLoaderILSConstructor: "+ loaderILS);
        assertNotNull(loaderILS);
    }

    @Test
    public void testLoad() throws IOException {
        String resourceName = "definition1.txt";
        Scanner scanner = new Scanner(new FileInputStream(resourceName));
        String line = scanner.nextLine();
        while(!line.matches("\\[NAVAID:ILS]") && scanner.hasNextLine()) {
            line = scanner.nextLine();
        }

        /* definition.txt
        [NAVAID:ILS]
        ils1, 118,325, 49,39,32, 117,25,30, 1950, 135, 14,13, 12,11, 10,9
         */
        String[] ids = {"ils1"};
        LoaderILS loaderILS = new LoaderILS(navaids, overlay);
        loaderILS.load(scanner);
        System.out.println("testILSLoad: " + navaids.get(ids[0]));
        assertNotNull(navaids.get(ids[0]));
    }
}
