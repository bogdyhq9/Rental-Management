package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InchiriereFactory implements IFactory<Inchiriere> {
    @Override
    public Inchiriere createEntity(String line) throws ParseException {
        int id = Integer.parseInt(line.split(",")[0]);
        int id_m = Integer.parseInt(line.split(",")[1]);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String data_start = line.split(",")[2];
        String date_end = (line.split(",")[3]);
        return new Inchiriere(id, id_m, data_start, date_end);
    }

    @Override
    public String parseEntity(Inchiriere entity) {
        return entity.getId() + "," + entity.getIdm() + "," + entity.getData_incepere() + "," + entity.getData_sfarsit();
    }
}