# Foundry VTT Discord Bot Spirit

This application is based on Quarkus and includes Maven/OpenAPI tooling for code generation.

## Project Structure

Node is required for generation tasks and recommended for development. `package.json` is always generated to improve developer experience with prettier, commit hooks, scripts, and related tooling.

The project root contains configuration files for tools such as git, prettier, eslint, husky, and others.

The `/src/*` tree follows the default Quarkus structure.

- `.yo-rc.json` - Yeoman configuration file
  The generator configuration is stored in this file and includes project options.
- `.yo-resolve` (optional) - Yeoman conflict resolver
  It allows a specific action when conflicts occur, skipping prompts for files matching a pattern. Each line must follow `[pattern] [action]`, where pattern is a [Minimatch](https://github.com/isaacs/minimatch#minimatch) and action is `skip` (default if omitted) or `force`. Lines starting with `#` are comments and ignored.
- `/src/main/docker` - Docker configuration for the application and dependent services

## Recent Updates

- Removed OpenRewrite references (`rewrite-maven-plugin` and `src/rewrite` folder).
- Aligned Checkstyle and Spotless (line length 100, consistent import ordering).
- Excluded generated OpenAPI sources from formatting/style checks to avoid churn on each regeneration.
- Consolidated OpenAPI relay client namespace to `com.foundryvtt.bot.spirit.openapi.relay.client`.
- Build aligned to Java 21 (wrapper and project properties).

## Useful Maven Commands

- Project install: `./mvnw clean install`
- Generate relay OpenAPI client: `./mvnw -Pgenerate-foundryvtt-rest-relay-openapi-client-java-okhttp-jackson-generate generate-sources`
- Generate relay OpenAPI server: `./mvnw -Pgenerate-foundryvtt-rest-relay-openapi-server-java-jaxrs-spec generate-sources`
- Generate relay OpenAPI client + server together: `./mvnw -Pgenerate-foundryvtt-rest-relay-openapi-client-java-okhttp-jackson-generate,generate-foundryvtt-rest-relay-openapi-server-java-jaxrs-spec generate-sources`
- Start Quarkus in dev mode: `./mvnw quarkus:dev`

## Java Package Schema (Current State)

Below are the actual packages under `com.foundryvtt.bot.spirit` in `src/main/java`.

```text
com.foundryvtt.bot.spirit
|-- config
|-- dto
|-- entity
|-- exception
|-- mapper
|-- openapi
|   `-- relay
|       |-- client
|       |   |-- api
|       |   |-- invoker
|       |   |   `-- auth
|       |-- model
|       `-- server
|           `-- api
|-- repository
|-- service
|-- util
`-- web
```

## Package Details `com.foundryvtt.bot.spirit`

List of actual packages and their purpose (style aligned with reference document `COMMON_PACKAGES.md`):

- `com.foundryvtt.bot.spirit.config` : Quarkus application configuration and technical bootstrap.
- `com.foundryvtt.bot.spirit.dto` : application DTOs for data exchange between layers.
- `com.foundryvtt.bot.spirit.entity` : domain/persistence entities.
- `com.foundryvtt.bot.spirit.exception` : application exceptions and error handling.
- `com.foundryvtt.bot.spirit.mapper` : mapping between entities, DTOs, and interface models.
- `com.foundryvtt.bot.spirit.openapi.relay.client.api` : generated OpenAPI client APIs for the Foundry relay.
- `com.foundryvtt.bot.spirit.openapi.relay.client.invoker` : generated client infrastructure (HTTP, serialization, configuration).
- `com.foundryvtt.bot.spirit.openapi.relay.client.invoker.auth` : generated client auth components (basic/bearer/api key).
- `com.foundryvtt.bot.spirit.openapi.relay.model` : shared OpenAPI models used by both client and server layers.
- `com.foundryvtt.bot.spirit.openapi.relay.server.api` : generated server-side JAX-RS interfaces from the relay OpenAPI contract.
- `com.foundryvtt.bot.spirit.repository` : data access and persistence.
- `com.foundryvtt.bot.spirit.service` : application logic and use-case orchestration.
- `com.foundryvtt.bot.spirit.util` : cross-cutting utilities and technical helpers.
- `com.foundryvtt.bot.spirit.web` : web layer and endpoint exposure.

Note: the previous split between `...relay.client.model` and `...relay.model` was removed to keep a single shared model package.
