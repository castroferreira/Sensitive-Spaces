/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sensitivespaces_gui;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author almis
 */
public class Client extends javax.swing.JFrame {
    //nothere 1, keepitup2, sleepy 3, toomuch 4
    public static long bpm;
    public static long looking;
    public static long obs;
    public static int count = 0;
    

    public static void main(String args[]) throws InterruptedException {

        JSONParser parser = new JSONParser();
        //consumer properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.2.125:9092");
        //props.put("bootstrap.servers", "128.168.2.125:9092");
        props.put("group.id", "topic");
        //using auto commit
        props.put("enable.auto.commit", "true");
        //string inputs and outputs
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //kafka consumer object
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        //subscribe to topic
        consumer.subscribe(Arrays.asList("topic"));
        //infinite poll loop
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                //System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
                //System.out.printf("Key = %s,Value = %s\n", record.key(), record.value());
                
            
                String value = record.value();
                String observer = record.value();
                String pulse = record.value();
                
               

                try {
                    JSONObject obj = (JSONObject) parser.parse(value);
                    bpm = (long) obj.get("BPM");

                } catch (Exception e) {
                }
                
                try {
                    JSONObject obj = (JSONObject) parser.parse(observer);
                    obs = (long) obj.get("image");

                } catch (Exception e) {
                }

                try {
                    JSONObject ps = (JSONObject) parser.parse(pulse);
                    looking = (long) ps.get("looking");
                } catch (Exception e) {

                }

                for (int i = 0; i < 100; i++) {
                   if(obs != 0){
                     if (obs == 2) {
                        
                        KeepItUp.getObj().setVisible(true);
                        TooMuch.getObj().setVisible(false);
                        Sleepy.getObj().setVisible(false);
                        NoThere.getObj().setVisible(false);
                        obs = 0;
                        TimeUnit.SECONDS.sleep(15);
                        
                    } else if (obs == 4 ) {
                        
                        KeepItUp.getObj().setVisible(false);
                        TooMuch.getObj().setVisible(true);
                        Sleepy.getObj().setVisible(false);
                        NoThere.getObj().setVisible(false);
                        obs = 0;
                        TimeUnit.SECONDS.sleep(15);
                        
                    } else if (obs == 3) {
                        
                        KeepItUp.getObj().setVisible(false);
                        TooMuch.getObj().setVisible(false);
                        Sleepy.getObj().setVisible(true);
                        NoThere.getObj().setVisible(false);
                        obs = 0;
                        TimeUnit.SECONDS.sleep(15);

                    } else if (obs == 1 ) {
                        
                        KeepItUp.getObj().setVisible(false);
                        TooMuch.getObj().setVisible(false);
                        Sleepy.getObj().setVisible(false);
                        NoThere.getObj().setVisible(true);
                        obs = 0;
                        TimeUnit.SECONDS.sleep(15);

                    }}else{
                    if (bpm >= 60 && bpm <= 90) {
                        if(looking == 1){
                        KeepItUp.getObj().setVisible(true);
                        TooMuch.getObj().setVisible(false);
                        Sleepy.getObj().setVisible(false);
                        NoThere.getObj().setVisible(false);

                        }
                    } else if (bpm > 91 ) {
                        if(looking == 1){
                        KeepItUp.getObj().setVisible(false);
                        TooMuch.getObj().setVisible(true);
                        Sleepy.getObj().setVisible(false);
                        NoThere.getObj().setVisible(false);

                        }
                    } else if (bpm < 60 && bpm > 0 ) {
                        if(looking == 0){
                        KeepItUp.getObj().setVisible(false);
                        TooMuch.getObj().setVisible(false);
                        Sleepy.getObj().setVisible(true);
                        NoThere.getObj().setVisible(false);

                        }

                    } else if (bpm == 0 ) {
                        if(looking == 0){
                        
                        KeepItUp.getObj().setVisible(false);
                        TooMuch.getObj().setVisible(false);
                        Sleepy.getObj().setVisible(false);
                        NoThere.getObj().setVisible(true);
                        }}
                    }i++;
                    }
                }
            }
        }
    }



        