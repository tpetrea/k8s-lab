# k8s-lab

Spring Boot + PostgreSQL running on a local Kubernetes cluster (kind).

## What it shows
- Multi-stage Docker build (Maven → JRE)
- Deployment, Service, ConfigMap, Secret
- Readiness/liveness probes via Spring Boot Actuator
- Load balancing, scaling, self-healing, rolling update and rollback

## Run it
    kind create cluster --name lab
    cd lab-app
    docker build -t lab-app:1.0 .
    kind load docker-image lab-app:1.0 --name lab
    kubectl apply -f k8s/
    kubectl run tmp --rm -it --image=curlimages/curl --restart=Never -- curl -s http://lab-app

Note: `k8s/db-secret.yaml` holds a lab-only password. Never commit real secrets.
