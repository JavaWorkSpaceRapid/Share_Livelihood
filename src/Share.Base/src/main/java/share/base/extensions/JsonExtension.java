package share.base.extensions;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static share.base.extensions.StringExtension.isNullOrWhiteSpace;

public class JsonExtension {

    /**
     * 将对象转换为json字符串
     *
     * @param obj 需要转换为json字符串的对象
     * @return json字符串
     */
    public static String asJsonString(Object obj) {
        return asJsonString(obj, false, false);
    }

    public static String asJsonString(Object obj, boolean formatDate) {
        return asJsonString(obj, formatDate, false);
    }

    /**
     * 转换成json字符串，并格式化时间为字符串
     *
     * @param obj
     * @param formatDate
     * @return
     */
    public static String asJsonString(Object obj, boolean formatDate, boolean pretty) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        if (formatDate)
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            if (pretty)
                return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转换成json字符串，并按照dateFormat格式化时间
     *
     * @param obj
     * @param dateFormat
     * @return
     */
    public static String asJsonString(Object obj, String dateFormat) {
        return asJsonString(obj, dateFormat, false);
    }

    /**
     * 优化的输出json字符串
     *
     * @param obj
     * @param dateFormat
     * @param pretty
     * @return
     */
    public static String asJsonString(Object obj, String dateFormat, boolean pretty) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        if (!isNullOrWhiteSpace(dateFormat))
            mapper.setDateFormat(new SimpleDateFormat(dateFormat));
        try {
            if (pretty)
                return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 字符串转换为对象
     *
     * @param jsonString 需要转换的json字符串
     * @param tClass     目标类
     * @param <T>
     * @return 转换后的对象
     */
    public static <T> T asJson(String jsonString, Class<T> tClass) {
        return asJson(jsonString, StringExtension.Empty, tClass, false);
    }

    /**
     * 字符串转换为对象，格式化时间
     *
     * @param jsonString
     * @param dateFormat
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T asJson(String jsonString, String dateFormat, Class<T> tClass) {
        return asJson(jsonString, dateFormat, tClass, false);
    }

    /**
     * 字符串转换为对象
     *
     * @param jsonString 需要转换的json字符串
     * @param tClass     目标类
     * @param pascal     是否是pascal（首字母大写的）
     * @param <T>
     * @return 转换后的
     */
    public static <T> T asJson(String jsonString, String dateFormat, Class<T> tClass, boolean pascal) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        if (!isNullOrWhiteSpace(dateFormat))
            mapper.setDateFormat(new SimpleDateFormat(dateFormat));
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if (pascal)
            mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.PascalCaseStrategy());
        try {
            return mapper.readValue(jsonString, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串转换为对象，目标对象是泛型这种类型的
     *
     * @param jsonString
     * @param tClass         目标类型
     * @param elementClasses 目标类型中的泛型
     * @param <T>
     * @return 目标对象
     */
    public static <T> T asJson(String jsonString, Class<?> tClass, Class<?>... elementClasses) {
        return asJson(jsonString, StringExtension.Empty, false, tClass, elementClasses);
    }

    /**
     * 字符串转换为对象，目标对象是泛型这种类型的,格式化时间
     *
     * @param jsonString
     * @param dateFormat
     * @param tClass
     * @param elementClasses
     * @param <T>
     * @return
     */
    public static <T> T asJson(String jsonString, String dateFormat, Class<?> tClass, Class<?>... elementClasses) {
        return asJson(jsonString, dateFormat, false, tClass, elementClasses);
    }

    /**
     * 字符串转换为对象，目标对象是泛型这种类型的
     *
     * @param jsonString
     * @param pascal         是否是pascal（首字母大写的）
     * @param tClass         目标类型
     * @param elementClasses 目标类型中的泛型
     * @param <T>
     * @return 目标对象
     */
    public static <T> T asJson(String jsonString, String dateFormat, boolean pascal, Class<?> tClass, Class<?>... elementClasses) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        if (!isNullOrWhiteSpace(dateFormat))
            mapper.setDateFormat(new SimpleDateFormat(dateFormat));
        JavaType javaType = mapper.getTypeFactory().constructParametricType(tClass, elementClasses);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if (pascal)
            mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.PascalCaseStrategy());
        try {
            return (T) mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
