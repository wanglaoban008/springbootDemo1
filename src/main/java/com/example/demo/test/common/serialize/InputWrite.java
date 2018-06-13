package com.example.demo.test.common.serialize;

import com.example.demo.entity.MyNote;

import java.io.*;
import java.util.Date;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-06-13 16:08
 **/
public class InputWrite {

    /**
     * @Description:  序列化对象  transient，不会写到文件中，会为null
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/6/13
    */
    public static void serializeMyNote(){
        MyNote myNote = new MyNote("记录吃饭的小事", "今天吃饭噎着了，还好只是噎着了没有被鱼刺卡着，不过今天我没有吃鱼啊怎么会被鱼刺卡住呢？", new Date(), "晴");
        myNote.setId("2");
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("d:/flyPig.txt")));
            outputStream.writeObject(myNote);
            System.err.println("对象序列化成功，保存到文件成功");
            outputStream.close();
        }catch (Exception e){
            System.err.println("对象序列化失败，保存到文件失败");
        }

    }

    /** 
     * @Description:  反序列化  static,反序列化时读取的是对象里面的值，不是文件的值
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/6/13 
    */ 
    public static MyNote UnSerializeNote(){
        MyNote myNote = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("d:/flyPig.txt")));
            myNote = (MyNote) ois.readObject();
            System.err.println("myNote："+myNote.toString());
            System.out.println("MyNote 对象反序列化成功！");
        }catch (Exception e){
            System.out.println("MyNote 对象反序列化成功！");
        }
        return myNote;
    }

    public static void main(String[] args) {
        //序列化
        //serializeMyNote();
        //反序列化
        UnSerializeNote();

    }
}
