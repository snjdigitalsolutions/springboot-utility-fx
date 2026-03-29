package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class IpAddressUtility {

    private static final Pattern IPV4_PATTERN = Pattern.compile(
            "^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}" +
            "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)$"
    );

    private static final Pattern IPV6_PATTERN = Pattern.compile(
            "^(" +
            "([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}|" +                  // 1:2:3:4:5:6:7:8
            "([0-9a-fA-F]{1,4}:){1,7}:|" +                                 // 1::
            "([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|" +                // 1::8
            "([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|" +        // 1::7:8
            "([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|" +        // 1::6:7:8
            "([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|" +        // 1::5:6:7:8
            "([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|" +        // 1::4:5:6:7:8
            "[0-9a-fA-F]{1,4}:(:[0-9a-fA-F]{1,4}){1,6}|" +               // 1::3:4:5:6:7:8
            ":(:[0-9a-fA-F]{1,4}){1,7}|" +                                 // ::2:3:4:5:6:7:8
            "::|" +                                                          // ::
            "fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]+|" +              // fe80::7%eth0 (link-local)
            "::(ffff(:0{1,4})?:)?" +
            "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}" +
            "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)|" +                 // ::255.255.255.255 (IPv4-mapped)
            "([0-9a-fA-F]{1,4}:){1,4}:" +
            "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}" +
            "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)" +                  // 1:2:3:4::255.255.255.255 (IPv4-mapped)
            ")$"
    );

    public boolean isValidIpv4(String address) {
        if (address == null || address.isBlank()) {
            return false;
        }
        return IPV4_PATTERN.matcher(address.trim()).matches();
    }

    public boolean isValidIpv6(String address) {
        if (address == null || address.isBlank()) {
            return false;
        }
        return IPV6_PATTERN.matcher(address.trim()).matches();
    }

    public boolean isValidIpAddress(String address) {
        return isValidIpv4(address) || isValidIpv6(address);
    }

    public List<String> sortIpAddresses(List<String> addresses) {
        return addresses.stream()
                .sorted(this::compareIpAddresses)
                .toList();
    }

    private int compareIpAddresses(String a, String b) {
        try {
            byte[] bytesA = InetAddress.getByName(a).getAddress();
            byte[] bytesB = InetAddress.getByName(b).getAddress();
            // IPv4 (4 bytes) sorts before IPv6 (16 bytes)
            if (bytesA.length != bytesB.length) {
                return Integer.compare(bytesA.length, bytesB.length);
            }
            return Arrays.compareUnsigned(bytesA, bytesB);
        } catch (UnknownHostException e) {
            return a.compareTo(b);
        }
    }
}
