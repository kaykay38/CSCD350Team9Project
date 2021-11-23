package atcsim.loader.navaid;

import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.world.navigation.A_ComponentNavaid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.Assert.assertNotNull;

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
        LoaderNDB loaderNDB = new LoaderNDB(navaids, overlay);
        String id = "fix1";
        /** definition.txt
         [NAVAIDS:FIX]
         fix1, 48,38,31, 116,24,29, 1949
         */
        Scanner scanner = new Scanner(new FileInputStream(resourceName));
        loaderNDB.load(scanner);
        System.out.println("testLoad: " + navaids.get(id));
        Assertions.assertNotNull(navaids.get(id));
    }
}
