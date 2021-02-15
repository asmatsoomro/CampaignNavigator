# CampaignNavigator

#Prerequisites
You must have java 8 installed in your machine

#Installing
If you want to run with maven, please run mvn clean install and then in project home run 
java -jar target/CampaignOrchestrator-0.0.1-SNAPSHOT.jar --file=<Location of your campaignFile> <DesiredCampaignList_seperated by spaces>
  
e.g:
java -jar target/CampaignOrchestrator-0.0.1-SNAPSHOT.jar --file=/Users/asmatsoomro/Documents/Projects/CampaignOrchestrator/src/main/resources/input.txt "3" "4" "10"java -jar target/CampaignOrchestrator-0.0.1-SNAPSHOT.jar --file=/Users/asmatsoomro/Documents/Projects/CampaignOrchestrator/src/main/resources/input.txt "3" "4" "10"

#Running in IDE
Import the project in your favorite IDE, e.g intellij
Give the location of your campaign file in environment variables
Provide your desired target campaign as command line arguments
Run CampaignNavigator.java
It will run your application on port 8090

#Running the tests
The test folder contains the unit tests.
Right click on CampaignServiceTest to run unit tests.

#Author
Asmat Soomro (soomro.asmat@gmail.com) is the sole author of the project.


