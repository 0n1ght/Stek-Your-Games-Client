package backend.business.analize.event;

public enum  MovingStyle {
    ATTACK_MOVE(true,true),
    ATTACK(true,false),
    MOVE(false, true),
    TELEPORT(true,true),
    CASTLING(false,true),
    EN_PASSANT(true,false),
    DEFENCE(false,false),
    UNKNOWN;

    private boolean attack;
    private boolean move;

    MovingStyle(boolean attack, boolean move) {
        this.attack = attack;
        this.move = move;
    }

    MovingStyle() {
        attack = false;
        move = false;
    }

    public boolean canAttack() {
        return attack;
    }

    public boolean canMove() {
        return move;
    }
}
