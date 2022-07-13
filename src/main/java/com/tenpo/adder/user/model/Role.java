package com.tenpo.adder.user.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="roles")
public class Role implements Serializable {

    private static final long serialVersionUID = -8189720426945481808L;

    private Long id;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(unique = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
