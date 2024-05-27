package qqclient.service;

import qqcommon.Message;
import qqcommon.MessageType;
import qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

/**
 * @author k
 * 2024/3/26 13:27
 * @version 1.0
 * 完成用户登录验证和用户注册登功能
 */
public class UserClientService {
    private User user = new User();
    private Socket socket;

    public boolean checkUser(String userId, String password) {
        user.setUserId(userId);
        user.setPassword(password);
        //链接服务端
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(user);
//            socket.shutdownOutput(); 会导致服务端IO错误
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message) objectInputStream.readObject();
            if (ms.getMsgType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) {
                //创建一个和服务器端保持通信的线程->创建一个类ClientConnectServerThread
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                clientConnectServerThread.start();
                //为了后续扩展，把线程放入集合中管理
                ManageClientConnectServerThread.addClientConnectServerThread(userId, clientConnectServerThread);
                return true;
            } else {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void showOnlineUsers() {
        Message message = new Message();
        message.setMsgType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        sendMessage(message);
    }

    public void exit() {
        Message exitMsg = new Message();
        exitMsg.setMsgType(MessageType.MESSAGE_CLIENT_EXIT);
        sendMessage(exitMsg);
    }

    public void sendMessageToUser(String target, String content) {
        Message message = new Message();
        message.setGetter(target);
        message.setContent(content);
        message.setMsgType(MessageType.MESSAGE_COMMON_MES);
        message.setSendTime(new Date().toString());
        sendMessage(message);
    }

    public void sendMessageToAll(String content) {
        Message message = new Message();
        message.setContent(content);
        message.setMsgType(MessageType.MESSAGE_TO_ALL_MES);
        message.setSendTime(new Date().toString());
        sendMessage(message);
    }

    public void sendMessage(Message message) {
        message.setSender(user.getUserId());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThreadByUserId(user.getUserId()).getSocket().getOutputStream());
            message.setSender(user.getUserId());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
