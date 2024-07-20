package pl.monopoly.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Board {
    private final List<Field> fields;

    public Board(Game game) throws IOException {

        FieldFactory fieldFactory = new FieldFactory(game);
        fields = fieldFactory.buildFields();

    }

    // get/set
    public Field getField(int number) {
        number = number == 40 ? 0 : number;
        return fields.get(number);
    }

    public List<Field> getFields() {
        return fields;
    }

    public boolean hasAllSet(Player player, FieldSet fieldSet) {

        for (BuyAbleField field : getSetFields(fieldSet)){
            if (field.getOwner() != player) {
                return false;
            }
        }

        return true;
    }

    private List<BuyAbleField> getSetFields(FieldSet fieldSet) {
        List<BuyAbleField> fieldList = new LinkedList<>();

        for (Field field : fields) {

            if (field.isBuyAble()) {
                if (((BuyAbleField) field).getSet() == fieldSet) {
                    fieldList.add((BuyAbleField) field);
                }
            }
        }

        return fieldList;
    }

    public void clearBankruptsFields(Player player) {
        for (Field field : fields) {

            if (field.isBuyAble()) {

                if (((BuyAbleField)field).getOwner() == player)
                    ((BuyAbleField) field).clearOwner();

            }
        }
    }
}

//if (player.isBankrupt()) {
//    board.clearBankruptsFields(player)
//}