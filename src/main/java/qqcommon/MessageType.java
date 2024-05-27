package qqcommon;

public interface MessageType {
    String MESSAGE_LOGIN_SUCCEED = "1"; //登录成功
    String MESSAGE_LOGIN_FAIL = "2"; //登录失败
    String MESSAGE_COMMON_MES = "3"; //普通信息
    String MESSAGE_GET_ONLINE_FRIEND = "4"; //获取在线用户列表
    String MESSAGE_RETURN_ONLINE_FRIEND = "5"; // 返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "6"; //退出
    String MESSAGE_TO_ALL_MES = "7"; //群发
    String MESSAGE_FILE_MES = "8"; //发送文件

}
