package com.crc.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description 文件工具类
 * @Author YANKAIBO
 * @Date 2022/1/20 14:32
 * @Version 1.0
 */
public class FileUtils {
    private static String[] allowTypes = new String[] { ".txt", ".TXT", ".pdf", ".PDF",".doc", ".DOC",".docx", ".DOCX", ".xls", ".XLS", ".xlsx", ".XLSX", ".zip", ".ZIP", ".rar", ".RAR", ".dwg", ".DWG", ".pptx", ".PPTX", ".BMP", ".bmp", ".JPG", "jpg", ".JPEG", ".jpeg", ".PNG", ".png", ".GIF", ".gif", ".wmv", ".asf", ".asx", ".rm", ".rmvb", ".mp4", ".MP4", ".3gp", ".mov", ".m4v", ".avi", ".dat", ".mkv", ".flv", ".vob", ".mp3", ".MP3", ".wav", ".WAV", ".ogg", ".OGG", ".flac", ".FLAC", ".3gpp", ".3GPP", ".amr", ".AMR", ".m4a", ".M4A" };
    public static boolean isValid(String contentType) {
        if (StringUtils.isEmpty(contentType)) {
            return false;
        }
        for (String type : allowTypes) {
            if (contentType.indexOf(type) > -1) {
                return true;
            }
        }
        return false;
    }
}
