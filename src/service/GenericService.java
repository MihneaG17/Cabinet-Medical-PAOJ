package service;

import java.util.List;

//interfata generica pentru operatiile CRUD pe baza de date
public interface GenericService<T> {
    void create(T entitate);
    T read(int id);
    void update(T entitate);
    void delete(int id);

    List<T> readAll();
}
