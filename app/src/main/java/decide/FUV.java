package decide;

public class FUV {
    boolean[] PUV;
    PUM PUM;
	/**
     * Constructor for FUV that accepts the PUV and PUM as arguemnts.
     * @param 'PUV' A vector of booleans representing the PUV.
	 * @param 'PUM' An object representing the PUM.
     */
	FUV(boolean[] PUV, PUM PUM) {
		if (PUV == null || PUM == null) {   
			throw new NullPointerException();
		}
		this.PUV = PUV;
        this.PUM = PUM;
	}

    /**
     * Retrieve entries in the FUV.
     * @param 'i' Matrix index of the Final Unlocking Vector (FUV).
     */
	boolean get(int i) {
		if(!PUV[i]) return true;
        for(int j = 0; j < 15; j++) {
            if(!PUM.get(i, j)) return false;
        }
        return true;
	}
}
