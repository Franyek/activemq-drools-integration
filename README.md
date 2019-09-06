Github project, used as an inspiration:
   * https://github.com/spring-guides/gs-messaging-jms
   * https://github.com/Quicksign/drools-server
   * https://github.com/ityouknow/drools-examples


Queue examples
```
fendtUptimeCenter.data.equipmentBusDiagnosticData.queue
{
	"header": {
		"schemaVersion": 2,
		"timeOfReception": "2019-08-30T14:04:09Z",
		"schemaID": "7e34bbe8-778f-4ec2-9940-87fb57025cec",
		"timeOfCreation": "2019-08-30T14:04:09.052Z",
		"id": "c948cef8-bd7c-480f-af9e-b1afe9dbff3f"
	},
	"body": {
		"deviceType": "Gateway",
		"environment": "engineering",
		"deviceRevision": "GW03",
		"equipmentIdentificationNumber": "WAM96123A00F00021",
		"diagnosticTroubleCodeData": {
			"activeTimeStamp": "2019-08-30T14:03:07Z",
			"suspectParameterNumber": 6070,
			"inactiveTimeStamp": "2019-08-30T14:03:13Z",
			"code": 2114459574,
			"sourceAddress": 0,
			"occurrenceCount": 126,
			"isActive": true,
			"failureModeIdentifier": 8
		},
		"deviceSerialNumber": "002168"
	}
}

fendtUptimeCenter.data.genericDriverBusDiagnosticData.queue
{
	"header": {
		"schemaVersion": 2,
		"timeOfReception": "2019-09-05T11:16:02Z",
		"schemaID": "ce7945d4-4d08-4ca5-864c-c45735638bac",
		"timeOfCreation": "2019-09-05T11:16:02.131Z",
		"id": "e42f5b51-8fce-48d2-8ee5-c502de23798a"
	},
	"body": {
		"deviceType": "Gateway",
		"environment": "engineering",
		"deviceRevision": "GW03",
		"genericDriverBusDiagnosticTroubleCodeData": {
			"activeTimeStamp": "2019-09-05T10:36:23Z",
			"inactiveTimeStamp": "2019-09-05T10:36:34Z",
			"faultCode": "04.x.C7",
			"code": 199,
			"sourceAddress": 4
		},
		"equipmentIdentificationNumber": "WAM96123A00F00021",
		"deviceSerialNumber": "002168"
	}
}

fendtUptimeCenter.data.telemetryData.queue
{
	"header": {
		"schemaVersion": 2,
		"timeOfReception": "2019-09-05T11:41:55Z",
		"schemaID": "5d6a9033-632a-48ef-8724-349ed1bf0648",
		"timeOfCreation": "2019-09-05T11:41:55.066Z",
		"id": "809a75fa-27d6-4530-9a7c-e5d3205336da"
	},
	"body": {
		"deviceType": "Gateway",
		"environment": "engineering",
		"geospatialData": {
			"timeStamp": "2019-09-04T08:45:04Z",
			"latitude": 47.13906766666667,
			"longitude": 16.85592838333333
		},
		"canData": {
			"unit": "enum",
			"valueRaw": 66.6666666668,
			"canName": "GSM_SIGNAL",
			"canID": 2147542304,
			"value": 66.6666666668
		},
		"deviceRevision": "GW03",
		"equipmentIdentificationNumber": "WAM96123A00F00021",
		"equipmentStatusRaw": 5,
		"deviceSerialNumber": "002168",
		"equipmentStatus": "Working"
	}
}

fendtUptimeCenter.reference.engineering.devices.queue
{
	"deviceType": "Topcon",
	"allowDataSharing": true,
	"serialNumber": "1223-090075C7",
	"description": "T37",
	"lastUpdatedTime": "2019-09-05T12:45:38.182Z",
	"equipmentIdentificationNumber": "VKKMX62ALGB357026",
	"serviceLevel": 2,
	"deviceId": "151025095",
	"hasSeenBefore": true,
	"activated": true,
	"revision": "AM53"
}

fendtUptimeCenter.reference.engineering.equipment.queue
{
	"allowDataSharing": true,
	"series": {
		"brandUuid": "2d3edfbe-7f82-4e1b-a34f-8230cf6d0ed5",
		"name": "H3",
		"uuid": "7e8217ef-7c2f-4253-b1eb-591d684bfbff"
	},
	"description": "539.23.00004",
	"identificationNumber": "WAM53923K00F00004",
	"lastUpdatedTime": "2019-09-05T13:45:17.234Z",
	"model": {
		"name": "H3-19P",
		"serviceScheduleUuid": null,
		"seriesUuid": "7e8217ef-7c2f-4253-b1eb-591d684bfbff",
		"uuid": "fbff81c0-36bc-4c81-98e5-8d0a00b08111"
	},
	"serviceLevel": 2,
	"uuid": "e6cd5601-f0c4-4b96-a25a-6fa4346bc928",
	"brand": {
		"name": "Prototype",
		"uuid": "2d3edfbe-7f82-4e1b-a34f-8230cf6d0ed5"
	},
	"hasSeenBefore": true
}

fendtUptimeCenter.reference.devices.queue
{
	"deviceType": "Gateway",
	"allowDataSharing": true,
	"serialNumber": "003091",
	"lastUpdatedTime": "2019-09-05T14:45:38.397Z",
	"equipmentIdentificationNumber": "ZN2C0008C03010002",
	"serviceLevel": 2,
	"deviceId": "003091",
	"hasSeenBefore": true,
	"activated": true,
	"revision": "GW03"
}
```