package com.example.demo.test.common.upload;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-06-29 16:38
 **/
@RestController
public class FileController {
    // private final static Logger logger =
    // LoggerFactory.getLogger(FileController.class);
    //@Autowired
    //private PushMsgRepository pushRep;
    public static void log(Object o)
    {
        System.out.println(o);
    }

    @RequestMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file)
    {
        if (file.isEmpty())
        {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        // String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = null;
        try
        {
            filePath = new File("").getCanonicalPath() + "/tmp/uploadFile/";
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }
        String tagFilePath = filePath + "" + fileName;
        System.out.println("存储路径为:" + tagFilePath);
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(tagFilePath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists())
        {
            dest.getParentFile().mkdirs();
        }
        try
        {
            file.transferTo(dest);
            //PushMsgEntity pushInfo=new PushMsgEntity();
            //pushInfo.setFilePath(fileName);
            //pushInfo.setUserName("深圳");
            //pushRep.save(pushInfo);
            return fileName + "推送成功";
        } catch (IllegalStateException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return fileName + "推送失败";
    }

    @RequestMapping("/uploads")
    public Map<String, Object> uploadFiles(HttpServletRequest request)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        List<MultipartFile> files2 = ((MultipartHttpServletRequest) request).getFiles("file");
        BufferedOutputStream stream = null;
        String uploadPath = "upload/";
        validatePath(uploadPath);
        result.put("fileLength", files2.size());
        int i = 0;
        for (MultipartFile file : files2)
        {
            String originalFilename = file.getOriginalFilename();
            i++;
            validateType(uploadPath);
            if (!file.isEmpty())
            {
                try
                {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(uploadPath, originalFilename)));
                    stream.write(bytes);
                    result.put("file" + i, uploadPath + originalFilename);
                    stream.close();
                } catch (Exception e)
                {
                    result.put("message", originalFilename + "You failed to upload  => " + e.getMessage());
                }
            } else
            {
                result.put("message", originalFilename + "You failed to upload  because the file was empty.");
            }
        }
        return result;
    }

    /**
     * 验证上传的文件类型
     *
     * @return
     */
    private Map<String, Object> validateType(String path)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        return result;
    }

    /**
     * 验证上传的文件路径,没有则创建
     *
     * @return
     */
    private void validatePath(String path)
    {

        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
    }

    //@RequestMapping("/showPushInfo")
    //public List<PushMsgEntity> showAllPushInfo()
    //{
    //    return pushRep.findAll(new Sort(Direction.DESC, "generateTime"));
    //}
}
