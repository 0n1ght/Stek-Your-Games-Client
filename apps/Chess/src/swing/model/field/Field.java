package swing.model.field;

import backend.model.figure.value.Coord;
import swing.Renderable;
import swing.Tickable;

public interface Field extends Tickable, Renderable {

    Coord getCoord();

    boolean isNullObject();

    void setHovered(boolean hovered);

    void setSelected(boolean selected);

    void setMovable(boolean movable);

    void setAttackable(boolean attackable);

    int getX();

    int getY();

    int getRow();

    int getColumn();

    void setCheck(boolean isCheck);
}
