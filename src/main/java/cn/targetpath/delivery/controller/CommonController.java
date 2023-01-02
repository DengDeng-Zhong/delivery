package cn.targetpath.delivery.controller;

import cn.targetpath.delivery.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传下载
 *
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/27 9:26
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${delivery.path}")
    private String basePath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String res = dateFormat.format(new Date());
        // 原始文件名
        String originalFilename = file.getOriginalFilename();
        // 新的文件名
        String fileName = res + UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        ;

        // 创建年月日文件夹
        Calendar calendar = Calendar.getInstance();
        File dateDirs = new File(calendar.get(Calendar.YEAR) + File.separator
                + (calendar.get(Calendar.MONTH) + 1) + File.separator
                + (calendar.get(Calendar.DATE)));
        // 新文件
        File newFile = new File(basePath + File.separator + dateDirs + File.separator + fileName);

        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        log.info("文件路径,{}",newFile);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.success(fileName);
    }

    /**
     * 文件下载
     *
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        try {
            FileInputStream inputStream = new FileInputStream(new File(basePath + name));

            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

            outputStream.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
