package atssim.part2;

import atcsim.datatype.Altitude;
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

    }

    @Test
    public void testCourse() {

    }

    @Test
    public void testCoordinateWorld() {

    }

    @Test
    public void testCoordinateWorld3D() {

    }
}
