package com.techdazzler.springconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({"com.techdazzler.beans"})
public class AppConfig {
}
