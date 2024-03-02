package domain;

public class Masina  extends  Entity {
    private String marca;
    private String model;
    //private static final long serialVersionUID = 1000L;

    public Masina(int id,String marca,String model)
    {
        super(id);
        this.marca=marca;
        this.model=model;
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Masina{" +
                "marca='" + marca + '\'' +
                ", model='" + model + '\'' +
                ", id=" + this.getId() +
                '}';
    }
}
