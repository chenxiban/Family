package com.cyj.resourcecenter.utils;

import com.cyj.resourcecenter.entity.FileSlice;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.UUID;

/**
 * 操作Properties文件的工具类
 *
 * @author MaShuai
 * @date 2018-12-18 注意:每个流使用完毕都要关闭掉,此类中有所省略
 * 如果流没有关闭,删除文件才会返回false
 */
@Slf4j
public class PropUtil {

    /**
     * 文件上传准备文件,属性值;属性是文件名称.文件大小[.文件最后修改时间]=值是文件的uuid唯一标志存盘存储文件名称,文件目录
     */
    private static final String FILEPREFIX = "G:\\fileUpload\\";
    private static final String FILEPATH = "fileslice.properties";
    private static ObjectMapper objectMapper = new ObjectMapper();
    private String clapath;


    /**
     * 上传之前获取(fileslice.properties)Properties文件对象
     *
     * @return
     * @throws IOException
     */
    private static Properties getProp() {
        Properties properties = new Properties();
        //它取的是classepath:下的文件
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(FILEPATH);
        try {
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            properties.load(reader);

        } catch (UnsupportedEncodingException e) {
            log.error("不支持的编码异常", e);
        } catch (IOException e) {
            log.error("io流操作异常", e);
        } finally {
        }
        return properties;
    }

    /**
     * 断点续传根据文件唯一标志获取Properties文件对象
     *
     * @param uuid 即 fid
     * @return 有文件则返回文件, 无文件则返回null
     */
    private static Properties getProp(String uuid) {
        //它取的是classepath:下的文件
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(FILEPATH);
        //uuid.properties文件不存在则会输出null
        log.info("inputStream=>" + inputStream);
        if (inputStream == null) {
            return null;
        }
        Properties properties = new Properties();
        try {
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            properties.load(reader);
        } catch (UnsupportedEncodingException e) {
            log.error("不支持的编码异常", e);
        } catch (IOException e) {
            log.error("io流操作异常", e);
        }
        return properties;
    }

    /**
     * 根据文件信息获取文件UUID
     *
     * @param fileName
     * @param totalSize
     * @return
     */
    public static String queryUUIDbyFileinfo(String fileName, Long totalSize) {
        String key = fileName + "." + totalSize;
        // 获取fileslice.properties
        Properties prop = getProp();
        // 如果取不到则返回null
        return prop.getProperty(key);
    }


