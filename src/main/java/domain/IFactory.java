package domain;

import java.text.ParseException;

public interface IFactory<T extends Entity> {
    public T createEntity(String line) throws ParseException;
    public String parseEntity(T entity);
}
