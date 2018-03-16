package common.data;



import common.services.Drawable;

import java.util.UUID;

public abstract class Entity implements Drawable {
    float[] location;
    UUID id;

    public Entity() {
        id = UUID.randomUUID();
    }

    public abstract void draw();
}
