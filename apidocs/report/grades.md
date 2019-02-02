Enter grades
--------------
localhost:8080/api/reports/score [POST]

Please consider that grades can not be entered for a 
report that already has grades. 409 status code gets returned
in that case.

###Permission
User should be reviewer of this report

### Request
About the map: 

> If criterion is qualitative valid scores are those 
enlisted in `scores` field of that criterion otherwise
the value should lie between min grade and max grade, 
both right hand side and left handside values should be 
strings. It is NOT mandatory to enter score for all criteria
of an evaluation.

```json
{
	"criterionScoreTextMap" : {
		"8": "3",
		"11": "great"
	},
	"reportId": 14
}
```

### Response
```json
{
    "rewarded": true,
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
    "scores": [
        {
            "score": "3",
            "criterion": {
                "grading": {
                    "minGrade": 1,
                    "maxGrade": 5
                },
                "weight": 1,
                "id": 8,
                "title": "first"
            },
            "value": 3
        },
        {
            "score": "great",
            "criterion": {
                "grading": {
                    "scores": [
                        "poor",
                        "great"
                    ]
                },
                "weight": 1,
                "id": 11,
                "title": "third criterion"
            },
            "value": 2
        }
    ],
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
```
