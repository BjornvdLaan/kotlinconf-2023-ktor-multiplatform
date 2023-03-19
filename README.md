# Ktor on JVM and Native (linux) in Docker and Kubernetes
This repository contains materials for my upcoming KotlinConf 2023 talk. Have a look, but don't tell your friends yet ;)

The instructions below are based on Minikube and Ddosify.

## Building the docker images
```
docker build -f Dockerfile-jvm -t ktor-jvm:v1.0.0 .
```
```
docker build -f Dockerfile-linux -t ktor-native:v1.0.0 .
```

## Copy docker image to minikube registry
```
minikube image load ktor-jvm:latest
```
```
minikube image load ktor-native:latest
```

## Exposing with Minikube
```
minikube service service-ktor-jvm --url
```
```
minikube service service-ktor-native --url
```

## Load test with Ddosify
```
 ddosify -t http://[host]:[port]/cat/{{_randomInt}} -d 5 -n 500
```