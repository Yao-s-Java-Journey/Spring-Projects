package com.manager.controller;

import com.manager.entity.Dept;
import com.manager.entity.Result;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 请求处理类
 */
@RestController
public class DeptController {

    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result getAll() {
        // 1. 加载并读取 dept.txt 文件
        // 通过类加载器可以获取类路径下的所有资源
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("dept.txt");
        List<String> strings = IOUtils.readLines(input, "UTF-8");

        // 2. 解析并封装成集合
        List<Dept> depts = strings.stream().map(s -> {
            String[] split = s.split(",");
            int id = Integer.parseInt(split[0]);
            String name = split[1];
            LocalDateTime updateTime = LocalDateTime.parse(split[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new Dept(id, name, updateTime);
        }).collect(Collectors.toList());

        // 3. 返回 JSON 响应
        return Result.success(depts);
    }
}
