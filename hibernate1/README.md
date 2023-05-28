# hibernate1

Example of root entity with details and a list (a set for now)



## Notes

Root relation to list has orphanRemoval = true

    https://vladmihalcea.com/orphanremoval-jpa-hibernate/
    https://thorben-janssen.com/hibernate-tips-how-to-delete-child-entities/


The PATCH operation in RootResource removes the existing list entries before adding new.
This gives the new entities new Id's, if that is not the wanted behavour then
one might need to do soem sorting to only remove those that are not in the new list and only add the really
new ones.

Another way to hanlde the set is of course to have separate rest endpoint for the handling and allow DELETE,and PATCH
for individual entries and POST to add a new.


The @JoinColumn in RootEntity is needed to stop hibernate from creationg a coupling table between Root and RootList.



## Curls

curl -v -X 'GET' 'http://localhost:8080/api/root' -H 'accept: application/json'

curl -X 'POST' 'http://localhost:8080/api/root?d1=d1&d2=d2&name=Olle' -H 'accept: */*' -d ''
curl -X 'PATCH' 'http://localhost:8080/api/root/1?name=Olle2&d1=patchedd1&d2=patchedd2&list=a%2Cb%2Cc' -H 'accept: */*'



curl -X 'POST' 'http://localhost:8080/api/root?d1=kd1&d2=kd2&name=Kalle' -H 'accept: */*'  -d ''
curl -X 'PATCH' 'http://localhost:8080/api/root/2?name=Kalle2&d1=patchedkalle1&d2=patchedd2&list=x,y,z' -H 'accept: */*'


curl -v 'http://localhost:8080/api/root' -H 'accept: application/json'
curl -v  'http://localhost:8080/api/root/list' -H 'accept: application/json'
curl -v  'http://localhost:8080/api/root/details' -H 'accept: application/json'
