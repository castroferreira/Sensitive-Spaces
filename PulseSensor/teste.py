import socket
import json
import time
from pulsesensor import Pulsesensor

p = Pulsesensor()
p.startAsyncBPM()

JSON_PORT = 5000
JSON_HOST = '192.168.2.125'

try:
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((JSON_HOST, JSON_PORT))
    while True:
        bpm = p.BPM
        if bpm > 0:
            data = {'message': 'BPM: %d' % bpm, 'hostname': socket.gethostname()}
            s.send(json.dumps(data))
            s.send('\n')
            print("BPM: %d" % bpm)
        else:
            print("No Heartbeat found")
        time.sleep(1)
except:
    p.stopAsyncBPM()
