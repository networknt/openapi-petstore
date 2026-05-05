# openapi-petstore sidecar Kubernetes template

This folder contains `light-deployer` templates for running
`openapi-petstore` and `http-sidecar` in the same Kubernetes pod.

The deployment follows the sidecar pattern:

- `http-sidecar` owns ingress and egress ports.
- `openapi-petstore` listens on pod-local HTTP port `8080`.
- the Kubernetes Service exposes sidecar ports `9445` and `9080`.
- the backend container is not exposed directly by the Service.

## Namespace

Create the namespace before deploying the application resources:

```sh
microk8s kubectl create namespace openapi-petstore --dry-run=client -o yaml \
  | microk8s kubectl apply -f -
```

## Images

The default image tags are:

- `networknt/openapi-petstore:2.3.4`
- `networknt/http-sidecar:2.2.1`

## Runtime identities

The sidecar and backend use separate config-server startup identities:

- sidecar: `com.networknt.petstore-lpsc-3.1.0` with `envTag: dev`
- backend: `com.networknt.petstore-3.1.0` with `envTag: dev`

Use the matching authorization token for each identity. Do not commit raw JWTs
to this repository.

## Template reference

For an in-cluster deployer, use a Git URL:

```json
{
  "repoUrl": "https://github.com/networknt/openapi-petstore.git",
  "ref": "master",
  "path": "k8s"
}
```

For a local standalone deployer started with
`LIGHT_DEPLOYER_TEMPLATE_BASE_DIR=/home/steve/workspace/openapi-petstore`, use:

```json
{
  "repoUrl": "local",
  "ref": "main",
  "path": "k8s"
}
```

## Start Deployer

cd /home/steve/workspace/light-fabric/apps/light-deployer

```
KUBECONFIG=/var/snap/microk8s/current/credentials/client.config \
LIGHT_DEPLOYER_KUBE_MODE=real \
LIGHT_DEPLOYER_TEMPLATE_BASE_DIR=/home/steve/workspace/openapi-petstore \
./run.sh

```


## Required values

- `bootstrapTruststoreBase64`: base64 content of the config-server bootstrap
  truststore
- `sidecar.lightPortalAuthorization`: bearer token for
  `com.networknt.petstore-lpsc-3.1.0`
- `api.lightPortalAuthorization`: bearer token for
  `com.networknt.petstore-3.1.0`

Example values are available in [render-request.json](render-request.json).

## Local deploy request

