package edu.byu.cs.superasteroids.game_objects;

import android.graphics.PointF;
import android.graphics.Rect;
import android.sax.StartElementListener;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.database.SuperAsteroids_DAO;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.InputManager;
import edu.byu.cs.superasteroids.model.Level;

/**
 * Created by jmeng2 on 10/31/16.
 */
public class ViewPort {
    private Background background = new Background();

    private static int viewWidth;
    private static int viewHeight;

    private Level level;
    private int levelNumber=0;
    private static int worldWidth;
    private static int worldHeight;

    private static float posX=0f;
    private static float posY=0f;

    public ViewPort() {
        getNextLevel();
    }

    public void update() {
        if((viewWidth == 0) || (viewHeight == 0)) {
            System.out.println("Bad width or height, recalibrating...");
            viewWidth = DrawingHelper.getGameViewWidth();
            viewHeight = DrawingHelper.getGameViewHeight();
            StarShip.getInstance().setLocation(new PointF(((float)viewWidth)/2f, ((float)viewHeight)/2f));
            StarShip.getInstance().draw();
        }
        StarShip.getInstance().update();
    }

    public void draw() {
        background.draw();

        level.draw();

        StarShip.getInstance().draw();
    }

    public void loadContent(ContentManager content) {
        background.loadImage(content);
        level.loadContent(content);
    }

    public void unloadContent(ContentManager content) {
        background.unloadImage(content);

        level.unloadContent(content);
    }

    public void getNextLevel() {
        levelNumber = levelNumber+1;
        setLevel(SuperAsteroids_DAO.getInstance().getLevel(levelNumber));
    }

    private void setLevel(Level level) {
        this.level = level;
        worldWidth = this.level.getWidth();
        worldHeight = this.level.getHeight();
    }

    public static PointF worldToView(PointF position) {
        return new PointF((position.x-posX),(position.y-posY));
    }

    // src A rectangle in image coordinates specifying the portion of the image to draw


    // dest A rectangle in view coordinates specifying where to draw the portion of the image
    public static Rect getDest() {
        Rect destRect = new Rect(0, 0, viewWidth, viewHeight);
        return destRect;
    }

    // src A rectangle in image coordinates specifying the portion of the image to draw
    public static Rect getSrc(int imageId) {
        float scaleX = ((float)ContentManager.getInstance().getImage(imageId).getWidth())/((float)worldWidth);
        float scaleY = ((float)ContentManager.getInstance().getImage(imageId).getHeight())/((float)worldHeight);
        Rect srcRect = new Rect((int)(posX*scaleX), (int)(posY*scaleY), (int)((posX+viewWidth)*scaleX), (int)((posY+viewHeight)*scaleY));
        return srcRect;
    }

    public static int getViewWidth() {
        return viewWidth;
    }

    public static int getViewHeight() {
        return viewHeight;
    }

    public static float getPosX() { return posX; }
    public static float getPosY() { return posY; }

    public static PointF getCenter() {
        return new PointF(viewWidth/2f, viewHeight/2f);
    }
    public static boolean canMoveHorizontal(float distance) {
        float newX = posX+distance;
        if((newX < 0) || (newX > (worldWidth-viewWidth))){
            if(newX < 0) {
                posX = 0;
            }
            else{
                posX = (worldWidth-viewWidth);
            }
            return false;
        }
        else {
            posX = newX;
            return true;
        }
    }

    public static boolean canMoveVertical(float distance) {
        float newY = posY+distance;
        if((newY < 0) || (newY > (worldHeight-viewHeight))){
            if(newY < 0) {
                posY = 0;
            }
            else{
                posY = (worldHeight-viewHeight);
            }
            return false;
        }
        else {
            posY = newY;
            return true;
        }
    }
}
