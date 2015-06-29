package chris.knotted;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Chris on 29/06/2015.
 */
public class OffThread extends Thread{

    private static final String TAG = OffThread.class.getSimpleName();

    private SurfaceHolder surfaceHolder;
    private MainPanel panel;
    private boolean running;

    public OffThread(SurfaceHolder surfaceHolder, MainPanel panel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.panel = panel;
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas;
        long count = 0L;
        Log.d(TAG, "Started loop");

        long beginning, difference, sleepT = 0, skipped;

        while (running) {
            count++;
            //  canvas = null;
//
            //          try{
            //            canvas = this.surfaceHolder.lockCanvas();
            //          synchronized (surfaceHolder){
            //            //draw canvas on panel
            //          this.panel.onDraw(canvas);
            //    }
            //} finally {
            //  if(canvas != null){
            //    surfaceHolder.unlockCanvasAndPost(canvas);
            //}
            //}

//            count++;
                  }
            Log.d(TAG, "loop executed " + count + "times");
        }
}
