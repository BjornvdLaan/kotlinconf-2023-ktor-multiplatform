# Ktor on JVM and Native (linux) in Docker and Kubernetes
This repository contains materials for my upcoming KotlinConf 2023 talk. Have a look, but don't tell your friends yet ;)

The instructions below are based on Minikube and Ddosify.

##
```
gradle assemble
```

## Building the docker images
```
docker build -f Dockerfile-jvm -t ktor-jvm:v1.0.0 .
```
```
docker build -f Dockerfile-linux -t ktor-native:v1.0.0 .
```

## Copy docker image to minikube registry
```
minikube image load ktor-jvm:v1.0.0
```
```
minikube image load ktor-native:v1.0.0
```

## Exposing with Minikube
```
minikube service service-ktor-jvm --url
```
```
minikube service service-ktor-native --url
```
```
minikube service prometheus-kube-prometheus-prometheus --url
```

## Load test with Ddosify
```
 ddosify -t http://127.0.0.1:[port]/cat/{{_randomInt}} -d 60 -n 1000 -l incremental
 ```

## PromQL (Prometheus)

```
(container_memory_working_set_bytes)* on (pod) kube_pod_info{pod=~"deployment-ktor-jvm-.+|deployment-ktor-native-.+"}
```

```
sum(
    rate(container_cpu_usage_seconds_total[5m])
) by (pod) * on (pod) kube_pod_info{pod=~"deployment-ktor-jvm-.+|deployment-ktor-native-.+"}
```