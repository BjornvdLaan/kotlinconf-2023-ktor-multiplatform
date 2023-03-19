
# Copy docker image to minikube registry
```
minikube image load ktor-jvm:latest
```
```
minikube image load ktor-native:latest
```

# Exposing with Minikube
```
minikube service service-ktor-jvm --url
```
```
minikube service service-ktor-native --url
```