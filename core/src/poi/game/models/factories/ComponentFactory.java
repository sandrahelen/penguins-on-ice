package poi.game.models.factories;

public interface ComponentFactory {
    void createPlayer();
    void createObstacle(int posX, int posY);
}
