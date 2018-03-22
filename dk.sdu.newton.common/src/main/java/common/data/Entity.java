package common.data;



import common.services.Drawable;

import java.util.UUID;

public abstract class Entity implements Drawable {
    protected Sprite sprite;
    UUID id;

    public Entity() {
        id = UUID.randomUUID();
    }

    public abstract Sprite draw();

    public Sprite getSprite() {
        return null;
    }
}
