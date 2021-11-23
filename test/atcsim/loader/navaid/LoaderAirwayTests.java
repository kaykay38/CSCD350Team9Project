package atcsim.loader.navaid;

import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.world.navigation.A_ComponentNavaid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class LoaderAirwayTests {
    HashMap<String, A_ComponentNavaid<?>> navaids;
    String id;

    @BeforeEach
    public void testLoaderAirwaySetup() {
        navaids = new HashMap<String, A_ComponentNavaid<?>>();
        id = "1";
    }

    @Test
    public void testLoaderAirwayConstructor() {
        OverlayNavigation overlayNavigation = new OverlayNavigation(id);
        LoaderAirway loader = new LoaderAirway(navaids, overlayNavigation);

        Assertions.assertNotNull(loader);
    }

    @Test
    public void testLoad() throws IOException {
        Scanner scan = new Scanner(new File("Loader_Airway_Test.txt"));
        OverlayNavigation overlayNavigation = new OverlayNavigation(id);
        LoaderAirway loader = new LoaderAirway(navaids, overlayNavigation);

        loader.load(scan);


    }
}
