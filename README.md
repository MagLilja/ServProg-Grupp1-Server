# Profile management server application.

Provides a simple server service with REST end points for accessing a "Profile" from a MySQL-database using JPA meant to be deployed and run on a Wildfly application server. 



## Building from the repo

Build using Maven

````
mvn build
````

## Configure wildfly and database

if running on wildfly in docker see documentation here:

https://github.com/MagLilja/WildFly-MySQL-DockerCompose


## Deploy to wildfly

Enter credentials for your wildfly server in the maven POM.xml

````
<plugin>
        <groupId>org.wildfly.plugins</groupId>
        <artifactId>wildfly-maven-plugin</artifactId>
        <version>2.1.0.Final</version>
        <configuration>
            <hostname>xxx.xx.xxx.xx</hostname>
            <port>xxxx</port>
            <username>xxxx</username>
            <password>*******</password>
        </configuration>
    </plugin>
````

use Maven to deploy to wildfly

````
mvn wildfly:deploy -f pom.xml
````


## API 

The API has X end points, all producing or consuming JSON data.

### @GET api/profiles

returns a response with a list of all profiles. 

### @GET api/profiles/search?firstname=<searchword>&lastname=<searchword>

Query all profiles for matching profiles. Takes one or two params, firstname and/or lastname. 
Returns a response with a list of all matching profiles.

If both parameters are present the result will be profiles that match both of the queries. 

### @GET api/profiles/{id}

Returns a response with a profile matching the id. 

### @GET api/profiles/username/{username}

Returns a response with a profile matching the username.

### @POST api/profiles/

Consumes a profile in the form of JSON data, on success it returns the same profile. 

### @DELETE api/profiles/{id}

Removes a profile from the api.

### @PUT api/profiles/{id}

Resource to update a profile with matching id.
Returns a response with the updated profile on success and status code.
