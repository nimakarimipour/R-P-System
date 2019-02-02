Add Qualitative Criterion 
----------------------
localhost:8080/api/evaluation/criterion/qualitative [POST]

For the case of criterion which are both qualitative and 
quantitative add two criterion with the same name and 
render them accordingly (in case it's needed at all).

### Permissions
Should be manager of that section

### Request
* All fields are required
* Grades map should not be empty 
```json
{
	"title": "third criterion",
	"weight": 1,
	"evaluationId": 5,
	"grades": {
		"poor": 1,
		"great": 2
	}
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
            "id": 6,
            "title": "first"
        },
        {
            "grading": {
                "scores": [
                    "poor",
                    "great"
                ]
            },
            "weight": 1,
            "id": 0,
            "title": "third criterion"
        }
    ],
    "id": 5,
    "sectionId": 3,
    "title": "stuff"
}
```
