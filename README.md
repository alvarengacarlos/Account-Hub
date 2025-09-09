# Account Hub
## Description
Account Hub aims to centralize your login into a single account, such as Google. It is for study purpose only.

## Documentation
It was built with [MkDocs](https://www.mkdocs.org/) then it is a service.

There are two ways to run the documentation service locally.

## By Docker
```bash
cd documentation-service

docker image build -f documentation-service/Dockerfile --no-cache -t documentation-service:latest .

docker container run --rm -p 9000:80 documentation-service:latest
```

## By Python

```bash
cd documentation-service

python3 -v venv .venv

source .venv/bin/activate

pip install mkdocs mkdocs-kroki-plugin

mkdocs serve
```

## License
Click [here](./LICENSE) to see the license.
