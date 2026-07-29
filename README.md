# MovieCue

A movie/show recommendation web app — built with a Spring Boot (Java) backend and a REST API, using OMDb for movie data. Recommends titles based on genre, cast, and rating similarity to what you've already rated highly.

## Status
Work in progress

## Tech stack
- Java 21 (Spring Boot 3.x)
- Spring Data JPA + H2 (in-memory database)
- OMDb API for movie data
- (Frontend: TBD)

## Running locally
See `movie-recommender/` for the backend project. Requires Java 17+ and Maven.

```bash
cd movie-recommender
mvn spring-boot:run
```
