package poi.game.views;

import com.badlogic.gdx.Screen;

import javax.swing.text.View;

public enum ScreenType {
    TEST(Test.class);

    private final Class<? extends Screen> screenClass;

    ScreenType(final Class<? extends Screen> screenClass){
        this.screenClass = screenClass;
    }

    public Class<? extends Screen> getScreenClass() {
        return screenClass;
    }
}
