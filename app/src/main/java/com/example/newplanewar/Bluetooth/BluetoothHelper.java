package com.example.newplanewar.Bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothHelper extends Thread{

    public interface BluetoothListenr{
        /**
         * 收到数据
         * @param data
         */
        void onReciveMessage(String data);

        /**
         * 连接到设备
         */
        void onConnectDecvice();

        /**
         * 断开连接
         */
        void onDisConnect();
    }

    private BluetoothSocket socket;
    private BluetoothDevice device;

    private InputStream inputStream;
    private OutputStream outputStream;

    private BluetoothListenr bluetoothListenr;

    private final int BUFFER_SIZE =1024;
    private volatile Boolean quitState = false;

    private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public BluetoothHelper(BluetoothDevice device){
        this.device = device;
        try {
            socket = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BluetoothHelper(BluetoothSocket socket){
        this.socket = socket;

        bluetoothListenr.onReciveMessage("test");

    }


    @Override
    public void run() {
        try {

            socket.connect();
            bluetoothListenr.onConnectDecvice();

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytes;
            String readMessage;

            while (!quitState){
                bytes = inputStream.read(buffer);
                if (bytes>0){

                    readMessage = new String(buffer,0,bytes,"GB2312");
                    bluetoothListenr.onReciveMessage(readMessage);

                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 设置监听器
     */
    public void setBluetoothListenr(BluetoothListenr bluetoothListenr){
        this.bluetoothListenr = bluetoothListenr;
    }

    /**
     * 发送消息数据
     * @param msg
     */
    public void sendMsg(final String msg){
        if (outputStream != null){
            try {
                byte[] bytes = msg.getBytes("GBK");
                outputStream.write(bytes);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 断开蓝牙连接
     */
    public void disableConnected(){
        try {
            socket.close();
            bluetoothListenr.onDisConnect();
            quitState = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
