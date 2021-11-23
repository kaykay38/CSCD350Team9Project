package atcsim.loader.navaid;

import atcsim.datatype.Altitude;
import atcsim.datatype.AngleNavigational;
import atcsim.datatype.CoordinateWorld3D;
import atcsim.datatype.Distance;
import atcsim.datatype.Latitude;
import atcsim.datatype.Longitude;
import atcsim.datatype.VHFFrequency;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.A_ComponentNavaid;
import atcsim.world.navigation.ComponentNavaidILS;
import atcsim.world.navigation.NavaidILSBeaconDescriptor;

public class LoaderILS extends A_Loader {

	private int ID = 0, VHF = 1, LAT = 2, LON = 3, ALT = 4, AZIMUTH = 5, OUTER = 6, MIDDLE = 7, INNER = 8; 
	
	public LoaderILS(java.util.Map<String, A_ComponentNavaid<?>> navaids, OverlayNavigation overlay) {
		
		super(navaids, overlay);
	}
	
	
	public void load(java.util.Scanner scanner) throws java.io.IOException {
		String line = scanner.nextLine();
		String[] split;

		while(line.matches("\\[NAVAID:.+") && scanner.hasNextLine())
			line = scanner.nextLine();

		while(!line.isBlank()) {
			split = line.split(", ");

			String id = split[ID];

			String[] splitVHF = split[VHF].split(",");
			VHFFrequency frequency = new VHFFrequency(Integer.parseInt(splitVHF[0]), Integer.parseInt(splitVHF[1]));
			
			String[] splitLat = split[LAT].split(",");
			Latitude latitude = readLatitude(splitLat[0], splitLat[1], splitLat[2]);
			String[] splitLon = split[LON].split(",");
			Longitude longitude = readLongitude(splitLon[0], splitLon[1], splitLon[2]);
			Altitude altitude = readAltitude(split[ALT]);
			CoordinateWorld3D position = new CoordinateWorld3D(latitude, longitude, altitude);
			
			AngleNavigational azimuth = new AngleNavigational(Double.parseDouble(split[AZIMUTH]));
			
			String[] splitOuter = split[OUTER].split(",");
			NavaidILSBeaconDescriptor markerOuter = new NavaidILSBeaconDescriptor(new Distance(Double.parseDouble(splitOuter[0])), readAltitude(splitOuter[1]));
			
			String[] splitMiddle = split[MIDDLE].split(",");
			NavaidILSBeaconDescriptor markerMiddle = new NavaidILSBeaconDescriptor(new Distance(Double.parseDouble(splitMiddle[0])), readAltitude(splitMiddle[1]));
			
			String[] splitInner = split[INNER].split(",");
			NavaidILSBeaconDescriptor markerInner = new NavaidILSBeaconDescriptor(new Distance(Double.parseDouble(splitInner[0])), readAltitude(splitInner[1]));
			
			ComponentNavaidILS ils = new ComponentNavaidILS(id, position, azimuth, frequency, markerOuter, markerMiddle, markerInner);
			
			overlay.addNavaid(ils);
			navaids.put(id, ils);

			if(scanner.hasNextLine())
				line = scanner.nextLine();
			else
				line = "";
		}
		
	}
}