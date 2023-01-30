package decide;

/* Adapted from the same source as the SmallestEnclosingCircle. */
final class Point {
	
	public double x;
	public double y;
	
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	
	public Point subtract(Point p) {
		return new Point(x - p.x, y - p.y);
	}
	
	
	public double distance(Point p) {
		return Math.hypot(x - p.x, y - p.y);
	}
	
	
	// Signed area / determinant thing
	public double cross(Point p) {
		return x * p.y - y * p.x;
	}

	/**
	 * Returns angle between point and p, or -1 if any of them coincides with the vertex.
	 * @param p Point
	 * @param vertex Point
	 * @return angle or -1
	 */
	public double angle(Point p, Point vertex) {
		// Check if any of the point and p coincide with the vertex
		if((x == vertex.x && y == vertex.y) || (p.x == vertex.x && p.y == vertex.y)) return -1;

		// Otherwise, calculate angle
		double num = x * p.x + y * p.y;
		double den = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2));
		return Math.acos(num/den);
	}
	
	
	public String toString() {
		return String.format("Point(%g, %g)", x, y);
	}
}