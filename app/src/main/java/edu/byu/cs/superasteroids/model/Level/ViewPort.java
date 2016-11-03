package edu.byu.cs.superasteroids.model.level;

import android.graphics.PointF;
import android.graphics.Rect;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.database.SuperAsteroids_DAO;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.InputManager;
import edu.byu.cs.superasteroids.model.ship.Laser;
import edu.byu.cs.superasteroids.model.ship.StarShip;

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

    private static float posX;
    private static float posY;

    private Set<Laser> lasers = new HashSet<>();

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

        if(level.getAsteroids().size() == 0) {
            getNextLevel();
        }

        level.update(lasers);

        StarShip.getInstance().update();

        if(InputManager.firePressed) {
            lasers.add(new Laser());
        }

        for(Iterator<Laser> iterator = lasers.iterator(); iterator.hasNext(); ) {
            Laser laser = iterator.next();
            if((!laser.isOffScreen()) && (!laser.isHit())) {
                laser.update(level.getAsteroids());
            }
            else {
                iterator.remove();
            }
        }
    }

    public void draw() {
        background.draw();

        level.draw();

        StarShip.getInstance().draw();

        for(Laser laser : lasers) {
            laser.draw();
        }
    }

    public void loadContent(ContentManager content) {
        background.loadImage(content);
        level.loadContent(content);
    }

    public void unloadContent(ContentManager content) {
        background.unloadImage(content);
        level.unloadContent(content);
    }

    private void getNextLevel() {
        levelNumber = levelNumber+1;
        setLevel(SuperAsteroids_DAO.getInstance().getLevel(levelNumber));
    }

    private void setLevel(Level level) {
        this.level = level;
        worldWidth = this.level.getWidth();
        worldHeight = this.level.getHeight();

        posX = (worldWidth/2)-(viewWidth/2);
        posY = (worldHeight/2)-(viewHeight/2);
    }

    public static PointF worldToView(PointF position) {
        return new PointF((position.x - posX), (position.y - posY));
    }

    public static PointF viewToWorld(PointF position) {
        return new PointF((position.x + posX), (position.y + posY));
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

    public static int getWorldWidth() { return worldWidth; }

    public static int getWorldHeight() { return worldHeight;}

    public static float getPosX() { return posX; }
    public static float getPosY() { return posY; }

    public static PointF getCenter() {
        return new PointF(viewWidth/2f, viewHeight/2f);
    }

    public static void moveHorizontal(float distance) {
        float newX = posX+distance;
        if((newX < 0) || (newX > (worldWidth-viewWidth))){
            if(newX < 0) {
                posX = 0;
            }
            else{
                posX = (worldWidth-viewWidth);
            }
        }
        else {
            posX = newX;
        }
    }

    public static void moveVertical(float distance) {
        float newY = posY+distance;
        if((newY < 0) || (newY > (worldHeight-viewHeight))){
            if(newY < 0) {
                posY = 0;
            }
            else{
                posY = (worldHeight-viewHeight);
            }
        }
        else {
            posY = newY;
        }
    }
}
