package share.livelihood.persongroup.api.service;

/**
 * Created by Administrator on 2019/4/24.
 */
public interface ISendSmsService
{
    void sendSms(String phoneNo);

    void sendWeiChatSms(String openId, String phoneNo);
}
