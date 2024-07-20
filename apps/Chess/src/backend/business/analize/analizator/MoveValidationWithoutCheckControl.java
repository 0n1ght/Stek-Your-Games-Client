package backend.business.analize.analizator;

public class MoveValidationWithoutCheckControl extends MoveValidator {
    public MoveValidationWithoutCheckControl(BasicValidator basic) {
        super(basic);
    }

    @Override
    void applyCheckStrategy() {

    }

}
