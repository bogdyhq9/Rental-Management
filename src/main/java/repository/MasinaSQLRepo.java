package repository;

import domain.Masina;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasinaSQLRepo extends GeneralRepository<Masina>{
    private final String JDBC_URL="jdbc:sqlite:rentals.db";
    private Connection connection;
    public MasinaSQLRepo()
    {
        openConnection();
        createTable();
        loadDB();
    }
    private void openConnection(){
        SQLiteDataSource ds= new SQLiteDataSource();
        ds.setUrl(JDBC_URL);
        try {
            if(connection==null || connection.isClosed())
            {
                connection=ds.getConnection();

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
            statement.execute("CREATE TABLE IF NOT EXISTS masini(id int,marca nvachar(40),model nvarchar(40));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addentity(Masina val) throws Exception {
        super.addentity(val);
        try(PreparedStatement statement=connection.prepareStatement("INSERT INTO masini values (?,?,?);")) {
            statement.setInt(1,val.getId());
            statement.setString(2,val.getMarca());
            statement.setString(3,val.getModel());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Masina val) throws Exception {
        super.update(id, val);
        try(PreparedStatement statement=connection.prepareStatement("UPDATE masini SET marca=?,model=? WHERE id=?;")) {
            statement.setString(1,val.getMarca());
            statement.setString(2,val.getModel());
            statement.setInt(3,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        super.delete(id);
        try(PreparedStatement statement=connection.prepareStatement("DELETE FROM masini  WHERE id=?;")) {
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadDB()
    {
        try(PreparedStatement statement=connection.prepareStatement("SELECT * FROM masini"))
        {
            ResultSet rs=statement.executeQuery();
            while (rs.next())
            {
                Masina m=new Masina(rs.getInt(1),rs.getString(2), rs.getString(3));
                this.entitati.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
