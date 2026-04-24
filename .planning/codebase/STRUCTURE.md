# Codebase Structure

**Analysis Date:** 2026-04-16

## Directory Layout

```text
Java-Learning/
├── dubbo-api/             # Shared Dubbo service contract
├── dubbo-consumer/        # Dubbo HTTP facade plus custom RPC / SPI experiments
├── dubbo-provider/        # Dubbo provider application
├── email-spring-starter/  # Auto-configured mail starter
├── java-learning-base/    # Large Java topic sandbox / demo library
├── spring-learning/       # Spring Boot REST + JPA + Redis demo
├── docker-compose.yml     # Local infra + app stack
├── Dockerfile.consumer    # Container build for dubbo-consumer
├── Dockerfile.provider    # Container build for dubbo-provider
├── Dockerfile.spring-learning # Container build for spring-learning
├── nginx.conf             # Reverse proxy routing for local stack
└── pom.xml                # Parent reactor build
```

## Directory Purposes

**`dubbo-api/`:**
- Purpose: shared RPC contract module
- Contains: interface-only Java source
- Key files: `dubbo-api/src/main/java/org/seu/dubbo/api/GreetingService.java`
- Subdirectories: standard `src/main/java`

**`dubbo-provider/`:**
- Purpose: runnable Dubbo provider
- Contains: Spring Boot main class, provider service implementation, `application.yml`
- Key files: `dubbo-provider/src/main/java/org/seu/dubbo/provider/DubboProviderApplication.java`, `dubbo-provider/src/main/java/org/seu/dubbo/provider/service/GreetingServiceImpl.java`
- Subdirectories: `src/main/java`, `src/main/resources`

**`dubbo-consumer/`:**
- Purpose: runnable Dubbo consumer plus extra experiments
- Contains: Spring Boot app, HTTP controller, old annotation config, custom RPC stack, SPI demos, tests
- Key files: `dubbo-consumer/src/main/java/org/seu/dubbo/consumer/DubboConsumerApplication.java`, `dubbo-consumer/src/main/java/org/seu/dubbo/consumer/controller/DubboGreetingController.java`, `dubbo-consumer/pom.xml`
- Subdirectories: `org/seu/dubbo/consumer`, `org/seu/customRPC`, `org/seu/spi`, `src/test/java`

**`spring-learning/`:**
- Purpose: Spring Boot web/data/cache demo
- Contains: controllers, service, repository, entity, event classes, Swagger config, tests
- Key files: `spring-learning/src/main/java/org/seu/spring/SpringLearningApplication.java`, `spring-learning/src/main/java/org/seu/spring/controller/AccountsController.java`, `spring-learning/src/main/resources/application.yml`
- Subdirectories: `controller`, `service`, `repository`, `model`, `event`, `config`, `util`, `src/test/java`

**`email-spring-starter/`:**
- Purpose: reusable-ish mail sender auto-configuration
- Contains: `MailSenderTemplate`, DTO / result models, starter properties, `spring.factories`
- Key files: `email-spring-starter/src/main/java/org/seu/config/MailSenderTemplate.java`, `email-spring-starter/src/main/resources/META-INF/spring.factories`
- Subdirectories: `config`, `model`, `src/main/resources`

**`java-learning-base/`:**
- Purpose: catch-all Java learning workspace
- Contains: concurrency demos, Zookeeper, Dubbo examples, Kafka, Elasticsearch/OpenSearch, algorithms, interview problems, patterns
- Key files: `java-learning-base/src/main/java/com/Application.java`, `java-learning-base/src/main/java/com/zookeeper/ZKLock2.java`, `java-learning-base/pom.xml`
- Subdirectories: many topic folders under `com/*` plus `src/test/java/com/zookeeper`

## Key File Locations

**Entry Points:**
- `dubbo-provider/src/main/java/org/seu/dubbo/provider/DubboProviderApplication.java` - Dubbo provider startup
- `dubbo-consumer/src/main/java/org/seu/dubbo/consumer/DubboConsumerApplication.java` - Dubbo consumer startup
- `spring-learning/src/main/java/org/seu/spring/SpringLearningApplication.java` - Spring API startup
- `java-learning-base/src/main/java/com/Application.java` - one of the base module entry samples

