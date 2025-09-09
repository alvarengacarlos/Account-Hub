# Account Hub
Account Hub aims to centralize your login into a single account, such as Google. It is for study purpose only.

```kroki-plantuml no-transparency=false
@startuml
left to right direction

actor User
node OtherService as "Other Service"

User --> OtherService : Login with AccountHub
@enduml
```

## Quick start
Follow the [X509 Certificates](./deploy.md#x509-certificates) section and back here.

Start `api_gateway`, `registry`, `dind`, `jenkins` and  `postgresql` services:
```bash
docker compose up api_gateway registry dind jenkins postgresql -d
```

The below table explain the way to access each service available:

> **Note:** The Documentation and Account services are not available because Jenkins are not configured yet.

|       Path       |          Description          |
| :--------------: | :---------------------------: |
|        /         |     Api Gateway dashboard     |
| /jenkins-service |       Jenkins dashboard       |
|   /doc-service   | Documentation service service |
| /account-service |        Account service        |

> **Note:** The Api Gateway dashboard is secured by a Basic HTTP Authentication. The default user and password are `admin` and `adminpw`.

Now you can create the pipelines following [Jenkins](./deploy.md#jenkins) section.
