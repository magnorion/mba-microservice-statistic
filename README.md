# Projeto final - Microserviços

## Docker

### Dockerfile
Arquivo principal do docker

```
FROM openjdk:8
ADD target/trabalho_final-0.0.1-SNAPSHOT.jar trabalho_final.jar
EXPOSE 8080:8080
ENTRYPOINT ["java", "-jar", "trabalho_final.jar"]
```

### Build
Montando a imagem do docker (*precisa gerar o jar primeiro!*)
```
docker build -f Dockerfile -t statistic-service .
```

Para rodar duas instancias ao mesmo tempo:

#### App 1
```
docker run -it --name app1 -p 8080:8080 statistic-service
```

#### App 2
```
docker run -it --name app2 -p 8090:8080 statistic-service
```

## Endpoints

#### App 1
> POST http://localhost:8080/transactions
```
{
  "amount": 358.75,
  "timestamp": 1556128271
}
```

> GET http://localhost:8080/
```
{
    "maximum": 358.75,
    "minimum": 358.75,
    "counter": 1,
    "sum": 358.75,
    "avger": 358.75
}
```

#### App 2
> POST http://localhost:8081/transactions
```
{
  "amount": 7349.55,
  "timestamp": 1556128326
}
```

> GET http://localhost:8081/statistics
```
{
    "maximum": 7349.55,
    "minimum": 7349.55,
    "counter": 1,
    "sum": 7349.55,
    "avger": 7349.55
}
```