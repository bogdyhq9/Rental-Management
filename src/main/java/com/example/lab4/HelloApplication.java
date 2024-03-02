package com.example.lab4;

import domain.Inchiriere;
import domain.Masina;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import repository.InchiriereSQLRepo;
import repository.MasinaSQLRepo;
import service.Service;

import java.io.IOException;

public class HelloApplication extends Application {
    TextField idtext=new TextField();
    TextField modeltext=new TextField();
    TextField marcatext=new TextField();
    TextField iditext=new TextField();
    TextField idmtext=new TextField();
    TextField ditext=new TextField();
    TextField dstext=new TextField();

    @Override
    public void start(Stage stage) throws IOException {
        MasinaSQLRepo  repoM=new MasinaSQLRepo();
        InchiriereSQLRepo repoI=new InchiriereSQLRepo();
        Service service=new Service(repoM,repoI);

        VBox mainVB=new VBox();
        mainVB.setPadding(new Insets(10));

        ObservableList<Masina> cars= FXCollections.observableArrayList(service.getallM());
        ListView<Masina> listm =new ListView<Masina>(cars);

        listm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Masina m =listm.getSelectionModel().getSelectedItem();
                idtext.setText(Integer.toString(m.getId()));
                modeltext.setText(m.getModel());
                marcatext.setText(m.getMarca());
            }
        });
        mainVB.getChildren().add(listm);

        GridPane carsgrid = new GridPane();
        Label idlabel=new Label();
        idlabel.setText("id: ");
        idlabel.setPadding(new Insets(10,5,10,0));

        Label modlabel=new Label();
        modlabel.setText("Model: ");
        modlabel.setPadding(new Insets(10,5,10,0));

        Label marcalabel=new Label();
        marcalabel.setText("Marca: ");
        marcalabel.setPadding(new Insets(10,5,10,0));

        carsgrid.add(idlabel,0,0);
        carsgrid.add(idtext,1,0);
        carsgrid.add(modlabel,0,1);
        carsgrid.add(modeltext,1,1);
        carsgrid.add(marcalabel,0,2);
        carsgrid.add(marcatext,1,2);

        mainVB.getChildren().add(carsgrid);

        HBox btnshorizontal=new HBox();
        mainVB.getChildren().add(btnshorizontal);

        Button addcarbtn=new Button("Adaugare ");
        btnshorizontal.getChildren().add(addcarbtn);
        addcarbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id=Integer.parseInt(idtext.getText());
                    String model=modeltext.getText();
                    String marca=marcatext.getText();
                    service.addM(id,marca,model);
                    cars.setAll(service.getallM());
                } catch (Exception e) {
                    Alert eror=new Alert(Alert.AlertType.ERROR);
                    eror.setTitle("Eroare");
                    eror.setContentText(e.getMessage());
                    eror.show();
                }
            }
        });


        Button updatecarbtn=new Button("Modificare");
        btnshorizontal.getChildren().add(updatecarbtn);
        updatecarbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id=Integer.parseInt(idtext.getText());
                    String model=modeltext.getText();
                    String marca=marcatext.getText();
                    service.uptateM(id,marca,model);
                    cars.setAll(service.getallM());
                } catch (Exception e) {
                    Alert eror=new Alert(Alert.AlertType.ERROR);
                    eror.setTitle("Eroare");
                    eror.setContentText(e.getMessage());
                    eror.show();
                }
            }
        });
        Button deletecarbtn=new Button("Stergere");
        btnshorizontal.getChildren().add(deletecarbtn);
        deletecarbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id=Integer.parseInt(idtext.getText());
                    service.deleteM(id);
                    cars.setAll(service.getallM());
                } catch (Exception e) {
                    Alert eror=new Alert(Alert.AlertType.ERROR);
                    eror.setTitle("Eroare");
                    eror.setContentText(e.getMessage());
                    eror.show();
                }
            }
        });

        ObservableList<Inchiriere> rentals= FXCollections.observableArrayList(service.getallI());
        ListView<Inchiriere> listr =new ListView<Inchiriere>(rentals);
        listr.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Inchiriere i = listr.getSelectionModel().getSelectedItem();
                iditext.setText(Integer.toString(i.getId()));
                idmtext.setText((Integer.toString(i.getIdm())));
                ditext.setText(i.getData_incepere());
                dstext.setText(i.getData_sfarsit());
            }
        });
        mainVB.getChildren().add(listr);

        GridPane rentalsgrid=new GridPane();

        Label idilabel=new Label();
        idilabel.setText("id: ");
        idilabel.setPadding(new Insets(10,5,10,0));

        Label idmlabel=new Label();
        idmlabel.setText("Id masina: ");
        idmlabel.setPadding(new Insets(10,5,10,0));

        Label dilabel=new Label();
        dilabel.setText("Data incepere: ");
        dilabel.setPadding(new Insets(10,5,10,0));

        Label dslabel=new Label();
        dslabel.setText("Data incheiere: ");
        dslabel.setPadding(new Insets(10,5,10,0));

        rentalsgrid.add(idilabel,0,0);
        rentalsgrid.add(iditext,1,0);

        rentalsgrid.add(idmlabel,0,1);
        rentalsgrid.add(idmtext,1,1);

        rentalsgrid.add(dilabel,0,2);
        rentalsgrid.add(ditext,1,2);

        rentalsgrid.add(dslabel,0,3);
        rentalsgrid.add(dstext,1,3);

        mainVB.getChildren().add(rentalsgrid);


        HBox rentanlshbox=new HBox();
        mainVB.getChildren().add(rentanlshbox);
        Button addrentalbtn=new Button("Adaugare ");
        rentanlshbox.getChildren().add(addrentalbtn);
        addrentalbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id=Integer.parseInt(iditext.getText());
                    int idm=Integer.parseInt(idmtext.getText());
                    String di=ditext.getText();
                    String ds=dstext.getText();
                    service.addI(id,idm,di,ds);
                    rentals.setAll(service.getallI());
                } catch (Exception e) {
                    Alert eror=new Alert(Alert.AlertType.ERROR);
                    eror.setTitle("Eroare");
                    eror.setContentText(e.getMessage());
                    eror.show();
                }

            }
        });

        Button updaterentalbtn=new Button("Modificare");
        rentanlshbox.getChildren().add(updaterentalbtn);
        updaterentalbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id=Integer.parseInt(iditext.getText());
                    int idm=Integer.parseInt(idmtext.getText());
                    String di=ditext.getText();
                    String ds=dstext.getText();
                    service.updateI(id,idm,di,ds);
                    rentals.setAll(service.getallI());
                } catch (Exception e) {
                    Alert eror=new Alert(Alert.AlertType.ERROR);
                    eror.setTitle("Eroare");
                    eror.setContentText(e.getMessage());
                    eror.show();
                }
            }
        });

        Button deleterentalbtn=new Button("Stergere");
        rentanlshbox.getChildren().add(deleterentalbtn);
        deleterentalbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id=Integer.parseInt(iditext.getText());
                    service.deleteI(id);
                    rentals.setAll(service.getallI());
                } catch (Exception e) {
                    Alert eror=new Alert(Alert.AlertType.ERROR);
                    eror.setTitle("Eroare");
                    eror.setContentText(e.getMessage());
                    eror.show();
                }
            }
        });

        HBox stats=new HBox();
        mainVB.getChildren().add(stats);
        Button norentals=new Button("Nr. inchirieri");
        stats.getChildren().add(norentals);
        norentals.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ObservableList<String> nrinch= FXCollections.observableArrayList(service.inchirieri());
                ListView<String> listm =new ListView<String >(nrinch);
                VBox vb=new VBox();
                vb.getChildren().add(listm);
                Stage stage1=new Stage();
                Scene scene1=new Scene(vb,320,320);
                stage1.setScene(scene1);
                stage1.show();


            }
        });
        Button monthrentalsbtn=new Button("Inchirieri/Luna");
        stats.getChildren().add(monthrentalsbtn);
        monthrentalsbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ObservableList<String> months= FXCollections.observableArrayList(service.luni());
                ListView<String> listm =new ListView<String >(months);
                VBox vb=new VBox();
                vb.getChildren().add(listm);
                Stage stage2=new Stage();
                Scene scene2=new Scene(vb,320,320);
                stage2.setScene(scene2);
                stage2.show();
            }
        });

        Button cardaysbtn=new Button("Masini/Zile");
        stats.getChildren().add(cardaysbtn);
        cardaysbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ObservableList<String> days= FXCollections.observableArrayList(service.nrzile());
                ListView<String> listm =new ListView<String >(days);
                VBox vb=new VBox();
                vb.getChildren().add(listm);
                Stage stage3=new Stage();
                Scene scene3=new Scene(vb,320,320);
                stage3.setScene(scene3);
                stage3.show();
            }
        });
        Scene scene = new Scene(mainVB, 320, 240);
        stage.setTitle("Rentals App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}