package com.nappla.models;

import com.nappla.AccountType;
import com.nappla.daos.GenericDAO;
import com.nappla.views.WarningDialog;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public abstract class GenericTableModel<T> extends AbstractTableModel implements TableModelListener{

    GenericDAO<T> dao;
    List<T> data;

    public GenericTableModel (GenericDAO<T> dao){
        this.dao = dao;
        data = dao.retrieve();

        this.addTableModelListener(this);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        T obj = data.get(0);
        Class<?> c = obj.getClass();

        int i = 0;
        for (Field f: c.getDeclaredFields()){
            if(f.isAnnotationPresent(MyColumn.class)){
                i++;
            }
        }
        return i;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T obj = data.get(rowIndex);
        Class<?> c = obj.getClass();
        Field[] fields = c.getDeclaredFields();
        for(Field f: fields){
            if(f.isAnnotationPresent(MyColumn.class)){
                MyColumn annotation = f.getAnnotation(MyColumn.class);
                if(columnIndex == annotation.position()){
                    String name = f.getName();
                    String method = "get" + name.replaceFirst(".", name.substring(0,1).toUpperCase());
                    try {
                        Method m = c.getDeclaredMethod(method);
                        return m.invoke(obj);
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        T obj = data.get(0);
        Class<?> c = obj.getClass();
        for (Field f: c.getDeclaredFields()){
            if(f.isAnnotationPresent(MyColumn.class)){
                MyColumn i = f.getAnnotation(MyColumn.class);
                if(i.position() == column){
                    return i.name();
                }
            }
        }
        return super.getColumnName(column);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        T obj = data.get(rowIndex);
        Class<?> c = obj.getClass();
        Field[] fields = c.getDeclaredFields();

        for(Field f: fields){
            if(f.isAnnotationPresent(MyColumn.class)){
                MyColumn annotation = f.getAnnotation(MyColumn.class);
                if(columnIndex == annotation.position()){
                    String name = f.getName();
                    String method = "set" + name.replaceFirst(".", name.substring(0,1).toUpperCase());
                    System.out.println(method);

                    try {
                        Method value = c.getDeclaredMethod(method, f.getType());
                        System.out.println(value);
                        value.invoke(obj, aValue);
                        fireTableCellUpdated(rowIndex,columnIndex);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        super.setValueAt(aValue, rowIndex, columnIndex);
    }

    @Override
    public abstract boolean isCellEditable(int rowIndex, int columnIndex);

    @Override
    public void tableChanged(TableModelEvent tableModelEvent) {
        int i = tableModelEvent.getFirstRow();
        T account = data.get(i);
        dao.update(account);
    }


}
