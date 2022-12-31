package cn.targetpath.delivery.common;

/**
 * 基于ThreadLocal封装工具类,用户保存和获取当前登陆用户ID
 *
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/26 9:39
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
