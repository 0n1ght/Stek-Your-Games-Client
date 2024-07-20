package swing.repository;

import backend.model.figure.value.Coord;
import swing.model.field.Field;

import java.util.Collection;


public interface FieldRepository {

    Collection<Field> getAll();

    Field getByCoords(Coord coord);

    Field getEmptyField();


}
