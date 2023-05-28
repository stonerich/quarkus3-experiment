package se.mikeri.q3.db.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.ManyToOne;

@Entity
public class RootListEntity extends PanacheEntity {


    @ManyToOne(fetch=FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    // @JoinColumn(name = "root_id", foreignKey=@ForeignKey(name="ROOTLIST_FK_ROOT" ))
    public RootEntity root;

    public String listData;
    
}
