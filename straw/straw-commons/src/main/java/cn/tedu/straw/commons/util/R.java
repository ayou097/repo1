package cn.tedu.straw.commons.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class R<E> implements Serializable {
    /**
     * 响应的状态码
     */
    private Integer state;
    /**
     * 操作失败时的信息
     */
    private String message;
    /**
     * 操作成功时响应到客户端的数据
     */
    private E data;

    public R(){
        super();
    }
    public R(Integer state){
        this.state = state;
    }
    public static R ok(){
        return new R(State.OK);
    }
    public static <E> R ok(E data){
        R r = new R();
        r.setState(State.OK);
        r.setData(data);
        return r;
    }
    public static R failure(Integer state,Throwable e){
        R r = new R();
        r.setState(state);
        r.setMessage(e.getMessage());
        return r;
    }

    //内部接口
    public interface State{
        /**
         * 正确
         */
        Integer OK = 2000;
        /**
         * 错误：用户名已经被注册
         */
        Integer ERR_USERNAME_DUPLICATE = 4000;
        /**
         * 错误：手机号码已经被注册
         */
        Integer ERR_PHONE_DUPLICATE = 4001;
        /**
         * 错误：邀请码错误
         */
        Integer ERR_INVITE_CODE = 4002;
        /**
         * 错误：班级已经被禁用
         */
        Integer ERR_CLASS_DISABLED = 4003;
        /**
         * 错误：插入数据失败
         */
        Integer ERR_INSERT = 4004;
        /**
         * 错误：插入数据失败
         */
        Integer ERR_ILLEGAL_PARAMETER = 4005;
        /**
         * 上传失败：文件为空
         */
        Integer ERR_FILE_EMPTY = 4006;
        /**
         * 上传失败：文件大小超出限制
         */
        Integer ERR_FILE_SIZE = 4007;
        /**
         * 上传失败：文件类型不支持
         */
        Integer ERR_FILE_TYPE = 4008;
        /**
         * 上传失败：文件上传时出现读写错误
         */
        Integer ERR_FILE_UPLOAD = 4009;
        /**
         * 上传失败：文件状态错误
         */
        Integer ERR_FILE_STATE = 4010;
        /**
         * 获取问题详情失败
         */
        Integer ERR_QUESTION_NOT_FOUND = 4011;
        /**
         * 发布评论失败失败
         */
        Integer ERR_ANSWER_NOT_FOUND = 4012;
        /**
         * 错误：未知错误
         */
        Integer ERR_UNKNOWN = 9000;

    }
}
