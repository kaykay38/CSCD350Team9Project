package atcsim.loader.navaid;

import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.world.navigation.A_ComponentNavaid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class LoaderFixTests {

    OverlayNavigation overlay = new OverlayNavigation("1");
    HashMap<String, A_ComponentNavaid<?>> navaids = new HashMap<>();

    @Test
    public void testLoaderFixConstructor() {
        LoaderFix loaderFix = new LoaderFix(navaids,overlay);
        System.out.println("testLoaderFixConstructor: "+ loaderFix);
        Assertions.assertNotNull(loaderFix);
    }

    @Test
    public void testLoad() throws IOException {
        String resourceName = "definition1.txt";
        Scanner scanner = new Scanner(new FileInputStream(resourceName));
        String line = scanner.nextLine();
        while(!line.matches("\\[NAVAID:FIX]") && scanner.hasNextLine()) {
            line = scanner.nextLine();
        }

        /* definition.txt
         [NAVAIDS:FIX]
         fix1, 48,38,31, 116,24,29, 1949
         */
        LoaderFix loaderFix = new LoaderFix(navaids,overlay);
        String[] ids = {"fix1"};
        loaderFix.load(scanner);
        System.out.println("testFixLoad: "+ navaids.get(ids[0]));
        Assertions.assertNotNull(navaids.get(ids[0]));
    }
}
