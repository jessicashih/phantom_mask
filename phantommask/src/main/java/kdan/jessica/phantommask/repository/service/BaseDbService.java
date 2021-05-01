package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.dao.BaseDao;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Common DB Service
 *
 * @param <D> JpaRepository
 * @param <E> Entity
 * @param <K> Key Type
 */
public abstract class BaseDbService<D extends BaseDao<E, K>, E, K> {
    /**
     * GenericDao，建構時由子類別提供實體
     */
    @Autowired
    protected D dao;

    /**
     * EntityManager
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 依主鍵查詢
     */
    @Transactional(readOnly = true)
    public Optional<E> findById(final K id) {
        return dao.findById(id);
    }

    public List<E> findAll(final K id) {
        return dao.findAll();
    }

    public E update(final E entity) {
        K id = getKey(entity);
        if (id != null && existsById(id)) {
            return dao.save(entity);
        } else {
//            TODO
            throw new RuntimeException();
        }
    }

    public E insert(final E entity) {
        K id = getKey(entity);
        if (id != null && existsById(id)) {
//            TODO
            throw  new RuntimeException();
        }else {
            return dao.save(entity);
        }
    }

    public K getKey(final E entity) {
        return (K) em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
    }

    /**
     * 判斷主鍵是否存在
     */
    @Transactional(readOnly = true)
    public Boolean existsById(final K id) {
        return this.dao.existsById(id);
    }
}
