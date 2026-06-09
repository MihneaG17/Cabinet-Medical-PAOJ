package service;

import models.Reteta;

import java.util.ArrayList;
import java.util.List;

public class RetetaService implements GenericService<Reteta> {
    private static RetetaService instance;

    private RetetaService() {
    }

    public static RetetaService getInstance() {
        if (instance == null) {
            instance = new RetetaService();
        }
        return instance;
    }

    @Override
    public void create(Reteta entitate) {

    }

    @Override
    public Reteta read(int id) {
        return null;
    }

    @Override
    public void update(Reteta entitate) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Reteta> readAll() {
        List<Reteta> lista=new ArrayList<>();
        return lista;
    }

}
