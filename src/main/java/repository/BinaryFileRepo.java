package repository;

import domain.Entity;

import java.io.*;
import java.util.ArrayList;

public class BinaryFileRepo<T extends Entity> extends GeneralRepository<T> {

    private String filename;
    public BinaryFileRepo(String name) throws RepoException {
        //super();
        this.filename=name;
        try {
            loadfile();
        }
         catch (IOException e) {
            throw new RepoException(e.getMessage());
        }
    }

    @Override
    public void addentity(T val) throws Exception {
        super.addentity(val);
        savefile();
    }

    @Override
    public void delete(int id) throws Exception {
        super.delete(id);
        savefile();
    }

    @Override
    public void update(int id, T val) throws Exception {
        super.update(id, val);
        savefile();
    }

    private void loadfile() throws RepoException, IOException {
        ObjectInputStream ois= null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filename));
            this.entitati =(ArrayList<T>) ois.readObject();
        }
        catch (FileNotFoundException e) {
            //throw new RepoException(e.getMessage());
            System.out.println("Repo starting new file");
        }
        catch (IOException e) {
            throw new RepoException(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            throw new RepoException(e.getMessage());
        }
        finally {
            if(ois!=null)
                ois.close();
        }

    }
    private void savefile() throws RepoException {
        try(ObjectOutputStream oos =new ObjectOutputStream( new FileOutputStream(filename))) {
            oos.writeObject(entitati);
        }
        catch (IOException e)
        {
            throw  new RepoException(e.getMessage());
        }

    }
}