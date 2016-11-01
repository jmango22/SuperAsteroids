package edu.byu.cs.superasteroids.game_objects;

import android.graphics.PointF;
import android.view.VelocityTracker;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.InputManager;
import edu.byu.cs.superasteroids.model.Cannon;
import edu.byu.cs.superasteroids.model.Engine;
import edu.byu.cs.superasteroids.model.ExtraPart;
import edu.byu.cs.superasteroids.model.Laser;
import edu.byu.cs.superasteroids.model.MainBody;
import edu.byu.cs.superasteroids.model.PowerCore;
import edu.byu.cs.superasteroids.model.ShipParts;

/**
 * Created by Jon on 10/27/2016.
 */

public class StarShip {
    private MainBody mainBody;
    private ExtraPart extraPart;
    private Cannon cannon;
    private Engine engine;
    private PowerCore powerCore;
    private float rotationDegrees;
    private float speed;

    private float velX;
    private float velY;

    private float scaleX;
    private float scaleY;

    private Set<Laser> lasers = new HashSet<>();

    private PointF location = ViewPort.getCenter();

    //Singleton instance
    private static volatile StarShip instance;

    public static StarShip getInstance() {
        if(instance == null) {
            instance = new StarShip();
        }
        return instance;
    }

    private StarShip() {
        mainBody = null;
        extraPart = null;
        cannon = null;
        engine = null;
        powerCore = null;
    }

