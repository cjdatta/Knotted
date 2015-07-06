package chris.knotted;

import android.app.Activity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.android.opengl.R;


/**
 * Created by Chris on 29/06/2015.
 */
public class OpenGLES20Activity extends Activity implements View.OnTouchListener {

/*    private MainPanel myPanel;
    private static final String TAG = OpenGLES20Activity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myPanel = new MainPanel(this);
        setContentView(myPanel);
    }
*/

    private ImageView mCrossView, mIdenView, mSwapView;
    private ImageView myImgView;
    private OpenGLES20Activity parent = this;
    private ViewGroup mRrootLayout;
    private int xDelta;
    private int yDelta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mRrootLayout = (ViewGroup) findViewById(R.id.root);

        //init the ImageViews
        mCrossView = (ImageView) mRrootLayout.findViewById(R.id.crossView);
        mSwapView = (ImageView) mRrootLayout.findViewById(R.id.swapView);
        mIdenView = (ImageView) mRrootLayout.findViewById(R.id.identityView);
        //set their images
        mSwapView.setImageBitmap(BitmapTools.decodeSampledBitmapFromResource(getResources(),
                R.drawable.swap, 40, 40));
        mCrossView.setImageBitmap(BitmapTools.decodeSampledBitmapFromResource(getResources(),
                R.drawable.cross, 40, 40));
        mIdenView.setImageBitmap(BitmapTools.decodeSampledBitmapFromResource(getResources(),
                R.drawable.identity, 40, 40));
        //set touch listeners
        //crossview is the lab rat for the drawing method
        mCrossView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    myImgView = new ImageView(parent);
                    myImgView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    myImgView.setOnTouchListener(parent);
                    int[] a = new int[2];
                    int[] b = new int[2];
                    a[0] = 10;
                    a[1] = 20;
                    b[0] = 50;
                    b[1] = 30;

                    myImgView.setImageBitmap(
                            BitmapTools.drawBeziers(a, b, BitmapTools.decodeSampledBitmapFromResource(getResources(),
                                    R.drawable.swap, 30, 30).getHeight()));
                    RelativeLayout rl = (RelativeLayout) (findViewById(R.id.root));
                    rl.addView(myImgView);
                    myImgView.setTop(500);
                    myImgView.setLeft(500);
                }
                return true;
            }
        });
        //mIdenView.setLayoutParams(layoutParamsB);
        mIdenView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    myImgView = new ImageView(parent);
                    myImgView.setLayoutParams(new RelativeLayout.LayoutParams(150, 150));
                    myImgView.setOnTouchListener(parent);
                    myImgView.setImageBitmap(BitmapTools.decodeSampledBitmapFromResource(getResources(),
                            R.drawable.identity, 30, 30));
                    ((RelativeLayout)(findViewById(R.id.root))).addView(myImgView);
                    myImgView.setTop(500);
                    myImgView.setLeft(500);
                }
                return true;
            }
        });


    }

    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams =
                        (RelativeLayout.LayoutParams) view.getLayoutParams();
                xDelta = X - lParams.leftMargin;
                yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams =
                        (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = X - xDelta;
                layoutParams.topMargin = Y - yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                view.setLayoutParams(layoutParams);
                break;
        }
        mRrootLayout.invalidate();
        return true;
    }

}
