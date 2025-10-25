# Spring Legacy Modernizer (Ade Ridwan N)

## Summary
This sample Spring Boot project demonstrates modernization of a legacy Java application:
- Exposes REST API endpoints (`POST /api/orders`, `GET /api/orders/{id}`)
- Loads configuration from `appsettings.json`
- Provides basic logging and centralized error handling
- Containerized with `Dockerfile`
- Includes a Postman collection for quick testing

> NOTE: This environment could not compile Java to produce a real JAR. The included JAR in `target/` is a placeholder. To produce a real compiled JAR locally, run:
>
> ```bash
> mvn clean package
> ```
>
> The project targets **Java 17**.

## Quick start (local)

Requirements: Java 17, Maven, Docker (optional)

1. Build:
```bash
mvn clean package -DskipTests
```

2. Run:
```bash
java -jar target/spring-legacy-modernizer-0.0.1.jar
# or: mvn spring-boot:run
```

3. Test endpoints:
- Create order:
```bash
curl -s -X POST http://localhost:8080/api/orders       -H "Content-Type: application/json"       -d '{"customerName":"Alice","items":["item-1","item-2"],"total":120.5}'
```

- Get order (replace {id} with response id):
```bash
curl http://localhost:8080/api/orders/{id}
```

## Docker

Build image:
```bash
docker build -t legacy-po-service:latest .
```

Run container:
```bash
docker run -p 8080:8080 legacy-po-service:latest
```

## Postman collection
See `spring-legacy-modernizer.postman_collection.json` for example requests (POST /api/orders and GET /api/orders/:id).

## Notes
- Do **not** include AXA/AMFS/AXA Mandiri words in the public repo name (assessment requirement).
- For production, move secrets out of `appsettings.json` into AWS Secrets Manager / SSM Parameter Store and use IAM roles.
