package edu.byu.cs.superasteroids.ship_builder;

import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.model.ship.ShipParts;
import edu.byu.cs.superasteroids.model.ship.StarShip;
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
        StarShip.getInstance().clearAll();
    }

    @Override
    public void onViewLoaded(IShipBuildingView.PartSelectionView partView) {
        shipBuildingActivity.setArrow(partView, IShipBuildingView.ViewDirection.UP, false, "");
        shipBuildingActivity.setArrow(partView, IShipBuildingView.ViewDirection.DOWN, false, "");
        if(StarShip.getInstance().isComplete()) {
            shipBuildingActivity.setStartGameButton(true);
        }
        if(state == PartSelectionView.MAIN_BODY) {
            shipBuildingActivity.setArrow(partView, IShipBuildingView.ViewDirection.LEFT, true, "Power Core");
            shipBuildingActivity.setArrow(partView, IShipBuildingView.ViewDirection.RIGHT, true, "Engine");
        }
        if(state == PartSelectionView.ENGINE) {
            shipBuildingActivity.setArrow(partView, IShipBuildingView.ViewDirection.LEFT, true, "Main Body");
            shipBuildingActivity.setArrow(partView, IShipBuildingView.ViewDirection.RIGHT, true, "Cannon");
        }
        if(state == PartSelectionView.CANNON) {
            shipBuildingActivity.setArrow(partView, IShipBuildingView.ViewDirection.LEFT, true, "Engine");
            shipBuildingActivity.setArrow(partView, IShipBuildingView.ViewDirection.RIGHT, true, "Extra Part");
        }
        if(state == PartSelectionView.EXTRA_PART) {
            shipBuildingActivity.setArrow(partView, IShipBuildingView.ViewDirection.LEFT, true, "Cannon");
            shipBuildingActivity.setArrow(partView, IShipBuildingView.ViewDirection.RIGHT, true, "Power Core");
        }
        if(state == PartSelectionView.POWER_CORE) {
            shipBuildingActivity.setArrow(partView, IShipBuildingView.ViewDirection.LEFT, true, "Extra Part");
            shipBuildingActivity.setArrow(partView, IShipBuildingView.ViewDirection.RIGHT, true, "Main Body");
        }
    }

    @Override
    public void update(double elapsedTime) {
        // leave empty
    }

    @Override
    public void loadContent(ContentManager content) {
        ShipParts.getInstance().loadAllShipPartImages();

        shipBuildingActivity.setPartViewImageList(PartSelectionView.MAIN_BODY, ShipParts.getInstance().getMainBodyImageIds());
        shipBuildingActivity.setPartViewImageList(PartSelectionView.CANNON, ShipParts.getInstance().getCannonImageIds());
        shipBuildingActivity.setPartViewImageList(PartSelectionView.ENGINE, ShipParts.getInstance().getEngineImageIds());
        shipBuildingActivity.setPartViewImageList(PartSelectionView.EXTRA_PART, ShipParts.getInstance().getExtraPartImageIds());
        shipBuildingActivity.setPartViewImageList(PartSelectionView.POWER_CORE, ShipParts.getInstance().getPowerCoreImageIds());
    }

    @Override
    public void unloadContent(ContentManager content) {
        //System.out.println("Unloading all the images...");
        //ShipParts.getInstance().unloadUnusedParts();
    }

    @Override
    public void draw() {
        float posX = ((float)DrawingHelper.getGameViewWidth())/2f;
        float posY = ((float)DrawingHelper.getGameViewHeight())/2f;

        StarShip.getInstance().setScaleX(.2f);
        StarShip.getInstance().setScaleY(.2f);

        StarShip.getInstance().draw(posX, posY, .2f, .2f, 0f);
    }

    @Override
    public void onSlideView(IShipBuildingView.ViewDirection direction) {
        state = state.onSlideView(direction);
        shipBuildingActivity.animateToView(state, shipBuildingActivity.getOppositeDirection(direction));

    }

    @Override
    public void onPartSelected(int index) {
        state.onPartSelected(index);
        if(StarShip.getInstance().isComplete()) {
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
