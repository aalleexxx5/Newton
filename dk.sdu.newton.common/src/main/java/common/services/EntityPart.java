package common.services;

import common.data.Entity;
import common.data.GameState;

public interface EntityPart {
    public void update(Entity container, GameState state);
}
