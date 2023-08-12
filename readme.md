## init setup
### run docker postgresql
- for run database use 
```
docker-compose -f ./db/docker-compose.yml up -d
```

- for kill database and images 
```
docker-compose down --volumes ; docker rmi postgres-technical-test
```
- db detail
    - user = 'zaki'
    - password = 'zaki'
    - database = 'zaki'
    - db port = `5433`

### run spring
- `cd ./user-test`
- `./gradlew bootrun`

## api
server run on port 8081
### POST (create user)
```
URL : http://localhost:8081/api/user
```
body
```
{
    "namaLengkap":"zaki maulana",
    "username":"zaki",
    "password":"zaki",
    "status":"ACTIVE"
}
```

### GET (get all)
```
URL : http://localhost:8081/api/user
```


### GET (get by id)
```
URL : http://localhost:8081/api/user/{id}
```

### PUT (update by id)

```
URL : http://localhost:8081/api/user/{id}
```
body
```
{
    "userId:"1"
    "namaLengkap":"zaki",
    "username":"zaki",
    "password":"zaki",
    "status":"ACTIVE"
}
```

### PATCH (partial update by id)
```
URL : http://localhost:8081/api/user/{id}
```
body
```
{
    "userId:"1"
    "username":"zakimaulana",
}
```

### DELETE (delete by id)
```
URL : http://localhost:8081/api/user/{id}
```




