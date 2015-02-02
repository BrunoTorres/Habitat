package pt.uminho.lei.dss.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
public abstract class AbstractRepository<T> implements DAO<T> {

    @Override
    public void addAll(Collection<T> entities) throws PersistenceException {
        for(T entity : entities) {
            add(entity);
        }
    }

    @Override
    public Iterable<T> findAll(Collection<Long> ids) throws PersistenceException {
        List<T> list = new ArrayList<>(ids.size());
        for(long id : ids) {
            list.add(get(id));
        }
        return list;
    }

    
    @Override
    public void deleteAll(Collection<T> entities) throws PersistenceException {
        for(T entity : entities) {
            rem(entity);
        }
    }

    @Override
    public boolean exists(long id) throws PersistenceException {
        return get(id) != null;
    }*/
    

