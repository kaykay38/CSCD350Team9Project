package atcsim.loader.navaid;

import atcsim.datatype.Altitude;
import atcsim.datatype.CoordinateWorld3D;
import atcsim.datatype.Latitude;
import atcsim.datatype.Longitude;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.A_ComponentNavaid;
import atcsim.world.navigation.ComponentNavaidFix;

public class LoaderFix extends A_Loader {
	
	private final int ID = 0, LAT = 1, LON = 2, ALT = 3;
	
	public LoaderFix(java.util.Map<String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay) {
		
		super(navaids, overlay);
	}
	
	
	public void load(java.util.Scanner scanner) throws java.io.IOException {
		
		String line = scanner.nextLine();
		while(!(line.equals("[NAVAID:FIX]")))
			line = scanner.nextLine();
		
		String[] splitString;
		
		line = scanner.nextLine();
		while(!(line.isEmpty())) {
			
			splitString = line.split(", ");
			String id = splitString[ID];
			String[] splitLat = splitString[LAT].split(",");
			Latitude latitude = readLatitude(splitLat[0], splitLat[1], splitLat[2]);
			
			String[] splitLon = splitString[LON].split(",");
			Longitude longitude = readLongitude(splitLon[0], splitLon[1], splitLon[2]);
			
			Altitude altitude = readAltitude(splitString[ALT]);
			
			CoordinateWorld3D position = new CoordinateWorld3D(latitude, longitude, altitude);
			ComponentNavaidFix fix = new ComponentNavaidFix(id, position);
			
			this.overlay.addNavaid(fix);
			this.navaids.put(id, fix);
			
			line = scanner.nextLine();
				
		}
			
					
	}


	
		
}

	

