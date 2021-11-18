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
	
	protected Latitude readLatitude(String[] latitudeArray) {
		  
		int degrees = Integer.parseInt(latitudeArray[0]);
		int minutes = Integer.parseInt(latitudeArray[1]);
		double seconds = Double.parseDouble(latitudeArray[2]);
		
		Latitude latitude = new Latitude(degrees, minutes, seconds);
		return latitude;
	}
	
	protected Longitude readLongitude(String[] longitudeArray) {
		
		int degrees = Integer.parseInt(longitudeArray[0]);
		int minutes = Integer.parseInt(longitudeArray[1]);
		double seconds = Double.parseInt(longitudeArray[2]);
		
		Longitude longitude = new Longitude(degrees, minutes, seconds);
		return longitude;
	}
	
	protected Altitude readAltitude(String altitude) {
		
		return new Altitude(Double.parseDouble(altitude));
	}
	
		
	
	
}
