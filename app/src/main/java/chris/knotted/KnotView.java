package chris.knotted;

import android.app.Activity;
import android.widget.ImageView;

/**
 * Created by Chris on 13/07/2015.
 */
public class KnotView extends ImageView {

    //these are the left cardinalitis, and right cardinalities respectively
    //i.e. the number of free "ports" on each side of the tile
    private final int[] cardL, cardR;
    //the resord of the algebraic representation
    private final String algebraRep;

    public KnotView(Activity activity, int[] cardL, int[] cardR, String algebraRep){
        super(activity);
        this.cardL = cardL;
        this.cardR = cardR;
        this.algebraRep = algebraRep;
    }

    public String getAlgebraRep() {
        return algebraRep;
    }

    public int[] getCardL() {
        return cardL;
    }

    public int[] getCardR() {
        return cardR;
    }
}
