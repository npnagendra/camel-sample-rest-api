# camel-sample-rest-api
Sample Rest API using Camel and Spring Boot

### Build
mvn clean package

### Docker
docker build -t camel-sample-rest-api .

### Run
docker run -p 8080:8080 -t camel-sample-rest-api

### Endpoint
http://localhost:8080/camel/automobile

### Sample Vaild Request
`<?xml version="1.0" encoding="UTF-8" ?>
<automobile>
  <id>1</id>
  <make>bmw</make>
  <model>x1</model>
</automobile>`


### Sample Invalid Request
`<?xml version="1.0" encoding="UTF-8" ?>
<automobile>
  <id>invalid</id>
  <make>bmw</make>
  <model>x1</model>
</automobile>`
