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
- `bind4j-security`
- `bind4j-observability`
- `bind4j-resilience`


### Build
- Requirements: Java 21, Maven 3.9+
- Code format (Google Java Format via Spotless):
  - Apply: `mvn com.diffplug.spotless:spotless-maven-plugin:2.43.0:apply -am`
- Build and test:
  - `mvn clean verify`