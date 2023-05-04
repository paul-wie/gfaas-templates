# Use the official Python image.
FROM python:3.9-slim

# Install Python dependencies.
#COPY requirements.txt requirements.txt
#RUN pip install -r requirements.txt

# Copy local code to the container image.
ENV APP_HOME /app
WORKDIR $APP_HOME
COPY . .

RUN pip install -r requirements.txt

EXPOSE 8080
ENTRYPOINT exec python3 server.py