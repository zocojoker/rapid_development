package com.zoco.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author zoco
 * @creat 2019-01-07-14:34
 */
public class Demo {
    public static void main(String[] args) {
        /*Map<String, String> map = new HashMap<>();
        map.put("11", "11");
        map.put("11", "11");
        System.out.println(map.get("11"));*/
        /*NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        System.out.println((float) 1 / 3);
        System.out.println(nf.format((float) 1 / 3));
        int successCount = 7;
        int failCount = 4;
        System.out.println(nf.format((float) successCount / (successCount + failCount)));

        String str1 = "100100009001";
        String str2 = "00010000902";
        String str3 = "000000000000";
        System.out.println(str1 + "==" + str2 + "==" + str3);

        long i1 = Long.parseLong(str1);
        long i2 = Integer.parseInt(str2);
        long i3 = Integer.parseInt(str3);
        System.out.println(i1 + "==" + i2 + "==" + i3);*/
        File file = new File("D:/Bpan/方欣/税云中心/发票明细上传/taxML_ZZSFPKJMX_1_1_20190501_20190531_91440101708358833M.zip");
        try {
            ZipFile zipFile = new ZipFile(file.getAbsoluteFile());
            ZipEntry zipEntry = zipFile.getEntry("taxML_ZZSFPKJMX_1_1_20190501_20190531_91440101708358833M_V10.xml");
            InputStream in = zipFile.getInputStream(zipEntry);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
