import paho.mqtt.client as mqtt, json, time, random
BROKER, PORT, TOPIC = "rabbitmq", 1883, "iot/device/data"
client = mqtt.Client()
client.connect(BROKER, PORT, 60)
while True:
    msg = {
        "device_id": f"DEV-{random.randint(100,999)}",
        "temperature": round(random.uniform(20,35),2),
        "humidity": round(random.uniform(30,70),2),
        "timestamp": time.strftime("%Y-%m-%d %H:%M:%S")
    }
    client.publish(TOPIC, json.dumps(msg))
    print("📤 Sent:", msg)
    time.sleep(5)
