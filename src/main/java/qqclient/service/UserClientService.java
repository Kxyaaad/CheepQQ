package qqclient.service;

import org.qqcommon.Message;
import org.qqcommon.MessageType;
import org.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

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
            socket.shutdownOutput();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message) objectInputStream.readObject();
            if (ms.getMsgType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) {
                //创建一个和服务器端保持通信的线程->创建一个类ClientConnectServerThread
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                clientConnectServerThread.start();
                //为了后续扩展，把线程放入集合中管理
                ManageClientConnectServerThread.addClientConnectServerThread(userId, clientConnectServerThread);
            }else {
                socket.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
