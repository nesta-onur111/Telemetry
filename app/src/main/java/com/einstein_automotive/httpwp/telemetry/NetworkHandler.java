package com.einstein_automotive.httpwp.telemetry;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Fabian Naaß on 13.06.2016 for Telemetry.
 */
public class NetworkHandler {

    private byte[] sendData;
    int port =0;
    String ip;
    public NetworkHandler(int port, String ip){
        this.port = port;
        this.ip = ip;
    }

    public void connect(){

    }

    public void receiveData(byte[] data) {
        try {
            DatagramSocket socket = new DatagramSocket(port);
            DatagramPacket packet = new DatagramPacket(new byte[130], 130);
            socket.receive(packet);
            data = packet.getData();
            //udpReceived = String.valueOf(packet.getLength());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData(byte[] data){
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddr = InetAddress.getByName(ip);
            DatagramPacket dp;
            dp = new DatagramPacket(data, data.length, serverAddr, 12354);
            socket.send(dp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
