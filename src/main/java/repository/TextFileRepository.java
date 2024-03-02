package repository;

import domain.Entity;
import domain.IFactory;

import java.io.*;
import java.text.ParseException;

public class TextFileRepository<T extends Entity> extends GeneralRepository<T>{
    private String filename;
    private IFactory<T> entityFactoy;
    public TextFileRepository(String filename,IFactory<T> entityFactoy) throws RepoException {
        this.filename=filename;
        this.entityFactoy=entityFactoy;
        loadfile();
    }
    private void loadfile() throws RepoException {
        try(BufferedReader br =new BufferedReader(new FileReader(filename))){
            String line= br.readLine();
            while(line!=null && !line.isEmpty()){
                super.addentity(entityFactoy.createEntity(line));
                line=br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Repo starting new file");
        } catch (IOException e) {
            throw new RepoException(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void saveFile() throws RepoException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.flush();
            for(T entity: this.getAll()) {
                writer.write(entityFactoy.parseEntity(entity));
            }
        } catch (IOException e) {
            throw new RepoException(e.getMessage());}
    }

    @Override
    public void addentity(T val) throws Exception {
        super.addentity(val);
        saveFile();
    }

    @Override
    public void delete(int id) throws Exception {
        super.delete(id);
        saveFile();
    }

    @Override
    public void update(int id, T val) throws Exception {
        super.update(id, val);
        saveFile();
    }
}
