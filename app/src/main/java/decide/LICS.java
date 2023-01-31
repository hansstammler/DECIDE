package decide;

import java.util.ArrayList;

public class LICS {

	/**
	 * Returns true if there exists at least one set of two consecutive data points that are a distance greater than
	 * the length, LENGTH1, apart.
	 * (0 ≤ LENGTH1)
	 * Returns false otherwise.
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean zero(InputVariables globals, Parameters params) {
		if(0 > params.LENGTH1) return false;
		for(int i = 0; i < globals.POINTS.length - 1; i++){
			if(globals.POINTS[i+1].distance(globals.POINTS[i]) > params.LENGTH1)
				return true;
		}
		return false;
	}
	
	/**
	 * There exists at least one set of three consecutive data points that cannot all be 
	 * contained within or on a circle of radius RADIUS1.
	 * 
	 * @param globals Instance of InputVariables, gives the state to evaluate
	 * @param params Instance of parameters
	 * @return truth value of launch condition 1
	 */

	static boolean one(InputVariables globals, Parameters params) {
		if (globals.NUMPOINTS < 3) {
			return false;
		}
		boolean could_bound = true;
		for (int i = 0; i + 2 < globals.NUMPOINTS && could_bound; i+= 3) {
			ArrayList<Point> points = new ArrayList<Point>();
			for (int j = 0; j < 3; j++) {
				points.add(globals.POINTS[i+j]);
			}
			/* This function uses an implementation for the bounding circle problem because that is nontrivial. */
			Circle cir = SmallestEnclosingCircle.makeCircle(points);
			could_bound &= cir.r <= params.RADIUS1;
		}

		return !could_bound;
	}

