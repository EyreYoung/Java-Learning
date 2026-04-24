# Architecture

**Analysis Date:** 2026-04-16

## Pattern Overview

**Overall:** Brownfield Java learning monorepo with multiple runnable Spring/Dubbo demos plus a large sandbox module for isolated experiments.

**Key Characteristics:**
- Single Maven reactor build with six sibling modules
- Multiple execution modes: HTTP services, Dubbo RPC, custom Netty RPC, and standalone demo classes
- Container-first local integration path via `docker-compose.yml`
- Mixture of "real runnable surface" code and scratch / learning code inside the same repo

## Layers

**Workspace / Orchestration Layer:**
- Purpose: build, package, and compose the local stack
- Contains: root `pom.xml`, `docker-compose.yml`, `Dockerfile.*`, `nginx.conf`
- Depends on: all module outputs
- Used by: local dev / demo execution

**Contract Layer:**
- Purpose: define cross-service RPC contracts
- Contains: `dubbo-api/src/main/java/org/seu/dubbo/api/GreetingService.java`
- Depends on: root dependency management only
- Used by: `dubbo-provider` and `dubbo-consumer`

**Runnable App Layer:**
- Purpose: expose the main demo behaviors
- Contains:
  - `dubbo-provider` - Dubbo provider app
  - `dubbo-consumer` - HTTP facade over Dubbo plus experimental RPC code
  - `spring-learning` - REST + JPA + Redis demo app
- Depends on: Spring Boot, Dubbo, data/cache libraries, and contract layer where needed
- Used by: Docker stack, manual local runs, future brownfield stabilization work

**Starter / Shared Capability Layer:**
- Purpose: package reusable or semi-reusable utilities
- Contains: `email-spring-starter` auto-config mail support and `java-learning-base` shared/demo code
- Depends on: root dependency management plus module-specific libraries
- Used by: future integration work and isolated learning exercises

**Experiment Layer:**
- Purpose: hold non-primary learning code that explores Java topics
- Contains:
  - `dubbo-consumer/src/main/java/org/seu/customRPC/*`
  - `dubbo-consumer/src/main/java/org/seu/spi/*`
  - many topical packages under `java-learning-base/src/main/java/com/*`
- Depends on: whichever library each experiment needs
- Used by: manual study, not by a single coherent application flow

## Data Flow

**Dubbo Hello Flow:**
1. Request reaches Nginx at `/dubbo/hello`
2. Nginx proxies to `dubbo-consumer`
3. `DubboGreetingController` calls `GreetingService` through `@DubboReference`
4. Dubbo discovers `dubbo-provider` through Nacos
5. `GreetingServiceImpl` returns the greeting string
6. Consumer returns the HTTP response

**Spring REST Flow:**
1. Request reaches Nginx at `/api/...`
2. Nginx proxies to `spring-learning`
3. Controller (`AppController` or `AccountsController`) handles the request
4. Service / repository layer runs
5. Data hits MySQL or Redis depending on the endpoint
6. Response is wrapped in `ResultVO` and returned

**Custom RPC Experiment Flow:**
1. Caller creates a proxy with `RPCProxy.newInstance(...)`
2. Proxy queries ZooKeeper for service instances
3. Proxy creates `RPCClient` and `Connection`
4. Request is encoded over Netty
5. Response resolves through `NettyResponseFuture`

**State Management:**
- Persistent: MySQL accounts table
- Cached: Redis keys through Spring Cache
- Registry state: Nacos and ZooKeeper
- Many learning demos remain stateless or use only in-memory objects

## Key Abstractions

**GreetingService:**
- Purpose: shared Dubbo service contract
- Examples: `GreetingService`, `GreetingServiceImpl`, `DubboGreetingController`
- Pattern: interface-driven RPC boundary

**Account Aggregate:**
- Purpose: basic CRUD example in the Spring Boot app
- Examples: `Account`, `AccountRepository`, `AccountsController`
- Pattern: entity + JPA repository + REST controller

**MailSenderTemplate:**
- Purpose: reusable mail sending abstraction exposed by the starter
- Examples: `email-spring-starter/src/main/java/org/seu/config/MailSenderTemplate.java`
- Pattern: Spring-managed template / facade service

**Custom RPC Building Blocks:**
- Purpose: demonstrate a hand-rolled RPC stack
- Examples: `RPCProxy`, `Registry`, `RPCClient`, `RPCServer`, protocol / codec classes
- Pattern: dynamic proxy + service discovery + Netty transport

## Entry Points

**Dubbo Provider App:**
- Location: `dubbo-provider/src/main/java/org/seu/dubbo/provider/DubboProviderApplication.java`
- Triggers: `java -jar`, Maven run, or Docker container startup
- Responsibilities: boot Spring + Dubbo provider beans

**Dubbo Consumer App:**
- Location: `dubbo-consumer/src/main/java/org/seu/dubbo/consumer/DubboConsumerApplication.java`
- Triggers: `java -jar`, Maven run, or Docker container startup
- Responsibilities: expose HTTP endpoints and consume Dubbo services

**Spring Learning App:**
- Location: `spring-learning/src/main/java/org/seu/spring/SpringLearningApplication.java`
- Triggers: `java -jar`, Maven run, or Docker container startup
- Responsibilities: expose REST endpoints, JPA, caching

**Standalone Demos:**
- Location: many classes under `java-learning-base/src/main/java/com/*`
- Triggers: manual class execution from IDE or targeted Maven runs
- Responsibilities: isolated learning exercises

## Error Handling

**Strategy:** Mostly local handling with minimal global infrastructure.

**Patterns:**
- Controllers often return failure payloads instead of throwing mapped exceptions
- Services occasionally catch broad `Exception` and log it (`AppServiceImpl`, `MailSenderTemplate`)
- Custom RPC code throws runtime / checked exceptions directly
- No `@ControllerAdvice` or shared error envelope layer was found

## Cross-Cutting Concerns

**Logging:**
- `@Slf4j` in selected Spring services and helper classes
- Many demos still print directly to stdout

**Validation:**
- Very light input validation
- Entity constraints exist on JPA fields, but request DTO validation is mostly absent

**Documentation:**
- Swagger/OpenAPI enabled in `spring-learning`
- Root README is effectively empty, so code is the main source of truth today

**Configuration Drift:**
- Dubbo config exists in both modern Spring Boot YAML and older configuration classes / examples
- The repo contains both Apache Dubbo 3.x and legacy Alibaba Dubbo 2.6.x usage

---
*Architecture analysis: 2026-04-16*
*Update when major patterns change*
