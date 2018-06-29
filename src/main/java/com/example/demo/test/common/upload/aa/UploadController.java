package com.example.demo.test.common.upload.aa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-06-29 18:33
 **/
@Controller
@RequestMapping("/uploadProgress")
public class UploadController {

    @GetMapping("/html")
    public String html() {
        return "/index.html";
    }

        @RequestMapping(value = "/showUpload", method = RequestMethod.GET)
    public ModelAndView showUpload() {
        return new ModelAndView("/UploadProgressDemo");
    }

    @RequestMapping("/upload")
    @ResponseBody
    public void uploadFile(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
    }

}
