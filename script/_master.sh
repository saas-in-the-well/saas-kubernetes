#!/bin/bash

# 클러스터 이름 지정
export CLUSTER_NAME="devops-saas-eks-cluster-0521"

echo "Starting the deployment process..."

./argoCD.sh
./ingress.sh
./grafana.sh

echo "All deployments completed successfully."
