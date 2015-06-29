package chris.knotted;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import chris.knotted.Wrapper;

import com.example.android.opengl.R;

/**
 * Created by Chris on 29/06/2015.
 */
public class MainPanel extends SurfaceView implements SurfaceHolder.Callback{

    private Wrapper wrapper;
    private OffThread thread;
    private static final String TAG = MainPanel.class.getSimpleName();

    public MainPanel(Context context){
        super(context);
        //set current class as handler for events from sruface
        getHolder().addCallback(this);
        wrapper = new Wrapper(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), 50, 50);
        thread = new OffThread(getHolder(), this);

        //make panel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread.setRunning(true);
        thread.start();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        //commands the thread to close safely
        boolean restart = true;
        while (restart){
            try{
                thread.join();
                restart = false;
            } catch(InterruptedException e){
                //TODO: thread shut off
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            wrapper.handleActionDown((int)event.getX(), (int)event.getY());

            if(event.getY() > getHeight() - 50){
                thread.setRunning(false);
                ((Activity)getContext()).finish();
            } else {
                Log.d(TAG, "Coordinates: (" + event.getX() +", " + event.getY() + ")");
            }
        } if (event.getAction() == MotionEvent.ACTION_MOVE){
            if(wrapper.isMoving()){
                wrapper.setX((int)event.getX());
                wrapper.setY((int)event.getY());
            }
        } if (event.getAction() == MotionEvent.ACTION_UP){
            if(wrapper.isMoving()){
                wrapper.setMoving(false);
            }
        }

        return true;
    }
    @Override
    protected void onDraw(Canvas canvas){

        canvas.drawColor(Color.BLACK);
        wrapper.draw(canvas);

        //canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.droid_1), 10, 10, null);
    }

}
