package atcsim.loader;

import java.util.Map;
import atcsim.datatype.Altitude;
import atcsim.datatype.Latitude;
import atcsim.datatype.Longitude;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.world.navigation.A_ComponentNavaid;

public abstract class A_Loader {
	
	protected String[] splitString;
	protected Map<String, A_ComponentNavaid<?>> navaids;
	protected OverlayNavigation overlay;
	
	public A_Loader(Map<String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay) {
		
		this.navaids = navaids;
		this.overlay = overlay;
		
	}
	
	protected abstract String readID();
	protected abstract Latitude readLatitude();
	protected abstract Longitude readLongitude();
	protected abstract Altitude readAltitude();
	
}
