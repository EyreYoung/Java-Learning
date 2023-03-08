package org.seu.customRPC.transport;

import io.netty.channel.ChannelHandlerContext;
import org.seu.customRPC.protocol.Message;
import org.seu.customRPC.protocol.Request;
import org.seu.customRPC.protocol.Response;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Handler中专用的Runnable任务
 */
public class InvokeRunnable implements Runnable{

    private final ChannelHandlerContext ctx;

    private final Message<Request> msg;

    public InvokeRunnable(ChannelHandlerContext ctx, Message<Request> msg) {
        this.ctx = ctx;
        this.msg = msg;
    }

    @Override
    public void run() {
        Response response = new Response();
        Object result = null;

        try {
            Request request = msg.getContent();
            String serviceName = request.getServiceName();
            // 利用serviceName获取bean
            ClassPathXmlApplicationContext context =
                    new ClassPathXmlApplicationContext("dubbo-consumer.xml");
            Object bean = context.getBean(serviceName);
            // 反射调用Request中要求的方法
            Method method = bean.getClass().getMethod(request.getMethodName(), request.getArgTypes());
            result = method.invoke(bean, request.getArgs());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        // 设置响应结果
        response.setResult(result);
        // 将响应返回给Client
        ctx.writeAndFlush(new Message<>(msg.getHeader(), response));
    }

}
