AddWorker 
---------------
localhost:8080/api/users/ [POST]

### Permissions
Superuser

### Request
* All fields are mandatory. 
* Email should be valid.
* Section should exist.
* Email should not be taken already. Value of code for this error is `duplicate`
 
```json
{
	"name": "second",
	"email": "second@gmail.com",
	"password": "secondPassword",
	"isManager": true,
	"sectionId": 1
}
```

### Response 
```json
{
    "sectionAssignment": {
        "section": {
            "title": "مدیریت",
            "id": 1
        },
        "isManager": true
    },
    "email": "second@gmail.com",
    "isSuperuser": false,
    "name": "second",
    "id": 3
}
```
