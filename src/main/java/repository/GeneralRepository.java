package repository;

import domain.Entity;

import java.util.ArrayList;
import java.util.Iterator;

public class GeneralRepository<T extends Entity> implements Repository<T> {
    protected ArrayList<T> entitati;
    public GeneralRepository() {
        entitati = new ArrayList<>();
    }
    @Override
    public void addentity(T val) throws Exception {
        if(val==null)
            throw new IllegalArgumentException("Entitatea nu poate fi null");
        if(this.getbyID(val.getId())!=null)
            throw new DuplicateRepositoryException("Id-ul trebuie sa fie unic");

        this.entitati.add(val);
    }

    @Override
    public T getbyID(int id) {
        for(T val:entitati)
        {
            if(val.getId()==id)
                return val;
        }
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        if(this.getbyID(id)==null)
            throw new NonExistingIDException("Entitatea nu exista");
        T ent=this.getbyID(id);
        entitati.remove(ent);

    }

    @Override
    public void update(int id, T val) throws Exception {
        int nr=0;
        if(this.getbyID(id)==null)
            throw new NonExistingIDException("Entitatea nu exista");
        for(T ent :entitati)
        {
            if(ent.getId()==id)
            {
               entitati.set(nr,val);
            }
            nr++;
        }

    }

    @Override
    public ArrayList<T> getAll() {
        return new ArrayList<T>(entitati);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayList<T>(entitati).iterator();
    }

}
