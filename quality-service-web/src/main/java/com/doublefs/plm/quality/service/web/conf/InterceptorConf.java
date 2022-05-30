package com.doublefs.plm.quality.service.web.conf;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.doublefs.plm.quality.service.web.handler.FrontStringConvertDate;
import com.doublefs.plm.quality.service.web.handler.SysInterceptor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * description:Interceptor配置
 *
 * @author 吴瑾 (wujin@doublefs.com)
 * @date 2021-11-16
 */
@Configuration
public class InterceptorConf extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SysInterceptor());
        super.addInterceptors(registry);
    }

    /**
     * 修改StringHttpMessageConverter默认配置
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 日期序列化配置
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // Date序列化 反序列化先不做
        javaTimeModule.addSerializer(Date.class, new JsonSerializer<>() {
            @Override
            public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString(DateUtil.formatDateTime(date));
            }
        });
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        // 将Long,BigInteger序列化的时候,转化为String,某些类里面的Long类型数据超长造成精度缺失可以在属性上使用@JsonSerialize注解
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule).registerModule(javaTimeModule);
        // 反序列化时忽略在JSON字符串中存在，而在Java中不存在的属性 不然会抛出异常
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN));
        messageConverter.setObjectMapper(objectMapper);
        List<MediaType> mediaTypes = Arrays.asList(
                new MediaType("application", "openmetrics-text"),
                MediaType.ALL,
                MediaType.APPLICATION_JSON,
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_HTML,
                MediaType.TEXT_XML,
                MediaType.APPLICATION_OCTET_STREAM);
        messageConverter.setSupportedMediaTypes(mediaTypes);
        converters.add(messageConverter);
    }


    @Override
    protected void addFormatters(FormatterRegistry registry) {
        //前端字符串类型的时间格式转换为Date
        registry.addConverter(new FrontStringConvertDate());
        super.addFormatters(registry);
    }
}
