Find By Reviewer
------------------
localhost:8080/api/reports/reviewer/6 [GET]

List reports in which worker with id 6 is the reviewer.

### Response
```json
[
    {
        "rewarded": false,
        "punished": false,
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
]
```
