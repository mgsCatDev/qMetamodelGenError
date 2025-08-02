package com.mgsCatDev;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class E1Impl implements E1<E2Impl> {

    @Id
    private Long id;

}
