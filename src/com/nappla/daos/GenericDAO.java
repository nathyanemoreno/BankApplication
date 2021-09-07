package com.nappla.daos;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

    void create(T obj) throws SQLException;
    List<T> retrieve();
    void update(T obj);
    void delete(T obj);

}
