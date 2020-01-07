package com.cyj.resourcecenter.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ChenYongJia
 * @date 2020-1-5 13:38:22
 * @version 1.0
 */
@Mapper
@Component
public interface UploadFile {

    /**
     * 根据名称查找file
     *
     * @param files_name
     * @return
     */
    @Select({"SELECT COUNT(*) FROM tb_files WHERE files_name=#{name}"})
    Integer getFileByFileName(@Param("name") String files_name);

    /**
     * 根据名称查找fileid
     *
     * @param files_name
     * @return
     */
    @Select({"SELECT files_id FROM tb_files WHERE files_name=#{n}"})
    String getFileidByFileName(@Param("n") String files_name);

    /**
     * 根据名称删除file
     *
     * @param files_name
     * @return
     */
    @Delete({"DELETE FROM filemanagement.tb_files WHERE files_name =#{name}"})
    void deleteFileByFileName(@Param("name") String files_name);

    /**
     * 根据文件id删除file
     *
     * @param fileids
     * @return
     */
    @Delete({"DELETE FROM filemanagement.tb_files WHERE files_id ==#{ids}"})
    Integer deleteFileByFileids(@Param("ids") String fileids);

    /**
     * 上传音频文件
     *
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
    @Insert({
            "INSERT INTO tb_files (files_id,files_name,files_uploadUrl,files_url,create_time,video_size,video_time,video_format,video_cover) VALUES (#{uuid},#{name},#{ur},#{url},#{creatTime},#{size},#{time},#{format},#{cover})"})
    Integer insertVidoFile(@Param("uuid") String uuid, @Param("name") String files_name, @Param("ur") String files_ur,
                           @Param("url") String files_url, @Param("creatTime") Date create_time, @Param("size") String video_size,
                           @Param("time") Integer video_time, @Param("format") String video_format,
                           @Param("cover") String video_cover);
}
