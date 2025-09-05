## Bind4J

Bind4J is a Java-first integration framework. The idea is simple: you write handlers; the framework applies logging, tracing, metrics, security, resilience, and idempotency in a consistent way across HTTP, messaging, data, caches, and external APIs.

### Why
- **Java-first**: no mandatory framework (Spring, Micronaut, Quarkus are optional adapters).
- **One programming model**: annotations declare intent; an interceptor pipeline does the heavy lifting.

### Modules (so far)
- `framework-bom`
- `bind4j-annotations`
- `bind4j-core`
- `bind4j-messaging-spi`


### Build
- Requirements: Java 21, Maven 3.9+
- Build and test:
  - `mvn clean verify`
- Code format (Google Java Format via Spotless):
  - Apply: `mvn com.diffplug.spotless:spotless-maven-plugin:2.43.0:apply`
  - Check runs on `verify`

### Principles
- **Uniform middleware**: logging, tracing, metrics, security, retry, idempotency.
- **Stable error shape**: `ErrorDetail` (inspired by RFC 9457, generalized).
- **Opinionated defaults**: correlation IDs, structured errors, sane retries.