```sh
BOOTSTRAP_TRUSTSTORE_BASE64="$(base64 -w0 /home/steve/workspace/light-gateway/config/ai-gateway/config/bootstrap.truststore)"
SIDECAR_LIGHT_PORTAL_AUTHORIZATION="Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkFacDAyTUN1Y3J1WmZGSmJ5eUZ3dWcifQ.eyJpc3MiOiJ1cm46Y29tOm5ldHdvcmtudDpvYXV0aDI6djEiLCJhdWQiOiJ1cm46Y29tLm5ldHdvcmtudCIsImV4cCI6MjA5MzMwNzAxOSwianRpIjoiRF9peUw1VEdTQ2EyS0tfNTQ2MVN5dyIsImlhdCI6MTc3Nzk0NzAxOSwibmJmIjoxNzc3OTQ2ODk5LCJ2ZXIiOiIxLjAiLCJjaWQiOiIwMTlkZjVlNS1jNTM4LTcyNWMtOWU5Zi1mMDUwNmZkM2ViMTQiLCJzY3AiOlsicG9ydGFsLnIiLCJwb3J0YWwudyJdLCJob3N0IjoiMDE5NjRiMDUtNTUyYS03YzRiLTkxODQtNjg1N2U3ZjNkYzVmIiwic2lkIjoiY29tLm5ldHdvcmtudC5wZXRzdG9yZS1scHNjLTMuMS4wIn0.5KX9pvTQVJ0rx5wl21GfxFjRNqTtgcDmzBRxB5otjsJlo2hZlTzAr1Bou3RvbF0wbrIWj9nj1xUMCBRCh5BgTv6L4P7t3KlK3NTqezQQhs8KV6CunGKKJ1ea02GTPuQr5WJuf_sen_FPMlBtVt0D6E64dI9IdJTHOPKmXJg9YUq3EAh-D0oUFgvZSc0Jf-3VximuK66p-3mG3gVCv5KB05Fg0xEuk8qePhUlkGOXyonaDHVMySiz5hpe0LCLWJ63USVjGJhK_SVDIxleH8fbVKRtxzYJl4Qd82ihw8i9ADJMKeZCwcBCkCxLaZMQmKp0ATfls3yZ16sZfX3g4zkKoQ"
API_LIGHT_PORTAL_AUTHORIZATION="Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkFacDAyTUN1Y3J1WmZGSmJ5eUZ3dWcifQ.eyJpc3MiOiJ1cm46Y29tOm5ldHdvcmtudDpvYXV0aDI6djEiLCJhdWQiOiJ1cm46Y29tLm5ldHdvcmtudCIsImV4cCI6MjA5MzMwNjg5MCwianRpIjoiYXFDbDM4TXJSNGFBZlY5eXluU2Z4dyIsImlhdCI6MTc3Nzk0Njg5MCwibmJmIjoxNzc3OTQ2NzcwLCJ2ZXIiOiIxLjAiLCJjaWQiOiIwMTlkZjVlMy0zNGQ0LTc0MGEtYTBjZC1lNDVlMDlhNzk0ZWQiLCJzY3AiOlsicG9ydGFsLnIiLCJwb3J0YWwudyJdLCJob3N0IjoiMDE5NjRiMDUtNTUyYS03YzRiLTkxODQtNjg1N2U3ZjNkYzVmIiwic2lkIjoiY29tLm5ldHdvcmtudC5wZXRzdG9yZS0zLjEuMCJ9.qHlReDgtSJLCuzmam7k4nHaDk544aUWoqosMeqlcITScbNDjCP3JGVDZHp19VOp_YZxxRTFDd3GHa_NM_HNwUyuhPVnsidsu6ACE62hG7aNSBEiT1i28k2kNOSm-P_jEy7QwX4mC93dnC4R8LRsnQIZq_mEBp1elgm0bF4UU8CCQfNa0qOEzqQX0TL0-pola-xtQ2EJmIAvhPss0VEXzQi1r6X4WP-BAwuXke4BVPLMqqbzygopE3aPY6h2lODx59W7N2eE8kd0N7PdLrKPQEly_I3ntuOAC9DsYDOcKBcr6Y8dyqyglwBy6dH0vKuM2XEHC_j-vCF2_hruIWZyxFg"

curl -sS http://127.0.0.1:8437/deployments \
  -H 'content-type: application/json' \
  -d "$(jq -n \
    --arg bootstrap "$BOOTSTRAP_TRUSTSTORE_BASE64" \
    --arg sidecarAuth "$SIDECAR_LIGHT_PORTAL_AUTHORIZATION" \
    --arg apiAuth "$API_LIGHT_PORTAL_AUTHORIZATION" \
    '{
      hostId: "01964b05-552a-7c4b-9184-6857e7f3dc5f",
      instanceId: "openapi-petstore-dev",
      environment: "dev",
      clusterId: "local",
      namespace: "openapi-petstore",
      action: "deploy",
      values: {
        namespace: "openapi-petstore",
        name: "openapi-petstore",
        sidecar: {
          image: {
            repository: "networknt/http-sidecar",
            tag: "2.2.1-20260505-1",
            pullPolicy: "IfNotPresent"
          },
          startup: {
            host: "dev.lightapi.net",
            serviceId: "com.networknt.petstore-lpsc-3.1.0",
            envTag: "dev"
          },
          lightPortalAuthorization: $sidecarAuth
        },
        api: {
          image: {
            repository: "networknt/openapi-petstore",
            tag: "2.3.4-20260505-1",
            pullPolicy: "IfNotPresent"
          },
          startup: {
            host: "dev.lightapi.net",
            serviceId: "com.networknt.petstore-3.1.0",
            envTag: "dev"
          },
          lightPortalAuthorization: $apiAuth
        },
        service: {
          name: "openapi-petstore",
          type: "ClusterIP",
          httpsPort: 9445,
          httpPort: 9080
        },
        configServer: {
          uri: "https://192.168.5.85:8435",
          verifyHostName: false
        },
        lightEnv: "dev",
        light4jConfigPassword: "DEV",
        configServerTruststorePassword: "password",
        bootstrapTruststoreBase64: $bootstrap
      },
      template: {
        repoUrl: "local",
        ref: "main",
        path: "k8s"
      }
    }')"
```


Check the request history.

```
curl -sS http://127.0.0.1:8437/deployments \
    -H 'content-type: application/json' \
    -d '{
      "requestId": "019df66a-8386-7681-8fa9-adff01006f14",
      "hostId": "01964b05-552a-7c4b-9184-6857e7f3dc5f",
      "instanceId": "openapi-petstore",
      "environment": "dev",
      "clusterId": "local",
      "namespace": "openapi-petstore",
      "action": "status",
      "template": {
        "repoUrl": "local",
        "ref": "main",
        "path": "k8s/openapi-petstore"
      }
    }' | jq .

```


## Validation

```sh
microk8s kubectl -n openapi-petstore get deploy,svc,pod
microk8s kubectl -n openapi-petstore rollout status deploy/openapi-petstore
microk8s kubectl -n openapi-petstore logs deploy/openapi-petstore -c http-sidecar
microk8s kubectl -n openapi-petstore logs deploy/openapi-petstore -c openapi-petstore
```

Port-forward the sidecar Service:

```sh
microk8s kubectl -n openapi-petstore port-forward svc/openapi-petstore 9445:9445 9080:9080
curl -k https://127.0.0.1:9445/health
curl -k https://127.0.0.1:9445/v1/pets
curl http://127.0.0.1:9080/health
```

To clean up and redeploy. 

```
kubectl -n openapi-petstore delete deploy,svc,cm,secret \
-l app.kubernetes.io/managed-by=light-deployer
```

Access pod shell. 

```
kubectl exec -it ai-microgateway-d7b864586-f9kgr -n openapi-petstore -- /bin/sh
```

