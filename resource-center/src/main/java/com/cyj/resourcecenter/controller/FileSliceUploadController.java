package com.cyj.resourcecenter.controller;


import com.cyj.resourcecenter.entity.FileSlice;
import com.cyj.resourcecenter.service.UploadFileService;
import com.cyj.resourcecenter.utils.IsEmptyUtils;
import com.cyj.resourcecenter.utils.PropUtil;
import com.cyj.resourcecenter.utils.SliceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.sauronsoftware.jave.Encoder;

/**
 * @author ChenYongJia
 * @date 2020-1-5 13:58:53
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/file", name = "文件管理")
public class FileSliceUploadController {
    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 获取视频某一帧的截图作为视频封面
     *
     * @param veido_path
     * @param ffmpeg_path
     * @return
     */
    public static boolean processImg(String veido_path, String ffmpeg_path) {

        File file = new File(veido_path);
        if (!file.exists()) {
            System.err.println("路径[" + veido_path + "]对应的视频文件不存在!");
            return false;

        }
        List<String> commands = new java.util.ArrayList<String>();
        commands.add(ffmpeg_path);
        commands.add("-i");
        commands.add(veido_path);
        commands.add("-y");
        commands.add("-f");
        commands.add("image2");
        commands.add("-ss");
        // 这个参数是设置截取视频多少秒时的画面
        commands.add("1");
        commands.add("-t");
        commands.add("0.001");
        commands.add("-s");
        // 宽X高
        commands.add("1920x1080");
        commands.add(veido_path.substring(0, veido_path.lastIndexOf(".")).replaceFirst("vedio", "file") + ".jpg");
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            builder.start();
            log.info("截取成功");
            return true;
        } catch (Exception e) {
            log.error("发生错误===>",e);
            return false;
        }

    }

    /**
     * 根据文件后缀名判断 文件是否是视频文件
     *
     * @param fileName 文件名
     * @return 是否是视频文件
     */
    public boolean isVedioFile(String fileName) {
        final String PREFIX_VIDEO = ".mp4";
        if (fileName.contains(PREFIX_VIDEO)) {
            return true;
        }
        return false;
    }

    /**
     * http://localhost:4010/file/beforeUpload
     * 文件上传之前的准备工作
    *
     * @param fileSlice
     * @return
     */
    @RequestMapping(value = "/beforeUpload", name = "上传支持分片断点传输", method = RequestMethod.POST)
    public Map<String, Object> beforeUpload(@RequestBody FileSlice fileSlice) {
        log.info("beforeUpload fileSlice=>" + fileSlice);
        // 文件是否续传
        int slice = 0;
        // 文件唯一标志
        String uuid = "";
        Map<String, Object> result = new HashMap<String, Object>();
        // 根据文件信息查询文件是否续传
        uuid = PropUtil.queryUUIDbyFileinfo(fileSlice.getName(), fileSlice.getTotalSize());
        // 新文件上传
        if (IsEmptyUtils.isEmpty(uuid)) {
            uuid = PropUtil.createUUIDbyFileinfo(fileSlice.getName(), fileSlice.getTotalSize());
            // 新文件ID
            fileSlice.setFid(uuid);
            // 新文件断点从头开始
            fileSlice.setPoint(0L);
            log.info("新文件上传=>" + fileSlice);
            // 新文件传输开始,把FileSlice传输状态记录在磁盘
            PropUtil.createFileSlice(fileSlice);
            // 新文件
            result.put("slice", 0);
            // 文件唯一标志
            result.put("uuid", uuid);
            // 文件上传状态
            result.put("fileSlice", fileSlice);
            return result;
        } else {
            fileSlice.setFid(uuid);
            // 查询出文件上次传输断点位置
            fileSlice = PropUtil.queryFileSliceByFid(fileSlice.getFid());
            log.info("FileSliceUploadController.beforeUpload(FileSlice) 断点续传=>" + fileSlice);
            // 断点续传
            result.put("slice", 1);
            // 文件唯一标志
            result.put("uuid", uuid);
            // 文件上传状态
            result.put("fileSlice", fileSlice);
            return result;
        }
    }



    /**
     * http://localhost:4010/file/sliceUpload
     * 大文件分片上传,支持断点续传
     * @param myfile
     * @param fid
     * @return
     */
    @RequestMapping("/sliceUpload")
    public Object sliceUpload(@RequestParam(value = "myfile", required = false) MultipartFile myfile,
                              @RequestParam(value = "fid", required = true) String fid) {
        // form表单中参数名称
        String name = myfile.getName();
        // 得到上传文件的名称
        String originalFilename = myfile.getOriginalFilename();
        log.info("表单中文件参数名称 name=>" + name);
        log.info("上传的文件原始名称 originalfileName=>" + originalFilename);
        log.info("文件 fid=>" + fid);
        String msg = "文件大小=>" + myfile.getSize();
        // 查询出文件上次传输断点位置
        FileSlice fileSlice = PropUtil.queryFileSliceByFid(fid);
        // 本次上传的字节数据
        byte[] sliceData = null;
        try {
            // 本次上传的字节数据
            sliceData = myfile.getBytes();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        FileSlice resultSlice = SliceUtil.writeSlice(fileSlice, sliceData);
        // 更新传输状态
        PropUtil.updateFileSlice(resultSlice);
        // 文件是否续传
        int slice = 1;
        // 文件唯一标志
        String uuid = fid;
        Map<String, Object> result = new HashMap<String, Object>();
        // 断点续传
        result.put("slice", 1);
        // 文件唯一标志
        result.put("uuid", uuid);
        // 更新后的文件上传状态
        result.put("fileSlice", resultSlice);
        return result;
    }

    /**
     * http://localhost:4010/file/deleteFiles
     *
     * @param fileids
     * @return
     */
    @RequestMapping(value = "/deleteFiles", name = "删除多个或一个文件", method = RequestMethod.DELETE)
    public Object deleteFileByFileIds(@RequestParam(value = "fileids", required = false) String fileids) {
        Map<Object, String> map = new HashMap<Object, String>();
        String[] modulesIds = fileids.split(",");
        Integer k = 0;
        for (String dids : modulesIds) {
            k += uploadFileService.deleteFileByFileids(dids);
        }
        map.put("result", "成功删除了：" + k + "条数据。");
        return map;
    }

    //
    //

    /**
     * 文件分片上传,传输完毕.删除所有传输状态记录并且保存数据库
     * http://localhost:4010/file/sliceUploadOver
     * @param fileSlice
     * @return
     */
    @RequestMapping("/sliceUploadOver")
    public Object sliceUploadOver(@RequestBody FileSlice fileSlice) {
        log.info("sliceUploadOver fileSlice=>" + fileSlice);
        // 根据文件信息,删除文件的UUID;并删除记录在fileslice.properties文件信息
        PropUtil.deleteUUIDbyFileinfo(fileSlice.getName(), fileSlice.getTotalSize());
        // 删除uuid.properties文件
        PropUtil.deleteFileSlice(fileSlice.getFid());
        // 创建文件路径,原始文件名称
        String fileName = fileSlice.getName();
        // 获取文件后缀名称
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        String path = "D:/fileUpload/" + fileSlice.getFid() + fileSuffix;
        log.info("===============================" + path);
        Integer videoTime = null;
        String videoFormat = "";
        String videoCover = "";
        Integer videoWeight = null;
        Integer videoHeight = null;
        File dest = new File(path);
        Map<String, Object> result = new HashMap<String, Object>();
        if (this.isVedioFile(fileSlice.getName())) {
            Encoder encoder = new Encoder();
            // 获取上传视频截图
            processImg("D:\\fileUpload\\" + fileSlice.getFid() + fileSuffix,
                    "E:\\ffmpeg\\ffmpeg-20181215-011c911-win64-shared\\bin\\ffmpeg.exe");
            try {
                // 获取视频时长
                it.sauronsoftware.jave.MultimediaInfo m = encoder.getInfo(dest);
                long ls = m.getDuration();

                videoTime = (int) ((ls) / 1000);
                // 获取视频格式
                videoFormat = m.getFormat();
                // 获取视频高度
                videoHeight = m.getVideo().getSize().getHeight();
                // 此视频宽度为
                videoWeight = m.getVideo().getSize().getWidth();
                // 视频截取图片
                videoCover = "http://localhost:4010/file/downloadFile?fileName=" + fileSlice.getFid() + ".jpg";
            } catch (Exception e) {
                log.error("发生异常处理截图等信息失败",e);
            }
        }
        try {
            // 获取文件的大小
            FileInputStream fis = new FileInputStream(dest);
            FileChannel fc = fis.getChannel();
            BigDecimal fileSize = new BigDecimal(fc.size());
            String size = fileSize.divide(new BigDecimal(1048576), 2, RoundingMode.HALF_UP) + "MB";
            // 文件主键
            String files_id = fileSlice.getFid();
            String url = "http://localhost:4010/file/downloadFile?fileName="
                    + fileSlice.getFid() + fileSuffix;
            if (uploadFileService.getFileByFileName(fileSlice.getName()) > 0) {
                String filed = uploadFileService.getFileidByFileName(fileSlice.getName());
                File f = new File("D:\\fileUpload\\" + filed + fileSuffix);
                log.info("----------------------------------------");
                log.info("----------------------------------------");
                log.info("D:/fileUpload/" + filed + fileSuffix);
                uploadFileService.deleteFileByFileName(fileSlice.getName());
                f.delete();
            }
            int q = uploadFileService.insertVidoFile(files_id, fileSlice.getName(), url, path, new Date(), size,
                    videoTime, videoFormat, videoCover);
            if (q > 0) {
                result.put("result", true);
                result.put("fileName", fileSlice.getName());
                result.put("url", path);
                result.put("videoTime", videoTime);
                result.put("videoFormat", videoFormat);
                result.put("videoSize", size);
                result.put("videoCover", videoCover);
            }
        } catch (Exception e) {
            log.error("发生异常处理文件失败",e);
        }
        // 断点续传
        result.put("slice", 1);
        // 文件唯一标志
        result.put("uuid", fileSlice.getFid());
        // 更新后的文件上传状态
        result.put("fileSlice", fileSlice);
        return result;
    }

    /**
     * 访问路径为：http://localhost:4010/file/downloadFile
     *
     * @param request
     * @param response
     * @returnGE
     * @author WangAnLing
     */
    @RequestMapping(value = "/downloadFile", name = "下载文件", method = RequestMethod.GET)
    public String downloadFile(String fileName, HttpServletResponse response) {
        if (!IsEmptyUtils.isEmpty(fileName)) {
            // 设置文件路径
            String realPath = "G://fileUpload//";
            File file = new File(realPath, fileName);
            if (file != null) {
                log.info("file=====>{}",file);
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    log.info("success");
                } catch (Exception e) {
                    log.error("操作异常",e);
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            log.error("io流操作异常",e);
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            log.error("io流操作异常",e);
                        }
                    }
                }
            }
        }
        return null;
    }

}
