# incubator


Reference:

Extractor Config:

{
    "name": "appDataConfig",
    "version": "1.0",
    "templateName": "appdata.xlsx",
    "executionType": "PARALLEL",
    "tasks": [
        {
            "name": "appData",
            "priority": null,
            "taskType": "REST_API",
            "requestUrl": "https://dummyjson.com/products",
            "responseMapping": {
                "responsePayload": null,
                "extractType": "EXCEL",
                "excelExtraction": null
            },
            "status": {
                "state": null,
                "code": null,
                "statusMessage": {},
                "valid": false
            }
        },
        {
            "name": "appProfile",
            "priority": null,
            "taskType": "REST_API",
            "requestUrl": "https://dummyjson.com/products",
            "responseMapping": {
                "responsePayload": null,
                "extractType": "EXCEL",
                "excelExtraction": null
            },
            "status": {
                "state": null,
                "code": null,
                "statusMessage": {},
                "valid": false
            }
        }
    ],
    "status": {
        "state": null,
        "code": null,
        "statusMessage": {},
        "valid": false
    }
}




