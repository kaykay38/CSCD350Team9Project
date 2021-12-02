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
        assertEquals("reciprocate a1",0,reciprocate1.compareTo(new AngleNavigational(270)));
        assertEquals("reciprocate a2",0,reciprocate2.compareTo(new AngleNavigational(0)));
        assertEquals("a1 interpolate a2",0,interpolate1.compareTo(new AngleNavigational(135)));
        assertEquals("a2 interpolate a1",0,interpolate2.compareTo(new AngleNavigational(315)));
    }

    @Test
    public void testAttitudePitch() {
        AttitudePitch p = new AttitudePitch(10);

        /* Verify:
         * 0 + p is correct.
         * 90 + p is correct.
         * 175 + p is correct.
         */
        AttitudePitch p0 = new AttitudePitch(0);
        AttitudePitch p90 = new AttitudePitch(90);
        AttitudePitch p175 = new AttitudePitch(175);
        assertEquals("0 + p", 10.0, p0.add_(p).getValue_(), .0001);
        assertEquals("90 + p", 100.0, p90.add_(p).getValue_(), .0001);
        assertEquals("175 + p", -175.0, p175.add_(p).getValue_(), .0001);
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
        Course c = new Course(10);

        /* Verify:
         * 0 + c is correct.
         * 355 + c is correct.
         * 0 - c is correct.
         * 355 - c is correct.
         */
        Course c0 = new Course(0);
        Course c355 = new Course(355);
        assertEquals("0 + c", 10.0, c0.add_(c).getValue_(), .0001);
        assertEquals("355 + c", 5.0, c355.add_(c).getValue_(), .0001);
        assertEquals("0 - c", 350.0, c0.subtract_(c).getValue_(), .0001);
        assertEquals("355 - c", 345.0, c355.subtract_(c).getValue_(), .0001);
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
        assertEquals(50.0, p1.add_(p2).getLatitude().getDegrees(),.0001);
        assertEquals(41.0, p1.add_(p2).getLatitude().getMinutes(), .0001);
        assertEquals(35.0, p1.add_(p2).getLatitude().getSeconds(), .0001);
        assertEquals(120.0, p1.add_(p2).getLongitude().getDegrees(),.0001);
        assertEquals(27.0, p1.add_(p2).getLongitude().getMinutes(), .0001);
        assertEquals(31.0, p1.add_(p2).getLongitude().getSeconds(), .0001);
    }

    @Test
    public void testCoordinateWorld3D() {
        CoordinateWorld p1 = CoordinateWorld.KSFF;
        CoordinatePolarNavigational bearingToSelf = p1.calculateBearing(p1);
        CoordinatePolarNavigational bearingKSFF_N = p1.calculateBearing(CoordinateWorld.KSFF_N);
        CoordinatePolarNavigational bearingKSFF_S = p1.calculateBearing(CoordinateWorld.KSFF_S);
        CoordinatePolarNavigational bearingKSFF_E = p1.calculateBearing(CoordinateWorld.KSFF_E);
        CoordinatePolarNavigational bearingKSFF_W = p1.calculateBearing(CoordinateWorld.KSFF_W);

        /* Verify:
         * p calculateBearing p is correct for angle. Use getAngle().getValue_().
         * p calculateBearing p is correct for distance. Use getRadiusNauticalMiles().getValue_().
         * p calculateBearing KSFF_N is correct for angle and distance.
         * p calculateBearing KSFF_S is correct for angle and distance.
         * p calculateBearing KSFF_E is correct for angle and distance.
         * p calculateBearing KSFF_W is correct for angle and distance.
         */
        assertEquals("p calculateBearing angle",90.0,bearingToSelf.getAngle().getValue_(),.0001);
        assertEquals("p calculateBearing distance",0.0,bearingToSelf.getRadiusNauticalMiles().getValue_(),.0001);
        assertEquals("p calculateBearing KSFF_N angle",0.0,bearingKSFF_N.getAngle().getValue_(),.0001);
        assertEquals("p calculateBearing KSFF_N distance",11.5,bearingKSFF_N.getRadiusNauticalMiles().getValue_(),.0001);
        assertEquals("p calculateBearing KSFF_S angle",180.0,bearingKSFF_S.getAngle().getValue_(),.0001);
        assertEquals("p calculateBearing KSFF_S distance",11.5,bearingKSFF_S.getRadiusNauticalMiles().getValue_(),.0001);
        assertEquals("p calculateBearing KSFF_E angle",90.0,bearingKSFF_E.getAngle().getValue_(),.0001);
        assertEquals("p calculateBearing KSFF_E distance",11.5,bearingKSFF_E.getRadiusNauticalMiles().getValue_(),.0001);
        assertEquals("p calculateBearing KSFF_W angle",270.0,bearingKSFF_W.getAngle().getValue_(),.0001);
        assertEquals("p calculateBearing KSFF_W distance",11.5,bearingKSFF_W.getRadiusNauticalMiles().getValue_(),.0001);
    }
}