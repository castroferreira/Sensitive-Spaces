#!/usr/bin/python

from Tkinter import *
import tkMessageBox
import webbrowser
import json

from pykafka import KafkaClient

client = KafkaClient(hosts="192.168.2.125:9092")

topic = client.topics[b'topic']

window = Tk();
window.title("Observer GUI")
window.geometry("480x450");


lb1 = Label(window,text="SENSITIVE SPACES");
lb1.pack();

def goToKibana():

	webbrowser.open('http://192.168.2.125:5601/')


dashboardBttn=Button(window, text="ANALYZE DASHBOARD", command=goToKibana);
dashboardBttn.pack();

lb2 = Label(window,text="Select Image To Show");
lb2.pack();

def setNoThere():

	msg_payload = json.dumps({"image": 1}).encode("utf-8")
	with topic.get_sync_producer() as producer:
   		producer.produce(msg_payload)

   	tkMessageBox.showinfo("SUCCESS", "Image changed to NoThere");


nothere=PhotoImage(file="img/nothere.png")

noThereBttn=Button(window, image=nothere, command=setNoThere, height=154, width=220);
noThereBttn.pack();
noThereBttn.place(x = 5, y = 70)

def setKeepItUp():

	msg_payload = json.dumps({"image": 2}).encode("utf-8")
	with topic.get_sync_producer() as producer:
   		producer.produce(msg_payload)

   	tkMessageBox.showinfo("SUCCESS", "Image changed to KeepItUp");


keepItUp=PhotoImage(file="img/keepitup.png")

keepItUpBttn=Button(window, image=keepItUp, command=setKeepItUp, height=154, width=220);
keepItUpBttn.pack();
keepItUpBttn.place(x = 250, y = 70)

def setSleepy():

	msg_payload = json.dumps({"image": 3}).encode("utf-8")
	with topic.get_sync_producer() as producer:
   		producer.produce(msg_payload)

   	tkMessageBox.showinfo("SUCCESS", "Image changed to Sleepy");


sleepy=PhotoImage(file="img/sleepy.png")

sleepyBttn=Button(window, image=sleepy, command=setSleepy, height=154, width=220);
sleepyBttn.pack();
sleepyBttn.place(x = 5, y = 250)

def setTooMuch():

	msg_payload = json.dumps({"image": 4}).encode("utf-8")
	with topic.get_sync_producer() as producer:
   		producer.produce(msg_payload)

   	tkMessageBox.showinfo("SUCCESS", "Image changed to TooMuch");


tooMuch=PhotoImage(file="img/toomuch.png")

toomuchBttn=Button(window, image=tooMuch, command=setTooMuch, height=154, width=220);
toomuchBttn.pack();
toomuchBttn.place(x = 250, y = 250)

def exitProgram():
	window.destroy();

exitButton=Button(window, text="EXIT", command=exitProgram);
exitButton.pack();
exitButton.place(x = 213,y = 420)


window.mainloop();

