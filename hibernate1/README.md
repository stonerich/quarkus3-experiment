# hibernate1

curls

curl -v -X 'GET' 'http://localhost:8080/api/root' -H 'accept: application/json'

curl -X 'POST' 'http://localhost:8080/api/root?d1=d1&d2=d2&name=Olle' -H 'accept: */*' -d ''
curl -X 'PATCH' 'http://localhost:8080/api/root/1?name=Olle2&d1=patchedd1&d2=patchedd2&list=a%2Cb%2Cc' -H 'accept: */*'



curl -X 'POST' 'http://localhost:8080/api/root?d1=kd1&d2=kd2&name=Kalle' -H 'accept: */*'  -d ''
curl -X 'PATCH' 'http://localhost:8080/api/root/2?name=Kalle2&d1=patchedkalle1&d2=patchedd2&list=x,y,z' -H 'accept: */*'


curl -v 'http://localhost:8080/api/root' -H 'accept: application/json'
curl -v  'http://localhost:8080/api/root/list' -H 'accept: application/json'
curl -v  'http://localhost:8080/api/root/details' -H 'accept: application/json'
