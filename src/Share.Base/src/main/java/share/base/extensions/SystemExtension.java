package share.base.extensions;


import share.base.SystemHelper;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class SystemExtension {
    public static boolean isWindowsOS() {
        return System.getProperty("os.name").toLowerCase()
                .startsWith("windows");
    }

    public static String getLocalIp() throws SocketException, UnknownHostException {
        if (isWindowsOS())
            return InetAddress.getLocalHost().getHostAddress();
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress().toString();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                ip = ipaddress;
                                System.out.println(ipaddress);
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            ip = "127.0.0.1";
            ex.printStackTrace();
        }
        return ip;
    }

    static final String SERVICE_NAME_PATH = "livelihood.service.name";
    public static String getServiceName() {
        String $serviceName = SystemHelper.getProperty(SERVICE_NAME_PATH);
        if (!StringExtension.isNullOrWhiteSpace($serviceName)) {
            return  $serviceName;
        }
        return "Certificate";
    }

}
