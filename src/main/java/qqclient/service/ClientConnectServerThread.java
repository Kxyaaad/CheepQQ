package qqclient.service;

import qqcommon.Message;
import qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author k
 * 2024/3/26 14:08
 * @version 1.0
 */
public class ClientConnectServerThread extends Thread{
    //该小昵称需要持有Socket
    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;

    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        super.run();
        //因为Thread需要后台保持和服务器通信，所以需要保活
        while (true) {
            System.out.println("客户端线程，等待从读取服务器端发送的消息");
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) objectInputStream.readObject(); //如果服务器没有返回信息，线程会阻塞
                if (msg.getMsgType().equals(MessageType.MESSAGE_RETURN_ONLINE_FRIEND)) {
                    String[] users = msg.getContent().split(",");
                    System.out.println("==========当前在线用户列表==========");
                    for (String user : users) {
                        System.out.println("用户：" + user );
                    }
                }else if (msg.getMsgType().equals(MessageType.MESSAGE_COMMON_MES)){
                    System.out.println( msg.getSender() + ":" + msg.getContent() + "\t\t" + msg.getSendTime());
                } else if (msg.getMsgType().equals(MessageType.MESSAGE_TO_ALL_MES)) {
                    System.out.println("群聊消息:" + msg.getContent() + "\t\t" + msg.getSendTime());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
