package com.yxh.weshare.utils.freechart;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @author Xinhao Yi
 * @date 2022/8/10 14:13
 * @description: 重写ServletUtilities中的saveChartAsJPEG方法，更改生成的图片的保存位置
 * Rewrite the saveChartAsJPEG method in ServletUtilities to change the location of the generated image
 */
public class MyServletUtilities extends ServletUtilities {
    private static String tempFilePrefix = "jfreechart-";
    private static String tempOneTimeFilePrefix = "jfreechart-onetime-";

    private static String basePath = "static/img/recommend";

    public static String saveChartAsJPEG(JFreeChart chart, int width, int height, HttpSession session) throws IOException {
        return saveChartAsJPEG(chart, width, height, (ChartRenderingInfo)null, session);
    }

    public static String saveChartAsJPEG(JFreeChart chart, int width, int height, ChartRenderingInfo info, HttpSession session) throws IOException {

        String realPath = session.getServletContext().getRealPath(basePath);
        System.out.println(realPath);

        File file = new File(realPath);
        if (!file.exists()){
            boolean mkdirs = file.mkdirs();
        }

        if (chart == null) {
            throw new IllegalArgumentException("Null 'chart' argument.");
        } else {
            createTempDir();
            String prefix = tempFilePrefix;
            if (session == null) {
                prefix = tempOneTimeFilePrefix;
            }

            File tempFile = File.createTempFile(prefix, ".jpeg", new File(realPath));
            ChartUtilities.saveChartAsJPEG(tempFile, chart, width, height, info);
            if (session != null) {
                registerChartForDeletion(tempFile, session);
            }

            return tempFile.getName();
        }
    }
}
