import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.NavigationOverlayBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        NavigationOverlayBuilder navOverlayBuilder = new NavigationOverlayBuilder();
        try {
            InputStream inputStream = new FileInputStream(args[0]);

            OverlayNavigation overlayNavigation = navOverlayBuilder.loadDefinition("");
        }
        catch (IOException ioException) {
            System.out.println(ioException);
        }
    }
}