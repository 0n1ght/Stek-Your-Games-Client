package pl.monopoly.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public record FieldFactory(Game game) {

    public List<Field> buildFields() throws IOException {
        List<Field> fields = new ArrayList<>();

        BufferedReader bufferedWriter = new BufferedReader(new FileReader("src\\main\\resources\\fields.csv"));

        for (int i = 0; i < 40; i++) {

            String csv = bufferedWriter.readLine();
            String[] split = csv.split(",");

            if (split[1].equals("BUY")) {
                fields.add(new BuyAbleField(game, FieldSet.valueOf(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[2]), Integer.parseInt(split[3])));
            } else {
                fields.add(new UnBuyAbleField(game));
            }
        }

        return fields;
    }
}
