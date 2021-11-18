package atcsim.loader.navaid;

import atcsim.datatype.Altitude;
import atcsim.datatype.Latitude;
import atcsim.datatype.Longitude;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.A_ComponentNavaid;

public class LoaderFix extends A_Loader {
	
	public LoaderFix(java.util.Map<String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay) {
		
		super(navaids, overlay);
	}
	
	
	public void load(java.util.Scanner scanner) throws java.io.IOException {
		
		
	}


	@Override
	protected String readID() {
		
		return null;
	}


	@Override
	protected Latitude readLatitude() {
		
		return null;
	}


	@Override
	protected Longitude readLongitude() {
		
		return null;
	}


	@Override
	protected Altitude readAltitude() {
		
		return null;
	}

}