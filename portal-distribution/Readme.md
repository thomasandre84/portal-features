# Distribute Notifications
## Setup Greenmail
```
{
"email": "test@test.org",
"login": "test",
"password": "test"
}'
```

And
``` 
{
  "email": "test2@test.org",
  "login": "test2",
  "password": "test2"
}
```  

or directly with curl
``` 
curl -X POST "http://localhost:8085/api/user" \
 -H "Accept: application/json" \
 -H "Content-Type: application/json" \
 -d '{"email":"test@test.org","login":"test","password":"test"}' 
```

```
curl -X POST "http://localhost:8085/api/user" \
 -H "Accept: application/json" \
 -H "Content-Type: application/json" \
 -d '{"email":"test2@test.org","login":"test2","password":"test2"}'  
```