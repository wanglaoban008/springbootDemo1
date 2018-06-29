package com.example.demo.test.common.upload;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-06-29 15:44
 **/
@Component
public class FileUploadProgressListener implements ProgressListener {

    private HttpSession session;
    public void setSession(HttpSession session){
        this.session=session;
        Progress status = new Progress();//保存上传状态
        session.setAttribute("status", status);
    }
    @Override
    public void update(long bytesRead, long contentLength, int items) {
        Progress status = (Progress) session.getAttribute("status");
        status.setBytesRead(bytesRead);
        status.setContentLength(contentLength);
        status.setItems(items);

    }
}
