package atssim.part2;

import atcsim.datatype.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatatypeTester {

    @Test
    public void testAltitude() {
        Altitude altitude1 = new Altitude(1000);
        Altitude altitude2 = new Altitude(200);

        assertEquals("a1 + a2", 1200.0, altitude1.add_(altitude2).getValue_(), .0001);
        assertEquals("a2 + a1", 1200.0, altitude2.add_(altitude1).getValue_(), .0001);
        assertEquals("a1 - a2", 800.0, altitude1.subtract_(altitude2).getValue_(), .0001);
        assertEquals("a2 - a1", -800.0, altitude2.subtract_(altitude1).getValue_(), .0001);
        assertEquals("a1 = a1", 0, altitude1.compareTo(altitude1));
        assertEquals("a1 < a2", -1, altitude2.compareTo(altitude1));
        assertEquals("a1 > a2", 1, altitude1.compareTo(altitude2));
    }

    @Test
    public void testAngleNavigational() {

    }

    @Test
    public void testAttitudePitch() {

    }

    @Test
    public void testAttitudeYaw() {
        AttitudeYaw y = new AttitudeYaw(10);

        assertEquals("0 + y", 10.0, 0 + y.getValue_(), .0001);
        assertEquals("355 + y", 365.0, 355 + y.getValue_(), .0001);
        assertEquals("0 - y", -10.0, 0 - y.getValue_(), .0001);
        assertEquals("355 - y", 345.0, 355 - y.getValue_(), .0001);
    }

    @Test
    public void testCourse() {

    }

    @Test
    public void testCoordinateWorld() {
        CoordinateWorld p1 = CoordinateWorld.KSFF;
        Latitude latitude = new Latitude(1, 2, 3);
        Longitude longitude = new Longitude(3, 2, 1);
        CoordinateWorld p2 = new CoordinateWorld(latitude, longitude);

        //KSFF starts at Lat: 49, 39, 32, Long: 117, 25, 30
        //p1 + p2 lat SHOULD be (49 + 1 = 50 N, 39 + 2 = 41, 32 + 3 = 35)
        //p1 + p2 long SHOULD be (117 + 3 = 120 W, 25 + 2 = 27, 30 + 1 = 31)
        assertEquals("p1 = p1", 0, p1.compareTo(p1));
        assertEquals("degrees=50 N minutes=41 seconds=35.0", p1.add_(p2).getLatitude().toString());
        assertEquals("degrees=120 W minutes=27 seconds=31.0", p1.add_(p2).getLongitude().toString());
    }

    @Test
    public void testCoordinateWorld3D() {

    }
}
