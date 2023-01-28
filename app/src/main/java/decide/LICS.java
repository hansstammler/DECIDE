package decide;

public class LICS {
	static boolean zero(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean one(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean two(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean three(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean four(InputVariables globals, Parameters params) {
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

	static boolean six(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean seven(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean eight(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean nine(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean ten(InputVariables globals, Parameters params) {
		return false;
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

	static boolean twelve(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean thirteen(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean fourteen(InputVariables globals, Parameters params) {
		return false;
	}

}
