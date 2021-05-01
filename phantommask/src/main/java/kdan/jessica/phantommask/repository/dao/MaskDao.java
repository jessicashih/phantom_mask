package kdan.jessica.phantommask.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kdan.jessica.phantommask.repository.entity.Mask;

@Repository
public interface MaskDao extends BaseDao<Mask, Long>{

}
