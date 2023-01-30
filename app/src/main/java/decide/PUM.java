package decide;

public class PUM {
	boolean[] CMV;
	LogicalConnectorMatrix LCM;
	/**
     * Constructor for PUM that accepts the CMV as an arguemnt.
     * @param 'VMC' A vector of booleans representing the CMV as described on p3.
	 * @param 'LCM' An object representing the logical connetor matrix as described on p5.
     */
	PUM(boolean[] CMV, LogicalConnectorMatrix LCM) {
		if (CMV == null || LCM == null) {
			throw new NullPointerException();
		}
		this.CMV = CMV;
		this.LCM = LCM;
	}
	/**
     * Retrieve entries in the PUM.
     * @param 'i' First matrix index to LCM and vector index to CMV.
	 * @param 'j' Second matrix index to LCM and vector index to CMV.
     */
	boolean get(int i, int j) {
		var op = LCM.get(i, j);
		switch (op) {
			case ANDD:
				return this.CMV[i] && this.CMV[j];
			case ORR:
				return this.CMV[i] || this.CMV[j];
			case NOTUSED:
				return true;
			default:
				// Chosen because this should be never reached and if it does
				// I do not want the nukes to fly because of it.
				return false;
		}
	}

}
