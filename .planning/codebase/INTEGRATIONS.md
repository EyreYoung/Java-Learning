# External Integrations

**Analysis Date:** 2026-04-16

## APIs & External Services

**Service Registry:**
- Nacos - Dubbo service discovery for `dubbo-provider` and `dubbo-consumer`
  - Integration method: Apache Dubbo Spring Boot starter
  - Config: `DUBBO_REGISTRY_ADDRESS` env var consumed in `dubbo-provider/src/main/resources/application.yml` and `dubbo-consumer/src/main/resources/application.yml`
  - Usage: provider registration + consumer lookup

**Legacy Service Discovery / Coordination:**
- ZooKeeper - used by the custom RPC experiment and ZooKeeper demos
  - Client libraries: `org.apache.zookeeper:zookeeper`, Curator, and `zkclient`
  - Code paths: `dubbo-consumer/src/main/java/org/seu/customRPC/registry/ZookeeperRegistry.java`, `java-learning-base/src/main/java/com/zookeeper/*`
  - Runtime: local `zookeeper` service in `docker-compose.yml`

**Email / SMTP:**
- SMTP server - mail sending for `email-spring-starter`
  - Client: Spring `JavaMailSender`
  - Config source: `email-spring-starter/src/main/resources/application.properties`
  - Templates: FreeMarker via `MailSenderTemplate`

**Messaging / Search Experiments:**
- Kafka - sample producer/consumer code in `java-learning-base/src/main/java/com/kafka/*`
  - Local broker: `kafka` service in `docker-compose.yml`
- Elasticsearch / OpenSearch - sample client code in `java-learning-base/src/main/java/com/elasticsearch/*` and `java-learning-base/src/main/java/com/opensearch/*`
  - No shared application flow depends on these demos today

## Data Storage

**Databases:**
- MySQL 8 - primary persistence for `spring-learning`
  - Connection: `SPRING_DATASOURCE_*` env vars or defaults from `spring-learning/src/main/resources/application.yml`
  - Client: Spring Data JPA / Hibernate
  - Schema management: `spring.jpa.hibernate.ddl-auto=update`

**Caching:**
- Redis 7 - cache backend for `spring-learning`
  - Connection: `SPRING_REDIS_HOST` and `SPRING_REDIS_PORT`
  - Client: Spring Cache abstraction + `spring-boot-starter-data-redis`
  - Usage: `@Cacheable` on `spring-learning/src/main/java/org/seu/spring/service/AppServiceImpl.java`

**Service Metadata Storage:**
- Nacos / ZooKeeper - registry state for Dubbo and custom RPC service discovery

## Authentication & Identity

**Auth Provider:**
- None
  - `spring-learning` REST APIs and `dubbo-consumer` HTTP endpoint are unauthenticated
  - No session, JWT, OAuth, or RBAC layer is present in the repo

## Monitoring & Observability

**Error Tracking:**
- None found

**Analytics:**
- None found

**Logs:**
- Application stdout / stderr only
  - `@Slf4j` is used in some classes such as `AppServiceImpl` and `MailSenderTemplate`
  - Many demo classes still use `System.out.println`

## CI/CD & Deployment

**Hosting:**
- Local Docker containers
  - `Dockerfile.provider` packages `dubbo-provider`
  - `Dockerfile.consumer` packages `dubbo-consumer`
  - `Dockerfile.spring-learning` packages `spring-learning`
  - `nginx.conf` reverse-proxies `/api/` to `spring-learning` and `/dubbo/` to `dubbo-consumer`

**CI Pipeline:**
- None found under the repo root
  - No `.github/workflows/`, Jenkinsfile, or other pipeline config was found in the inspected tree

## Environment Configuration

**Development:**
- Critical env vars are injected by `docker-compose.yml`
- SMTP config currently lives in committed `email-spring-starter/src/main/resources/application.properties`
- Dubbo registry defaults to Nacos on `localhost:8848` unless overridden

**Staging / Production:**
- No dedicated environment separation is documented
- Current setup reads as local-dev/demo oriented rather than production-ready

## Webhooks & Callbacks

**Incoming:**
- None found

**Outgoing:**
- None found

## Internal-but-Important Integrations

**Reverse Proxy Routing:**
- Nginx routes `/api/` to `spring-learning` and `/dubbo/` to `dubbo-consumer`
  - Config file: `nginx.conf`

**Spring Application Events:**
- `spring-learning` publishes account events through `ApplicationEventPublisher`
  - Producer: `spring-learning/src/main/java/org/seu/spring/controller/AccountsController.java`
  - Event types: `spring-learning/src/main/java/org/seu/spring/event/*`
  - No listeners were found in the repo at mapping time

---
*Integration audit: 2026-04-16*
*Update when adding/removing external services*
