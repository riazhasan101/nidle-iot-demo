FROM python:3.10
WORKDIR /app
RUN pip install paho-mqtt pymongo
COPY consumer.py .
CMD ["python", "consumer.py"]
