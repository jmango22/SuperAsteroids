package edu.byu.cs.superasteroids.main_menu;

import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.model.ShipParts;
import edu.byu.cs.superasteroids.game_objects.StarShip;

/**
 * Created by Jon on 10/30/2016.
 */
public class MainMenuController implements IMainMenuController {
    private IMainMenuView mainMenuView;

    public MainMenuController(IMainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
    }

    @Override
    public void onQuickPlayPressed() {
        ShipParts.getInstance().loadAllShipPartImages();

        StarShip.getInstance().setRandomParts();

        //ShipParts.getInstance().unloadUnusedParts();

        mainMenuView.startGame();
    }

    @Override
    public IView getView() {
        return null;
    }

    @Override
    public void setView(IView view) {

    }
}
