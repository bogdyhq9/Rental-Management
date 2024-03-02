package domain;

public class MasinaFactory implements IFactory<Masina>{


    @Override
    public Masina createEntity(String line) {
            int ID = Integer.parseInt(line.split(",")[0]);
            String marca = line.split(",")[1];
            String model = line.split(",")[2];

            return new Masina(ID, marca, model);
    }
    @Override
    public String parseEntity(Masina entity) {
        return entity.getId() + "," + entity.getMarca() + "," + entity.getModel() + "\n";

    }
}
