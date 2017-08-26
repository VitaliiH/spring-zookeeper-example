# spring-zookeeper-example

This is a test application, that shows ability of Spring framework to pull its configurations from Curator/Zookeeper.
All properties stored from Zookeeper, are refresheable at runtime without server restart.

For now, the application pulls a simple user-defined property, called "name" that may represent a name of an autor or contributor.
Also, Datasource and Hibernate configurations are also stored and pulled from Zookeeper - as example of how complicated and critical parts
of application may be treated.

To be picked up by Spring, configurations should exist under /config/testapp root znode, where testapp - is a name of given application 
(defined in /src/main/resources/application.properties file).

The application requires next configurations to be provided stored Zookeeper:

/config/testapp/name (e.g. "Vitalii")

/config/testapp/datasource/url (e.g. "jdbc:h2:tcp://localhost/~/TestDB")
/config/testapp/datasource/username
/config/testapp/datasource/password
/config/testapp/datasource/driverClassName "org.h2.Driver"
/config/testapp/datasource/pool-size 40

/config/testapp/hibernate/hbm2ddl.method (e.g. create-update)
/config/testapp/hibernate/dialect=org.hibernate.dialect.H2Dialect

/config/testapp/hibernate/show_sql
/config/testapp/hibernate/format_sql

As you understand, the application requires a Zookeeper instance to be up and running on localhost:2181 and also - H2 database, to test
correctness of Hibernate work.
