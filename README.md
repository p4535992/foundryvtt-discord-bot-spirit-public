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
- Added multi-system routing skeleton (`system.core`) with a first DnD5e module plugin.
- Added split OpenAPI specs for relay client generation:
  - `src/main/resources/META-INF/openapi/relay/v13_1/openapi-v3-foundry-rest-api-relay-core.yaml`
  - `src/main/resources/META-INF/openapi/relay/v13_1/openapi-v3-foundry-rest-api-relay-dnd5e.yaml`
  - Both files are now standalone (no `$ref` to the full relay YAML).

## Useful Maven Commands

- Project install: `./mvnw clean install`
- Generate relay OpenAPI client (core only): `./mvnw -Pgenerate-foundryvtt-rest-relay-openapi-client-java-okhttp-gson-core-generate-v13 generate-sources`
- Generate relay OpenAPI client (dnd5e only): `./mvnw -Pgenerate-foundryvtt-rest-relay-openapi-client-java-okhttp-gson-dnd5e-generate-v13 generate-sources`
- Generate split relay clients together: `./mvnw -Pgenerate-foundryvtt-rest-relay-openapi-client-java-okhttp-gson-core-generate-v13,generate-foundryvtt-rest-relay-openapi-client-java-okhttp-gson-dnd5e-generate-v13 generate-sources`
- Generate relay OpenAPI server: `./mvnw -Pgenerate-foundryvtt-rest-relay-openapi-server-java-jaxrs-spec-v13 generate-sources`
- Generate relay OpenAPI client + server together: `./mvnw -Pgenerate-foundryvtt-rest-relay-openapi-client-java-okhttp-gson-core-generate-v13,generate-foundryvtt-rest-relay-openapi-client-java-okhttp-gson-dnd5e-generate-v13,generate-foundryvtt-rest-relay-openapi-server-java-jaxrs-spec-v13 generate-sources`
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
|       |   |-- core
|       |   |   `-- invoker
|       |   |       `-- auth
|       |   `-- dnd5e
|       |       `-- invoker
|       |           `-- auth
|       |-- model
|       `-- server
|           `-- api
|-- repository
|-- service
|-- system
|   |-- core
|   |   |-- model
|   |   |-- registry
|   |   |-- routing
|   |   |-- spi
|   |   `-- ws
|   `-- dnd5e
|       |-- command
|       `-- service
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
- `com.foundryvtt.bot.spirit.openapi.relay.client.core` : generated core relay API clients (split profile).
- `com.foundryvtt.bot.spirit.openapi.relay.client.core.invoker` : generated core client infrastructure.
- `com.foundryvtt.bot.spirit.openapi.relay.client.dnd5e` : generated DnD5e relay API clients (split profile).
- `com.foundryvtt.bot.spirit.openapi.relay.client.dnd5e.invoker` : generated DnD5e client infrastructure.
- `com.foundryvtt.bot.spirit.openapi.relay.model` : shared OpenAPI models used by both client and server layers.
- `com.foundryvtt.bot.spirit.openapi.relay.server.api` : generated server-side JAX-RS interfaces from the relay OpenAPI contract.
- `com.foundryvtt.bot.spirit.repository` : data access and persistence.
- `com.foundryvtt.bot.spirit.service` : application logic and use-case orchestration.
- `com.foundryvtt.bot.spirit.system` : multi-system orchestration entry point.
- `com.foundryvtt.bot.spirit.system.core.model` : system ids, capabilities, and world routing context.
- `com.foundryvtt.bot.spirit.system.core.registry` : CDI registry for system module plugins.
- `com.foundryvtt.bot.spirit.system.core.routing` : system detection and command router services.
- `com.foundryvtt.bot.spirit.system.core.spi` : extension interfaces for pluggable system modules.
- `com.foundryvtt.bot.spirit.system.core.ws` : relay websocket integration abstractions.
- `com.foundryvtt.bot.spirit.system.dnd5e` : DnD5e module implementation.
- `com.foundryvtt.bot.spirit.system.dnd5e.command` : DnD5e command identifiers.
- `com.foundryvtt.bot.spirit.system.dnd5e.service` : DnD5e relay service facade.
- `com.foundryvtt.bot.spirit.util` : cross-cutting utilities and technical helpers.
- `com.foundryvtt.bot.spirit.web` : web layer and endpoint exposure.

Note: the previous split between `...relay.client.model` and `...relay.model` was removed to keep a single shared model package.

# LICENSES DETAILS

## Documentation Policy

- Keep this README and project-level documentation in English.
- Update this README whenever dependencies, external integrations, or major architecture decisions change.
- Keep the `Project License Files` section current when adding/removing dependencies.

## Project License Files

This section tracks licenses for:
- this repository,
- external runtime platforms used by this project,
- direct Maven dependencies declared in `pom.xml`,
- selected build-time tooling.

Baseline last reviewed: **March 6, 2026**.

### Core Repository and External Platforms

| Component | Project URL | License (full name) | License URL |
|---|---|---|---|
| Foundry VTT Discord Bot Spirit (this repository) | https://github.com/p4535992/foundryvtt-discord-bot-spirit | MIT License | [LICENSE](LICENSE) |
| Foundry REST API Relay (external bridge service used by this project) | https://github.com/ThreeHats/foundryvtt-rest-api-relay | MIT License | https://github.com/ThreeHats/foundryvtt-rest-api-relay/blob/main/LICENSE |
| Foundry Virtual Tabletop (upstream platform) | https://foundryvtt.com/ | Foundry Virtual Tabletop End User License Agreement | https://foundryvtt.com/article/license |
| PostgreSQL Database Server (runtime dependency via Quarkus datasource) | https://www.postgresql.org/ | PostgreSQL License | https://www.postgresql.org/about/licence/ |
| PostgreSQL JDBC Driver (`org.postgresql:postgresql:42.7.9`, transitive) | https://github.com/pgjdbc/pgjdbc | BSD 2-Clause License | https://jdbc.postgresql.org/about/license.html |

