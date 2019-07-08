package com.example.demo.Controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class UploadController {

    @GetMapping("/")
    public String Index()
    {
        return "index";
    }

    @GetMapping("/download")
    public String DownloadPage(ModelMap map)
    {
        map.put("link","www.google.com");
        return "download";
    }

    @GetMapping(value = "/downloadFile")
    public ResponseEntity<InputStreamResource> downloadFile(HttpServletResponse response) throws IOException {
//        // 文件所在的路径
//        File file = new File("https://img3.doubanio.com/view/status/l/public/a68d7709f5701ff.jpg");
//        // 使用response 获取字节输出
//        OutputStream outputStream = response.getOutputStream();
//        // 进行文件下载
//        response.setContentType("application/x-download");
//        response.setCharacterEncoding("utf-8");
//        response.setHeader("Content-Disposition", "attachment;filename=" + new String(("下载名称").getBytes("gbk"), "iso8859-1") + ".jpg");
//        // outputStream 写入到输出流
//        outputStream.write(FileCopyUtils.copyToByteArray(file));
//        // 刷新流
//        outputStream.flush();
//        // 关闭流
//        outputStream.close();

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        File file = new File("/Users/Steven/Downloads/PicTemp/anpu.jpg");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
