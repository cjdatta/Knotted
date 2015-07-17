package chris.knotted;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.android.opengl.R;

/**
 * Created by Chris on 06/07/2015.
 */
public abstract class BitmapTools {

    public static Bitmap drawBeziers(int[] left, int[] right, int reqH){

        //TODO: Modify for a 0, 0 size inputs

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);

        int w = 100, h = reqH;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(w, h, conf); // MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);
        canvas.drawColor(Color.WHITE);
        for (int i = 0; i<left.length; i++){
            canvas.drawLine(0, left[i], 100, right[i], paint);
        }
        return Bitmap.createBitmap(bmp);
    }

    /* The following two methods were reused from
    http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
    and when used will scale bitmaps appropriately
     */

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap combineImagesHorizontal(Bitmap c, Bitmap s) {
        Bitmap cs = null;

        int width, height = 0;

        if(c.getWidth() > s.getWidth()) {
            width = c.getWidth() + s.getWidth();
            height = c.getHeight();
        } else {
            width = s.getWidth() + s.getWidth();
            height = c.getHeight();
        }

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas comboImage = new Canvas(cs);

        comboImage.drawBitmap(c, 0f, 0f, null);
        comboImage.drawBitmap(s, c.getWidth(), 0f, null);

        return cs;
    }

    public static Bitmap combineImagesVertical(Bitmap c, Bitmap s) {
        Bitmap cs = null;

        int width, height = 0;

        if(c.getWidth() > s.getWidth()) {
            width = c.getWidth();
            height = c.getHeight() + s.getHeight();
        } else {
            width = s.getWidth();
            height = c.getHeight() + s.getHeight();
        }

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas comboImage = new Canvas(cs);

        comboImage.drawBitmap(c, 0f, 0f, null);
        comboImage.drawBitmap(s, 0f, c.getHeight(), null);

        return cs;
    }


}
