package poi.game.models.entityComponents;

public class Position {
    //private Vector2 pos;
    private float x;
    private float y;

    public Position(float x, float y){
        //this.pos = new Vector2(x, y);
        this.x = x;
        this.y = y;
    }


    //@Override
    public void reset(){
        this.x = 0;
        this.y = 0;
    }

    public float getPosX() {
        return this.x;
    }

    public float getPosY() {
        return this.y;
    }

    public void setPosX(float x) {
        this.x = x;
    }

    public void setPosY(float y) {
        this.y = y;
    }

}
