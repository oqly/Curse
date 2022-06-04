package com.company;

import java.util.Arrays;
import javax.swing.*;

public class Main {

    public static void main(String[] arg) {
        boolean correct = true;
        IPAddress IP = new IPAddress(JOptionPane.showInputDialog("Input IP address in B.B.B.B/M format, where B - byte of IP address, M - mask."));
        MaskAddress maskAddress = new MaskAddress(IP);
        NetworkAddress networkAddress = new NetworkAddress(IP, maskAddress);
        BroadcastAddress broadcastAddress = new BroadcastAddress(networkAddress);
        for (int i = 0; i < 4; i++) {
            if (IP.getIpByte(i) < 0 | IP.getIpByte(i) > 255) {
                correct = false;
                JOptionPane.showMessageDialog(null, "Incorrect value in " + (i + 1) + " byte");
                return;
            }
        }
        if (IP.getMask() < -1 | IP.getMask() > 32) {
            correct = false;
            JOptionPane.showMessageDialog(null, "Incorrect mask! Elon?!");
            return;
        } else if (IP.getMask() == -1) {
            correct = false;
        }
        if (correct) {
            ArrayToString Ip = new ArrayToString("IP", IP.getIpBytes());
            ArrayToString Mask = new ArrayToString("Mask", maskAddress.getMaskBytes());
            ArrayToString Network = new ArrayToString("Network", networkAddress.getNetworkAddressBytes());
            ArrayToString Broadcast = new ArrayToString("Broadcast", broadcastAddress.getBroadcastAddressBytes());
            JOptionPane.showMessageDialog(null,
                    new String[] {Ip.nameArray,
                            Mask.nameArray,
                            Network.nameArray,
                            Broadcast.nameArray,
                            "Hosts: " + networkAddress.getHosts()},
                    "Output",
                    JOptionPane.INFORMATION_MESSAGE );
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect input!");
        }
    }
}