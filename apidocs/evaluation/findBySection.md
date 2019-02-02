Find by section
-------------------
localhost:8080/api/evaluation/section/3

List evaluations of section 3

# Response
```json
[
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
    }
]
```