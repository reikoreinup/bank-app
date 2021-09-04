package com.monese.bank.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoRepositoryBean
public interface BaseRepository<T, U> extends CrudRepository<T, U>, QuerydslPredicateExecutor<T> {

    default T findOneOrNull(U id) {
        return findById(id).orElse(null);
    }

    default T findOneOrNull(Predicate predicate) {
        return findOne(predicate).orElse(null);
    }

    default List<T> findAllList(Predicate predicate) {
        return StreamSupport.stream(findAll(predicate).spliterator(), false)
                .collect(Collectors.toList());
    }
}
