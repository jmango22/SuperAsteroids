package edu.byu.cs.superasteroids.ship_builder;

import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.model.ShipParts;
import edu.byu.cs.superasteroids.model.StarShip;
import edu.byu.cs.superasteroids.ship_builder.IShipBuildingView.PartSelectionView;


/**
 * Created by Jon on 10/27/2016.
 */
public class ShipBuildingController implements IShipBuildingController {
    private ShipBuildingActivity shipBuildingActivity;
    private PartSelectionView state;

    public ShipBuildingController(ShipBuildingActivity shipBuildingActivity) {
        this.shipBuildingActivity = shipBuildingActivity;
        state = PartSelectionView.MAIN_BODY;
    }

    @Override
    public void onViewLoaded(IShipBuildingView.PartSelectionView partView) {

    }

    @Override
    public void update(double elapsedTime) {
        // leave empty
    }

    @Override
    public void loadContent(ContentManager content) {
        ShipParts.SINGLETON.loadAllShipPartImages();

        shipBuildingActivity.setPartViewImageList(PartSelectionView.MAIN_BODY, ShipParts.SINGLETON.getMainBodyImageIds());
        shipBuildingActivity.setPartViewImageList(PartSelectionView.CANNON, ShipParts.SINGLETON.getCannonImageIds());
        shipBuildingActivity.setPartViewImageList(PartSelectionView.ENGINE, ShipParts.SINGLETON.getEngineImageIds());
        shipBuildingActivity.setPartViewImageList(PartSelectionView.EXTRA_PART, ShipParts.SINGLETON.getExtraPartImageIds());
        shipBuildingActivity.setPartViewImageList(PartSelectionView.POWER_CORE, ShipParts.SINGLETON.getPowerCoreImageIds());
    }

    @Override
    public void unloadContent(ContentManager content) {
        ShipParts.SINGLETON.unloadUnusedMainBodies();
        ShipParts.SINGLETON.unloadUnusedCannons();
        ShipParts.SINGLETON.unloadUnusedEngines();
        ShipParts.SINGLETON.unloadUnusedExtraParts();
        ShipParts.SINGLETON.unloadUnusedPowerCores();
    }

    @Override
    public void draw() {

    }

    @Override
    public void onSlideView(IShipBuildingView.ViewDirection direction) {
        state = state.onSlideView(direction);
        shipBuildingActivity.animateToView(state, shipBuildingActivity.getOppositeDirection(direction));

    }

    @Override
    public void onPartSelected(int index) {
        state.onPartSelected(index);
        if(StarShip.SINGLETON.isComplete()) {
            shipBuildingActivity.setStartGameButton(true);
        }
    }

    @Override
    public void onStartGamePressed() {
        shipBuildingActivity.startGame();
    }

    @Override
    public void onResume() {

    }

    @Override
    public IView getView() {
        return null;
    }

    @Override
    public void setView(IView view) {

    }
}
