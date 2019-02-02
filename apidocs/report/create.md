Create 
-----------
localhost:8080/api/reports [POST]

### Permissions
Superuser

### Request
All fields are required
```json
{
	"evaluationId": 7,
	"reviewerId": 6,
	"revieweeId": 5
}
```

### Response
```json
{
    "evaluation": {
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
                "id": 8,
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
                "id": 11,
                "title": "third criterion"
            }
        ],
        "id": 7,
        "sectionId": 3,
        "title": "stuff"
    },
    "scores": [],
    "reviewee": {
        "name": "معمولی",
        "isSuperuser": false,
        "sectionAssignment": {
            "isManager": false,
            "section": {
                "id": 3,
                "title": "مارکتینگ"
            }
        },
        "id": 5,
        "email": "ee@gmail.com"
    },
    "id": 14,
    "rewarded": false,
    "punished": false,
    "reviewer": {
        "name": "ارزیاب",
        "isSuperuser": false,
        "sectionAssignment": {
            "isManager": false,
            "section": {
                "id": 3,
                "title": "مارکتینگ"
            }
        },
        "id": 6,
        "email": "er@gmail.com"
    }
}
```
