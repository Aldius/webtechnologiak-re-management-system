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
- Registration: _api_url/register endpoint. Request method POST. <br>Required data (JSON): <br>{ "username": "string", "password": "string", "email": "string", "fullName": "string" }<br>Response: <br>- JSON object with the user information (the one defined above), except the password, if the registration is successfull.<br>- If the username or the email address is already taken, 400 BadRequest._

- Authentication: _api_url/authenticate endpoint. Request method POST. <br>Required data (JSON): <br>{ "username": "string", "password": "string" }<br>Response: <br>- A generated JWT Token that belongs to the user.<br>- If the username or password is incorrect, 401 UnAuthorized._

- Profile creation: _api_url/create-profile endpoint. Request method POST, the user has to be authenticated to be able to access this endpoint. <br>Required data (JSON): <br>{ "name": "string", "displayName": "string" }<br>Response: <br>- JSON object with the created profile information.<br>- If the user is not authorized, 401 UnAuthorized._

- Query all profiles: _api_url/profiles endpoint. Request method GET, the user has to be authenticated to be able to access this endpoint. <br>Response: <br>- JSON array with the profile informations.<br>- If the user is not authorized, 401 UnAuthorized._

- Role creation: _api_url/create-role endpoint. Request method POST, the user has to be authenticated to be able to access this endpoint. <br>Required data (JSON): <br>{ "name": "string", "displayName": "string" }<br>Response: <br>- JSON object with the created role information.<br>- If the user is not authorized, 401 UnAuthorized._

- Assign role to profile: _api_url/assign-role endpoint. Request method POST, the user has to be authenticated to be able to access this endpoint. <br>Required data (JSON): <br>{ "roleId": "int", "profileId": "int" }<br>Response: <br>- 200 OK, if the assignment is successfull.<br>- If the user is not authorized, 401 UnAuthorized._

- Query if the profile has a specific role assigned: _api_url/profile-has-role endpoint. Request method POST, the user has to be authenticated to be able to access this endpoint. <br>Required data (JSON): <br>{ "roleId": "int", "profileId": "int" }<br>Response: <br>- A boolean regarding the result.<br>- If the user is not authorized, 401 UnAuthorized._
