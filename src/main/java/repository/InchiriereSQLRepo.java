package repository;

import domain.Inchiriere;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InchiriereSQLRepo extends GeneralRepository<Inchiriere>{
    private final String JDBC_URL = "jdbc:sqlite:rentals.db";
    private Connection connection;

    public InchiriereSQLRepo()
    {
        openConnection();
        createTable();
        loadDB();
    }
    private void openConnection() {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(JDBC_URL);
        try {
            if (connection == null || connection.isClosed()) {
                connection = ds.getConnection();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeConnection()
    {
        if(connection!=null)
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void createTable()
    {
        try(final Statement statement=connection.createStatement())
        {
            statement.execute("CREATE TABLE IF NOT EXISTS inchirieri(id int,id_m int,data_start date,data_sfartsit date);");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addentity(Inchiriere val) throws Exception {
        super.addentity(val);
        try(PreparedStatement statement=connection.prepareStatement("INSERT INTO inchirieri values (?,?,?,?);")) {
            statement.setInt(1,val.getId());
            statement.setInt(2,val.getIdm());
            statement.setString(3,val.getData_incepere());
            statement.setString(4,val.getData_sfarsit());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadDB()
    {
        try(PreparedStatement statement=connection.prepareStatement("SELECT * FROM inchirieri"))
        {
            ResultSet rs=statement.executeQuery();
            while (rs.next())
            {
                Inchiriere i=new Inchiriere(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4));
                this.entitati.add(i);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        super.delete(id);
        try(PreparedStatement statement=connection.prepareStatement("DELETE FROM inchirieri  WHERE id=?;")) {
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Inchiriere val) throws Exception {
        super.update(id, val);
        try(PreparedStatement statement=connection.prepareStatement("UPDATE inchirieri SET id_m=?,data_start=?,data_sfartsit=? WHERE id=?;")) {
            statement.setInt(1,val.getIdm());
            statement.setString(2,val.getData_incepere());
            statement.setString(3,val.getData_sfarsit());
            statement.setInt(4,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
