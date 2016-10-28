package edu.byu.cs.superasteroids.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.byu.cs.superasteroids.database.SuperAsteroids_DAO;

/**
 * Created by Jon on 10/27/2016.
 */
public class ShipParts {
    private static List<MainBody> mainBodies = SuperAsteroids_DAO.SINGLETON.getMainBodies();
    private static List<ExtraPart> extraParts = SuperAsteroids_DAO.SINGLETON.getExtraParts();
    private static List<Cannon> cannons = SuperAsteroids_DAO.SINGLETON.getCannons();
    private static List<Engine> engines = SuperAsteroids_DAO.SINGLETON.getEngines();
    private static List<PowerCore> powerCores = SuperAsteroids_DAO.SINGLETON.getPowerCores();

    public static void loadAllShipPartImages() {
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

    public static void unloadUnusedMainBodies() {
        for(MainBody mainBody : mainBodies) {
            if(!(mainBody.equals(StarShip.SINGLETON.getMainBody()))) {
                mainBody.unloadImage();
            }
        }
    }

    public static void unloadUnusedExtraParts() {
        for(ExtraPart extraPart : extraParts) {
            if(!(extraPart.equals(StarShip.SINGLETON.getExtraPart()))) {
                extraPart.unloadImage();
            }
        }
    }

    public static void unloadUnusedCannons() {
        for(Cannon cannon : cannons) {
            if(!(cannon.equals(StarShip.SINGLETON.getCannon()))) {
                cannon.unloadImage();
            }
        }
    }

    public static void unloadUnusedEngines() {
        for(Engine engine : engines) {
            if(!(engine.equals(StarShip.SINGLETON.getEngine()))) {
                engine.unloadImage();
            }
        }
    }

    public static void unloadUnusedPowerCores() {
        for(PowerCore powerCore : powerCores) {
            if(!(powerCore.equals(StarShip.SINGLETON.getEngine()))) {
                powerCore.unloadImage();
            }
        }
    }

    public static List<MainBody> getMainBodies () {
        return mainBodies;
    }

    public static List<ExtraPart> getExtraParts () {
        return extraParts;
    }

    public static List<Cannon> getCannons() {
        return cannons;
    }

    public static List<Engine> getEngines() {
        return engines;
    }

    public static List<PowerCore> getPowerCores() {
        return powerCores;
    }

    public static List<Integer> getMainBodyImageIds () {
        List<Integer> mainBodyImageIds = new ArrayList<>();
        for(MainBody mainBody : mainBodies) {
            mainBodyImageIds.add(mainBody.getImageId());
        }
        return mainBodyImageIds;
    }

    public static List<Integer> getExtraPartImageIds () {
        List<Integer> extraPartImageIds = new ArrayList<>();
        for(ExtraPart extraPart : extraParts) {
            extraPartImageIds.add(extraPart.getImageId());
        }
        return extraPartImageIds;
    }

    public static List<Integer> getCannonImageIds () {
        List<Integer> cannonImageIds = new ArrayList<>();
        for(Cannon cannon : cannons) {
            cannonImageIds.add(cannon.getImageId());
        }
        return cannonImageIds;
    }

    public static List<Integer> getEngineImageIds () {
        List<Integer> engineImageIds = new ArrayList<>();
        for(Engine engine : engines) {
            engineImageIds.add(engine.getImageId());
        }
        return engineImageIds;
    }

    public static List<Integer> getPowerCoreImageIds () {
        List<Integer> powerCoreImageIds = new ArrayList<>();
        for(PowerCore powerCore : powerCores) {
            powerCoreImageIds.add(powerCore.getImageId());
        }
        return powerCoreImageIds;
    }
}