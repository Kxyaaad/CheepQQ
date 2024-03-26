package qqclient.service;

import java.util.HashMap;

/**
 * @author k
 * 2024/3/26 14:39
 * @version 1.0
 */
public class ManageClientConnectServerThread {
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();
    //将某个线程加入集合中
    public static void addClientConnectServerThread(String userId, ClientConnectServerThread clientConnectServerThread) {
        hm.put(userId, clientConnectServerThread);
    }

    public static ClientConnectServerThread getClientConnectServerThreadByUserId(String userId) {
        return hm.get(userId);
    }
}
