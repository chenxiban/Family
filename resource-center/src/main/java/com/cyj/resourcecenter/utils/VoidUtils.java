package com.cyj.resourcecenter.utils;


import it.sauronsoftware.jave.Encoder;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 视频处理帮助类
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.resourcecenter.utils
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-05 15:51
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
@Slf4j
public class VoidUtils {

    public static void main(String[] args) {
        // 这里文件路径自己来吧
        File source = new File("xxxxx");
        Encoder encoder = new Encoder();
        FileChannel fc = null;
        String size = "";
        try {
            it.sauronsoftware.jave.MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration();
            log.info("此视频时长为:" + ls / 60000 + "分" + (ls) / 1000 + "秒！");
            //视频帧宽高
            log.info("此视频高度为:{}", m.getVideo().getSize().getHeight());
            log.info("此视频宽度为:{}", m.getVideo().getSize().getWidth());
            log.info("此视频格式为:{}", m.getFormat());
            FileInputStream fis = new FileInputStream(source);
            fc = fis.getChannel();
            BigDecimal fileSize = new BigDecimal(fc.size());
            size = fileSize.divide(new BigDecimal(1048576), 2, RoundingMode.HALF_UP) + "MB";
            log.info("此视频大小为" + size);
        } catch (Exception e) {
            log.error("视频信息截取出现异常，截取失败", e);
        } finally {
            if (null != fc) {
                try {
                    fc.close();
                } catch (IOException e) {
                    log.error("io流操作异常，截取失败", e);
                }
            }
        }
    }

    /**
     * @param path
     * @return Map
     */
    public static Map<String, Object> getVoideMsg(String path) {

        Map<String, Object> map = new HashMap<String, Object>();
        File file = new File(path);
        Encoder encoder = new Encoder();
        FileChannel fc = null;
        String size = "";

        if (file != null) {
            try {
                it.sauronsoftware.jave.MultimediaInfo m = encoder.getInfo(file);
                long ls = m.getDuration();

                FileInputStream fis = new FileInputStream(file);
                fc = fis.getChannel();
                BigDecimal fileSize = new BigDecimal(fc.size());
                size = fileSize.divide(new BigDecimal(1048576), 2, RoundingMode.HALF_UP) + "MB";

                map.put("height", m.getVideo().getSize().getHeight());
                map.put("width", m.getVideo().getSize().getWidth());
                map.put("format", m.getFormat());
            } catch (Exception e) {
                log.error("操作出现异常，截取失败", e);
            } finally {
                if (null != fc) {
                    try {
                        fc.close();
                    } catch (IOException e) {
                        log.error("io流操作异常，截取失败", e);
                    }
                }
            }
        }

        return map;
    }
}

