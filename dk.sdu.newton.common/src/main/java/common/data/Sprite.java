package common.data;

public class Sprite {
	String filename;
	float x, y, width, height;
	
	public Sprite(String filename, float x, float y, float width, float height) {
		this.filename = filename;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

    public float getX(){
        return x;}
    public float getY() {
        return y;
    }public float getHeightY() {
        return height;
    }public float getHeightX() {
        return width;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}