package backend.business.analize.analizator;

public class MoveValidationWitchCheckControl extends MoveValidator {

    private CheckValidator checkValidator;

    public MoveValidationWitchCheckControl(BasicValidator basic, CheckValidator check) {
        super(basic);
        this.checkValidator = check;
    }

    @Override
    void applyCheckStrategy() {
        checkValidator.removeMovesCausingCheck(moves, game);
    }
}
