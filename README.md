# Account Hub

## Documentation
It was built with [MkDocs](https://www.mkdocs.org/) then it is a service.

There are two ways to run the doc service locally.

## By Docker
```bash
cd doc-service

docker image build -f Dockerfile.doc --no-cache -t doc-service:latest .

docker container run --rm -p 8000:80 doc-service:latest
```

## By Python

```bash
cd doc-service

python3 -v venv .venv

source .venv/bin/activate

pip install mkdocs mkdocs-kroki-plugin

mkdocs serve
```

## License
Click [here](./LICENSE) to see the license.
