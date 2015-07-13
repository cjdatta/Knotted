package chris.knotted;

import android.app.Activity;
import android.widget.ImageView;

/**
 * Created by Chris on 13/07/2015.
 */
public class KnotView extends ImageView {

    private int[] cardL, cardR;

    public KnotView(Activity activity, int[] cardL, int[] cardR){
        super(activity);
        this.cardL = cardL;
        this.cardR = cardR;
    }

}
