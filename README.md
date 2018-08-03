# spring-hibernate-custom-validation
Fun with Spring, Hibernate, @Formula and other custom queries

The goal is to showcase how to do custom queries with JDBC templates + @Transient,
@Formula, or by overloading Hibernate's schema validation.

We used to Postgres to showcase a use-case of nativeQuery with "DISTINCT ON", which
is postgres-specific and hence not supported by Hibernate.

## TODO
- [x] Docker file to launch a DB on start
- [x] Define a schema, do a liquibase migration
- [x] Add seeds to the DB
- [x] Have fun with JoinFormula
- [x] Have fun with Native Queries (e.g. Postgres DISTINCT ON)
- [x] Implement a custom SchemaValidator