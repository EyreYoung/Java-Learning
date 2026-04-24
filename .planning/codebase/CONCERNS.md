# Codebase Concerns

**Analysis Date:** 2026-04-16

## Tech Debt

**`dubbo-consumer` mixed responsibilities:**
- Issue: runnable Dubbo HTTP consumer code, legacy annotation config, SPI demos, and a full custom RPC framework all live in one module
- Why: this repo doubles as a learning sandbox
- Impact: it is hard to tell which files are part of the real app path versus experiments
- Fix approach: split experiments into separate modules or clearly document / fence the compiled runtime path

**`java-learning-base` catch-all sandbox:**
- Issue: 250+ Java files span unrelated topics, libraries, and legacy integrations
- Why: the module acts as a long-lived personal notebook for Java practice
- Impact: dependency upgrades and ownership become noisy, and reusable code is hard to distinguish from disposable demos
- Fix approach: group by stable themes, archive stale demos, and separate reusable utilities from one-off exercises

## Known Bugs / Likely Breakpoints

**`AppTest` likely null-injects its controller:**
- Symptoms: `appController` will likely be `null` if the test is executed as written
- Trigger: running `spring-learning/src/test/java/org/seu/spring/AppTest.java`
- Workaround: convert it into a real Spring Boot test or construct dependencies explicitly
- Root cause: `@Resource` is used without `@SpringBootTest` or a test runner

**Account events are published but not consumed:**
- Symptoms: `AccountsController` publishes `AccountCreatedEvent`, but nothing observable happens after publish
- Trigger: calling `POST /accounts`
- Workaround: treat events as placeholders only
- Root cause: no `@EventListener` or `ApplicationListener` implementation was found for the account events

**Custom RPC test surface is mostly unverified:**
- Symptoms: the custom RPC package can drift without detection
- Trigger: refactoring any of `org/seu/customRPC/*`
- Workaround: manual review and selective local runs
- Root cause: `RPCTest` is empty and there is no real regression suite around the Netty/ZooKeeper flow

## Security Considerations

**Committed local credentials / defaults:**
- Risk: local database and SMTP defaults live in committed config files
- Current mitigation: none beyond "this looks like a demo repo"
- Recommendations: move secrets to env files, sanitize starter defaults, and avoid committed real sender addresses

**Unauthenticated HTTP endpoints:**
- Risk: `/accounts`, `/app`, `/str`, and `/dubbo/hello` are open by default
- Current mitigation: local-only deployment assumption
- Recommendations: if the repo ever leaves local-dev use, add auth and request validation first

**Starter mail validation uses internal JDK API:**
- Risk: `MailSenderTemplate` depends on `com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil`
- Current mitigation: none
- Recommendations: replace with `Pattern`, Jakarta Mail validation, or Apache Commons Validator

## Performance Bottlenecks

**`AppServiceImpl.app()` is intentionally slow:**
- Problem: method sleeps for 3 seconds before returning
- Measurement: hard-coded `Thread.sleep(3000)` in `spring-learning/src/main/java/org/seu/spring/service/AppServiceImpl.java`
- Cause: likely a cache demonstration
- Improvement path: keep it only for demos and document that the cache should eliminate repeat latency

**Custom RPC creates a client per call:**
- Problem: `RPCProxy.remoteCall(...)` constructs a new `RPCClient` and connection every invocation
- Measurement: no benchmark in repo, but the code path is per-request object creation + connect
- Cause: demo simplicity
- Improvement path: add connection pooling / reuse before treating the stack as anything beyond an experiment

## Fragile Areas

**Dubbo configuration surface:**
- Why fragile: modern YAML config, old annotation config, and registry assumptions coexist
- Common failures: wrong registry target, confusion between Nacos and ZooKeeper, port mismatches during local runs
- Safe modification: decide on one supported Dubbo path first, then quarantine the others
- Test coverage: only consumer context-load coverage exists

**`dubbo-consumer/pom.xml` compile includes:**
- Why fragile: only `org/seu/dubbo/consumer/**/*.java` is explicitly included for compilation
- Common failures: developers edit nearby files assuming they are part of the built app when they are not
- Safe modification: either remove the include filter or document it loudly
- Test coverage: no test protects this boundary

**Spring learning data flow:**
- Why fragile: controller, repository, cache, and event concerns are wired directly with minimal safety rails
- Common failures: startup issues from datasource/cache config, silent event no-ops, Swagger compatibility drift
- Safe modification: add focused smoke tests before refactoring
- Test coverage: mostly context-load only

## Scaling Limits

**Local single-node stack:**
- Current capacity: single-container MySQL, Redis, Kafka, Nacos, ZooKeeper, Nginx
- Limit: suitable for local demos only
- Symptoms at limit: startup contention, stateful debugging pain, no redundancy
- Scaling path: externalize infra or strip the stack down to only the services each module actually needs

## Dependencies at Risk

**Springfox 3.0.0:**
- Risk: known compatibility friction with newer Spring Boot / Spring MVC versions
- Impact: API docs may be one of the first things to break during upgrades
- Migration plan: move to `springdoc-openapi` when touching Swagger support

**`mockito-all` 1.10.19:**
- Risk: very old duplicate Mockito dependency beside `mockito-core`
- Impact: confusing test classpath and harder modern JUnit upgrades
- Migration plan: remove `mockito-all`, keep one modern Mockito dependency

**Alibaba Dubbo 2.6.12 in `java-learning-base`:**
- Risk: legacy branch coexists with Apache Dubbo 3.3.0 elsewhere in the repo
- Impact: conceptual and dependency confusion during Dubbo work
- Migration plan: isolate old examples or migrate them to Apache Dubbo APIs

## Missing Critical Features

**Root developer guide:**
- Problem: `README.md` only contains the project title
- Current workaround: read source code and docker files directly
- Blocks: fast onboarding and repeatable demo verification
- Implementation complexity: low

**Smoke-level verification for runnable modules:**
- Problem: provider, starter, and most integration paths are effectively untested
- Current workaround: manual local runs
- Blocks: confident refactoring
- Implementation complexity: medium

## Test Coverage Gaps

**Dubbo provider + email starter:**
- What's not tested: primary runnable paths and starter wiring
- Risk: packaging or runtime regressions go unnoticed
- Priority: High
- Difficulty to test: Moderate

**Custom RPC experiment:**
- What's not tested: protocol, registry, transport, and proxy integration
- Risk: any change can break the demo silently
- Priority: High
- Difficulty to test: High

**Most of `java-learning-base`:**
- What's not tested: the majority of example classes
- Risk: examples drift or stop compiling when dependencies move
- Priority: Medium
- Difficulty to test: Medium to High

---
*Concerns audit: 2026-04-16*
*Update as issues are fixed or new ones are discovered*
