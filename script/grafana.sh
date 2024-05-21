#!/bin/bash
set -e

kubectl create namespace monitoring

kubectl apply -f grafana.yaml --namespace=monitoring

kubectl port-forward service/grafana 3000:3000 --namespace=monitoring

