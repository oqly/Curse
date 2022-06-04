package com.company;

public class BroadcastAddress {
    int[] broadcastAddressBytes = new int[4];
    long hostsForBroadcast;

    private void parseBroadcastAddressBytes(NetworkAddress networkAddress) {
        for (int i = 0; i < 4; i++) {
            broadcastAddressBytes[i] = networkAddress.getNetworkAddressByte(i);
        }
    }

    private void hostsForBroadcastCounting(NetworkAddress networkAddress) {
        hostsForBroadcast = networkAddress.getHosts() - 1;
    }

    private void broadcastAddressBytesAdding() {
        for (int i = 3; i >= 0; i--) {
            broadcastAddressBytes[i] += hostsForBroadcast % 256;
            hostsForBroadcast /= 256;
            if (i < 3) {
                if (broadcastAddressBytes[i + 1] > 255) {
                    broadcastAddressBytes[i + 1] -= 256;
                    broadcastAddressBytes[i] += 1;
                }
            }
        }
    }

    public int[] getBroadcastAddressBytes() {
        return broadcastAddressBytes;
    }

    public BroadcastAddress(NetworkAddress networkAddress) {
        parseBroadcastAddressBytes(networkAddress);
        hostsForBroadcastCounting(networkAddress);
        broadcastAddressBytesAdding();
    }

}
