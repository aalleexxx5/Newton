package common.data;

public class Room{
    private String name;

    public Sprite createBigNWBox() {
        Sprite sprite = new Sprite("bigNWBox", -550, 540, 500, 50);
        return sprite;
    }

    public Sprite createBigNEBox() {
        Sprite sprite = new Sprite("bigNEBox", 50, 540, 500, 50);
        return sprite;
    }

    public Sprite createBigSWBox() {
        Sprite sprite = new Sprite("bigSWBox", -550, -490, 500, 50);
        return sprite;
    }

    public Sprite createBigSEBox() {
        Sprite sprite = new Sprite("bigSEBox", 50, -490, 500, 50);
        return sprite;
    }

    public Sprite createSmallNWBox() {
        Sprite sprite = new Sprite("smallNWBox", -600, 540, 50, 490);
        return sprite;
    }

    public Sprite createSmallNEBox() {
        Sprite sprite = new Sprite("smallNEBox", 550, 540, 50, 490);
        return sprite;
    }

    public Sprite createSmallSWBox() {
        Sprite sprite = new Sprite("smallSWBox", -600, -50, 50, 490);
        return sprite;
    }

    public Sprite createSmallSEBox() {
        Sprite sprite = new Sprite("smallSEBox", 550, -50, 50, 490);
        return sprite;
    }
}
