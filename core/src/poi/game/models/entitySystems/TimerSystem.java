package poi.game.models.entitySystems;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.Gdx;

public class TimerSystem extends IntervalSystem {
    private float elapsedTime;
    private int seconds;
    private int minutes;
    private int hours;
    private String time;


    public TimerSystem(){
        super(Gdx.graphics.getDeltaTime());
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        time = "";
    }

    @Override
    protected void updateInterval() {
        elapsedTime += getInterval();
        if (elapsedTime >= 1) {
            while (elapsedTime >= 1) {
                elapsedTime -= 1;
                ++seconds;
                if (seconds == 60) {
                    seconds = 0;
                    ++minutes;
                    if (minutes == 60) {
                        minutes = 0;
                        ++hours;
                    }
                }
            }
        }
        Gdx.app.log("Time:", getTime());
    }

    public String getTime(){
        this.time = "";
        this.time += "H:";
        this.time += String.valueOf(hours);
        this.time += " M:";
        this.time += String.valueOf(minutes);
        this.time += " S:";
        this.time += String.valueOf(seconds);
        return time;
    }

}
