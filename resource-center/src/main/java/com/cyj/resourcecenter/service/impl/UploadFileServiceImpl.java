package com.cyj.resourcecenter.service.impl;

import com.cyj.resourcecenter.dao.UploadFile;
import com.cyj.resourcecenter.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 实现类
 * @author ChenYongJia
 * @date 2020-1-5 13:36:22
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private UploadFile uploadFile;

    /**
     * 根据名字获取文件
     * @param files_name
     * @return
     */
    @Override
    public Integer getFileByFileName(String files_name) {
        return uploadFile.getFileByFileName(files_name);
    }

    /**
     * 根据名字删除文件
     * @param files_name
     */
    @Override
    public void deleteFileByFileName(String files_name) {
        uploadFile.deleteFileByFileName(files_name);
    }

    /**
     * 添加视频文件
     * @param uuid
     * @param files_name
     * @param files_ur
     * @param files_url
     * @param create_time
     * @param video_size
     * @param video_time
     * @param video_format
     * @param video_cover
     * @return
     */
    @Override
    public Integer insertVidoFile(String uuid, String files_name, String files_ur, String files_url, Date create_time,
                                  String video_size, Integer video_time, String video_format, String video_cover) {
        return uploadFile.insertVidoFile(uuid, files_name, files_ur, files_url, create_time, video_size, video_time,
                video_format, video_cover);
    }

    /**
     * 根据文件id删除file
     * @param fileids
     * @return
     */
    @Override
    public Integer deleteFileByFileids(String fileids) {
        return uploadFile.deleteFileByFileids(fileids);
    }

    /**
     * 根据名字获取文件
     * @param files_name
     * @return
     */
    @Override
    public String getFileidByFileName(String files_name) {
        return uploadFile.getFileidByFileName(files_name);
    }

}
