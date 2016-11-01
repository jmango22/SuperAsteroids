package edu.byu.cs.superasteroids.base;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.game_objects.ViewPort;

/**
 * Created by Jon on 10/30/2016.
 */
public class GameDelegate implements IGameDelegate {
    private ViewPort viewPort = new ViewPort();

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
