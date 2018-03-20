package common.services;

import common.data.GameState;

public interface Updatable {


    //update method need state:groupstate as parameter
    public void update(GameState state);

}
