# code-with-quarkus



This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

But in short:

Install quarkus:

```sdk install quarkus```

Create Getting Started Application

```quarkus create && cd code-with-quarkus```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

## Own notes
### Hello World and some nice features
```shell script
sdk install quarkus
```
Can also install using homebrew
```shell script
quarkus create && cd code-with-quarkus
```
start up your app
```shell script
quarkus dev
```
Your web app is now running at http://localhost:8080

Modify your code and refresh your web browser to see change

DEV UI is nice feature of quarkus: http://localhost:8080/q/dev

Continue testing, updating your code and quarkus will inform you immediately affected test is failing

Live configuration update via DEV UI > Configuration

Add dependencies via Quarkus CLI
```shell script
quarkus extension add rest-jackson jdbc-h2 hibernate-orm-panache smallrye-openapi
```
### Cloud Native Development

Consume another REST API:
```shell script
quarkus extension add rest-client-jackson
```
We only need to provide an interface, Quarkus will provide the implementation of the service using the RegisterRestClient annotation
Note that you have to use base url via configuration though

Adding resilience features

```shell script
quarkus extension add quarkus-smallrye-fault-tolerance
```
Adding api health check

```shell script
quarkus extension add quarkus-smallrye-health
```
```shell script
curl http://localhost:8080/q/health
```
Implement my own health probes
```shell script
curl http://localhost:8080/q/health/ready -v
```
```shell script
curl http://localhost:8080/q/health/live -v
```
Adding metrics using mircometer (same facade like slf4j for logging but for metrics, a common ground for metrics systems)
```shell script
quarkus extension add quarkus-micrometer-registry-prometheus
```
```shell script
curl http://localhost:8080/q/metrics
```
Implement my own metrics
```shel script
curl http://localhost:8080/q/metrics|grep "time.now"
```
Note that to see the metrics, you need to perform some requests ;)

# Try out TMDB API
https://developer.themoviedb.org/reference/account-watchlist-movies

# Try out Tiingo API
[Passing token](https://www.tiingo.com/documentation/general/connecting)
[End Of Day](https://www.tiingo.com/documentation/end-of-day)
Reference [Quarkus REST client](https://quarkus.io/guides/rest-client#path-parameters)
```
curl http:http://localhost:8080/stocks/aapl/prices?startDate=2023-6-23&endDate=2023&endDate=2023-7-23
```
should display the closing price of 23 jul 2023 (actually 21 jul due to a weekend)

# Benefit

Quarkus applications start really fast, either on GraalVM (having AOT) as a natively compile library or our de facto JVM, OpenJDK (JIT).

Quarkus applications also have a really low memory footprint and Memory footprint is money in the cloud

Tradeoff: throughput is a bit less compared to traditional stack (3000 TPS)

Live Coding

Continuous testing

Zero configuration with DevServices

A counter candidate to Spring Boot, migration to Quarkus is easy

# Compared to Spring Boot

We can store both properties that are relevant at build time and especially runtime

