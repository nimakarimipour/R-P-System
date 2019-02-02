List All
------------
localhost:8080/api/evaluation

### Response 
```json
[
    {
        "thresholds": {
            "reward": 6,
            "punishment": 4,
            "rewardAction": "Celebrate",
            "punishmentAction": "Cry"
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
]
```