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
 ddosify -t http://[host]:[port]/cat/{{_randomInt}} -d 5 -n 500
```

```
label_replace(
(kube_pod_status_container_ready_time - kube_pod_start_time)* on (pod) kube_pod_info{pod=~"deployment-ktor-jvm-.+|deployment-ktor-native-.+"}, 
"type", "$1", "pod", "deployment-ktor-(.+)-[a-z0-9]+-[a-z0-9]+"
)
```

```
label_replace(
  (container_memory_working_set_bytes)* on (pod) kube_pod_info{pod=~"deployment-ktor-jvm-.+|deployment-ktor-native-.+"}, 
  "type", "$1", "pod", "deployment-ktor-(.+)-[a-z0-9]+-[a-z0-9]+"
  )
```