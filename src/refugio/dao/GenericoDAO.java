package refugio.dao;

import java.util.Collection;

/**
 *
 * @author Jairo
 */
public interface GenericoDAO<T, ID> {
    T read(ID id);
    Collection<T> getAll();
    void insert(T t);
    void update (T t);
    void delete (T t);
    void deleteByID (ID id);
}
