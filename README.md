# spring-zookeeper-example

This is a test application, that shows ability of Spring framework to pull its configurations from Zookeeper.
All properties are refreshable at runtime without server restart.

For now, the application pulls a simple user-defined property, called "name" that may represent a name of an autor or contributor.
Also, Datasource and Hibernate configurations are stored and pulled from Zookeeper - as example of how complicated and critical parts
of application may be treated.

To be picked up by Spring, configurations should exist under /config/testapp root znode, where testapp - is a name of given application 
(defined in /src/main/resources/bootstrap.yml file).

Next properties are required:
```
	/config/testapp/name (e.g. "Vitalii")

	/config/testapp/datasource/url (e.g. "jdbc:h2:tcp://localhost/~/TestDB")
	/config/testapp/datasource/username
	/config/testapp/datasource/password
	/config/testapp/datasource/driverClassName "org.h2.Driver"

	/config/testapp/hibernate/hbm2ddl/method (e.g. create-update)
	/config/testapp/hibernate/dialect "org.hibernate.dialect.H2Dialect"
```
**How to launch**
-------------------
The application can be started by Maven goal:
``` 
	mvn spring-boot:run
```
Notice, that it requires a Zookeeper instance to be up and running on localhost:2181 (see bootstrap.yml) and also - H2 database, to test
correctness of Hibernate work.
It'll be accessible by localhost:8080 port.

**Integration test**
-------------------
Correctness of concept is proved by ApplicationConfigurationIntegrationTest. It can be run directly from ide or
using maven goal:
```
	mvn verify
```
	or 
```
	mvn integration-test
```