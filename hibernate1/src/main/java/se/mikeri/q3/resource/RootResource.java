package se.mikeri.q3.resource;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import se.mikeri.q3.db.entity.RootDetailEntity;
import se.mikeri.q3.db.entity.RootEntity;
import se.mikeri.q3.db.entity.RootListEntity;

@Path("/api/root")
public class RootResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RootDto> getAll() {
        List<RootEntity> allRoot =  RootEntity.listAll();

        List<RootDto> result = new ArrayList<RootDto>();
        for (RootEntity root : allRoot) {
            RootDto rootDto = new RootDto(root.name, root.counter, root.details);
            rootDto.setId(root.id);
            for (RootListEntity rle : root.items) {
                rootDto.addToList(rle.id + ":" + rle.listData);
            }
            result.add(rootDto);
        }

        return result;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public List<String> getAllLists()
    {
        List<RootListEntity> allList = RootListEntity.listAll();
        List<String> result = new ArrayList<>();
        for (RootListEntity rle : allList) {
            String addStr =  rle.id + ":" + rle.listData + ":" + rle.root.name;
            result.add(addStr);
        }
        return result;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("details")
    public List<String> getAllDetails()
    {
        List<RootDetailEntity> allList = RootDetailEntity.listAll();
        List<String> result = new ArrayList<>();
        for (RootDetailEntity rde : allList) {
            String addStr =  rde.id + ":" + rde.detail1 + ":" + rde.detail2;
            result.add(addStr);
        }
        return result;
    }


    @POST
    @Transactional
    public void addRoot(@QueryParam(value="name") String newName,
        @QueryParam(value="d1") String detail1,
        @QueryParam(value="d2") String detail2)
    {
        System.out.println("Adding " + newName);
        if (newName == null || newName.length() == 0) {
            throw new RuntimeException("Name must be given");
        }
        RootEntity newRoot = new RootEntity();
        newRoot.name = newName;

        if (detail1 != null  || detail2 != null) {
            RootDetailEntity rde = new RootDetailEntity();
            rde.detail1 = detail1;
            rde.detail2 = detail2;
            newRoot.details = rde;
        }

        RootListEntity rle = new RootListEntity();
        rle.listData = "DEFAULT LIST ";
        rle.root = newRoot; // https://www.baeldung.com/hibernate-not-null-error

        newRoot.items = Set.of(rle);


        newRoot.persist();
        System.out.println("Persisted");
        
    }

    @Path("{id}/increment")
    @Transactional
    @POST
    public void incrementRootCount(@PathParam(value="id") int id)
    {
        RootEntity oldRoot = RootEntity.findById(id);
        oldRoot.counter = oldRoot.counter + 1;
    }


    @Path("{id}")
    @PATCH
    @Transactional
    public void patchRoot(@PathParam(value="id") long id,
        @QueryParam(value="name") String newName,
        @QueryParam(value="d1") String detail1,
        @QueryParam(value="d2") String detail2,
        @QueryParam(value="list") String strList) {



        RootEntity theRoot = RootEntity.findById(id);
        // RootEntity theRoot = new RootEntity();
        // theRoot.id = id;
        theRoot.name = newName;


        if (detail1 != null  || detail2 != null) {
            System.out.println("Add detail " + detail1 + " " + detail2);
            RootDetailEntity rde = theRoot.details;
            if (rde == null) {
                rde = new RootDetailEntity();
                theRoot.details = rde;
            }
            rde.detail1 = detail1;
            rde.detail2 = detail2;
        }




        if (strList != null && strList.length() > 0) {
            List<String> newList = List.of(strList.split(","));
            System.out.println("LIST :" + newList);

            
            Set<RootListEntity> rleSet = theRoot.items;
            if (rleSet == null) {
                rleSet = new HashSet<>();
                theRoot.items = rleSet;
            } else {
                rleSet.forEach((RootListEntity rle) ->  {
                     rle.root = null; // Decouple old entity
                });
                rleSet.clear();
            }
            for (String s : newList) {
                RootListEntity rle = new RootListEntity();
                rle.listData = s;
                rle.root = theRoot;
                rleSet.add(rle);
            }
        } else {
            System.out.println("LIST = NULL");
            theRoot.items = null;
        }



    }
        



}

