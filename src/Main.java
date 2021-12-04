import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.NavigationOverlayBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        
    	NavigationOverlayBuilder navOverlayBuilder = new NavigationOverlayBuilder();
        
        try {
            OverlayNavigation overlayNavigation = navOverlayBuilder.loadDefinition("definition1.txt");
        }
        
        catch (IOException ioException) {
            System.out.println(ioException);
        }
    }
}
