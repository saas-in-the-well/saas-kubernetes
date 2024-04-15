#!/bin/bash

# 쿠버네티스에서 admin 비밀번호를 변경하는 스크립트

# 변경할 비밀번호
NEW_PASSWORD="1234"

# 비밀번호를 bcrypt 해시로 변환
HASHED_PASSWORD=$(htpasswd -bnBC 10 "" $NEW_PASSWORD | tr -d ':\n' | sed 's/$2y/$2a/')

# 쿠버네티스 명령어로 비밀번호 변경
kubectl -n argocd patch secret argocd-secret -p '{"stringData": {"admin.password": "'$HASHED_PASSWORD'"}}'

echo "admin 계정의 비밀번호를 $NEW_PASSWORD로 변경하였습니다."
