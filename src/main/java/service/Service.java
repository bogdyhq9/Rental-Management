package service;

import domain.Inchiriere;
import domain.Masina;
import repository.InchiriereSQLRepo;
import repository.MasinaSQLRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service {
    private MasinaSQLRepo repoM;
    private InchiriereSQLRepo repoI;
    public Service(MasinaSQLRepo repoM, InchiriereSQLRepo repoI){
        this.repoM=repoM;
        this.repoI=repoI;
    }
    public void addM(int id,String marca, String model) throws Exception {
        Masina m=new Masina(id,marca,model);
        this.repoM.addentity(m);
    }
    public void uptateM(int id,String marca,String model) throws Exception {
        Masina m = new Masina(id,marca,model);
        repoM.update(id,m);
    }
    public void deleteM(int id) throws Exception {
        repoM.delete(id);
    }

    public ArrayList<Masina> getallM()
    {
        return repoM.getAll();
    }

    public void addI(int id, int idm, String di, String ds) throws Exception {
        Inchiriere i= new Inchiriere(id,idm,di,ds);
        if(repoM.getbyID(idm)==null)
            throw new Exception("Masina nu exista");
        repoI.addentity(i);
    }
    public void updateI(int id, int idm, String di, String ds) throws Exception {
        Inchiriere i= new Inchiriere(id,idm,di,ds);
        if(repoM.getbyID(idm)==null)
            throw new Exception("Masina nu exista");
        repoI.update(id,i);
    }
    public void deleteI(int id) throws Exception {
        repoI.delete(id);
    }
    public  ArrayList<Inchiriere> getallI(){return repoI.getAll();}
    public ArrayList<String> inchirieri()
    {
        ArrayList<String> stats=new ArrayList<>();
        ArrayList<Inchiriere> inchirieriList = repoI.getAll();
        Map<Integer, Long> rentalCounts = inchirieriList.stream()
                .collect(Collectors.groupingBy(Inchiriere::getIdm, Collectors.counting()));
        List<Map.Entry<Integer, Long>> sortedEntries = rentalCounts.entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .collect(Collectors.toList());
        sortedEntries.forEach(entry -> {
            int idm = entry.getKey();
            long rentalCount = entry.getValue();
            stats.add( repoM.getbyID(idm) + " Inchirieri: " + rentalCount);
        });
        return stats;
    }
    public ArrayList<String> luni()
    {
        ArrayList<String> stats=new ArrayList<>();
        List<Inchiriere> inchirieriList = repoI.getAll();

        // Group by month and count the number of rentals for each month
        Map<String, Long> rentalsByMonth = inchirieriList.stream()
                .collect(Collectors.groupingBy(Inchiriere::getluna, Collectors.counting()));

        // Sort the entries by rental count in descending order
        rentalsByMonth.entrySet().stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                .forEach(entry -> {
                    String month = entry.getKey();
                    long rentalCount = entry.getValue();
                    stats.add("Luna: " + month + ", Inchirieri: " + rentalCount);
                });
        return stats;
    }
    public ArrayList<String> nrzile() {
        List<Inchiriere> inchirieriList = repoI.getAll();

        Map<Integer, Long> totalDaysRentedPerCar = inchirieriList.stream()
                .collect(Collectors.groupingBy(Inchiriere::getIdm, Collectors.summingLong(Inchiriere::getNumberOfDays)));

        ArrayList<String> result = new ArrayList<>();

        totalDaysRentedPerCar.entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .forEach(entry -> {
                    int carId = entry.getKey();
                    long totalDaysRented = entry.getValue();
                    result.add(repoM.getbyID(carId)+ " Zile : " + totalDaysRented);
                });

        return result;
    }

}