**Configuration:**
- `pom.xml` - parent dependency / module management
- `docker-compose.yml` - infra and container composition
- `dubbo-provider/src/main/resources/application.yml` - provider registry config
- `dubbo-consumer/src/main/resources/application.yml` - consumer registry / HTTP config
- `spring-learning/src/main/resources/application.yml` - datasource, cache, Swagger config
- `email-spring-starter/src/main/resources/application.properties` - SMTP defaults

**Core Logic:**
- `dubbo-provider/src/main/java/org/seu/dubbo/provider/service/` - Dubbo service implementation
- `dubbo-consumer/src/main/java/org/seu/dubbo/consumer/controller/` - Dubbo HTTP facade
- `spring-learning/src/main/java/org/seu/spring/controller/` - REST endpoints
- `spring-learning/src/main/java/org/seu/spring/repository/` - JPA repository layer
- `email-spring-starter/src/main/java/org/seu/config/` - mail sending abstraction
- `dubbo-consumer/src/main/java/org/seu/customRPC/` - custom RPC transport / protocol / proxy

**Testing:**
- `dubbo-consumer/src/test/java/` - context load, Mockito demo, custom RPC placeholders
- `spring-learning/src/test/java/` - context load plus one direct controller test attempt
- `java-learning-base/src/test/java/com/zookeeper/` - ZooKeeper integration test

**Documentation:**
- `README.md` - currently only project title
- `dubbo-consumer/src/main/java/org/seu/customRPC/自定义RPC框架.md` - custom RPC note inside source tree
- `.planning/` - GSD planning docs after initialization

## Naming Conventions

**Files:**
- Java source uses `PascalCase.java` matching the public class name
- Test files typically sit under `src/test/java` and also use class-name style (`SpringLearningApplicationTests.java`, `ZookeeperTest.java`)
- Config files use standard Spring names (`application.yml`, `application.properties`)

**Directories:**
- Maven modules use kebab-case at the top level
- Java packages use lower-case, dot-separated domain names mapped to directories
- Topic folders in `java-learning-base` are organized by concept (`com/core`, `com/leetcode`, `com/zookeeper`, etc.)

**Special Patterns:**
- Dockerfiles are split per runnable module at repo root
- `target/` directories are build output and should stay disposable even though some are currently present in the working tree

## Where to Add New Code

**New Dubbo Feature:**
- Shared contract: `dubbo-api/src/main/java/...`
- Provider implementation: `dubbo-provider/src/main/java/...`
- Consumer HTTP facade or integration: `dubbo-consumer/src/main/java/org/seu/dubbo/consumer/...`
- Tests: `dubbo-consumer/src/test/java/...` and/or a new provider test tree

**New Spring REST Feature:**
- Controllers: `spring-learning/src/main/java/org/seu/spring/controller/`
- Persistence: `spring-learning/src/main/java/org/seu/spring/model/` and `repository/`
- Tests: `spring-learning/src/test/java/org/seu/spring/`

**New Shared / Starter Capability:**
- Starter code: `email-spring-starter/src/main/java/org/seu/...`
- Shared demos or utilities: `java-learning-base/src/main/java/com/...`

**Utilities / Experiments:**
- If it supports a runnable app, prefer the owning module instead of `java-learning-base`
- If it is a pure learning exercise, keep it in a clearly named package under `java-learning-base`

## Special Directories

**`target/`:**
- Purpose: Maven build output
- Source: generated by Maven packaging / compilation
- Committed: No, ignored by `.gitignore`, though `dubbo-api/target` and `java-learning-base/target` currently exist on disk

**`.mvn/`:**
- Purpose: Maven wrapper support
- Source: present only in some modules
- Committed: Yes

**`.planning/`:**
- Purpose: GSD planning state and brownfield map
- Source: initialized during this session
- Committed: Intended to be tracked (`commit_docs: true`)

---
*Structure analysis: 2026-04-16*
*Update when directory structure changes*
