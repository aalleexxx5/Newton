package common.data;



import common.services.Drawable;

import java.util.UUID;

public abstract class Entity implements Drawable {
    Sprite sprite;
    UUID id;

    public Entity() {
        id = UUID.randomUUID();
    }

    public Sprite draw() {
        return sprite;
    }
}
