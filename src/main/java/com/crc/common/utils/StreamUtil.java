package com.crc.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author YUHONGFA3
 * @create 2019-10-15 9:22
 * @Description 流相关一些操作
 */
@Slf4j
public class StreamUtil {

//    private static final CommonLogger logger = (CommonLogger) IpisLoggerFactory.getLogger(StreamUtil.class);

    /**
     * 关闭流
     *
     * @param closeables
     */
    public static void closeStream(Closeable... closeables) {
        if (ArrayUtils.isEmpty(closeables)) {
            log.warn("Nothing to close, closeables is empty .");
            return;
        }

        for (Closeable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (IOException e) {
                log.error("Close Stream Exception, ", e);
            }
        }
    }

}
