package se.mikeri.q3.resource;

import java.util.HashSet;
import java.util.Set;

import se.mikeri.q3.db.entity.RootDetailEntity;

public class RootDto {
    private long id;
    private String name;

    private long counter;



    private RootDetailEntity details;
    private Set<String> rootList;    

    public RootDto(String name, long counter, RootDetailEntity details)
    {
        this.name = name;
        this.counter = counter;
        this.details = details;
        this.rootList = new HashSet<String>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public void addToList(String data)
    {
        rootList.add(data);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RootDetailEntity getDetails() {
        return details;
    }

    public void setDetails(RootDetailEntity details) {
        this.details = details;
    }

    public Set<String> getRootList() {
        return rootList;
    }

    public void setRootList(Set<String> rootList) {
        this.rootList = rootList;
    }

    
}
