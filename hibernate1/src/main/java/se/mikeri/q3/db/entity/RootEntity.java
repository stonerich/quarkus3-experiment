package se.mikeri.q3.db.entity;


import java.util.HashSet;
import java.util.Set;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class RootEntity  extends PanacheEntity {
    
    @NotNull
    public String name;

    @NotNull
    public long counter;


    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "details_id", foreignKey=@ForeignKey(name="ROOT_FK_DETAILS"))
    public RootDetailEntity details;

    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="root_id", foreignKey=@ForeignKey(name="ROOTLIST_FK_ROOT"))
    public Set<RootListEntity> items = new HashSet<RootListEntity>();


}
