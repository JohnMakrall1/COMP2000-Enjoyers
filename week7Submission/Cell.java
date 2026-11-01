import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;


public class Cell extends Rectangle {
    //fields
    int x;
    int y;
    static int size = 20;
    private final List<Human> airborneSources = new ArrayList<>();

    //constructor
    public Cell(int inX, int inY) {
        x = inX;
        y = inY;
    }

    //methods
    public void paint(Graphics g) {

        g.setColor(airborneSources.isEmpty() ? Color.WHITE : new Color(210, 235, 255));
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
    }

    void addAirborneSource(Human source) {
        if (!airborneSources.contains(source)) {
            airborneSources.add(source);
        }
    }

    void removeAirborneSource(Human source) {
        airborneSources.remove(source);
    }

}
