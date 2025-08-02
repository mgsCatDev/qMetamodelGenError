package com.mgsCatDev;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public abstract class AbstractE2<T1> {

    @Id
    private Long id;

    @ManyToOne
    @NotNull
    private T1 t1;

}
