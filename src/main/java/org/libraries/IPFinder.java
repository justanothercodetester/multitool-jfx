package org.libraries;

import java.net.*;
import java.util.Enumeration;

public class IPFinder {
    public static String getIPv4() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();

                // Skip inactive or loopback interfaces
                if (!ni.isUp() || ni.isLoopback()) continue;

                Enumeration<InetAddress> addresses = ni.getInetAddresses();

                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    if (addr instanceof Inet4Address) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Not found";
    }
}