/**
 * Contract for computing distance between two points (e.g. for fare).
 */
public interface DistanceCalculator {
    double km(GeoPoint a, GeoPoint b);
}
