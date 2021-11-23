package atcsim.loader.navaid;

import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.A_ComponentNavaid;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.assertNotNull;

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

        assertNotNull(loader);
    }

    @Test
    public void testLoad() throws IOException {
        Scanner scan = new Scanner(new File("Loader_Airway_Test.txt"));
        OverlayNavigation overlayNavigation = new OverlayNavigation(id);
        LoaderAirway loader = new LoaderAirway(navaids, overlayNavigation);

        loader.load(scan);


    }
}
