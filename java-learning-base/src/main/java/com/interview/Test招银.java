package com.interview;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

public class Test招银 {

    /**
     * 程序入口
     * @param args
     */
    public static void main(String[] args) {
        UserService target = new UserServiceImpl();
        //生成代理类型，添加了权限认证功能
        UserService proxy = (UserService) PermissionProxy.getInstance(target);
        proxy.deleteUser();
        proxy.addUser();
    }
}

/**
 * 权限代理类，继承InvocationHandler
 */
class PermissionProxy implements InvocationHandler {

    // 2、创建被代理对象
    private Object target;

    /**
     * 创建代理对象，参数是要被代理的对象，返回值是代理对象
     * @param o
     * @return
     */
    public static Object getInstance(Object o) {
        PermissionProxy proxy = new PermissionProxy();
        proxy.target = o;
        Object result = Proxy.newProxyInstance(o.getClass().getClassLoader(),
              o.getClass().getInterfaces()  , proxy);
        return result;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        // 检查该方法上是否有Permission注解
        if (method.isAnnotationPresent(Permission.class)) {

            // 取得注解的权限信息
            Permission permission = method.getAnnotation(Permission.class);

            //对当前用户的请求权限认证,没权限抛异常
            if(!doCheck(permission.permissionCode())){
                throw new Exception("没有权限访问");
            }
        }
        Object o = method.invoke(target, args);
        return o;
    }

    /**
     * 判断当前用户是否具备ADD权限
     * @param permissionCode
     * @return
     * @throws Exception
     */
    private boolean doCheck(String permissionCode) throws Exception {

        //获取用户权限
        List<String> permissions = getUserPermissions();

        //判断用户权限是否等于注解对应的权限码
        if(permissions != null && permissions.contains(permissionCode)){
            System.out.println(String.format("校验成功，当前用户具备%s权限", permissionCode));
            return true;
        }else{
            System.out.println(String.format("校验失败，当前用户不具备%s权限", permissionCode));
            return false;
        }
    }

    /**
     * 获取当前用户权限
     * @return
     */
    private List<String> getUserPermissions() {
        //初始化用户权限
        String[] permissions = {"ADD","DELETE","UPDATE"};
        System.out.println(String.format("当前用户初始化权限%s", Arrays.toString(permissions)));
        return Arrays.asList(permissions);
    }
}

class UserServiceImpl implements UserService{
    @Override
    public void deleteUser() {
        System.out.println("操作=====================删除用户成功");
    }

    public void addUser() {
        System.out.println("添加用户成功");
    }
}

/**
 *用户服务类
 */
interface UserService{

    /**
     * 删除用户
     */
	@Permission(permissionCode="DELETE")
    void deleteUser();

	@Permission(permissionCode = "ADD")
	void addUser();
}


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Permission {

    /**
     * @return	 * 权限码控制

     */
    public String permissionCode() default "";
}
