Apache Kafka stream application :
Instalation steps:
  - confluent is not supporting to install in windows, so using WSL2 tool installed linux deabin plugin in wsl2.
  - confluent installed and started in wsl2. all required 
  Confluent Image : 
 

 
 - Created kafka topic in confluent and added avro schemas to that.
 
- Sent a test message to topic using .sh script in wsl2.
Spring Boot App : Kafka Handler
- Implemented a java application using spring boot with Kafka dependencies.
- Added kafka broker url and serialization configurations to application.yml file.
 
- Implemented a rest service to produce messages to Customer and Balance topics.
 - Added listeners to Customer and Balance topics in java and consumed messages from topics and combined into Customer Balance object and published to Customer Balance Topic.
Image : springboot app.
 
   

Testing: 
-  Sent a message to published endpoint with customer topic. 

  Issue: Java windows app is unable to connect to wsl2 confluent server.
it is having a network issue to establish connection.
Sol : Install tomcat app in WSL2 and generated a war file of Spring boot app and copied to wsl2 and deployed.
-Now trying started tomcat, it raised a java heap memory issue. Increased a java heap memory . 
- Published a message to producer service, but it is not working and confluent was shut downing with memory issues.
    
