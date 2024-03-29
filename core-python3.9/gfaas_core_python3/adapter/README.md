# gFaaS Function Python 3.9

The function is based on [Flask](https://flask.palletsprojects.com/en/2.2.x/) which is a lightweight web framework.
The business code is located in <code>gfaas_entry.py</code>. Additional requirements which are used by the function can be placed in <code>requirements.txt</code>.
[Gunicorn](https://gunicorn.org) is used to serve the function in the container/productive environment.

## Run the function locally using flask command

This is useful for testing the function locally. The function can be reached http://127.0.0.1:8080/.

- <code>flask --app gfaas_entry run --port 8080</code>
- Or execute <code>gfaas_entry:main</code>

## Run and build the function using docker

- Run <code>pip3 freeze > requirements.txt</code> before building
- <code>docker build -t gfaas-function-python3.9 .</code>
- <code>docker run --rm -p 8080:8080 gfaas-function-python3.9</code>

