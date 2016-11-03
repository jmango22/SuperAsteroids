package edu.byu.cs.superasteroids.base;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.database.SuperAsteroids_DAO;
import edu.byu.cs.superasteroids.model.level.Level;
import edu.byu.cs.superasteroids.model.level.ViewPort;
import edu.byu.cs.superasteroids.model.ship.StarShip;

/**
 * Created by Jon on 10/30/2016.
 */
public class GameDelegate implements IGameDelegate {
    private ViewPort viewPort = new ViewPort();

    public GameDelegate() {
        viewPort.setLevel(SuperAsteroids_DAO.getInstance().getLevel(1));
        StarShip.getInstance().setHealth(10);
    }

    @Override
    public void update(double elapsedTime) {
        //Called 60 times a second
        viewPort.update();
    }

    @Override
    public void loadContent(ContentManager content) {
       viewPort.loadContent(content);
    }

    @Override
    public void unloadContent(ContentManager content) {
        viewPort.unloadContent(content);
    }

    @Override
    public void draw() {
        //Called 60 times a second
        viewPort.draw();
    }
}
