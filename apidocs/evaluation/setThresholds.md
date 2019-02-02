Set Thresholds
-------------------
localhost:8080/api/evaluation/threshold [POST]


# Permissions
Should be manager of that section

### Request 
* All fields are required
* punishmentThreshold <= rewardThreshold
```json
{
	"evaluationId": 5,
	"punishmentThreshold": 4,
	"rewardThreshold": 6
}
```

### Response 
```json
{
    "thresholds": {
        "reward": 6,
        "punishment": 4,
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
            "id": 9,
            "title": "third criterion"
        }
    ],
    "id": 5,
    "sectionId": 3,
    "title": "stuff"
}
```