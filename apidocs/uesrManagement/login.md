login
------
localhost:8080/api/users/login/ [POST]

### Request

```json
{
	"email": "admin@ood.ir",
	"password": "admin"
}

```

### Response
`is_manager` determines whether or not user is manager of that specific section. It is 
not the same as `isSuperuser` in terms of privileges.

```json
{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6Mn0.lWtZwDVvJieG55kSFAHZQao2M9VfS6e93AG3ErTDRlU",
    "worker": {
        "email": "admin@ood.ir",
        "name": "مدیر کل",
        "id": 2,
        "isSuperuser": true,
        "sectionAssignment": {
            "section": {
                "title": "مدیریت",
                "id": 1
            },
            "isManager": true
        }
    }
}
```
