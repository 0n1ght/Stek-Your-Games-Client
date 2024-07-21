package pl.monopoly.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record FieldFactory(Game game) {

    public List<Field> buildFields() throws IOException {
        List<Field> fields = new ArrayList<>();

        // Use getResourceAsStream to load the resource from within the JAR
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/fields.csv"))))) {

            String csv;
            while ((csv = bufferedReader.readLine()) != null) {
                String[] split = csv.split(",");

                if (split[1].equals("BUY")) {
                    fields.add(new BuyAbleField(game, FieldSet.valueOf(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[2]), Integer.parseInt(split[3])));
                } else {
                    fields.add(new UnBuyAbleField(game));
                }
            }
        }

        return fields;
    }
}
