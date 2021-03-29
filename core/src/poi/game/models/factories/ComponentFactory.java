package poi.game.models.factories;

public interface ComponentFactory {
    void createPlayer(int posX, int posY);
    void createObstacle(int posX, int posY);
}
