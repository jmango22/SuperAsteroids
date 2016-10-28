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
        ShipParts.loadAllShipPartImages();

        shipBuildingActivity.setPartViewImageList(PartSelectionView.MAIN_BODY, ShipParts.getMainBodyImageIds());
        shipBuildingActivity.setPartViewImageList(PartSelectionView.CANNON, ShipParts.getCannonImageIds());
        shipBuildingActivity.setPartViewImageList(PartSelectionView.ENGINE, ShipParts.getEngineImageIds());
        shipBuildingActivity.setPartViewImageList(PartSelectionView.EXTRA_PART, ShipParts.getExtraPartImageIds());
        shipBuildingActivity.setPartViewImageList(PartSelectionView.POWER_CORE, ShipParts.getPowerCoreImageIds());
    }

    @Override
    public void unloadContent(ContentManager content) {
        ShipParts.unloadUnusedMainBodies();
        ShipParts.unloadUnusedCannons();
        ShipParts.unloadUnusedEngines();
        ShipParts.unloadUnusedExtraParts();
        ShipParts.unloadUnusedPowerCores();
    }

    @Override
    public void draw() {
        StarShip.SINGLETON.draw(50f, 50f, 0f);
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
