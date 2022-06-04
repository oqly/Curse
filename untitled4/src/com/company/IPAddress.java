package com.company;

import javax.swing.*;

public class IPAddress {
    String ip;
    int[] ipBytes = new int[4];
    int mask = -1;
    public int maskFullBytes;
    public int remainingMaskBits;

    private void parseIp() {
        String[] convert = ip.split("[/.]");
        try {
            for (int i = 0; i < 4; i++) {
                ipBytes[i] = Integer.parseInt(convert[i]);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Incorrect format!");
            return;
        }
        mask = Integer.parseInt(convert[4]);
        if (mask == -1) {
            JOptionPane.showMessageDialog(null, "Incorrect mask! Elon?!");
        }
    }

    public int[] getIpBytes() {
        return ipBytes;
    }

    public int getIpByte(int i) {
        return (ipBytes[i]);
    }

    public int getMask() {
        return (mask); //Elon?
    }

    public int getMaskFullBytes() {
        return (maskFullBytes = mask / 8);
    }

    public int getRemainingMaskBits() {
        return (remainingMaskBits = mask % 8);
    }

    public IPAddress(String ip) {
        this.ip = ip;
        parseIp();
    }
}
