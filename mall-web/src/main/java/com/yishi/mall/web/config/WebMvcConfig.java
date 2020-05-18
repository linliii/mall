package com.yishi.mall.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.yishi.mall.domain.ServiceException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.text.DateFormatter;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Bean(name = "mapperObject")
  public ObjectMapper getObjectMapper() {
    ObjectMapper om = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(LocalDateTime.class,
        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
    javaTimeModule.addDeserializer(LocalDateTime.class,
        new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    javaTimeModule
        .addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
    om.registerModule(javaTimeModule);
    return om;
  }

  /**
   * 添加转换器
   */
  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new LocalDateTimeConverter());
    registry.addConverter(new LocalDateConverter());
    registry.addConverter(new LocalTimeConverter());
  }
}

@Slf4j
class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

  @Override
  public LocalDateTime convert(String src) {
    if (StringUtils.isBlank(src)) {
      return null;
    }
    try {
      if (src.length() == 10) {
        LocalDate date = LocalDate.parse(src, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0, 0);
      } else if (src.length() == 8) {
        LocalTime time = LocalTime.parse(src, DateTimeFormatter.ofPattern("HH:mm:ss"));
        return LocalDateTime.of(LocalDate.now(), time);
      } else {
        return LocalDateTime.parse(src, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      }
    } catch (Exception e) {
      log.error("时间参数转换错误", e);
      throw ServiceException.exception("时间参数转换错误");
    }
  }
}

@Slf4j
class LocalDateConverter implements Converter<String, LocalDate> {

  @Override
  public LocalDate convert(String src) {
    if (StringUtils.isBlank(src)) {
      return null;
    }
    try {
      return LocalDate.parse(src, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    } catch (Exception e) {
      log.error("时间参数转换错误", e);
      throw ServiceException.exception("时间参数转换错误");
    }
  }
}

@Slf4j
class LocalTimeConverter implements Converter<String, LocalTime> {

  @Override
  public LocalTime convert(String src) {
    if (StringUtils.isBlank(src)) {
      return null;
    }
    try {
      return LocalTime.parse(src, DateTimeFormatter.ofPattern("HH:mm:ss"));
    } catch (Exception e) {
      log.error("时间参数转换错误", e);
      throw ServiceException.exception("时间参数转换错误");
    }
  }
}