package com.monese.bank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends CrudRepository<T, ID> {

    default T findOne(ID id) {
        return findById(id).orElse(null);
    }

//    default T findOneOrNull(Predicate predicate) {
//        return findOne(predicate).orElse(null);
//    }
//
//    default List<T> findAllGetImmutableList(Predicate predicate) {
//        return ImmutableList.copyOf(findAll(predicate));
//    }
}
