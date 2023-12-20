# Use the official Python image as the base image
FROM python:3.9-slim

ENV PYTHONUNBUFFERED True

# Set the working directory in the container
ENV APP_HOME /app
WORKDIR $APP_HOME
COPY . ./

# Install any dependencies specified in requirements.txt
RUN pip install -r requirements.txt

# Specify the command to run on container start
CMD exec gunicorn --bind :$PORT --workers 1 --threads 8 --timeout 0 main:app
