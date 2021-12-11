package refugio.dao;

import java.util.Collection;
import refugio.model.Animal;

/**
 *
 * @author Jairo
 */
public interface GenericoDAO<T, ID> {
    T read(Animal id);
    Collection<T>getAll(Animal t);
    Collection<T> getAll();
    void get(int id);
    void insert(T t);
    void update (T t);
}
