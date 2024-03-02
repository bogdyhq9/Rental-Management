package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Inchiriere extends  Entity{
    private int idm;
    private String data_incepere;
    private String data_sfarsit;

    public Inchiriere(int id, int idm, String di, String ds)
    {
        super(id);
        this.idm=idm;
        this.data_incepere=di;
        this.data_sfarsit=ds;
    }
    public long getNumberOfDays() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate startDate = LocalDate.parse(data_incepere, formatter);
        LocalDate endDate = LocalDate.parse(data_sfarsit, formatter);

        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public String getData_incepere() {
        return data_incepere;
    }
    public String getluna(){ return data_incepere.substring(3,5);}

    public String getData_sfarsit() {
        return data_sfarsit;
    }

    public int getIdm() {
        return idm;
    }

    public void setData_incepere(String data_incepere) {
        this.data_incepere = data_incepere;
    }

    public void setData_sfarsit(String data_sfarsit) {
        this.data_sfarsit = data_sfarsit;
    }

    public void setMasina(int idm) {
        this.idm = idm;
    }

    @Override
    public String toString() {
        return "Inchiriere{" +
                "ID masina=" + idm +
                ", data_incepere=" + data_incepere +
                ", data_sfarsit=" + data_sfarsit +
                ", id=" + this.getId() +
                '}';
    }
}
