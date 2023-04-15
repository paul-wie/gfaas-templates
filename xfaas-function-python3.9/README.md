# XFaaS Function Python 3.9

The function is located in <code>function.py</code>.

#### Requirements

You need to have [Docker](https://www.docker.com), otherwise the CLI will not work properly.

### About

The function is based on [Flask](https://flask.palletsprojects.com/en/2.2.x/) which is a lightweight web framework.
The business code is located in <code>xfaas_entry.py</code>. Additional requirements which are used by the function can be placed in <code>requirements.txt</code>.
[Gunicorn](https://gunicorn.org) is used to serve the function in the container/productive environment.

## Workflow

##### Build the Function locally and push it to the local docker repository
```
pip3 freeze > requirements.txt
```
```
xfaas build function.yaml
```

##### Push the Function to the configured remote repository which can be reached by the FaaS Platform

```
xfaas push function.yaml
```

##### Deploy the Function to the given FaaS Platform

```
xfaas deploy function.yaml target_faas_platform
```

## Run the function locally

This is useful for testing the function locally. The function can be reached http://127.0.0.1:8080/.

```
flask --app xfaas_entry run --port 8080
```
- Or execute <code>xfaas_entry:main</code>

## Run and build the function using docker

```
pip3 freeze > requirements.txt
```
```
docker build -t xfaas-function-python3.9 .
```
```
docker run --rm -p 8080:8080 xfaas-function-python3.9
```

## Adapter

#### Adapt your project to XFaaS

1. Execute <code>xfaas adapt --lang python3</code> inside the root folder of your python project. The following files are created:
   - <code>./function.py</code>
   - <code>./README.md</code>
   - <code>./Dockerfile</code>
   - <code>./function.yaml</code>
2. Install dependencies <code>pip install flask</code> and <code>pip install xfaas_core_python3</code>
3. Execute <code>pip3 freeze > requirements.txt</code> to update the function dependencies.
4. Open <code>function.yaml</code> and give your function a name. Also, enter a valid image me and registry, to which the function image should be pushed.
5. In <code>./function.py:call</code> you can put the code that should be executed on the function call. You can call some code from your existing project.
6. Follow the steps under **Workflow**