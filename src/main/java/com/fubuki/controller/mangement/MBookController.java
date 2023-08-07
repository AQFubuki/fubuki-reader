package com.fubuki.controller.mangement;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fubuki.service.BookService;
import com.fubuki.utils.ResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/management/book")
public class MBookController {

    private final BookService bookService;

    public MBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public ResponseUtils list(Integer page, Integer rows) {
        if (page == null) {
            page = 1;
        }
        ;

        if (rows == null) {
            rows = 10;
        }
        ResponseUtils resp = null;
        try {
            IPage p = bookService.selectBookMap(page, rows);
            resp = new ResponseUtils().put("list", p.getRecords()).put("count", p.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }

    @PostMapping("/upload")
    public Map upload(@RequestParam("img") MultipartFile file , HttpServletRequest request) throws IOException {
        //得到上传文件目录
        String uploadPath = request.getServletContext().getResource("/").getPath() + "/upload/";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileName = sdf.format(new Date());
        //abc.jpg
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        file.transferTo(new File(uploadPath + fileName + suffix));
        Map result = new LinkedHashMap();
        result.put("errno", 0);
        result.put("data", new String[]{"/upload/" + fileName + suffix});
        return result;
    }
}
