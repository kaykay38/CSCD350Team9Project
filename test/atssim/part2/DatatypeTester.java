package atssim.part2;

import atcsim.datatype.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DatatypeTester {

    @Test
    public void testAltitude() {
        Altitude a1 = new Altitude(1000);
        Altitude a2 = new Altitude(200);
        /* Verify:
        * a1 + a2 is correct. Use getValue_().
        * a2 + a1 is correct.
        * a1 - a2 is correct.
        * a2 - a1 is correct.
        * a1 = a1 is correct. Use compareTo().
        * a1 < a2 is correct.
        * a1 > a2 is correct.
        */
        assertEquals("a1 + a2", 1200.0, a1.add_(a2).getValue_(), .0001);
        assertEquals("a2 + a1", 1200.0, a2.add_(a1).getValue_(), .0001);
        assertEquals("a1 - a2", 800.0, a1.subtract_(a2).getValue_(), .0001);
        assertEquals("a2 - a1", -800.0, a2.subtract_(a1).getValue_(), .0001);
        assertEquals("a1 = a1", 0, a1.compareTo(a1));
        assertEquals("a1 < a2", 1, a1.compareTo(a2));
        assertEquals("a2 > a1", -1, a2.compareTo(a1));
    }

    @Test
    public void testAngleNavigational() {
        AngleNavigational a1 = new AngleNavigational(90);
        AngleNavigational a2 = new AngleNavigational(180);
        /* Verify:
        * a1 reciprocate is correct.
        * a2 reciprocate is correct.
        * a1 interpolate a2 is correct. Use scaler 50% (0.5).
        * a2 interpolate a1 is correct.
        */
        AngleNavigational reciprocate1 = a1.reciprocate();
        AngleNavigational reciprocate2 = a2.reciprocate();
        AngleNavigational interpolate1 = a1.interpolate(a2,new Scaler(.5));
        AngleNavigational interpolate2 = a2.interpolate(a1,new Scaler(.5));
        assertEquals(0,reciprocate1.compareTo(new AngleNavigational(270)));
        assertEquals(0,reciprocate2.compareTo(new AngleNavigational(0)));
        assertEquals(0,interpolate1.compareTo(new AngleNavigational(135)));
        assertEquals(0,interpolate2.compareTo(new AngleNavigational(315)));
    }

    @Test
    public void testAttitudePitch() {
        AttitudePitch pitch = new AttitudePitch(10);
        /* Verify:
        * 0 + p is correct.
        * 90 + p is correct.
        * 175 + p is correct.
        */

    }

    @Test
    public void testAttitudeYaw() {
        AttitudeYaw y = new AttitudeYaw(10);
        AttitudeYaw test1 = new AttitudeYaw(-5);
        AttitudeYaw test2 = new AttitudeYaw(175);
        AttitudeYaw test3 = new AttitudeYaw(-175);
        AttitudeYaw test4 = new AttitudeYaw(5);

        /* Verify:
         * -5 + y is correct.
         * 175 + y is correct.
         * 5 - y is correct.
         * -175 - y is correct.
         */
        assertEquals("-5 + y", 5.0, test1.add_(y).getValue_(), .0001);
        assertEquals("175 + y", -175.0, test2.add_(y).getValue_(), .0001);
        assertEquals("5 - y", -5.0, test4.subtract_(y).getValue_(), .0001);
        assertEquals("-175 - y", 175.0, test3.subtract_(y).getValue_(), .0001);
    }

    @Test
    public void testCourse() {
        Course course = new Course(10);
        /* Verify:
         * 0 + c is correct.
         * 355 + c is correct.
         * 0 - c is correct.
         * 355 - c is correct.
         */

    }

    @Test
    public void testCoordinateWorld() {
        CoordinateWorld p1 = CoordinateWorld.KSFF;
        Latitude latitude = new Latitude(1, 2, 3);
        Longitude longitude = new Longitude(3, 2, 1);
        CoordinateWorld p2 = new CoordinateWorld(latitude, longitude);

        /* Verify:
         * p1 = p1 is correct. Use compareTo().
         * p1 + p2 is correct.
         */
        //KSFF starts at Lat: 49, 39, 32, Long: 117, 25, 30
        //p1 + p2 lat SHOULD be (49 + 1 = 50 N, 39 + 2 = 41, 32 + 3 = 35)
        //p1 + p2 long SHOULD be (117 + 3 = 120 W, 25 + 2 = 27, 30 + 1 = 31)
        assertEquals("p1 = p1", 0, p1.compareTo(p1));
        assertEquals("degrees=50 N minutes=41 seconds=35.0", p1.add_(p2).getLatitude().toString());
        assertEquals("degrees=120 W minutes=27 seconds=31.0", p1.add_(p2).getLongitude().toString());
    }

    @Test
    public void testCoordinateWorld3D() {
        //p1 = 49 39 32N, 117 25 30W
        //p2 = 36 9 58.9N, 115 9 31.3W
        CoordinateWorld p1 = CoordinateWorld.KSFF;
        CoordinateWorld p2 = new CoordinateWorld(new Latitude(36,9,58.9),new Longitude(115,9,31.3));
        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        /* Verify:
         * p calculateBearing p is correct for angle. Use getAngle().getValue_().
         * p calculateBearing p is correct for distance. Use getRadiusNauticalMiles().getValue_().
         * p calculateBearing KSFF_N is correct for angle and distance.
         * p calculateBearing KSFF_S is correct for angle and distance.
         * p calculateBearing KSFF_E is correct for angle and distance.
         * p calculateBearing KSFF_W is correct for angle and distance.
         */
        CoordinatePolarNavigational bearing = p1.calculateBearing(p2);
        // this bearing is correct
        double expectedBearingP2 = 172.2;
        double expectedDistanceP2 = 1511;

        double expectedBearingKSFF_N = 0;
        double expectedDistanceKSFF_N = 0;

        double expectedBearingKSFF_S = 0;
        double expectedDistanceKSFF_S = 0;

        double expectedBearingKSFF_E = 0;
        double expectedDistanceKSFF_E = 0;

        double expectedBearingKSFF_W = 0;
        double expectedDistanceKSFF_W = 0;

        assertEquals("p calculateBearing angle",expectedBearingP2,bearing.getAngle().getValue_(),.1);
        assertEquals("p calculateBearing distance",expectedDistanceP2,bearing.getRadiusNauticalMiles().getValue_(),.1);
    }
}