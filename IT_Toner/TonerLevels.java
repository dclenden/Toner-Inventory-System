//David W. Clendenning Jr. - CSC214 - Mini - Project
/* this class is entirely used as a pair for the toner levels
 * (only contains basic program get/set methods) 
 * */
package IT_Toner;

public class TonerLevels {
    private int blackToner;
    private int colorToner;
    
    public TonerLevels() {
    	
    }
    public TonerLevels(int bToner, int cToner) {
    	this.blackToner = bToner;
    	this.colorToner = cToner;
    }
    public void setBlackToner(int bToner) {
    	this.blackToner = bToner;
    }
    public int getBlackToner() {
    	return this.blackToner;
    }
    public void setColorToner(int cToner) {
    	this.colorToner = cToner;
    }
    
    public int getColorToner() {
    	return this.colorToner;
    }
    public String toString() {
    	return "[" + this.getBlackToner() + ", " + this.getColorToner() + "]";
    }
}
