Add Quantitative Criterion
------------------------------
localhost:8080/api/evaluation/criterion/quantitative[POST]

For the case of criterion which are both qualitative and 
quantitative add two criterion with the same name and 
render them accordingly (in case it's needed at all).

### Permissions
Should be manager of section of evaluation

### Request
* All fields are mandatory
* minGrade should be <= maxGrade
```json
{
	"title": "first",
	"weight": 1,
	"evaluationId": 5,
	"minGrade": 1,
	"maxGrade": 5
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
    "criteria": [
        {
            "grading": {
                "minGrade": 1,
                "maxGrade": 5
            },
            "weight": 1,
            "id": 0,
            "title": "first"
        }
    ],
    "id": 5,
    "sectionId": 3,
    "title": "stuff"
}
```
