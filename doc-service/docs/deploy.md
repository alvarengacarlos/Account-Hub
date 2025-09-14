# Deploy
> **Requirements:** Linux distribution, openssl, docker, htpasswd.

To deploy the Account Hub follow the below steps.

## X509 certificates
> **Note:** This application uses `https` then you need x509 certificates. This part of the documentation will help you to generate them.

Create the `tls` directory and access it:
```bash
mkdir tls && cd tls
```

Generate a private key:
```bash
openssl genrsa 2048 > pk.pem
```

Generate the CSR for `api gateway`, `registry` and `dind`:
```bash
openssl req -new -key pk.pem -out csr.pem
```

Sign the CSR:
```bash
openssl x509 -req -days 365 -in csr.pem -signkey pk.pem -out public.crt
```

### References
- [AWS](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/configuring-https-ssl.html)

## Services
Generate a new password for Api Gateway dashboard auth:
```bash
htpasswd -B -n -b USER_NAME USER_PASSWORD
```

Replace the value `admin:$5$3F.SO1Ukcrfn5CmJ$Us8ElLe3NQ/4tS4pcUEUa.oPFLlolhXtTPSIE4Ljl37` in the `traefik-dynamic.yml` file.

Create the networks:
```bash
docker network create public_net && \
docker network create private_net
```

Start `api_gateway`, `registry`, `dind`, `jenkins` and `account_web` services:
```bash
docker compose up api_gateway registry dind jenkins account_web -d
```

> **Note**: If you want test you can up the pizza service with the command `docker compose -f docker-compose-dev.yml up pizza -d`

### Jenkins
When Jenkins finalizes your installation it will generate a password for your `admin` user. You can access this password through this command:
```bash
docker compose exec jenkins bash -c "cat /var/jenkins_home/secrets/initialAdminPassword"
```

With the `admin` password you can access Jenkins. It will be available on your `https://{your_domain_or_ip}/jenkins:80. Access it and skip the plugins configuration.

To finalize change the `admin` password.

## Pipelines
Create the `account service` pipeline with the name `account-pipeline` and the `doc service` pipeline with the name `doc-pipeline`, both of type `Pipeline`.
