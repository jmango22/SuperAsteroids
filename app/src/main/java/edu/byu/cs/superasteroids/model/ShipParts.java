package edu.byu.cs.superasteroids.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.byu.cs.superasteroids.database.SuperAsteroids_DAO;

/**
 * Created by Jon on 10/27/2016.
 */
public class ShipParts {
    private List<MainBody> mainBodies;
    private List<ExtraPart> extraParts;
    private List<Cannon> cannons;
    private List<Engine> engines;
    private List<PowerCore> powerCores;

    //Singleton instance
    private static volatile ShipParts instance;

    private ShipParts() {
        mainBodies = SuperAsteroids_DAO.getInstance().getMainBodies();
        extraParts = SuperAsteroids_DAO.getInstance().getExtraParts();
        cannons = SuperAsteroids_DAO.getInstance().getCannons();
        engines = SuperAsteroids_DAO.getInstance().getEngines();
        powerCores = SuperAsteroids_DAO.getInstance().getPowerCores();
    }

    public static ShipParts getInstance() {
        if(instance == null) {
            instance = new ShipParts();
        }
        return instance;
    }

    public void loadAllShipPartImages() {
        loadMainBodies();
        loadEngines();
        loadCannons();
        loadExtraParts();
        loadPowerCores();
    }

    private void loadMainBodies() {
        for(MainBody mainBody : mainBodies) {
            mainBody.loadImage();
        }
    }

    private void loadExtraParts() {
        for(ExtraPart extraPart : extraParts) {
            extraPart.loadImage();
        }
    }

    private void loadCannons() {
        for(Cannon cannon : cannons) {
            cannon.loadImage();
        }
    }

    private void loadEngines() {
        for(Engine engine : engines) {
            engine.loadImage();
        }
    }

    private void loadPowerCores() {
        for(PowerCore powerCore : powerCores) {
            powerCore.loadImage();
        }
    }

    public void unloadUnusedParts() {
        unloadUnusedMainBodies();
        unloadUnusedEngines();
        unloadUnusedCannons();
        unloadUnusedExtraParts();
        unloadUnusedPowerCores();
    }

    private void unloadUnusedMainBodies() {
        for(MainBody mainBody : mainBodies) {
            if(!(mainBody.equals(StarShip.getInstance().getMainBody()))) {
                mainBody.unloadImage();
            }
        }
    }

    private void unloadUnusedExtraParts() {
        for(ExtraPart extraPart : extraParts) {
            if(!(extraPart.equals(StarShip.getInstance().getExtraPart()))) {
                extraPart.unloadImage();
            }
        }
    }

    private void unloadUnusedCannons() {
        for(Cannon cannon : cannons) {
            if(!(cannon.equals(StarShip.getInstance().getCannon()))) {
                cannon.unloadImage();
            }
        }
    }

    private void unloadUnusedEngines() {
        for(Engine engine : engines) {
            if(!(engine.equals(StarShip.getInstance().getEngine()))) {
                engine.unloadImage();
            }
        }
    }

    private void unloadUnusedPowerCores() {
        for(PowerCore powerCore : powerCores) {
            if(!(powerCore.equals(StarShip.getInstance().getEngine()))) {
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