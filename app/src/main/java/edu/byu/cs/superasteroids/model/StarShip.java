package edu.byu.cs.superasteroids.model;

import java.util.Random;

import edu.byu.cs.superasteroids.database.SuperAsteroids_DAO;

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

    private float scaleX;
    private float scaleY;

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

    public void draw(float posX, float posY, float scaleX, float scaleY, float rotationDegrees) {
        setScaleX(scaleX);
        setScaleY(scaleY);
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
        int max = ShipParts.getInstance().getExtraParts().size()-1;
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
    }

    public void setPowerCore(PowerCore powerCore) {
        this.powerCore = powerCore;
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

    public PowerCore getPowerCore () {
        return powerCore;
    }

}
