package share.springboot;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2019/6/19.
 */
public class IpAddress
{
    public static String getIpAddress() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }
}
