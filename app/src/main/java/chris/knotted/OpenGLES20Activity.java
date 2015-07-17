package chris.knotted;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.android.opengl.R;

import java.util.ArrayList;


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

    private final int bmpW = 300;
    private final int bmpH = 225;

    private ImageView mCrossView, mIdenView, mSwapView;
    private ArrayList<KnotView> viewList = new ArrayList<KnotView>();
    private KnotView myImgView;
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

                    int[] basic = new int[2];
                    basic[0] = 75;
                    basic[1] = 150;

                    myImgView = new KnotView(parent, basic, basic, "C");
                    myImgView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    myImgView.setOnTouchListener(parent);
                    /*int[] a = new int[2];
                    int[] b = new int[2];
                    a[0] = 10;
                    a[1] = 20;
                    b[0] = 50;
                    b[1] = 30;

                    myImgView.setImageBitmap(
                            BitmapTools.drawBeziers(a, b, BitmapTools.decodeSampledBitmapFromResource(getResources(),
                                    R.drawable.swap, 30, 30).getHeight()));
                                    */
                    myImgView.setImageBitmap(BitmapTools.decodeSampledBitmapFromResource(getResources(),
                            R.drawable.cross, 50, 50));
                    RelativeLayout rl = (RelativeLayout) (findViewById(R.id.root));
                    rl.addView(myImgView);
                    myImgView.setX(500);
                    myImgView.setY(500);
                    viewList.add(myImgView);
                    Log.d("this", ": " + viewList.size());
                    Log.d("MEASURE", "" + BitmapTools.decodeSampledBitmapFromResource(getResources(),
                            R.drawable.cross, 50, 50).getHeight());
                    Log.d("MEASURE", "" + BitmapTools.decodeSampledBitmapFromResource(getResources(),
                            R.drawable.cross, 50, 50).getWidth());
                }
                return true;
            }
        });
        //mIdenView.setLayoutParams(layoutParamsB);
        mIdenView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    int[] basic = new int[1];
                    basic[0] = 112;

                    myImgView = new KnotView(parent, basic, basic, "I");
                    myImgView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    myImgView.setOnTouchListener(parent);
                    myImgView.setImageBitmap(BitmapTools.decodeSampledBitmapFromResource(getResources(),
                            R.drawable.identity, 50, 50));
                    ((RelativeLayout)(findViewById(R.id.root))).addView(myImgView);
                    myImgView.setX(500);
                    myImgView.setY(500);

                    viewList.add(myImgView);
                }
                return true;
            }
        });


        mSwapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    int[] basic = new int[2];
                    basic[0] = 75;
                    basic[1] = 150;

                    myImgView = new KnotView(parent, basic, new int[0], "S");
                    myImgView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    myImgView.setOnTouchListener(parent);
                    myImgView.setImageBitmap(BitmapTools.decodeSampledBitmapFromResource(getResources(),
                            R.drawable.swap, 50, 50));
                    ((RelativeLayout)(findViewById(R.id.root))).addView(myImgView);
                    myImgView.setX(500);
                    myImgView.setY(500);

                    viewList.add(myImgView);
                }
                return true;
            }
        });


    }

    public boolean onTouch(View view, MotionEvent event) {
        KnotView currentImage = (KnotView)view;

        Rect rc1 = new Rect();
        view.getHitRect(rc1);

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
                //check collisions
                Rect rc2 = new Rect();

                for( int i = 0; i < viewList.size(); i++){
                    viewList.get(i).getHitRect(rc2);
                    //check through list for possible collisions
                    if(!viewList.get(i).equals(view) && Rect.intersects(rc1, rc2)){
                        Log.d("TAG", "INTERSECT");

                        BitmapDrawable bitmapDrawable = ((BitmapDrawable) currentImage.getDrawable());
                        Bitmap bitmapFirst = bitmapDrawable.getBitmap();

                        bitmapDrawable = ((BitmapDrawable)viewList.get(i).getDrawable());
                        Bitmap bitmapSecond = bitmapDrawable.getBitmap();

                        /*
                        Check collision location; to make sure combination happens correctly
                         */

                        Bitmap bitmapFinal = null;
                        int[] newCardR = null;
                        int[] newCardL = null;



                        if(checkCollisionLoc(currentImage, viewList.get(i)) == 1){

                            if(currentImage.getCardR().length == viewList.get(i).getCardL().length){
                                Log.d("HERE", "Equal cards");

                                Bitmap bitmapInterstitial;
                                bitmapFinal = BitmapTools.combineImagesHorizontal(BitmapTools.combineImagesHorizontal(
                                        bitmapFirst, BitmapTools.drawBeziers(currentImage.getCardR(), viewList.get(i).getCardL(), bmpH)
                                ), bitmapSecond);

                                newCardR = viewList.get(i).getCardR();
                                newCardL = currentImage.getCardL();
                            } else {
                                //reset position
                                bitmapFinal = BitmapTools.combineImagesHorizontal(bitmapFirst, bitmapSecond);
                            }

                        }

                        if(checkCollisionLoc(currentImage, viewList.get(i)) == 2){

                            bitmapFinal = BitmapTools.combineImagesVertical(bitmapSecond, bitmapFirst);

                            newCardR = concat(currentImage.getCardR(), viewList.get(i).getCardR());
                            newCardL = concat(currentImage.getCardL(), viewList.get(i).getCardL());

                        }

                        if(checkCollisionLoc(currentImage, viewList.get(i)) == 3){

                            bitmapFinal = BitmapTools.combineImagesHorizontal(bitmapSecond, bitmapFirst);

                            newCardL = viewList.get(i).getCardL();
                            newCardR = currentImage.getCardR();

                        }

                        if(checkCollisionLoc(currentImage, viewList.get(i)) == 4){

                            bitmapFinal = BitmapTools.combineImagesVertical(bitmapFirst, bitmapSecond);

                            newCardR = concat( viewList.get(i).getCardR(), currentImage.getCardR());
                            newCardL = concat(viewList.get(i).getCardL(), currentImage.getCardL());

                        }

                        if(checkCollisionLoc(currentImage, viewList.get(i)) == 0){

                            Log.d("COLLISION ERROR", "0 returned");

                        }

                        myImgView = new KnotView(parent, newCardL, newCardR, "");
                        myImgView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                        myImgView.setOnTouchListener(parent);

                        myImgView.setImageBitmap(Bitmap.createScaledBitmap(bitmapFinal, bmpW, bmpH, false));
                        ((RelativeLayout)(findViewById(R.id.root))).addView(myImgView);

                        myImgView.setX(500);
                        myImgView.setY(500);

                        viewList.add(myImgView);

                        RelativeLayout rl = (RelativeLayout) (findViewById(R.id.root));

                        rl.removeView(viewList.get(i));
                        viewList.remove(viewList.get(i));

                        rl.removeView(currentImage);
                        viewList.remove(currentImage);

                    }
                }

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

    /*
    returns 1 if collide left, 2 if top, 3 if right, 4 if bottom
     */
    public int checkCollisionLoc(ImageView moved, ImageView collided) {

        int movedCenterXOnImage = moved.getWidth() / 2;
        int movedCenterYOnImage = moved.getHeight() / 2;

        int movedCenterXOfImageOnScreen = moved.getLeft() + movedCenterXOnImage;
        int movedCenterYOfImageOnScreen = moved.getTop() + movedCenterYOnImage;

        int collidedCenterXOnImage = collided.getWidth() / 2;
        int collidedCenterYOnImage = collided.getHeight() / 2;

        int collidedCenterXOfImageOnScreen = collided.getLeft() + collidedCenterXOnImage;
        int collidedCenterYOfImageOnScreen = collided.getTop() + collidedCenterYOnImage;


        if (movedCenterXOfImageOnScreen > collidedCenterXOfImageOnScreen) {
            if (movedCenterYOfImageOnScreen > collidedCenterYOfImageOnScreen + 30) {
                return 2;
            } else if (movedCenterYOfImageOnScreen < collidedCenterYOfImageOnScreen - 30) {
                return 4;
            } else return 3;
        } else {
            if (movedCenterYOfImageOnScreen > collidedCenterYOfImageOnScreen + 30) {
                return 2;
            } else if (movedCenterYOfImageOnScreen < collidedCenterYOfImageOnScreen - 30) {
                return 4;
            } else return 1;

        }
    }

    public int[] concat(int[] a, int[] b) {
        int aLen = a.length;
        int bLen = b.length;
        int[] c= new int[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

}
