# Booking Service

This service Performs Database operations with Cassandra.


This service runs on 8080 port and Another [booking-service](https://github.com/suchayj/booking-service) service will run on 8100.

/checkAvailable API

Payloads
```javascript
url: http://localhost:8080/api/bookings/checkAvailable
{
 "containerSize" : 20,
 "containerType" : "REEFER",
 "origin" : "Southampton",
 "destination" : "Singapore",
 "quantity" : 4
}
```

```javascript
response:
{
    "id": 3,
    "size": 20,
    "type": "DRY",
    "availableSpace": 30
}

```

/api/bookings API

```javascript
url: http://localhost:8080/api/bookings/
{
 "containerSize" : 20,
 "containerType" : "REEFER",
 "origin" : "Southampton",
 "destination" : "Singapore",
 "quantity" : 4
}
```

```javascript
response:
{
    "bookingRef": "957000003",
    "containerSize": 40,
    "containerType": "REEFER",
    "origin": "Southampton",
    "destination": "Singapore",
    "quantity": 6,
    "timestamp": "2021-06-07T08:42:20Z"
}
```