    /**
     * 断点续传根据文件唯一标志获取Properties文件对象
     *
     * @param uuid 即 fid
     * @return 有文件则返回文件, 无文件则返回null
     */
    private static Properties getUuidProp(String uuid) {

        String uuidFile = uuid + ".properties";
        //它取的是classepath:下的文件
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(uuidFile);
        //uuid.properties文件不存在则会输出null
        log.info("inputStream=>" + inputStream);
        if (inputStream == null) {
            return null;
        }
        Properties properties = new Properties();
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(inputStream, "UTF-8");
            properties.load(reader);
            String fileSliceStr = properties.getProperty(uuid);
            log.info("PropUtil.getProp(String) fileSliceStr=>{}", fileSliceStr);
        } catch (UnsupportedEncodingException e) {
            log.error("不支持的编码异常", e);
        } catch (IOException e) {
            log.error("io流操作异常", e);
        } finally {
            try {
                reader.close();
                inputStream.close();
            } catch (IOException e) {
                log.error("io流操作异常", e);
            }

        }
        return properties;
    }

    /**
     * 根据文件的UUID获取文件上传的状态信息
     *
     * @param uuid
     * @return
     */
    public static FileSlice queryFileSliceByFid(String uuid) {
        log.info("根据文件的UUID获取文件上传的状态信息 uuid=>" + uuid);
        FileSlice fileSlice = null;

        /*
         * 获取uuid的文件状态信息
         */
        Properties prop = getUuidProp(uuid);
        log.info("根据文件的UUID获取{}", prop.values());
        String fileSliceStr = prop.getProperty(uuid);
        log.info("PropUtil.queryFileSliceByFid(String) fileSliceStr=>{}", fileSliceStr);
        //断点续传记录找不到.
        if (fileSliceStr == null || fileSliceStr == "") {
            return null;
        }
        try {
            fileSlice = objectMapper.readValue(fileSliceStr, FileSlice.class);
        } catch (JsonParseException e) {
            log.error("Json解析异常", e);
        } catch (JsonMappingException e) {
            log.error("Json映射异常", e);
        } catch (IOException e) {
            log.error("io流操作异常", e);
        }
        return fileSlice;
    }

    /**
     * 根据文件信息,删除文件的UUID;并删除记录在fileslice.properties文件
     *
     * @param fileName
     * @param totalSize
     * @return UUID
     */
    public static String deleteUUIDbyFileinfo(String fileName, Long totalSize) {
        //键值
        String key = fileName + "." + totalSize;
        try {
            deleteFileSliceByFileInfo(key);
        } catch (IOException e) {
            log.error("io流操作异常", e);
        }
        return key;
    }

    /**
     * 删除文件传输记录
     * 修改fileslice.properties文件
     *
     * @throws IOException
     */
    private static void deleteFileSliceByFileInfo(String key) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = PropUtil.class.getClassLoader().getResourceAsStream(FILEPATH);
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");

        String clapath = PropUtil.class.getResource("").toString();
        log.info("clapath => {}", clapath);

        String classpath = ClassLoader.getSystemResource("").toString();
        //file:/D:/Workspace8.6/FileData/WebRoot/WEB-INF/classes/
        log.info("classpath => {}", classpath);

        // file:/D:/Workspace8.6/FileData/WebRoot/WEB-INF/classes/../../
        //去掉文件路径其前面   file:/
        classpath = classpath.replaceAll("file:/", "");
        log.info("去掉文件路径其前面   file:/ classpath => {}", classpath);
        //文件的真实路径
        String realPath = classpath + FILEPATH;


        properties.load(reader);

        log.info("修改文件--------------> {}", realPath);
        File file = new File(realPath);
        OutputStream outputStream = new FileOutputStream(file);
        /*
         * UTF-8
         */
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charset.defaultCharset());

        properties.remove(key);
        properties.store(writer, "我是Properties文件头注释信息");
        reader.close();
        inputStream.close();
        writer.flush();
        writer.close();
        outputStream.close();

        log.info("fileslice.properties文件删除<<  {}  >>成功---------------------------", key);
    }

    /**
     * 根据文件信息,生成文件的UUID;并记录在fileslice.properties文件
     *
     * @param fileName
     * @param totalSize
     * @return
     */
    public static String createUUIDbyFileinfo(String fileName, Long totalSize) {
        //键值
        String key = fileName + "." + totalSize;
        //生成唯一不重复的32位uuid字符串,例如 66377a7daf914f17ba4ed9e7147a0a53
        String value = UUID.randomUUID().toString().replace("-", "");
        try {
            updateFileSlice(key, value);
        } catch (IOException e) {
            log.error("io流操作异常", e);
        }
        return value;
    }

    /**
     * 修改fileslice.properties文件
     *
     * @throws IOException
     */
    private static void updateFileSlice(String key, String value) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = PropUtil.class.getClassLoader().getResourceAsStream(FILEPATH);
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");

        String clapath = PropUtil.class.getResource("").toString();
        log.info("clapath => {}", clapath);

        String classpath = ClassLoader.getSystemResource("").toString();
        log.info("classpath => {}", classpath);

        /*
         * file:/D:/Workspace8.6/FileData/WebRoot/WEB-INF/classes/../../
         * 去掉文件路径其前面   file:/
         */
        classpath = classpath.replaceAll("file:/", "");
        log.info("去掉文件路径其前面   file:/ classpath => {}", classpath);
        //文件的真实路径
        String realPath = classpath + FILEPATH;

        properties.load(reader);

        log.info("修改文件--------------> {}", realPath);
        File file = new File(realPath);
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charset.defaultCharset());//UTF-8

        properties.put(key, value);
        properties.store(writer, "我是Properties文件头注释信息");

        writer.flush();
        writer.close();
        outputStream.close();

        log.info("fileslice.properties文件修改成功---------------------------");
    }

    /**
     * 创建文件上传状态FileSlice
     *
     * @param fileSlice
     * @return
     */
    public static FileSlice createFileSlice(FileSlice fileSlice) {
        String uuid = fileSlice.getFid();
        String value = null;
        try {
            value = objectMapper.writeValueAsString(fileSlice);
        } catch (JsonProcessingException e) {
            log.error("Json处理异常", e);
        }
        try {
            createUuidProperties(uuid, uuid, value);
        } catch (IOException e) {
            log.error("io流操作异常", e);
        }
        return fileSlice;
    }

    /**
     * 修改文件上传状态FileSlice
     *
     * @param fileSlice
     * @return
     */
    public static FileSlice updateFileSlice(FileSlice fileSlice) {
        String uuid = fileSlice.getFid();
        String value = null;
        try {
            value = objectMapper.writeValueAsString(fileSlice);
        } catch (JsonProcessingException e) {
            log.error("Json处理异常", e);
        }
        try {
            updateUuidProperties(uuid, uuid, value);
        } catch (IOException e) {
            log.error("io流操作异常", e);
        }
        return fileSlice;
    }

    /**
     * 创建uuid.properties文件
     * 文件中存储的是uuid对应的文件上传状态信息
     *
     * @throws IOException
     */
    private static void createUuidProperties(String uuid, String key, String value) throws IOException {
        String uuidFile = uuid + ".properties";
        Properties properties = new Properties();

        String clapath = PropUtil.class.getResource("").toString();
        log.info("clapath => {}", clapath);

        String classpath = ClassLoader.getSystemResource("").toString();
        log.info("classpath => {}", classpath);

        classpath = classpath.replaceAll("file:/", "");
        log.info("创建uuid.properties文件 去掉文件路径其前面   file:/ classpath => {}", classpath);
        //文件的真实路径
        String realPath = classpath + uuidFile;

        log.info("创建文件--------------> {}", realPath);

        try {
            File file = new File(realPath);
            OutputStream outputStream = new FileOutputStream(file);
            //UTF-8
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charset.defaultCharset());

            properties.put(key, value);
            properties.store(writer, "我是Properties文件头注释信息");
            writer.flush();
            writer.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info(uuid + ".properties文件创建成功---------------------------记录信息" + value);
    }


    /**
     * 修改uuid.properties文件
     * 文件中存储的是uuid对应的文件上传状态信息
     *
     * @throws IOException
     */
    private static void updateUuidProperties(String uuid, String key, String value) throws IOException {
        String uuidFile = uuid + ".properties";
        Properties properties = new Properties();
        InputStream inputStream = PropUtil.class.getClassLoader().getResourceAsStream(uuidFile);
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");

        String clapath = PropUtil.class.getResource("").toString();
        log.info("clapath => {}", clapath);

        String classpath = ClassLoader.getSystemResource("").toString();
        log.info("classpath => {}", classpath);

        classpath = classpath.replaceAll("file:/", "");
        log.info("去掉文件路径其前面   file:/ classpath => {}", classpath);
        String realPath = classpath + uuidFile;


        properties.load(reader);

        log.info("修改文件--------------> {}", realPath);
        File file = new File(realPath);
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charset.defaultCharset());

        properties.put(key, value);
        properties.store(writer, "我是Properties文件头注释信息");

        reader.close();
        writer.flush();
        writer.close();
        outputStream.close();

        log.info("fileslice.properties文件修改成功---------------------------");
    }

    /**
     * 删除uuid.properties文件
     * 文件中存储的是uuid对应的文件上传状态信息
     * 当文件上传成功时,不需要uuid.properite文件记录上传的状态信息了
     *
     * @param uuid
     */
    public static Boolean deleteFileSlice(String uuid) {
        String uuidFile = uuid + ".properties";

        String classpath = ClassLoader.getSystemResource("").toString();
        log.info("classpath => {}", classpath);

        // file:/D:/Workspac
        classpath = classpath.replaceAll("file:/", "");
        log.info("去掉文件路径其前面   file:/ classpath => " + classpath);

        String realPath = classpath + uuidFile;


        log.info("删除文件--------------> {}", realPath);
        File file = new File(realPath);
        Boolean boo = true;
        if (file.exists()) {
            boo = file.delete();
            log.info("删除文件[" + boo + "],文件存在删除成功--------------> {}", realPath);
            return boo;
        } else {
            log.info("删除文件,文件不存在--------------> {}" + realPath);
            return boo;
        }
    }


}
