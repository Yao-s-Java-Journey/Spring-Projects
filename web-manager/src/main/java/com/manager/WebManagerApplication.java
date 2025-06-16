package com.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 开启 Servlet 组件扫描
public class WebManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebManagerApplication.class, args);
	}

}
