package chris.knotted;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Chris on 29/06/2015.
 */
public class OpenGLES20Activity extends Activity {

    private MainPanel myPanel;
    private static final String TAG = OpenGLES20Activity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myPanel = new MainPanel(this);
        setContentView(myPanel);
    }



}
