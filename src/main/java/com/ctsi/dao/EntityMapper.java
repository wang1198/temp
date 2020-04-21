package com.ctsi.dao;

import java.util.List;

/**
 * ClassName EntityMapper
 * Description TODO
 * Author tongliwei
 * Date 2020/2/25 13:24
 * Version v1.0
 **/
public interface EntityMapper <D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List <D> toDto(List<E> entityList);
}