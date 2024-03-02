package ui;

import domain.Inchiriere;
import domain.Masina;
import repository.*;
import service.Service;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private Service service;
    public UI(Service service)
    {
        this.service=service;
    }
    private void addM() {
        int id;
        String marca;
        String model;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Dati id-ul masinii ");
            id = scanner.nextInt();
            System.out.println("Dati marca masinii ");
            marca = scanner.next();
            System.out.println("Dati modelul masinii ");
            model = scanner.next();
            service.addM(id, marca, model);
        }
        catch (DuplicateRepositoryException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    private void deleteM()
    {
        int id;
        try{
            Scanner scanner= new Scanner(System.in);
            System.out.println("Dati id-ul masini care va fi stearsa ");
            id= scanner.nextInt();
            service.deleteM(id);
        }
        catch (NonExistingIDException ex)
        {
            System.out.println("Entitatea cu id-ul dat nu exista");
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    private void updateM()
    {
        int id;
        String marca;
        String model;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Dati id-ul masinii ");
            id = scanner.nextInt();
            System.out.println("Dati noua marca masinii ");
            marca = scanner.next();
            System.out.println("Dati  nou model masinii ");
            model = scanner.next();
            service.uptateM(id, marca, model);
        }
        catch (NonExistingIDException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    private void afisM()
    {
        ArrayList<Masina> masini=service.getallM();
        for(Masina m :masini)
            System.out.println(m);
    }
    private void addI(){
        int id;
        int idm;
        String data_start;
        String data_sfarsit;
        String input;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Dati id-ul inchirierii ");
            id = scanner.nextInt();
            System.out.println("Dati id-ul masinii ");
            idm = scanner.nextInt();
            System.out.println("Dati data de inceput(dd-MM-yyyy) ");
            input = scanner.next();
            data_start=(input);
            System.out.println("Dati data de sfarsit(dd-MM-yyyy)");
            input=scanner.next();
            data_sfarsit=(input);
            service.addI(id, idm, data_start,data_sfarsit);
        }
        catch (DuplicateRepositoryException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (RepoException ex) {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    private void updateI()
    {
        int id;
        int idm;
        String data_start;
        String data_sfarsit;
        String input;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Dati id-ul inchirierii ");
            id = scanner.nextInt();
            System.out.println("Dati noul id masinii ");
            idm = scanner.nextInt();
            System.out.println("Dati noua data de inceput(dd-MM-yyyy) ");
            input = scanner.next();
            data_start=(input);
            System.out.println("Dati noua data de sfarsit(dd-MM-yyyy)");
            input=scanner.next();
            data_sfarsit=(input);
            service.updateI(id, idm, data_start,data_sfarsit);
        }
        catch (NonExistingIDException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void deleteI()
    {
        int id;
        try{
            Scanner scanner= new Scanner(System.in);
            System.out.println("Dati id-ul inchirierii care va fi stearsa ");
            id= scanner.nextInt();
            service.deleteI(id);
        }
        catch (NonExistingIDException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    private void afisI()
    {
        ArrayList<Inchiriere> inchirieri=service.getallI();
        for(Inchiriere i :inchirieri)
            System.out.println(i);
    }
    private void printmeniu()
    {
        System.out.println("Alegeti optiunea ");
        System.out.println("1 Adaugare masina ");
        System.out.println("2 Stergere masina ");
        System.out.println("3 Modificare masina ");
        System.out.println("m Afisare masini ");
        System.out.println("4 Adaugare inchiriere ");
        System.out.println("5 Modificati inchirierea ");
        System.out.println("6 Stergeti inchirierea ");
        System.out.println("i Afisare inchirieri ");
        System.out.println("x Inchidere ");
    }

    public void console() {
        //this.ent();

        while (true) {
            printmeniu();
            String option;
            Scanner scanner = new Scanner(System.in);
            option=scanner.next();
            switch (option){
                case "1": {
                    this.addM();
                    break;
                }
                case "m":{
                    this.afisM();
                    break;
                }
                case "x": {
                    return;
                }
                case "2":
                {
                    this.deleteM();
                    break;
                }
                case "3":
                {
                    this.updateM();
                    break;
                }
                case "4":
                {
                    this.addI();
                    break;
                }
                case "i":
                {
                    this.afisI();
                    break;
                }
                case "5":
                {
                    this.updateI();
                    break;
                }
                case "6":
                {
                    this.deleteI();
                    break;
                }
                case "7":
                {
                    ArrayList<String> masini=this.service.inchirieri();
                    for(String m:masini)
                    {
                        System.out.println(m);
                    }
                }
                case "8":
                {
                    ArrayList<String> months=this.service.luni();
                    for(String m:months ){
                        System.out.println(m);

                    }
                }
                case "9":
                {
                    ArrayList<String> days=this.service.nrzile();
                    for(String d:days)
                    {
                        System.out.println(d);
                    }
                }
                default:{
                    System.out.println("Optiune gresita ");
                }
            }

        }
    }

    public static void main(String[] args) {
        MasinaSQLRepo repoM=new MasinaSQLRepo();
        InchiriereSQLRepo repoI=new InchiriereSQLRepo();
        Service service1=new Service(repoM,repoI);
        UI ui=new UI(service1);
        ui.console();
    }

}
