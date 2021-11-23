package atcsim.loader;

import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.navaid.*;
import atcsim.world.navigation.A_ComponentNavaid;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class NavigationOverlayBuilder {
    public NavigationOverlayBuilder() {
        System.out.println("Using Team 9 NavigationOverlayBuilder.");
    }
    public OverlayNavigation loadDefinition(String filespec) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filespec);
        Scanner scanner = new Scanner(fileInputStream);
        HashMap navaid = new HashMap<String, A_ComponentNavaid>();
        String id = "1";
        OverlayNavigation overlayNavigation = new OverlayNavigation(id);
        // fix, NDB, VOR, ILS, and airway
        LoaderFix loaderFix = new LoaderFix(navaid, overlayNavigation);
        loaderFix.load(scanner);
        LoaderNDB loaderNDB = new LoaderNDB(navaid, overlayNavigation);
        loaderNDB.load(scanner);
        LoaderVOR loaderVOR = new LoaderVOR(navaid, overlayNavigation);
        loaderVOR.load(scanner);
        LoaderILS loaderILS = new LoaderILS(navaid, overlayNavigation);
        loaderILS.load(scanner);
        LoaderAirway loaderAirway = new LoaderAirway(navaid, overlayNavigation);
        loaderAirway.load(scanner);
        scanner.close();

        return overlayNavigation;
    }
}
