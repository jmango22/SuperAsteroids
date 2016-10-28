package edu.byu.cs.superasteroids.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.byu.cs.superasteroids.database.SuperAsteroids_DAO;

/**
 * Created by Jon on 10/27/2016.
 */
public class ShipParts {
    public static final ShipParts SINGLETON = new ShipParts();

    private List<MainBody> mainBodies;
    private List<ExtraPart> extraParts;
    private List<Cannon> cannons;
    private List<Engine> engines;
    private List<PowerCore> powerCores;

    private ShipParts() {
        mainBodies = SuperAsteroids_DAO.SINGLETON.getMainBodies();
        extraParts = SuperAsteroids_DAO.SINGLETON.getExtraParts();
        cannons = SuperAsteroids_DAO.SINGLETON.getCannons();
        engines = SuperAsteroids_DAO.SINGLETON.getEngines();
        powerCores = SuperAsteroids_DAO.SINGLETON.getPowerCores();
    }

    public void loadAllShipPartImages() {
        for(MainBody mainBody : mainBodies) {
            mainBody.loadImage();
        }

        for(ExtraPart extraPart : extraParts) {
            extraPart.loadImage();
        }

        for(Cannon cannon : cannons) {
            cannon.loadImage();
        }

        for(Engine engine : engines) {
            engine.loadImage();
        }

        for(PowerCore powerCore : powerCores) {
            powerCore.loadImage();
        }
    }

    public void unloadUnusedMainBodies() {
        for(MainBody mainBody : mainBodies) {
            if(!(mainBody.equals(StarShip.SINGLETON.getMainBody()))) {
                mainBody.unloadImage();
            }
        }
    }

    public void unloadUnusedExtraParts() {
        for(ExtraPart extraPart : extraParts) {
            if(!(extraPart.equals(StarShip.SINGLETON.getExtraPart()))) {
                extraPart.unloadImage();
            }
        }
    }

    public void unloadUnusedCannons() {
        for(Cannon cannon : cannons) {
            if(!(cannon.equals(StarShip.SINGLETON.getCannon()))) {
                cannon.unloadImage();
            }
        }
    }

    public void unloadUnusedEngines() {
        for(Engine engine : engines) {
            if(!(engine.equals(StarShip.SINGLETON.getEngine()))) {
                engine.unloadImage();
            }
        }
    }

    public void unloadUnusedPowerCores() {
        for(PowerCore powerCore : powerCores) {
            if(!(powerCore.equals(StarShip.SINGLETON.getEngine()))) {
                powerCore.unloadImage();
            }
        }
    }

    public List<MainBody> getMainBodies () {
        return mainBodies;
    }

    public List<ExtraPart> getExtraParts () {
        return extraParts;
    }

    public List<Cannon> getCannons() {
        return cannons;
    }

    public List<Engine> getEngines() {
        return engines;
    }

    public List<PowerCore> getPowerCores() {
        return powerCores;
    }

    public List<Integer> getMainBodyImageIds () {
        List<Integer> mainBodyImageIds = new ArrayList<>();
        for(MainBody mainBody : mainBodies) {
            mainBodyImageIds.add(mainBody.getImageId());
        }
        return mainBodyImageIds;
    }

    public List<Integer> getExtraPartImageIds () {
        List<Integer> extraPartImageIds = new ArrayList<>();
        for(ExtraPart extraPart : extraParts) {
            extraPartImageIds.add(extraPart.getImageId());
        }
        return extraPartImageIds;
    }

    public List<Integer> getCannonImageIds () {
        List<Integer> cannonImageIds = new ArrayList<>();
        for(Cannon cannon : cannons) {
            cannonImageIds.add(cannon.getImageId());
        }
        return cannonImageIds;
    }

    public List<Integer> getEngineImageIds () {
        List<Integer> engineImageIds = new ArrayList<>();
        for(Engine engine : engines) {
            engineImageIds.add(engine.getImageId());
        }
        return engineImageIds;
    }

    public List<Integer> getPowerCoreImageIds () {
        List<Integer> powerCoreImageIds = new ArrayList<>();
        for(PowerCore powerCore : powerCores) {
            powerCoreImageIds.add(powerCore.getImageId());
        }
        return powerCoreImageIds;
    }
}