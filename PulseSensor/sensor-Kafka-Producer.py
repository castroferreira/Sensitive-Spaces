import time
import json
from pulsesensor import Pulsesensor
from pykafka import KafkaClient

p = Pulsesensor()
p.startAsyncBPM()

bootstrap_servers = '192.168.2.125:9092'
client = KafkaClient(hosts=bootstrap_servers)
topic = client.topics[b'topic']
with topic.get_producer() as producer:
	try:
	    while True:
	        bpm = p.BPM
		msg_payload = json.dumps({"BPM":bpm}).encode("utf-8")
	        print("BPM" "%d" % bpm)
		producer.produce(msg_payload)
	        time.sleep(1.2)
	except:
	    p.stopAsyncBPM()
