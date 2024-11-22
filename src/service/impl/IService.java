package service;

import java.util.List;

public interface IService<T> {
    List<T> getAll();

    void save(T t);

    void update(T oldItem, T newItem);

    boolean remove(String name);

    List<T> findByName(String name);

    void readFromFile();

    void writeToFile();
}