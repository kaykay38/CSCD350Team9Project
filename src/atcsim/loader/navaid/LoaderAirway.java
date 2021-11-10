package atcsim.loader.navaid;

import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.A_ComponentNavaid;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class LoaderAirway extends A_Loader {

    /**
     * Creates an airway loader
     * @param navaids - the shared collection of navaids
     * @param overlay - the navigation overlay that holds the navaids to be loaded
     */
    public LoaderAirway(Map<String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay) {
        super(navaids, overlay);
    }

    public void load(Scanner scanner) throws IOException {

    }
}
