# spring-user-demo

Spring Boot Rest Demo with h2 db, gradle, java 11.

Some unit tests with jUnit 5 and MockMVC.

---

Post User Example
``` bash
curl --location --request POST 'localhost:8080/users/' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Juan Rodriguez",
"email": "juan2@rodriguez.org",
"password": "hunterR2",
"phones": [
{
"number": "1234567",
"citycode": "1",
"contrycode": "57"
}
],
"active": true
}'
```

Post User Response
```json
{
    "id": "43b74e9b-0e15-4f46-ae6c-666f62c34137",
    "name": "Juan Rodriguez",
    "email": "juan2@rodriguez.org",
    "password": "hunterR2",
    "created": "2021-07-22T14:52:05.446854067",
    "updated": "2021-07-22T14:52:05.446899047",
    "lastLogin": "2021-07-22T14:52:05.414104555",
    "token": "2ac5cdae-dadd-4216-b609-4f2a4492fd21",
    "isActive": true,
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

---

Partial Update User

```bash
curl --location --request PATCH 'localhost:8080/users/43b74e9b-0e15-4f46-ae6c-666f62c34137' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Juan Rodriguez2",
    "email": "juan@rodriguez.org",
    "isActive": false
}'
```

Partial Update Response
```json
{
    "id": "43b74e9b-0e15-4f46-ae6c-666f62c34137",
    "name": "Juan Rodriguez2",
    "email": "juan@rodriguez.org",
    "password": "hunterR2",
    "created": "2021-07-22T14:52:05.446854",
    "updated": "2021-07-22T14:54:20.290479275",
    "lastLogin": "2021-07-22T14:52:05.414105",
    "token": "2ac5cdae-dadd-4216-b609-4f2a4492fd21",
    "isActive": false,
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

---

Delete Existing User
```bash
curl --location --request DELETE 'localhost:8080/users/43b74e9b-0e15-4f46-ae6c-666f62c34137'
```

Delete Existing User Response Status 200
```json
{
    "id": "43b74e9b-0e15-4f46-ae6c-666f62c34137",
    "name": "Juan Rodriguez2",
    "email": "juan@rodriguez.org",
    "password": "hunterR2",
    "created": "2021-07-22T14:52:05.446854",
    "updated": "2021-07-22T14:54:20.290479",
    "lastLogin": "2021-07-22T14:52:05.414105",
    "token": "2ac5cdae-dadd-4216-b609-4f2a4492fd21",
    "isActive": false,
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

Delete Non Existing User Error Response Status 404

```json
{
    "mensaje": "No encontrado"
}
```