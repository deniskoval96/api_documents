======================== [How To Run] =======================

You should have `maven` installed before.

    $ mvn clean install
    $ mvn spring-boot:run

======================== [How To Test API] =======================

## Get auth token
POST - http://localhost:8080/login
    request body:
        { 
            "username" : "given login",
            "password" : "given password" 
        }
        
## Call for instance the endpoint to check security
GET - localhost:8080/users/all

Add header 'Authorization' : 'token from `/login` endpoint'
        


======================== [Endpoints] =======================

## Logged in and get auth token
POST - http://localhost:8080/login
    request body:
        { 
            "username" : "given login",
            "password" : "given password" 
        }
    response:
       {
            "authToken" : "your full diadok token to attach it in 'Authorization' header for each call of the back-end api" 
       }

## Get documents from service and store to DB; then return the documents
GET - http://localhost:8080/documents
    response:
        [{
            "title" : "name of file",
            "signedStatus" : "SIGNED / WAIT_SIGNING / NOT_SIGNABLE"
        }]

##                  {Signing documents with the key}
## Sign only documents which price more than 5000 and return the response: documents which were signed
##  internally the method should get `key to signing` from another service (API) 
##  
##  Front-End part:
##      Invoke the endpoint when user press `Sign documents` button
##      During waiting response from API
##      1) Show spinner with text `Please hold on, documents are signing...`
##      2) Mark all documents which price less than 5000 with `NOT_SIGNABLE`
##              info with statuses for documents we can get from:  
##                                              http://localhost:8080/documents
##      3) After getting response mark documents which were got from endpoint as `SIGNED` 
##          
PUT - http://localhost:8080/documents/sign
   response:
        [{
            "title" : "name of file",
            "signedStatus" : "SIGNED"
        }]

## Stop signing documents
## Return all documents which were signed by that time
PUT - http://localhost:8080/documents/stop


## Download document by id
GET - http://localhost:8080/documents/download/{documentId}/


======================== [Technologies] =======================

 SpringBoot, RestTemplate
 MyBatis, H2

========================== [DataBase] =====================

Database:

	store documents in db BLOB


table DOCUMENT_INFO
	user_id    document_id     document_title        signed_status (SIGNED / WAIT_SIGNING / NOT_SIGNABLE)     




table DOCUMENT_DATA
    documentId(INTEGER)       document (BLOB)




========================== [ToDO] =====================

Add interceptor for check token header of logged in user - done

Add docker



========================================================