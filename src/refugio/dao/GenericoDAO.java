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
    void insert(T t);
    void update (T t);
    void delete (T t);
    void deleteByID (ID id);
}
