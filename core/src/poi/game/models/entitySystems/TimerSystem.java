package poi.game.models.entitySystems;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.Gdx;

public class TimerSystem extends IntervalSystem {
    private float elapsedTime;
    private int seconds;
    private int minutes;
    private String stringTime;
    private int time;
    private boolean finish = false;



    public TimerSystem(){
        super(Gdx.graphics.getDeltaTime());
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        stringTime = "";
    }

    @Override
    protected void updateInterval() {
        if(!finish) {
            elapsedTime += getInterval();
            if (elapsedTime >= 1) {
                while (elapsedTime >= 1) {
                    elapsedTime -= 1;
                    ++seconds;
                    ++time;
                    if (seconds == 60) {
                        seconds = 0;
                        ++minutes;
                    }
                }
            }
        }
    }

    public void setFinish(){
        this.finish = true;
    }

    public String getStringTime(){
        this.stringTime = "";
        this.stringTime += " M:";
        this.stringTime += String.valueOf(minutes);
        this.stringTime += " S:";
        this.stringTime += String.valueOf(seconds);
        return stringTime;
    }

    public int getTime(){
        return time;
    }

}
