package share.livelihood.persongroup.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lycan.base.exceptions.BaseException;
import lycan.base.extensions.ObjectExtension;
import lycan.base.extensions.StringExtension;
import lycan.base.result.LiveResult;
import lycan.base.result.RtnMessage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * http请求
 */
@Component
public class HttpBasicUtils
{

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpBasicUtils.class);

    /**
     * 用户令牌
     */
    private static final String JWT = "jwt";

    private static final String TOKEN = "Token";

    /**
     * json工具
     */
    private  ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 接收的格式
     */
    private static final String ACCEPT = "Accept";

    /**
     * 数据格式
     */
    private static final String TEXTTYPE = "application/json";

    /**
     * 发送的格式
     */
    private static final String CONTENT_TYPE = "Content-Type";

    /**
     * 编码格式
     */
    private static final String ENCODE = "UTF-8";

    /**
     * 发送体的格式
     */
    private static final String ENTITYTYPE = "text/json";

    /**
     * httpget请求
     *
     * @param uri url
     * @param msg 错误信息
     * @return
     */
    public Object getHttp(String uri, String msg) {
        LOGGER.info(uri);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        LiveResult<Object> data;
        try {
            HttpGet httpGet = new HttpGet(uri);
//            httpGet.setHeader(JWT, TokenContextLocal.getContext().getToken());
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String jsonString = EntityUtils.toString(httpEntity);
            data = objectMapper.readValue(jsonString, LiveResult.class);
        } catch (IOException e) {
            LOGGER.error(msg + e.getMessage() + "url:" + uri, e);
            throw new BaseException(msg);
        } finally {
            closeHttpClient(httpClient, msg);
        }
        disposeMsg(data,msg);
        return data.getRtnData();
    }

    /**
     * httppost请求
     *
     * @param uri  url
     * @param msg  错误信息
     * @param body body
     * @return
     */
    public Object postHttp(String uri, String msg, Object body) {
        LOGGER.info(uri);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        LiveResult<Object> data;
        try {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader(ACCEPT, TEXTTYPE);
            httpPost.setHeader(CONTENT_TYPE, TEXTTYPE);
//            httpPost.setHeader(JWT, TokenContextLocal.getContext().getToken());
            String jsonStr = objectMapper.writeValueAsString(body);
            StringEntity stringEntity = new StringEntity(jsonStr, ENCODE);
            stringEntity.setContentType(ENTITYTYPE);
            stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, TEXTTYPE));
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            String jsonString = EntityUtils.toString(httpResponse.getEntity());
            data = objectMapper.readValue(jsonString, LiveResult.class);
        } catch (IOException e) {
            LOGGER.error(msg + e.getMessage() + "url:" + uri, e);
            throw new BaseException(msg);
        } finally {
            closeHttpClient(httpClient, msg);
        }
        disposeMsg(data,msg);
        return data.getRtnData();
    }

    /**
     * httpput请求
     *
     * @param uri  url
     * @param msg  错误信息
     * @param body body
     * @return
     */
    public Object putHttp(String uri, String msg, Object body) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        Object object;
        try {
            HttpPut httpPut = new HttpPut(uri);
            httpPut.setHeader(ACCEPT, TEXTTYPE);
            httpPut.setHeader(CONTENT_TYPE, TEXTTYPE);
//            httpPut.setHeader(JWT, TokenContextLocal.getContext().getToken());
            String jsonStr = objectMapper.writeValueAsString(body);
            StringEntity stringEntity = new StringEntity(jsonStr, ENCODE);
            stringEntity.setContentType(ENTITYTYPE);
            stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, TEXTTYPE));
            httpPut.setEntity(stringEntity);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPut);
            String jsonString = EntityUtils.toString(httpResponse.getEntity());
            LiveResult<Object> data = objectMapper.readValue(jsonString, LiveResult.class);
            object = data.getRtnData();
        } catch (IOException e) {
            LOGGER.error(msg + e.getMessage() + "url:" + uri, e);
            throw new BaseException(msg);
        } finally {
            closeHttpClient(httpClient, msg);
        }
        return object;
    }

    /**
     * httpdelete请求
     *
     * @param uri url
     * @param msg 错误信息
     * @return
     */
    public Object deleteHttp(String uri, String msg) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        Object object;
        try {
            HttpDelete httpDelete = new HttpDelete(uri);
//            httpDelete.setHeader(JWT, TokenContextLocal.getContext().getToken());
            CloseableHttpResponse httpResponse = httpClient.execute(httpDelete);
            String jsonString = EntityUtils.toString(httpResponse.getEntity());
            LiveResult<Object> data = objectMapper.readValue(jsonString, LiveResult.class);
            object = data.getRtnData();
        } catch (IOException e) {
            LOGGER.error(msg + e.getMessage() + "url:" + uri, e);
            throw new BaseException(msg);
        } finally {
            closeHttpClient(httpClient, msg);
        }
        return object;
    }

    /**
     * post请求，以json字符串形式 ，带有token
     *
     * @param uri        url 访问地址
     * @param tokenValue token
     * @param msg        错误时抛出的信息
     * @param map        数据
     * @return
     */
    public void postJob(String uri, String tokenValue, String msg, Map<String, String> map) {
        LOGGER.info(uri);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.addHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpPost.setHeader("Accept", "*/*");
            // 设置token
            if (!StringExtension.isNullOrWhiteSpace(tokenValue)) {
                httpPost.setHeader("Cookie", tokenValue);
            }
            List<NameValuePair> params = new ArrayList<>();
            Set<String> keys = map.keySet();
            for (String key : keys) {
                params.add(new BasicNameValuePair(key, map.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, ENCODE));
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            } else {
                LOGGER.error(msg + httpResponse.getStatusLine().getStatusCode());
                throw new BaseException(msg);
            }
        } catch (IOException e) {
            LOGGER.error(msg + e.getMessage() + "url:" + uri, e);
            throw new BaseException(msg);
        } finally {
            closeHttpClient(httpClient, msg);
        }
    }

    public String postYDSms(String jsonStr, String url, String tokenValue) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader(CONTENT_TYPE, "application/json; charset=utf-8");
        httpPost.setHeader(ACCEPT, TEXTTYPE);

        if (!StringExtension.isNullOrWhiteSpace(tokenValue)) {
            httpPost.setHeader(TOKEN, tokenValue);
        }
        httpPost.setEntity(new StringEntity(jsonStr, Charset.forName(ENCODE)));
        try (CloseableHttpResponse response = httpClient.execute(httpPost);) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity(), ENCODE);
            } else {
                throw new BaseException(String.valueOf(response.getStatusLine().getStatusCode()));
            }
        } catch (IOException e) {
            throw new BaseException(e.getMessage());
        } finally {
            closeHttpClient(httpClient, "发送短信失败");
        }
    }

    public String getSendSms(String url, Map<String, String> nameValues, String tokenValue) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        if (ObjectExtension.isNotNull(nameValues)) {
            ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> nameValue : nameValues.entrySet()) {
                pairs.add(new BasicNameValuePair(nameValue.getKey(), nameValue.getValue()));
            }
            ub.setParameters(pairs);
        }
        try {
            HttpGet httpGet = new HttpGet(ub.build());

            if (StringExtension.isNullOrWhiteSpace(tokenValue)) {
                httpGet.setHeader("Token", tokenValue);
            }
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            } else {
                LOGGER.error("发送短信失败：" + response.getStatusLine().getStatusCode());
                throw new BaseException("发送短信失败");
            }
        } catch (Exception e) {
            LOGGER.error("发送短信失败", e);
            throw new BaseException("发送短信失败");
        } finally {
            closeHttpClient(httpClient, "发送短信失败");
        }
    }

    /**
     * 关闭 httpClient
     *
     * @param httpClient httpClient
     * @param msg        错误信息
     */
    private void closeHttpClient(CloseableHttpClient httpClient, String msg) {
        try {
            httpClient.close();
        } catch (IOException e) {
            LOGGER.error(msg + e.getMessage(), e);
            throw new BaseException(msg);
        }
    }

    /**
     * 处理错误异常
     */
    private void disposeMsg(LiveResult<Object> gaeaResult, String msg){
        int resultResult = gaeaResult.getResult();
        if (resultResult != 1) {
            for (RtnMessage rtnMessage : gaeaResult.getRtnMsg()) {
                msg = msg + rtnMessage.getRtnMsg() + " ";
            }
            throw new BaseException(msg);
        }
    }

}
