package share.livelihood.bussiness.infrastructure.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/12/10 20:04
 */
@Component
public class ToolUtil
{
    public String getOrderIdByTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++)
        {
            result += random.nextInt(10);
        }
        return newDate + result;
    }
}
