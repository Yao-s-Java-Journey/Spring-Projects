package com.manager.dao;

import com.manager.entity.Dept;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.List;

/**
 * 数据访问层
 */
public class DeptDaoImpl implements DeptDao {
    public List<String>  getAll() {
        // 1. 加载并读取 dept.txt 文件
        // 通过类加载器可以获取类路径下的所有资源
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("dept.txt");
        return IOUtils.readLines(input, "UTF-8");
    }
}
