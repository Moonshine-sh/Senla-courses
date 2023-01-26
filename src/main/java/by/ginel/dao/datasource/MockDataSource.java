package by.ginel.dao.datasource;

import by.ginel.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MockDataSource {
    Map<Class<? extends AbstractEntity>, List<AbstractEntity>> db = Map.ofEntries(
            Map.entry(Author.class, new ArrayList<>()),
            Map.entry(Book.class, new ArrayList<>()),
            Map.entry(BookItem.class, new ArrayList<>()),
            Map.entry(Genre.class, new ArrayList<>()),
            Map.entry(Order.class, new ArrayList<>()),
            Map.entry(Person.class, new ArrayList<>()),
            Map.entry(PersonCredentials.class, new ArrayList<>()),
            Map.entry(Role.class, new ArrayList<>()),
            Map.entry(Status.class, new ArrayList<>()),
            Map.entry(VerificationToken.class, new ArrayList<>())
    );

    Map<Class<?>, Long> counters = new HashMap<>(Map.ofEntries(
            Map.entry(Author.class, 0L),
            Map.entry(Book.class, 0L),
            Map.entry(BookItem.class, 0L),
            Map.entry(Genre.class, 0L),
            Map.entry(Order.class, 0L),
            Map.entry(Person.class, 0L),
            Map.entry(PersonCredentials.class, 0L),
            Map.entry(Role.class, 0L),
            Map.entry(Status.class, 0L),
            Map.entry(VerificationToken.class, 0L)
    ));

    public <T extends AbstractEntity> T save(T entity) {
        List<AbstractEntity> table = db.get(entity.getClass());
        Long c = counters.get(entity.getClass());
        entity.setId(c++);
        counters.put(entity.getClass(), c);
        table.add(entity);
        return entity;
    }

    public <T extends AbstractEntity> T getById(Class<T> entityClass, Long id) {
        List<AbstractEntity> table = db.get(entityClass);
        return (T) table.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public <T extends AbstractEntity> List<T> getAll(Class<T> entityClass) {
        return (List<T>) db.get(entityClass);
    }

    public <T extends AbstractEntity> Long delete(Class<T> entityClass, Long id) {
        List<AbstractEntity> table = db.get(entityClass);
        T e = getById(entityClass, id);
        table.remove(e);
        return id;
    }

    public <T extends AbstractEntity> T update(T entity) {
        List<AbstractEntity> table = db.get(entity.getClass());
        delete(entity.getClass(), entity.getId());
        table.add(entity);
        return entity;
    }
}
