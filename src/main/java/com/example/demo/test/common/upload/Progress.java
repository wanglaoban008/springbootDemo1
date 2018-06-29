package com.example.demo.test.common.upload;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-06-29 15:41
 **/
public class Progress {
    private long bytesRead; //已读取文件的比特数
    private long contentLength;//文件总比特数
    private long items; //正读的第几个文件

    public long getBytesRead()
    {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead)
    {
        this.bytesRead = bytesRead;
    }

    public long getContentLength()
    {
        return contentLength;
    }

    public void setContentLength(long contentLength)
    {
        this.contentLength = contentLength;
    }

    public long getItems()
    {
        return items;
    }

    public void setItems(long items)
    {
        this.items = items;
    }
}
