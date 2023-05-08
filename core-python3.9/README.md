# Build the package

The build files are located in <code>./dist</code>.

```
python3 -m build
```

# Upload the package to [PyPi](https://pypi.org/account/login/)

```
python3 -m pip install --upgrade twine
```
```
python3 -m twine upload --repository pypi dist/*
```
Use ```__token__``` as username and token with ```pypi-``` prefix.

# Install the package

```
pip install gfaas_core_python3
```