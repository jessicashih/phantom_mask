package kdan.jessica.phantommask.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Common DAO Interface
 *
 * @param <T> Entity
 * @param <K> Key Type
 */
@NoRepositoryBean
public interface BaseDao <T, K> extends JpaRepository<T, K>, JpaSpecificationExecutor<T> {

}
