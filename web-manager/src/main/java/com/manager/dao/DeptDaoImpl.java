package com.manager.dao;

import com.manager.entity.Dept;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

/**
 * 数据访问层
 */
// @Component // 程序启动时，自动创建该类对象，并交由 IOC 容器管理
@Repository // @Component 的衍生注解，标注为一个数据访问层类
public class DeptDaoImpl implements DeptDao {
    public List<String>  getAll() {
        // 1. 加载并读取 dept.txt 文件
        // 通过类加载器可以获取类路径下的所有资源
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("dept.txt");
        return IOUtils.readLines(input, "UTF-8");
    }
}
