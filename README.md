# CampaignNavigator

# Prerequisites
You must have java 8 installed in your machine

# Installation
If you want to run with maven, please run mvn clean install and then in project home run 
java -jar target/CampaignNavigator-0.0.1-SNAPSHOT.jar --file=<Location of your campaignFile> <DesiredCampaignList_seperated_by_spaces>
  
e.g:
java -jar target/CampaignNavigator-0.0.1-SNAPSHOT.jar --file=/Users/asmatsoomro/Documents/Projects/CampaignNavigator/src/main/resources/input.txt "3" "4" "10"

# Running in IDE
Import the project in your favorite IDE, e.g intellij
Give the location of your campaign file in environment variables
Provide your desired target campaign as command line arguments
Run CampaignNavigator.java
It will run your application on port 8090

# Calling a REST Endpoint
The application can also be executed using the rest endpoint.
localhost:8030/campaign/navigate?segments=<Segments>
e.g. localhost:8090/campaign/navigate?segments=15 9 21

# Running the tests
The test folder contains the unit tests.
Right click on CampaignServiceTest to run unit tests.

# Assumptions/Decisions
With two or more segments containing equal number of matches, the last processed segment will be returned as a result

# Author
Asmat Soomro (soomro.asmat@gmail.com) is the sole author of the project.


