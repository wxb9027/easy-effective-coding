package easy.effective.coding.io;

import org.apache.log4j.Logger;

import java.io.File;

public class FileIO {
    private static final Logger LOG = Logger.getLogger(FileIO.class);
    public static void main(String[] args) {
        String dir = "/mydata/why-log";
        File file = new File(dir);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            if(mkdirs){
                LOG.info("成功创建目录："+dir);
            }else {
                LOG.error("创建目录失败："+dir);
            }

        }

    }
}
