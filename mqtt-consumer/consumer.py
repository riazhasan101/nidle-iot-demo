import paho.mqtt.client as mqtt, pymongo, json
BROKER, PORT, TOPIC = "rabbitmq", 1883, "iot/device/data"
mongo = pymongo.MongoClient("mongodb://mongodb:27017/")
col = mongo["iot_data"]["readings"]
def on_message(c,u,m):
    d=json.loads(m.payload.decode()); col.insert_one(d); print("💾 Stored:",d)
client=mqtt.Client(); client.connect(BROKER,PORT,60)
client.subscribe(TOPIC); client.on_message=on_message
print("🔊 Listening..."); client.loop_forever()
