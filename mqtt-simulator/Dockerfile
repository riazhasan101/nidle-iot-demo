FROM python:3.10
WORKDIR /app
RUN pip install paho-mqtt
COPY simulate.py .
CMD ["python", "simulate.py"]
