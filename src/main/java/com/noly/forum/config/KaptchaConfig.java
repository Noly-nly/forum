package com.noly.forum.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {

    @Bean
    public Producer kaptchaProducer() {
        // 这里的properties也可以在配置文件里写，然后读进来
        Properties properties = new Properties();
        properties.setProperty("keptcha.image.width", "100");
        properties.setProperty("keptcha.image.height", "40");
        properties.setProperty("keptcha.textproducer.font.size", "32");
        properties.setProperty("keptcha.textproducer.font.color", "black");
        properties.setProperty("keptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        properties.setProperty("keptcha.textproducer.char.length", "4");
        properties.setProperty("keptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }

}
