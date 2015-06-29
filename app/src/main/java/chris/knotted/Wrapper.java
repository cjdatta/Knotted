package chris.knotted;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
/**
 * Created by Chris on 29/06/2015.
 */
public class Wrapper {

    private Bitmap bitmap;
    private boolean moving;
    private int x, y;

    public Wrapper(Bitmap bitmap, int x, int y){
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, x - (bitmap.getWidth()/2), y - (bitmap.getHeight()/2), null);

    }

    public void handleActionDown(int eventX, int eventY){
        if (eventX >= (x-bitmap.getWidth())/2 && (eventX <= (x + bitmap.getWidth()/2))) {
            if (eventY >= (y - bitmap.getHeight() / 2) && (y <= (y + bitmap.getHeight() / 2))) {
                setMoving(true);

            } else setMoving(false);

        } else setMoving(false);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
