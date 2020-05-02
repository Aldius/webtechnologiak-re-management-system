# Functions

## Data store
- Function XYZ1: _Please provide a description_
- Function XYZ2: _Please Provide a description_


## Document handling
<ul>
  
<li>Upload document
  <ul>
    <li>Description: Accepts the document's properties and the document file as an input and stores them in the DB. The file is stored as a BLOB.</li>
    <li>Method: POST</li>
    <li>Endpoint: .../api/documentHandling/document/add?uid={RealEstate/Appraisal UID}</li>
    <li>Required JSON: {"properties" : {"description" : "abc","documentType":0}, "file" : {the file you want to upload} }</li>
    <li>Response: 200 OK, and the newly created document's properties in JSON.</li>
    <li>Errors: 400 Bad Request, if there's a file upload error or the RealEstate/Appraisal is not found.</li>
  </ul>
</li>

<li>Retrieve document (by ID)
  <ul>
    <li>Description: Retrieves a document's properties in JSON by its ID.</li>
    <li>Method: GET</li>
    <li>Endpoint: .../api/documentHandling/document/{id}</li>
    <li>Response: 200 OK, and the document's properties in JSON.</li>
    <li>Errors: 400 Bad Request, if the document is not found.</li>
  </ul>
</li>

<li>Retrieve document(s) (by Real Estate/Appraisal UID)
  <ul>
    <li>Description: Retrieves all documents related to a Real Estate/Appraisal.</li>
    <li>Method: GET</li>
    <li>Endpoint: .../api/documentHandling/document/all?uid={RealEstate/Appraisal UID}</li>
    <li>Response: 200 OK, and the array of documents' properties in JSON.</li>
    <li>Errors: 400 Bad Request, if the RealEstate/Appraisal is not found.</li>
  </ul>
</li>

<li>Update document
  <ul>
    <li>Description: Updates a document's description and/or type based on JSON data.</li>
    <li>Method: PATCH</li>
    <li>Endpoint: .../api/documentHandling/document/{id}</li>
    <li>Required JSON: {"description" : "abc"} OR {"documentType":0} OR {"description" : "abc","documentType":0}</li>
    <li>Response: 200 OK, and the updated document's properties in JSON.</li>
    <li>Errors: 400 Bad Request, if the document is not found.</li>
  </ul>
</li>

<li>Delete document
  <ul>
    <li>Description: Invalidates a document (sets its status to 1 in the DB). The invalidated document's ID can't be used in future requests.</li>
    <li>Method: DELETE</li>
    <li>Endpoint: .../api/documentHandling/document/{id}</li>
    <li>Response: 200 OK.</li>
    <li>Errors: 400 Bad Request, if the document is not found.</li>
  </ul>
</li>

<li>Download document
  <ul>
    <li>Description: By visiting the endpoint, the user can download the given document file.</li>
    <li>Method: GET</li>
    <li>Endpoint: .../api/documentHandling/document/download/{id}</li>
    <li>Response: 200 OK, and the document file.</li>
    <li>Errors: 400 Bad Request, if the document is not found.</li>
  </ul>
</li>

<li>CRUD Methods on Data Store entities
  <ul>
    <li>Description: The microservice synchronizes (creates, updates, deletes) the Data Store's entities and their properties which are necessary for the microservice in its own DB. Required properties: Unique ID, status, version.</li>
    <li>Endpoint: .../api/documentHandling/DataStoreEntity/...</li>
  </ul>
</li>

</ul>


## External-api module
- Function XYZ1: _Please provide a description_
- Function XYZ2: _Please Provide a description_


## Recalc module
- Function XYZ1: _Please provide a description_
- Function XYZ2: _Please Provide a description_


## User handling

<ul>
 <li>Registration:
    <li>Description: Endpoint for registration</li>
    <li>Method: POST</li>
    <li>Endpoint: .../register</li>
    <li>Required data (JSON body): <i>{ "username": "string", "password": "string", "email": "string", "fullName": "string" }</i></li>
    <li>Response: 200 OK, and the JSON object with the user information</li>
    <li>Errors: 400 Bad Request, if the username or the email address is already taken</li>
 </li>
 <li>Authentication: 
    <li>Description: Endpoint for authentication</li>
    <li>Method: POST</li>
    <li>Endpoint: .../authenticate</li>
    <li>Required data (JSON body): <i>{ "username": "string", "password": "string" }</i></li>
    <li>Response: 200 OK, and a generated JWT Token that belongs to the user</li>
    <li>Errors: 400 Bad Request, if the username or password is incorrect</li>
 </li>
 <li>Assign role: 
    <li>Description: Endpoint for assigning a role to a specified user</li>
    <li>Method: POST</li>
    <li>Endpoint: .../role/assign?username=<b>{username}</b>&roleName=<b>{roleName}</b></li>
    <li>Required header: <i>authorization:Bearer {the given token from authentication}</i></li>
    <li>Response: 200 OK, if the process is successful</li>
    <li>Errors: 400 Bad Request, if the user or role is not found. 401 Unauthorized, if the user is not authenticated or the token expired</li>
 </li>
 <li>Remove role: 
    <li>Description: Endpoint for removing a role from a specified user</li>
    <li>Method: POST</li>
    <li>Endpoint: .../role/remove?username=<b>{username}</b>&roleName=<b>{roleName}</b></li>
    <li>Required header: <i>authorization:Bearer {the given token from authentication}</i></li>
    <li>Response: 200 OK, if the process is successful</li>
    <li>Errors: 400 Bad Request, if the user or role is not found. 401 Unauthorized, if the user is not authenticated or the token expired</li>
 </li>
 <li>Get all roles: 
    <li>Description: Endpoint for getting all the assigned roles for a specified user</li>
    <li>Method: GET</li>
    <li>Endpoint: .../role/get-all?username=<b>{username}</b></li>
    <li>Response: 200 OK, and the collection of the assigned roles</li>
    <li>Errors: 400 Bad Request, if the user is not found</li>
 </li>
</ul>