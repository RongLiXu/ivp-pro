package com.xunyat.iot.vmp.utils;

import com.xunyat.iot.vmp.media.zlm.dto.MediaServerItem;
import com.xunyat.iot.vmp.service.bean.DownloadFileInfo;

public class CloudRecordUtils {

    public static DownloadFileInfo getDownloadFilePath(MediaServerItem mediaServerItem, String filePath) {
        DownloadFileInfo downloadFileInfo = new DownloadFileInfo();

        String pathTemplate = "%s://%s:%s/index/api/downloadFile?file_path=" + filePath;

        downloadFileInfo.setHttpPath(String.format(pathTemplate, "http", mediaServerItem.getStreamIp(),
                mediaServerItem.getHttpPort()));

        if (mediaServerItem.getHttpSSlPort() > 0) {
            downloadFileInfo.setHttpsPath(String.format(pathTemplate, "https", mediaServerItem.getStreamIp(),
                    mediaServerItem.getHttpSSlPort()));
        }
        return downloadFileInfo;
    }
}
