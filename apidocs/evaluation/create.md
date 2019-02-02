Create 
----------
localhost:8080/api/evaluation [POST]


### Permission
Should be manager of section indicated by `section_id`

### Request
* All fields are necessary

```json
{
	"title": "stuff",
	"sectionId": 3
}
```

### Response
```json
{
    "thresholds": {
        "reward": 0,
        "punishment": 0,
        "rewardAction": null,
        "punishmentAction": null
    },
    "criteria": [],
    "id": 5,
    "sectionId": 3,
    "title": "stuff"
}
```
