package com.cyj.resourcecenter.utils;

import com.cyj.resourcecenter.entity.FileSlice;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 分片上传文件的工具类
 *
 * @author MaShuai
 * @date 2018-12-18
 */
@Slf4j
public class SliceUtil {

    /**
     * 分片写文件
     * 把新传输的文件字节数据追加到断点文件字节之后
     *
     * @param slice
     * @param sliceData
     * @return 写入数据之后, 更新之后的文件传输状态, 主要更新了断点
     */
    public static FileSlice writeSlice(FileSlice slice, byte[] sliceData) {
        // 原始文件名称
        String fileName = slice.getName();
        // 获取文件后缀名称
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        String fileNameEnd = "D:\\fileUpload\\" + slice.getFid() + fileSuffix;
        //1048576 字节= 1M = 1024 * 1024 字节
        log.info("******分片写入" + slice.getFid() + "." + "断点" + slice.getPoint() + "文件的数据大小" + sliceData.length);

        //开始写的断点
        Long point = slice.getPoint();
//		Long sliceSize = 1204L * 1024L ;//1M = 1204 * 1024;每片字节数,每次读的长度

        // 创建输入流关联源,因为要指定位置读和写,所以我们需要用随机访问流
        try {
            RandomAccessFile desc = new RandomAccessFile(fileNameEnd, "rw");
            log.info("分片写入前文件的大小" + desc.length());
            //开始读写
            //每次读1M = 1024 * 1024 字节

            //设置写文件指针偏移,即断点
            desc.seek(point);
            //本次读到的内容
            desc.write(sliceData);
            log.info("分片写入之后文件的大小=>" + desc.length());
            log.info("分片写入之后文件的信息FileSlice=>" + slice);
            //断点向后移动量就是新写入数据的数据字节大小
            slice.setPoint(point + sliceData.length);
            desc.close();
        } catch (FileNotFoundException e) {
            log.error("文件未找到异常",e);
        } catch (IOException e) {
            log.error("io流操作异常",e);
        }
        return slice;

    }

}
