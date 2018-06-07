package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program:
 * @description:   切面类实现要实现的功能
 * @author: malili
 * @create: 2018-04-25 17:26
 **/
@Aspect
@Component
public class TestAop {

    /*
    * 定义一个切入点
    */
    @Pointcut("@annotation(com.example.demo.aop.MyInfoAnnotation)")
    public void myInfoAnnotation() {
    }

    // 用@Pointcut来注解一个切入方法
    @Pointcut("execution(* com.example.demo..*.*.*(..))")
    public void excudeController() {

    }

      /*
     * 通过连接点切入
     */
//    @Before("execution(* findById*(..)) &&" + "args(id,..)")
//    public void twiceAsOld1(Long id) {
//        System.out.println("切面before执行了。。。。id==" + id);
//
//    }


    //@annotation 这个你应当知道指的是匹配注解
    //括号中的 annotation 并不是指所有自定标签，而是指在你的注释实现类中 *Aspect 中对应注解对象的别名，所以别被俩 annotation  所迷惑。
    @Around(value ="myInfoAnnotation()&&excudeController()&&@annotation(annotation)")
    public String twiceAsOld(ProceedingJoinPoint thisJoinPoint,
                             MyInfoAnnotation annotation
    ) {
        System.out.println(annotation.value());

        return annotation.value();
    }

    @AfterReturning(returning = "ret",pointcut = "excudeController()")
    public void sysLog(Object ret){
        //ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //HttpServletRequest request = attributes.getRequest();
        //String meiNv  = request.getParameter("meinv");
        //if (!StringUtils.isEmpty(meiNv)){
        //    System.err.println(meiNv+"是超级大美女");
        //}
        System.err.println(ret+"打你哟~");
    }
}
