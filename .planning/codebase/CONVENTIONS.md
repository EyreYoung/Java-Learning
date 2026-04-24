# Coding Conventions

**Analysis Date:** 2026-04-16

## Naming Patterns

**Files:**
- Java files use `PascalCase.java` matching the public class name
- Spring entry classes end in `Application` (`DubboProviderApplication`, `SpringLearningApplication`)
- Controllers, services, repositories, and models use conventional Spring naming (`AccountsController`, `AppServiceImpl`, `AccountRepository`, `Account`)
- Tests usually end in `Test` or `Tests`, though casing is inconsistent (`mockTest`)

**Functions / Methods:**
- Java methods use `camelCase`
- Accessor / mutator methods follow bean naming (`getId`, `setEmail`)
- HTTP handler methods are short verb-style names (`hello`, `listAccounts`, `deleteAccount`)

**Variables:**
- Local variables and fields use `camelCase`
- Constants use `UPPER_SNAKE_CASE` where present, especially in `dubbo-consumer/src/main/java/org/seu/customRPC/Constants.java`
- Private fields do not use an underscore prefix

**Types:**
- Classes / interfaces use `PascalCase`
- Service interfaces often end with `Service`
- DTO / result wrappers use suffixes like `Dto`, `DTO`, `VO`, or `Result`

## Code Style

**Formatting:**
- Standard Java brace style: opening brace on the same line
- 4-space indentation
- Semicolons required
- No repo-wide formatter config such as Checkstyle, Spotless, or EditorConfig was found

**Linting / Static Analysis:**
- None found in the repo
- Style consistency relies on IDE defaults and manual habits

## Import Organization

**Observed Order:**
1. Third-party framework imports (`org.springframework.*`, `org.apache.dubbo.*`, Lombok, etc.)
2. Project-local imports (`org.seu.*`, `com.*`)
3. JDK imports (`java.util.*`, `java.time.*`, etc.)

**Grouping:**
- Blank lines usually separate logical groups
- Wildcard imports were not seen in the sampled files
- Ordering is broadly conventional but not strictly enforced

## Error Handling

**Patterns:**
- Controllers often return `ResultVO.fail(...)` for expected failures instead of throwing typed exceptions
- Services and helpers may catch broad `Exception` and log it (`AppServiceImpl`, `MailSenderTemplate`)
- Experimental code throws raw `RuntimeException` / checked exceptions directly (`RPCProxy`)

**Error Types:**
- Validation and not-found cases are often handled inline in the controller
- No shared global exception mapper (`@ControllerAdvice`) was found
- Failure messages are plain strings, not structured error codes beyond the starter's `Result`

## Logging

**Framework:**
- Lombok `@Slf4j` in selected Spring classes
- No central logging abstraction or config file was found

**Patterns:**
- Log only around obvious failure points in the sampled production-ish code
- Many demo / learning classes still use `System.out.println`
- Structured logging with context objects is not an established pattern yet

## Comments

**When to Comment:**
- Header comments are common and often include author, summary, copyright, and date
- Inline comments explain framework steps or learning notes, especially in experimental code
- Mixed Chinese + English commentary is normal

**Doc Style:**
- Javadoc-like block comments are used even for internal classes
- Comments often explain intent or tutorial context rather than only API contracts

**TODO Comments:**
- One tracked TODO was found in `java-learning-base/src/main/java/com/zookeeper/ZKLock2.java`
- TODOs are not tied to issue numbers or a central tracking system

## Function Design

**Size / Shape:**
- Many methods are intentionally small and demo-oriented
- Controllers tend to keep logic inline unless a dedicated service already exists
- Overloads are used in helper abstractions such as `MailSenderTemplate`

**Parameters / Returns:**
- Methods usually take a small number of explicit parameters
- Spring MVC methods rely on annotations like `@RequestParam`, `@PathVariable`, and `@RequestBody`
- Public HTTP responses are commonly wrapped in `ResultVO`

## Module Design

**Exports / Visibility:**
- Standard Java package/class visibility rules, no barrel re-export concept
- Each Maven module owns its own `pom.xml` and local classpath concerns
- Cross-module sharing happens through Maven dependencies, not source inclusion

**Important Local Convention:**
- Runnable code lives in a small subset of packages, but scratch / experiment code can exist beside it
- `dubbo-consumer/pom.xml` narrows compilation to `org/seu/dubbo/consumer/**/*.java`, so not every Java file in that module participates in the runnable app build

---
*Convention analysis: 2026-04-16*
*Update when patterns change*
