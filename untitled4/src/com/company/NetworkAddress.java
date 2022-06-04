package com.company;

public class NetworkAddress {
    int[] networkAddressBytes = new int[4];
    long hosts;

    private void parseNetworkAddress(IPAddress ip, MaskAddress maskAddress) {
        for (int i = 0; i <= 3; i++) {
            if (maskAddress.getMaskByte(i) == 255) {
                networkAddressBytes[i] = ip.getIpByte(i);
            } else if (maskAddress.getMaskByte(i) == 0) {
                networkAddressBytes[i] = 0;
            } else {
                networkAddressBytes[i] = ip.getIpByte(i);
                for (int j = 0; j < maskAddress.getMaskZeroes() % 8; j++) {
                    if (networkAddressBytes[i] % (Math.pow(2, j + 1)) != 0) {
                        networkAddressBytes[i] -= Math.pow(2, j);
                    }
                }

            }
        }
    }

    private void hostsCounting(MaskAddress maskAddress) {
        hosts = (long) Math.pow(2, maskAddress.getMaskZeroes());
    }

    public int[] getNetworkAddressBytes() {
        return networkAddressBytes;
    }

    public int getNetworkAddressByte(int i) {
        return networkAddressBytes[i];
    }

    public long getHosts() {
        return hosts;
    }

    public NetworkAddress(IPAddress ip, MaskAddress maskAddress) {
        parseNetworkAddress(ip, maskAddress);
        hostsCounting(maskAddress);
    }

}
