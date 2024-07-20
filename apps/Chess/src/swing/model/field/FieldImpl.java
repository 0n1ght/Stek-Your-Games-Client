package swing.model.field;

import swing.Board;
import backend.model.figure.value.Coord;
import swing.Pixel;

import java.awt.*;

import static java.awt.Color.*;

public final class FieldImpl implements Field {

    private static final Color HOVER_COLOR = LIGHT_GRAY;
    private static final Color SELECTED_COLOR = YELLOW;
    private static final Color MOVABLE_COLOR = YELLOW.darker();
    private static final Color ATTACKABLE_COLOR = RED.darker();

    private Coord coord;
    private Pixel pixel;

    private Color color;
    private boolean isHovered;
    private boolean isSelected;
    private boolean isMovable;
    private boolean isAttackable;
    private boolean isCheck;

    public FieldImpl(Coord coord, Color color) {
        this.color = color;
        this.coord = coord;
        pixel = coord.convertToPixels();
        isHovered = false;
        isSelected = false;
        isMovable = false;
        isAttackable = false;
        isCheck = false;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(getRenderColor());
        g.fillRect(pixel.getX(), pixel.getY(), Board.getFieldSize(), Board.getFieldSize());
    }

    private Color getRenderColor() {
        if (isCheck) {
            return ATTACKABLE_COLOR;
        } else if (isAttackable) {
            return ATTACKABLE_COLOR;
        } else if (isSelected) {
            return SELECTED_COLOR;
        } else if (isMovable) {
            return MOVABLE_COLOR;
        } else if (isHovered) {
            return HOVER_COLOR;
        }
        return color;
    }

    @Override
    public Coord getCoord() {
        return coord;
    }

    @Override
    public boolean isNullObject() {
        return false;
    }

    public void setHovered(boolean hovered) {
        isHovered = hovered;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setMovable(boolean movable) {
        isMovable = movable;
    }

    public void setAttackable(boolean attackable) {
        isAttackable = attackable;
    }

    @Override
    public int getX() {
        return pixel.getX();
    }

    @Override
    public int getY() {
        return pixel.getY();
    }

    @Override
    public int getRow() {
        return coord.getRow();
    }

    @Override
    public int getColumn() {
        return coord.getColumn();
    }

    @Override
    public void setCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public String toString() {
        return "Field: " + getCoord();
    }
}
