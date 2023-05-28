package se.mikeri.q3.db.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class RootDetailEntity extends PanacheEntity {


    public String detail1;
    public String detail2;
    
}
