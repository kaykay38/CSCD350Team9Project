package atcsim.loader;

import java.util.Map;
import atcsim.datatype.Altitude;
import atcsim.datatype.Latitude;
import atcsim.datatype.Longitude;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.world.navigation.A_ComponentNavaid;

public abstract class A_Loader {
	
	protected Map<String, A_ComponentNavaid<?>> navaids;
	protected OverlayNavigation overlay;
	
	public A_Loader(Map<String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay) {
		this.navaids = navaids;
		this.overlay = overlay;
	}
	
	protected Latitude readLatitude(String degreeString, String minutesString, String secondsString) {
		  
		int degrees = Integer.parseInt(degreeString);
		int minutes = Integer.parseInt(minutesString);
		double seconds = Double.parseDouble(secondsString);
		
		return new Latitude(degrees, minutes, seconds);
	}
	
	protected Longitude readLongitude(String degreeString, String minutesString, String secondsString) {
		
		int degrees = Integer.parseInt(degreeString);
		int minutes = Integer.parseInt(degreeString);
		double seconds = Double.parseDouble(degreeString);
		
		return new Longitude(degrees, minutes, seconds);
    }
	
	protected Altitude readAltitude(String altitude) {
		
		return new Altitude(Double.parseDouble(altitude));
	}
	
}
