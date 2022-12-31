package cn.targetpath.delivery.common;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/27 08:02
 */
public class CustomException extends RuntimeException {
    public CustomException(String msg){
        super(msg);
    }
}
