package com.company;

public class MaskAddress {
    int[] maskBytes = new int[4];
    int maskZeroes = 0;
    int nibble = 128;

    private void parseMask(IPAddress ip) {
        for (int i = 0; i < 4; i++) {
            if (ip.getMaskFullBytes() > i) {
                maskBytes[i] = 255;
            } else if (ip.getMaskFullBytes() < i || ip.getRemainingMaskBits() == 0) {
                maskBytes[i] = 0;
            } else {
                for (int j = 0; j < ip.getRemainingMaskBits(); j++) {
                    maskBytes[i] += nibble;
                    nibble = nibble / 2;
                }
            }
        }
    }

    private void maskZeroesCounting(IPAddress ip) {
        for (int i = 3; i >= ip.getMaskFullBytes(); i--) {
            if (maskBytes[i] == 0) {
                maskZeroes += 8;
            }
        }
        if (ip.getMask() % 8 != 0) {
            maskZeroes += 8 - ip.getRemainingMaskBits();
        }
    }

    public int getMaskZeroes() {
        return maskZeroes;
    }

    public int[] getMaskBytes() {
        return maskBytes;
    }

    public int getMaskByte(int i) {
        return (maskBytes[i]);
    }

    public MaskAddress(IPAddress ip) {
        parseMask(ip);
        maskZeroesCounting(ip);
    }
}
