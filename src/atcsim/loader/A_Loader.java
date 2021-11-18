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
	
	protected String readID() {
		
		return this.splitString[0];
	}
	
	protected abstract String readLatitude();
	protected abstract String readLongitude();
	protected abstract String readAltitude();
	
	protected Latitude getLatitude(String degreeString, String minutesString, String secondsString) {
		  
		int degrees = Integer.parseInt(degreeString);
		int minutes = Integer.parseInt(minutesString);
		double seconds = Double.parseDouble(secondsString);
		
		Latitude latitude = new Latitude(degrees, minutes, seconds);
		return latitude;
	}
	
	protected Longitude getLongitude(String degreeString, String minutesString, String secondsString) {
		
		int degrees = Integer.parseInt(degreeString);
		int minutes = Integer.parseInt(degreeString);
		double seconds = Double.parseDouble(degreeString);
		
		Longitude longitude = new Longitude(degrees, minutes, seconds);
		return longitude;
	}
	
	protected Altitude readAltitude(String altitude) {
		
		return new Altitude(Double.parseDouble(altitude));
	}
	
		
	
	
}
