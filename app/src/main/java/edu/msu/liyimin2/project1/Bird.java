package edu.msu.liyimin2.project1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * This is a starting point for a class for a bird. It includes functions to
 * load the bird image and to do collision detection against another bird.
 */
public class Bird implements Serializable {
    //region Member Variables

    /**
     * The image for the actual bird.
     */
    private transient Bitmap bird;

    /**
     * Rectangle that is where our bird is.
     */
    private transient Rect rect;

    /**
     * Rectangle we will use for intersection testing
     */
    private transient Rect overlap = new Rect();

    /**
     * x location in pixels
     */
    private float x = 0;

    /**
     * y location
     */
    private float y = 0;



    private int birdId;

    //endregion

    public Bird(Context context, int id) {
        rect = new Rect();
        ///!!!!ATTENTION!!!!DISABLE BECAUSE NOT WORKING!!!///
        //setRect();
        bird = BitmapFactory.decodeResource(context.getResources(), id);
        birdId = id;
    }

    public void move(float dx, float dy, int marginX, int marginY, int viewSize) {
        x += dx;
        y += dy;
        ///!!!!ATTENTION!!!!DISABLE BECAUSE NOT WORKING!!!///
        // setRect();
        /*if (0.1f <= x+dx && x+dx <= 0.9f && 0.1f <= y+dy && y+dy <= 0.9f){
            x += dx;
            y += dy;
            ///!!!!!!!!//setRect();
        }*/
        //return;

    }

    public boolean hit(float testX, float testY) {
        int pX = (int)((testX - x));
        int pY = (int)((testY - y));

        if(pX < 0 || pX >= bird.getWidth() || pY < 0 || pY >= bird.getHeight()) {
            return false;
        }

        // We are within the rectangle of the piece.
        // Are we touching actual picture?
        return (bird.getPixel(pX, pY) & 0xff000000) != 0;
    }

    /**
     * Collision detection between two birds. This object is
     * compared to the one referenced by other
     * @param other Bird to compare to.
     * @return True if there is any overlap between the two birds.
     */
    public boolean collisionTest(Bird other) {
        // Do the rectangles overlap?
        if(!Rect.intersects(rect, other.rect)) {
            return false;
        }

        // Determine where the two images overlap
        overlap.set(rect);
        overlap.intersect(other.rect);

        // We have overlap. Now see if we have any pixels in common
        for(int r=overlap.top; r<overlap.bottom;  r++) {
            int aY = (int)((r - y));
            int bY = (int)((r - other.y));

            for(int c=overlap.left;  c<overlap.right;  c++) {

                int aX = (int)((c - x));
                int bX = (int)((c - other.x));

                if( (bird.getPixel(aX, aY) & 0x80000000) != 0 &&
                        (other.bird.getPixel(bX, bY) & 0x80000000) != 0) {
                    //Log.i("collision", "Overlap " + r + "," + c);
                    return true;
                }
            }
        }

        return false;
    }

    private synchronized void writeObject(java.io.ObjectOutputStream stream) throws java.
            io.IOException {
        stream.writeFloat(x);
        stream.writeFloat(y);
        stream.writeInt(birdId);
        /*ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        bird.compress(Bitmap.CompressFormat.PNG, 0, byteStream);
        byte bitmapBytes[] = byteStream.toByteArray();
        stream.write(bitmapBytes, 0, bitmapBytes.length);*/
    }

    private synchronized void readObject(java.io.ObjectInputStream stream) throws java.
            io.IOException, ClassNotFoundException {
        x = stream.readFloat();
        y = stream.readFloat();
        birdId = stream.readInt();
        /*ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int b;
        while((b = stream.read()) != -1)
            byteStream.write(b);
        byte bitmapBytes[] = byteStream.toByteArray();
        bird = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
        */
    }

    public void draw(Canvas canvas, int marginX, int marginY, int gameViewSize, float scaleFactor) {
        //setRect();

        canvas.save();
        // Convert x,y to pixels and add the margin, then draw
        canvas.translate(marginX + x * gameViewSize, marginY + y * gameViewSize);
        canvas.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        canvas.translate(-bird.getWidth() / 2, -bird.getHeight()/2);
        canvas.drawBitmap(bird, 0, 0 , null);
        canvas.restore();
    }

    public boolean hit(float testX, float testY, int gameViewSize, float scaleFactor) {
        // Make relative to the location and size to the piece size
        int pX = (int)((testX - x) * gameViewSize / scaleFactor) + bird.getWidth() / 2;
        int pY = (int)((testY - y) * gameViewSize / scaleFactor) + bird.getHeight() / 2;

        if(pX < 0 || pX >= bird.getWidth() ||
                pY < 0 || pY >= bird.getHeight()) {
            return false;
        }

        // We are within the rectangle of the piece.
        // Are we touching actual picture?
        return (bird.getPixel(pX, pY) & 0xff000000) != 0;
    }

    public void update(Context context){
        bird = BitmapFactory.decodeResource(context.getResources(), birdId);
    }

    //region Getters and Setters

    private void setRect() {
        rect.set((int)x, (int)y, (int)x, (int)y);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getBirdId() {
        return birdId;
    }
    //endregion
}
