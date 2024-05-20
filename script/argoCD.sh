#!/bin/bash
set -e

# 클러스터 이름 지정
CLUSTER_NAME="devops-saas-eks-cluster-0521"

echo "Updating AWS EKS kubeconfig for cluster: $CLUSTER_NAME..."

# Update kubeconfig using the variable
aws eks update-kubeconfig --region ap-northeast-2 --name $CLUSTER_NAME

# Create ArgoCD namespace
echo "Creating ArgoCD namespace..."
kubectl create namespace argocd

# Install ArgoCD
echo "Installing ArgoCD..."
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml

# Change ArgoCD server service type to LoadBalancer
echo "Patching ArgoCD service to be accessible externally..."
kubectl patch svc argocd-server -n argocd -p '{"spec": {"type": "LoadBalancer"}}'

# Retrieve ArgoCD initial admin password
echo "Retrieving ArgoCD initial admin password..."
#생략 가능..
kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d; echo
echo "ArgoCD initial admin password retrieved."

# Check ArgoCD server service
echo "Checking ArgoCD server service..."
kubectl get svc argocd-server -n argocd

echo "ArgoCD setup complete."


# Define the patch to apply
PATCH_STRING='{"data":{"accounts.hyeongwon":"login","accounts.jaeho":"login","accounts.seongjin":"login"}}'

# Apply the patch
kubectl patch configmap argocd-cm -n argocd --type merge -p $PATCH_STRING

echo "ConfigMap 'argocd-cm' updated successfully."

# Update secret in ArgoCD namespace
kubectl patch secret argocd-secret -n argocd --type='json' -p='[
    {"op": "add", "path": "/data/accounts.hyeongwon.password", "value": "JDJiJDEyJGIwRzREWVIwSWppQ3hPZU51YWRmemVhZmo5cWJ3QXdDaC5OZHlOdXpUcWJGOHYxR2VXeGFT"},
    {"op": "add", "path": "/data/accounts.jaeho.password", "value": "JDJiJDEyJGIwRzREWVIwSWppQ3hPZU51YWRmemVhZmo5cWJ3QXdDaC5OZHlOdXpUcWJGOHYxR2VXeGFT"},
    {"op": "add", "path": "/data/accounts.seongjin.password", "value": "JDJiJDEyJGIwRzREWVIwSWppQ3hPZU51YWRmemVhZmo5cWJ3QXdDaC5OZHlOdXpUcWJGOHYxR2VXeGFT"}
]'

echo "Secret 'argocd-secret' updated successfully."


# Apply patch to ConfigMap
kubectl patch configmap argocd-rbac-cm -n argocd --type merge -p '{"data":{"policy.default":"role:admin"}}'

echo "ConfigMap 'argocd-rbac-cm' updated successfully."