	/**
	 * Returns true if there exists a set of three consecutive points 
	 * in array POINTS in globals which form an angle 
	 * (with second point as vertex) such that 
	 * angle < (PI - EPISILON) or angle > (PI + EPSILON).
	 * If not met, or any point coincides with the vertex, false is returned.
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean two(InputVariables globals, Parameters params) {
		for(int i = 0; i < globals.NUMPOINTS - 2; i++) {
			Point vertex = globals.POINTS[i + 1];
			Point p = globals.POINTS[i].subtract(vertex);
			double angle = p.angle(globals.POINTS[i + 2].subtract(vertex), vertex);

			if(angle == -1) return false;  // If undefined angle, LIC is not satisfied
			if(angle < params.PI - params.EPSILON || angle > params.PI + params.EPSILON) return true;
		}
		return false;
	}

	/**
	 * Returns true if there exists at least one set of three consecutive data 
	 * points that are the vertices of a triangle with area greater than AREA1.
	 * 
	 * The area is calculted with the determinant from the matrix of the three points
	 * a, b and c:
	 * X1 Y1 1
	 * X2 Y2 1
	 * X3 Y3 1
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean three(InputVariables globals, Parameters params) {
		for (int i = 0; i < globals.POINTS.length - 2; i++) {
			Point a = globals.POINTS[i];
			Point b = globals.POINTS[i + 1];
			Point c = globals.POINTS[i + 2];
			double area = Geometry.triangleArea(a, b, c);
			if(area > params.AREA1)
				return true;
		}
		return false;
	}

	/**
	 * There exists at least one set of Q PTS consecutive data points that lie in more than QUADS
	 * quadrants. Where there is ambiguity as to which quadrant contains a given point, priority
	 * of decision will be by quadrant number, i.e., I, II, III, IV. For example, the data point (0,0)
	 * is in quadrant I, the point (-l,0) is in quadrant II, the point (0,-l) is in quadrant III, the point
	 * (0,1) is in quadrant I and the point (1,0) is in quadrant I.
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean four(InputVariables globals, Parameters params) {
		boolean[] inQuadrant = new boolean[5];
		int numberOfQuadrants = 0;
		for (int j = 0; j < globals.POINTS.length - (params.Q_PTS - 1); j++) {
			for (int i = 0; i < params.Q_PTS; i++) {
				if (globals.POINTS[i].x >= 0) {
					if (globals.POINTS[i].y >= 0) {
						inQuadrant[1] = true;
					} else {
						if (globals.POINTS[i].x == 0) {
							inQuadrant[3] = true;
						} else {
							inQuadrant[4] = true;
						}
					}
				} else {
					if (globals.POINTS[i].y >= 0) {
						inQuadrant[2] = true;
					} else {
						inQuadrant[3] = true;
					}
				}
			}
			numberOfQuadrants = 0;
			for (int i = 1; i <= 4; i++) {
				if (inQuadrant[i]) {
					numberOfQuadrants++;
				}
			}
			if (numberOfQuadrants > params.QUADS) {
				return true;
			}
			for (int i = 1; i <= 4; i++) {
				inQuadrant[i] = false;
			}
		}
		return false;
	}

	/**
	 * Returns true if there exists two consectutive points in array POINTS in globals
	 * such that the difference in x values of a point and the point prior to it is negative.
	 * Returns false otherwise.
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean five(InputVariables globals, Parameters params) {
		for(int i = 0; i < globals.POINTS.length - 1; i++){
			if(globals.POINTS[i+1].x - globals.POINTS[i].x < 0)
				return true;
		}
		return false;
	}

	/**
	 * There exists at least one set of N PTS consecutive data points such that at least one of the
	 * points lies a distance greater than DIST from the line joining the first and last of these N PTS
	 * points. If the first and last points of these N PTS are identical, then the calculated distance
	 * to compare with DIST will be the distance from the coincident point to all other points of
	 * the N PTS consecutive points. The condition is not met when NUMPOINTS < 3.
	 * (3 ≤ N PTS ≤ NUMPOINTS), (0 ≤ DIST)
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean six(InputVariables globals, Parameters params) {
		int N_PTS = params.N_PTS;
		double DIST = params.DIST;

		if(globals.NUMPOINTS < 3 || N_PTS < 3 || N_PTS > globals.NUMPOINTS || DIST < 0) {
			return false;
		}

		for(int i = 0; i <= globals.POINTS.length - N_PTS; i++){
			Point firstPoint = globals.POINTS[i];
			Point lastPoint = globals.POINTS[i + N_PTS - 1];
            
			if (firstPoint.x == lastPoint.x && firstPoint.y == lastPoint.y) {
				for (int j = i + 1; j < i + N_PTS - 1; j++) {
					Point currPoint = globals.POINTS[j];
					double distance =  firstPoint.distance(currPoint);
					if (distance > DIST) {
						return true;
					}
				}
			} else {
				for (int j = i + 1; j < i + N_PTS - 1; j++) {
					Point currPoint = globals.POINTS[j];
					double numerator = Math.abs((lastPoint.y - firstPoint.y) * currPoint.x - (lastPoint.x - firstPoint.x) * currPoint.y + lastPoint.x * firstPoint.y - lastPoint.y * firstPoint.x);
    				double denominator = lastPoint.distance(firstPoint);
					double distance = numerator / denominator;
					if (distance > DIST) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * There exists at least one set of two data points separated by exactly K PTS consecutive in-
 	 * tervening points that are a distance greater than the length, LENGTH1, apart. The condition
	 * is not met when NUMPOINTS < 3.
	 * 1 ≤ K PTS ≤ (NUMPOINTS − 2)
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean seven(InputVariables globals, Parameters params) {
		int K_PTS = params.K_PTS;
		double LENGTH1 = params.LENGTH1;

		if (globals.NUMPOINTS < 3 ||  K_PTS < 1 ||  K_PTS > globals.NUMPOINTS -2) {
			return false;
		}

		for (int i = 0; i < globals.POINTS.length - K_PTS - 1; i++) {
			Point firstPoint = globals.POINTS[i];
			Point secondPoint = globals.POINTS[i + K_PTS + 1];

			double distance = secondPoint.distance(firstPoint);
			if (distance > LENGTH1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * There exists at least one set of three data points separated by exactly A PTS and B PTS
	 * consecutive intervening points, respectively, that cannot be contained within or on a circle of
	 * radius RADIUS1. The condition is not met when NUMPOINTS < 5
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean eight(InputVariables globals, Parameters params) {
		if (globals.NUMPOINTS < 5) {
			return false;
		}
		boolean could_bound = true;
		for (int i = 0; i + params.A_PTS + params.B_PTS + 2 < globals.POINTS.length; i++){
			Point a = globals.POINTS[i];
			Point b = globals.POINTS[i+params.A_PTS + 1];
			Point c = globals.POINTS[i+params.A_PTS+params.B_PTS + 2];
			
			ArrayList<Point> points = new ArrayList<Point>();
			points.add(a);
			points.add(b);
			points.add(c);
			/* This function uses an implementation for the bounding circle problem because that is nontrivial. */
			Circle cir = SmallestEnclosingCircle.makeCircle(points);
			could_bound &= cir.r <= params.RADIUS1;
		}
		
		return !could_bound;
	}

	/**
	 * Returns true if there exists a set of three points separated by exactly 
	 * C_PTS and D_PTS consecutive intervening points, respectively,
	 * in array POINTS in globals which form an angle 
	 * (with second point as vertex) such that 
	 * angle < (PI - EPISILON) or angle > (PI + EPSILON).
	 * If not met, or any point coincides with the vertex, or NUMPOINTS < 5, 
	 * false is returned.
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean nine(InputVariables globals, Parameters params) {
		if(globals.NUMPOINTS < 5) return false;

		for(int i = 0; i < globals.NUMPOINTS - params.C_PTS - params.D_PTS - 2; i++) {
			Point vertex = globals.POINTS[i + params.C_PTS + 1];
			Point p = globals.POINTS[i].subtract(vertex);
			double angle = p.angle(globals.POINTS[i + params.C_PTS + params.D_PTS + 2].subtract(vertex), vertex);

			if(angle == -1) return false;  // If undefined angle, LIC is not satisfied
			if(angle < params.PI - params.EPSILON || angle > params.PI + params.EPSILON) return true;			
		}
		return false;
	}
	/**
	 * Returns true if there exists at least one set of three data points separated by exactly E PTS and F PTS con-
     * secutive intervening points, respectively, that are the vertices of a triangle with area greater
     * than AREA1.
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */

	static boolean ten(InputVariables globals, Parameters params) {
		if (globals.NUMPOINTS < 5) {
			return false;
		}
		boolean got_area = false;
		for (int i = 0; i + params.E_PTS + params.F_PTS + 2 < globals.NUMPOINTS && !got_area; i++) {
			Point a = globals.POINTS[i];
			Point b = globals.POINTS[i + params.E_PTS + 1];
			Point c = globals.POINTS[i + params.E_PTS + params.F_PTS+ 2];

			double area = Geometry.triangleArea(a, b, c);

			got_area |= area >= params.AREA1;
		}

		return got_area;
	}

	/**
	 * Returns true if there exists two poins in array POINTS in globals
	 * separated by G_PTS points such that the difference in x values of a 
	 * point and the prior to it is negative.
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean eleven(InputVariables globals, Parameters params) {
		if (globals.NUMPOINTS < 3 || params.G_PTS < 1 || globals.NUMPOINTS < params.G_PTS) return false;
		for(int i = 0; i < globals.POINTS.length - 1 - params.G_PTS; i++){
			if(globals.POINTS[i + params.G_PTS + 1].x - globals.POINTS[i].x < 0)
				return true;
		}
		return false;
	}

	/**
	 * Returns true if there exists at least two points separated by K_PTS consecutive intervening points, which are:
	 *     1. at a distance > LENGTH1 apart
	 *     2. at a distance < LENGTH2 apart.
	 * Both condition 1 and 2 has to be met, and the points can be the same or different points.
	 * If NUMPOINTS < 3, we also return false.
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean twelve(InputVariables globals, Parameters params) {
		if(globals.NUMPOINTS < 3) return false;
		double dist;
		for(int i = 0; i < globals.NUMPOINTS - params.K_PTS - 1; i++) {
			dist = globals.POINTS[i].distance(globals.POINTS[i + params.K_PTS + 1]);
			if(dist > params.LENGTH1 && dist < params.LENGTH2) return true;
		}; 
		return false;
	}
	
	/**
	 * Returns true if there exists set(s) of three points in array POINTS in globals
	 * separated by A_PTS and B_PTS consecutive intervening points respectively
	 * that can be contained in circles of both RADIUS1 and RADIUS2.
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	//https://opencurriculum.org/5492/circumscribed-and-inscribed-circles/ corollary 3.
	//https://www.geogebra.org/m/mWnvve4s
	//Calculates radius of circumscribed circle and compares to RADIUS1 and RADIUS2
	//if any angle is larger than 90 degrees the radius is half of longest side (obtuse triangle -> radius is half of longest side)
	//https://www.varsitytutors.com/gmat_math-help/calculating-the-length-of-the-side-of-an-acute-obtuse-triangle#:~:text=To%20determine%20whether%20the%20triangle,square%20of%20the%20largest%20side.&text=Since%20this%20sum%20is%20greater%2C%20the%20triangle%20is%20acute.
	static boolean thirteen(InputVariables globals, Parameters params) {
		if(globals.NUMPOINTS < 5 || params.RADIUS2 <= 0) return false;
		boolean cond1 = false, cond2 = false;
		double a, b, c, s, area, radius;
		Point p1, p2, p3;
		for(int i = 0; i < globals.POINTS.length - 2 - params.A_PTS - params.B_PTS; i++){
			p1 = globals.POINTS[i];
			p2 = globals.POINTS[i + 1 + params.A_PTS];
			p3 = globals.POINTS[i + 2 + params.A_PTS + params.B_PTS];

			a = p1.distance(p2);
			b = p2.distance(p3);
			c = p3.distance(p1);

			area = Geometry.triangleArea(p1, p2, p3);
			radius = (a * b * c) / (4 * area);
			//make a the longest side
			if (c > b){
				double temp = c;
				c = b;
				b = temp;
			}
			if (b > a){
				double temp = b;
				b = a;
				a = temp;
			}
			//if the triangle is obtuse circle center is middle of longest side and thus radius is half of longest side.
			if(Math.pow(b, 2) + Math.pow(c, 2) < Math.pow(a, 2)) radius = a/2;	

			if(radius > params.RADIUS1) cond1 = true;
			if(radius <= params.RADIUS2) cond2 = true;

			if (cond1 && cond2) return true;
		}
		return false;
	}

	/**
	 * There exists at least one set of three data points, separated by exactly E PTS and F PTS 
	 * consecutive intervening points, respectively, that are the vertices of a triangle with area greater
	 * than AREA1. In addition, there exist three data points (which can be the same or different
	 * from the three data points just mentioned) separated by exactly E PTS and F PTS 
	 * consecutive intervening points, respectively, that are the vertices of a triangle with area 
	 * less than AREA2. Both parts must be true for the LIC to be true. The condition is not met 
	 * when NUMPOINTS <5
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean fourteen(InputVariables globals, Parameters params) {
		if (globals.NUMPOINTS < 5) {
			return false;
		}
		boolean greaterThanAREA1 = false;
		boolean lesserThanAREA2 = false;

		int end = globals.POINTS.length - (params.E_PTS + params.F_PTS) - 2;
		for (int i = 0; i < end; i++) {
			Point a = globals.POINTS[i];
			Point b = globals.POINTS[i + params.E_PTS + 1];
			Point c = globals.POINTS[i + params.E_PTS + params.F_PTS + 2];
			double area = Geometry.triangleArea(a, b, c);
			if (area > params.AREA1) {
				greaterThanAREA1 = true;
			}
			if (area < params.AREA2) {
				lesserThanAREA2 = true;
			}
		}
		if (greaterThanAREA1 && lesserThanAREA2) {
			return true;
		}
		return false;
	}
}
