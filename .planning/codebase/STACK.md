# Technology Stack

**Analysis Date:** 2026-04-16

## Languages

**Primary:**
- Java 17 - all application and demo code, set in `pom.xml`

**Secondary:**
- YAML / `.properties` - Spring and Dubbo runtime config in `dubbo-provider/src/main/resources/application.yml`, `dubbo-consumer/src/main/resources/application.yml`, `spring-learning/src/main/resources/application.yml`, and `email-spring-starter/src/main/resources/application.properties`
- XML - legacy Spring/Dubbo config in `java-learning-base/src/main/resources/META-INF/spring/dubbo-provider.xml` and `java-learning-base/src/main/resources/service.xml`
- Dockerfile / Compose YAML - local container orchestration via `Dockerfile.*` and `docker-compose.yml`

## Runtime

**Environment:**
- JVM 17 - required by the parent build and Docker images (`eclipse-temurin:17-jre`)
- Spring Boot 2.6.6 runtime for runnable modules
- Standalone local infra for demos: MySQL, Redis, Nacos, Zookeeper, Kafka, and Nginx via `docker-compose.yml`

**Package Manager:**
- Maven reactor build from root `pom.xml`
- Root-level Maven wrapper is not present
- Module wrappers exist in `dubbo-consumer/mvnw`, `java-learning-base/mvnw`, and `spring-learning/mvnw`
- Lockfile: none (normal for Maven)

## Frameworks

**Core:**
- Spring Boot 2.6.6 - application bootstrap in `dubbo-provider`, `dubbo-consumer`, and `spring-learning`
- Spring Framework 5.3.23 - base DI / context APIs managed from root `pom.xml`
- Apache Dubbo 3.3.0 - provider/consumer RPC demo in `dubbo-provider` and `dubbo-consumer`
- Spring Data JPA - persistence in `spring-learning`
- Spring Cache + Redis - cache demo in `spring-learning`
- Spring Mail + FreeMarker - auto-configured mail starter in `email-spring-starter`
- Netty 4.1.86.Final + Curator 5.3.0 - custom RPC experiment under `dubbo-consumer/src/main/java/org/seu/customRPC`

**Testing:**
- JUnit 5.9.0 - baseline test runner from root `pom.xml`
- Spring Boot Test - context-load tests in `dubbo-consumer` and `spring-learning`
- Mockito (`mockito-core` plus legacy `mockito-all`) - ad hoc mocking demos
- PowerMock 2.0.9 - dependency present for legacy mocking scenarios

**Build/Dev:**
- `spring-boot-maven-plugin` - packaging runnable jars
- `maven-compiler-plugin` - custom include filtering in `dubbo-consumer/pom.xml`
- Docker multi-stage builds in `Dockerfile.provider`, `Dockerfile.consumer`, and `Dockerfile.spring-learning`

## Key Dependencies

**Critical:**
- `org.apache.dubbo:dubbo-spring-boot-starter` - Dubbo runtime wiring for provider/consumer modules
- `org.apache.dubbo:dubbo-nacos-spring-boot-starter` - service registry integration through Nacos
- `org.springframework.boot:spring-boot-starter-data-jpa` - account persistence in `spring-learning`
- `org.springframework.boot:spring-boot-starter-data-redis` - cache backend for `spring-learning`
- `org.springframework.boot:spring-boot-starter-mail` - email sending support for the starter module

**Infrastructure:**
- `io.netty:netty-all` - transport layer for the custom RPC experiment
- `org.apache.curator:curator-framework` / `curator-x-discovery` / `curator-recipes` - ZooKeeper-based discovery and coordination
- `mysql:mysql-connector-java` - runtime JDBC driver for `spring-learning`
- `io.springfox:springfox-boot-starter` - Swagger/OpenAPI docs with Spring MVC compatibility workaround
- `com.alibaba:transmittable-thread-local` - thread-local propagation experiments in the root dependency set

## Configuration

**Environment:**
- Central build and version management in root `pom.xml`
- Local runtime env vars injected by `docker-compose.yml` for Dubbo registry, Redis, and MySQL
- Module-local Spring configs in `src/main/resources/application.yml`
- Starter auto-configuration registration in `email-spring-starter/src/main/resources/META-INF/spring.factories`

**Build:**
- Root reactor coordinates all six modules from `pom.xml`
- Docker images build module jars independently and copy only the target artifact into runtime images
- No CI config or root developer bootstrap script is present yet

## Platform Requirements

**Development:**
- macOS / Linux / Windows should all work if JDK 17 and Maven are available
- Docker is effectively required for the documented local infra stack in `docker-compose.yml`
- Nacos, MySQL, Redis, Zookeeper, and Kafka are expected for the full demo environment

**Production / Demo Runtime:**
- Current deployment target is local Docker containers, not a cloud platform
- `dubbo-provider` exposes Dubbo on port `20880`
- `dubbo-consumer` and `spring-learning` run as HTTP services behind `nginx.conf`

---
*Stack analysis: 2026-04-16*
*Update after major dependency changes*