### Direct Maven Dependencies (`pom.xml`)

| Dependency / Module(s) | Scope | Project URL | License (full name) | License URL |
|---|---|---|---|---|
| `io.quarkus:*` extensions: `quarkus-undertow`, `quarkus-rest`, `quarkus-rest-jackson`, `quarkus-rest-client`, `quarkus-rest-client-jackson`, `quarkus-mailer`, `quarkus-hibernate-orm-panache`, `quarkus-hibernate-validator`, `quarkus-jdbc-postgresql`, `quarkus-liquibase`, `quarkus-elytron-security`, `quarkus-oidc`, `quarkus-logging-json`, `quarkus-micrometer`, `quarkus-micrometer-registry-prometheus`, `quarkus-smallrye-health`, `quarkus-scheduler`, `quarkus-smallrye-fault-tolerance`, `quarkus-vertx`, `quarkus-reactive-routes`, `quarkus-cache`, `quarkus-smallrye-openapi`, `quarkus-swagger-ui`, `quarkus-arc`, `quarkus-junit`, `quarkus-junit-mockito`, `quarkus-jacoco` | compile/test | https://github.com/quarkusio/quarkus | Apache License, Version 2.0 | https://www.apache.org/licenses/LICENSE-2.0.txt |
| `io.quarkiverse.resteasy-problem:quarkus-resteasy-problem` | compile | https://github.com/quarkiverse/quarkus-resteasy-problem | Apache License, Version 2.0 | https://www.apache.org/licenses/LICENSE-2.0.txt |
| `org.zalando:logbook-core`, `org.zalando:logbook-jaxrs` | compile | https://github.com/zalando/logbook | MIT License | https://opensource.org/licenses/MIT |
| `com.fasterxml.jackson.module:jackson-module-jakarta-xmlbind-annotations` | compile | https://github.com/FasterXML/jackson-modules-base | Apache License, Version 2.0 | https://www.apache.org/licenses/LICENSE-2.0.txt |
| `com.google.code.gson:gson` | compile | https://github.com/google/gson | Apache License, Version 2.0 | https://www.apache.org/licenses/LICENSE-2.0.txt |
| `io.gsonfire:gson-fire` | compile | http://gsonfire.io | Apache License, Version 2.0 | https://www.apache.org/licenses/LICENSE-2.0.txt |
| `com.squareup.okhttp3:okhttp`, `com.squareup.okhttp3:logging-interceptor` | compile | https://square.github.io/okhttp/ | Apache License, Version 2.0 | https://www.apache.org/licenses/LICENSE-2.0.txt |
| `io.vertx:vertx-web-client` | compile | https://github.com/eclipse-vertx/vert.x | Apache License, Version 2.0 **or** Eclipse Public License v1.0 (as declared by upstream parent POM) | http://www.apache.org/licenses/LICENSE-2.0.txt ; http://www.eclipse.org/legal/epl-v10.html |
| `net.dv8tion:JDA` | compile | https://github.com/discord-jda/JDA | Apache License, Version 2.0 | http://www.apache.org/licenses/LICENSE-2.0.txt |
| `org.eclipse.microprofile.openapi:microprofile-openapi-api` | provided | https://github.com/eclipse/microprofile-open-api | Apache License, Version 2.0 | https://www.apache.org/licenses/LICENSE-2.0.txt |
| `org.jboss.logmanager:jboss-logmanager` | compile | https://github.com/jboss-logging/jboss-logmanager | Apache License, Version 2.0 | https://repository.jboss.org/licenses/apache-2.0.txt |
| `org.mapstruct:mapstruct`, `org.mapstruct:mapstruct-processor` | compile/provided | https://mapstruct.org/ | Apache License, Version 2.0 | http://www.apache.org/licenses/LICENSE-2.0.txt |
| `io.rest-assured:rest-assured` | test | https://rest-assured.io/ | Apache License, Version 2.0 | https://www.apache.org/licenses/LICENSE-2.0.txt |
| `org.assertj:assertj-core` | test | https://github.com/assertj/assertj | Apache License, Version 2.0 | https://www.apache.org/licenses/LICENSE-2.0.txt |
| `com.tngtech.archunit:archunit-junit5` | test | https://github.com/TNG/ArchUnit | Apache License, Version 2.0 | https://www.apache.org/licenses/LICENSE-2.0.txt |
| `org.wiremock:wiremock` | test | https://github.com/wiremock/wiremock | Apache License, Version 2.0 | https://www.apache.org/licenses/LICENSE-2.0.txt |
| `com.github.dasniko:testcontainers-keycloak` | test | https://github.com/dasniko/testcontainers-keycloak | Apache License, Version 2.0 | https://opensource.org/licenses/Apache-2.0 |

### Build and Generation Tooling

| Tool | Project URL | License (full name) | License URL |
|---|---|---|---|
| OpenAPI Generator Maven Plugin (`org.openapitools:openapi-generator-maven-plugin`) | https://github.com/OpenAPITools/openapi-generator | Apache License, Version 2.0 | https://www.apache.org/licenses/LICENSE-2.0.html |
| Apache Maven | https://maven.apache.org/ | Apache License, Version 2.0 | https://www.apache.org/licenses/LICENSE-2.0.txt |
| Node.js (required for some generation/dev workflows) | https://nodejs.org/ | MIT License | https://github.com/nodejs/node/blob/main/LICENSE |
