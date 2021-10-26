# DataPersistance-demo
Hibernate, Entity, Not Entity --- Initialization With MySQL 
First, you need to connect to your mysql and create plant schema manually.

application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/plant
spring.datasource.username=sa
spring.datasource.password=sa1234
spring.sql.init.mode=always
spring.jpa.hibernate.ddl-auto= none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

SQL Initialization Scripts
Spring attempts to execute two scripts by default when the application starts:

First, you need to connect to your mysql and create plant schema manually.

schema.sql - Create or update the schema.
data.sql - Initialize or modify the data in your tables.
The default directory for these files is src/main/resources


Properties
Still controlled by the same property:

spring.datasource.initialization-mode=[always|embedded|never]
Make sure to disable hibernate initialization if you use these scripts to avoid conflicts:

spring.jpa.hibernate.ddl-auto=none



WITHOUT any sql scripts,use the following configuration:
spring.datasource.initialization-mode=always
spring.jpa.hibernate.ddl-auto=none
