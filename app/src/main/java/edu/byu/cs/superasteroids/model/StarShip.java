package edu.byu.cs.superasteroids.model;

import java.util.Set;

import edu.byu.cs.superasteroids.database.SuperAsteroids_DAO;

/**
 * Created by Jon on 10/27/2016.
 */
public class StarShip {
    public static final StarShip SINGLETON = new StarShip();

    private MainBody mainBody;
    private ExtraPart extraPart;
    private Cannon cannon;
    private Engine engine;
    private PowerCore powerCore;

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

    public void draw(Float posX, Float posY, Float rotationDegrees) {
        if(mainBody != null) {
            mainBody.draw(posX, posY, rotationDegrees);
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
