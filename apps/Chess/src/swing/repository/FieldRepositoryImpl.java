package swing.repository;

import swing.factory.FieldFactory;
import backend.model.figure.value.Coord;
import swing.model.field.Field;

import java.util.Collection;
import java.util.Map;

public final class FieldRepositoryImpl implements FieldRepository {

    private Map<Coord,Field> fields;
    private Field empty;



    public FieldRepositoryImpl(Map<Coord,Field> fields) {
        this.fields = fields;
        empty = FieldFactory.getEmptyField();
    }

    @Override
    public Collection<Field> getAll() {
        return fields.values();
    }

    @Override
    public Field getByCoords(Coord coord) {
       return fields.getOrDefault(coord,empty);
    }

    @Override
    public Field getEmptyField() {
        return empty;
    }
}
