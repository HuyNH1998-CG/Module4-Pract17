package Big.service;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(long id);

    void save(T t);

    void remove(Long id);
}
