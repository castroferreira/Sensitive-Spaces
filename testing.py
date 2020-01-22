from pykafka import KafkaClient
client = KafkaClient(hosts="192.168.2.125:9092")
 
topic = client.topics['my.test']
with topic.get_sync_producer() as producer:
   producer.produce("190")
