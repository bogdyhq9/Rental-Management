package repository;


import java.util.ArrayList;

public interface Repository<T> extends Iterable<T>{
    public void addentity(T val) throws Exception;
    public T getbyID(int id);
    public void delete(int id) throws Exception;
    public void update(int id, T val) throws Exception;
    public ArrayList<T> getAll();
}
