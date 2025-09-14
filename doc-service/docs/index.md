# Account Hub
Account Hub aims to centralize your login into a single account, such as Google does. It is for study purpose only, then does not use it in production environment, there are many secrets exposures and other issues. For production environment I recommend the [Keycloak](https://www.keycloak.org/).

It was implemented using the [OpenID Connect Specification](https://openid.net/), more specifically the [OpenID Connect Core](https://openid.net/specs/openid-connect-core-1_0.html), but not all of its features, because, as mentioned earlier, this project is for study purpose only.

Simplified diagram:

```kroki-plantuml no-transparency=false
@startuml
participant Browser as b
participant "External App" as e
participant "Account Hub" as a

b -> e : 1. Access External App GUI
e -> a : 2. Redirect to sign in
a -> a : 3. Authenticate user
a -> e : 4. Confirm with tokens
e -> b : 5. Store the tokens
b -> e : 6. Do something
@enduml
```

## Quick start - Dev Mode
Follow the [X509 Certificates](./deploy.md#x509-certificates) section and back here.

Create the networks:
```bash
docker network create public_net && \
docker network create private_net
```

Start the services:
```bash
docker compose -f docker-compose-dev.yml up -d
```

The below table explain the way to access each service available:

|       Path       |          Description          |
| :--------------: | :---------------------------: |
|        /         |     Api Gateway dashboard     |
| /jenkins-service |       Jenkins dashboard       |
|   /doc-service   | Documentation service service |
|   /account-web   |          Account web          |
| /account-service |        Account service        |

> **Note**: The Jenkins are not available in the dev mode.

Also, there is the Pizza service used to mock a third party service. You can access it through `http://localhost:8080/index.html` url. This is the start point of the flow. The `Account Hub` user email and password are `johndoe@email.com` and `John@123`.

> **Note:** The Api Gateway dashboard is secured by a Basic HTTP Authentication. The default user and password are `admin` and `adminpw`.