    public Boolean isComplete() {
        if((mainBody != null)
        && (extraPart != null)
        && (cannon != null)
        && (engine != null)
        && (powerCore != null)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void moveHorizontal(float distance, PointF worldPoint) {
        float worldLeft = ViewPort.getViewWidth()/2f;
        float worldRight = ViewPort.getWorldWidth()-(worldLeft);
        if((ViewPort.getPosX() == 0) && (worldPoint.x < ViewPort.getViewWidth()/2f)) {
            this.setLocation(new PointF((location.x + distance), location.y));
        }
        else if((ViewPort.getPosX() == (ViewPort.getWorldWidth()-ViewPort.getViewWidth())) && (worldPoint.x > (ViewPort.getWorldWidth()-(ViewPort.getViewWidth()/2f)))) {
            this.setLocation(new PointF((location.x + distance), location.y));
        }
        else if(!((ViewPort.viewToWorld(location).x < worldLeft) || (ViewPort.viewToWorld(location).x > worldRight))) {
            ViewPort.moveHorizontal(distance);
        }
        else {
            this.setLocation(new PointF((location.x + distance), location.y));
        }
    }

    public void moveVertical(float distance, PointF worldPoint) {
        float worldTop = ViewPort.getViewHeight()/2f;
        float worldBottom = ViewPort.getWorldHeight()-(worldTop);
        if((ViewPort.getPosY() == 0) && (worldPoint.y < ViewPort.getViewHeight()/2f)) {
            this.setLocation(new PointF(location.x, (location.y+distance)));
        }
        else if((ViewPort.getPosY() == (ViewPort.getWorldHeight()-ViewPort.getViewHeight())) && (worldPoint.y > (ViewPort.getWorldHeight()-(ViewPort.getViewHeight()/2f)))) {
            this.setLocation(new PointF(location.x, (location.y+distance)));
        }
        else if(!((ViewPort.viewToWorld(location).y < worldTop) || (ViewPort.viewToWorld(location).y > worldBottom))) {
            ViewPort.moveVertical(distance);
        }
        else {
            this.setLocation(new PointF(location.x, (location.y+distance)));
        }
    }

    public void setVelX(float differenceX) {
        float tempX = speed*((differenceX/ViewPort.getViewWidth())/20);
        if(tempX > 10) {
            tempX = 10;
        }
        if(tempX < -10) {
            tempX = -10;
        }
        velX = tempX;
    }

    public void setVelY(float differenceY) {
        float tempY = speed*((differenceY/ViewPort.getViewHeight())/20);
        if(tempY > 10) {
            tempY = 10;
        }
        if(tempY < -10) {
            tempY = -10;
        }
        velY = tempY;
    }

    public void update() {
        PointF movePoint;
        if((movePoint = InputManager.movePoint) != null) {
            System.out.println("Move Point: "+InputManager.movePoint.toString());
            PointF worldPoint = ViewPort.viewToWorld(movePoint);

            float shipX = location.x;
            float shipY = location.y;

            float pointX = movePoint.x;
            float pointY = movePoint.y;

            float differenceX = pointX-shipX;
            float differenceY = pointY-shipY;

            setVelX(differenceX);
            setVelY(differenceY);

            System.out.println("Horizontal Speed: " + velX);
            System.out.println("Vertical Speed: " + velY);

            moveHorizontal(velX, worldPoint);
            moveVertical(velY, worldPoint);

            double radians = Math.atan2(differenceY, differenceX) + (Math.PI/2);

            StarShip.getInstance().setRotationDegrees((float)GraphicsUtils.radiansToDegrees(radians));
        }
        if(InputManager.firePressed) {
            lasers.add(new Laser(cannon, velX, velY));
        }
        for(Laser laser : lasers) {
            if(!(laser.isOffScreen())) {
                laser.update();
            }
        }
    }

    public void draw() {
        if(mainBody != null) {
            mainBody.draw(location.x, location.y, .3f, .3f, rotationDegrees);
            if(cannon != null) {
                cannon.draw(mainBody, mainBody.getCannonAttach());
            }
            if(extraPart != null) {
                extraPart.draw(mainBody, mainBody.getExtraAttach());
            }
            if(engine != null) {
                engine.draw(mainBody, mainBody.getEngineAttach());
            }
            for(Laser laser : lasers) {
                if(!(laser.isOffScreen())) {
                    laser.draw();
                }
            }
        }
    }

    public void draw(float posX, float posY, float scaleX, float scaleY, float rotationDegrees) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        setRotationDegrees(rotationDegrees);

        if(mainBody != null) {
            mainBody.draw(posX, posY, this.scaleX, this.scaleY, rotationDegrees);
            if(cannon != null) {
                cannon.draw(mainBody, mainBody.getCannonAttach());
            }
            if(extraPart != null) {
                extraPart.draw(mainBody, mainBody.getExtraAttach());
            }
            if(engine != null) {
                engine.draw(mainBody, mainBody.getEngineAttach());
            }
        }
    }

    public void setLocation(PointF location) {
        this.location = location;
    }

    public void setRandomParts() {
        this.setRandomMainBody();
        this.setRandomEngine();
        this.setRandomCannon();
        this.setRandomExtraPart();
        this.setRandomPowerCore();
    }

    private int getRandomNumber(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    private void setRandomMainBody() {
        int max = ShipParts.getInstance().getMainBodies().size()-1;
        int randomIndex = getRandomNumber(0, max);
        MainBody mainBody = ShipParts.getInstance().getMainBodies().get(randomIndex);
        this.setMainBody(mainBody);
    }

    private void setRandomEngine() {
        int max = ShipParts.getInstance().getEngines().size()-1;
        int randomIndex = getRandomNumber(0, max);
        Engine engine = ShipParts.getInstance().getEngines().get(randomIndex);
        this.setEngine(engine);
    }

    private void setRandomCannon() {
        int max = ShipParts.getInstance().getCannons().size()-1;
        int randomIndex = getRandomNumber(0, max);
        Cannon cannon = ShipParts.getInstance().getCannons().get(randomIndex);
        this.setCannon(cannon);
    }

    private void setRandomExtraPart() {
        int max = ShipParts.getInstance().getExtraParts().size() - 1;
        int randomIndex = getRandomNumber(0, max);
        ExtraPart extraPart = ShipParts.getInstance().getExtraParts().get(randomIndex);
        this.setExtraPart(extraPart);
    }

    private void setRandomPowerCore() {
        int max = ShipParts.getInstance().getPowerCores().size()-1;
        int randomIndex = getRandomNumber(0, max);
        PowerCore powerCore = ShipParts.getInstance().getPowerCores().get(randomIndex);
        this.setPowerCore(powerCore);
    }

    public void clearAll() {
        mainBody = null;
        cannon = null;
        extraPart = null;
        engine = null;
        powerCore = null;
    }

    public void setRotationDegrees(float rotationDegrees) { this.rotationDegrees = rotationDegrees; }

    public void setMainBody(MainBody mainBody) {
        this.mainBody = mainBody;
    }

    public void setExtraPart(ExtraPart extraPart) {
        this.extraPart = extraPart;
    }

    public void setCannon(Cannon cannon) {
        this.cannon = cannon;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
        this.speed = engine.getBaseSpeed();
    }

    public void setPowerCore(PowerCore powerCore) {
        this.powerCore = powerCore;
        if(engine != null) {
            this.speed = this.speed+this.powerCore.getEngineBoost();
        }
    }

    public void setScaleX(float scaleX) {this.scaleX = scaleX;}

    public void setScaleY(float scaleY) {this.scaleY = scaleY;}

    public MainBody getMainBody () {
        return mainBody;
    }

    public ExtraPart getExtraPart () {
        return extraPart;
    }

    public Cannon getCannon () {
        return cannon;
    }

    public Engine getEngine () {
        return engine;
    }
}
