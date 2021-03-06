package com.momentum.active.store.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="product")
@ToString
@Getter
@Setter
public class ProductDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="code")
    @JsonIgnore
    private int code;

    @Column(name="name")
    private String name;

    @Column(name="cost")
    private int cost;

}

