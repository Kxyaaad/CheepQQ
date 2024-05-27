package qqclient.view;

import qqclient.service.UserClientService;

import java.util.Scanner;

/**
 * @author k
 * 2024/3/26 10:46
 * @version 1.0
 */
public class QQView {

    private boolean loop = true;
    private String key = "";
    private UserClientService userClientService;

    public static void main(String[] args) {
        new QQView().mainMenu();
        System.out.println("==========感谢使用==========");
        System.exit(0);
    }

    //显示主菜单
    private void mainMenu() {

        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("==========欢迎登录网络通信系统==========");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "1":
                    System.out.println("==========登录系统==========");
                    login(scanner);
                    break;
                case "9":
                    loop = false;
                    break;
            }
        }
    }

    private void login(Scanner scanner) {
        System.out.println("请输入用户号：");
        String userId = scanner.next();
        System.out.println("请输入密码：");
        String password = scanner.next();
        userClientService =new UserClientService();
        Boolean isLogSucceed = userClientService.checkUser(userId, password);
        if (isLogSucceed) {
            System.out.println("==========欢迎(用户" + userId + ")==========");
            while (loop) {
                System.out.println("\n==========网络通信系统二级菜单(用户"+userId+")==========");
                System.out.println("\t\t 1 显示在线用户列表");
                System.out.println("\t\t 2 群发消息");
                System.out.println("\t\t 3 私聊消息");
                System.out.println("\t\t 4 发送文件");
                System.out.println("\t\t 9 退出系统");
                System.out.println("请输入你的选择：");
                key = scanner.next();
                switch (key) {
                    case "1":
                        System.out.println("显示在线用户列表");
                        userClientService.showOnlineUsers();
                        break;
                    case "2":
                        System.out.println("群发消息");
                        System.out.println("请输入聊天内容：");
                        String contentToAll = scanner.next();
                        userClientService.sendMessageToAll(contentToAll);
                        break;
                    case "3":
                        System.out.println("私聊消息");
                        System.out.println("请输入发送对象：");
                        String targetUser = scanner.next();
                        System.out.println("请输入聊天内容：");
                        String content = scanner.next();
                        userClientService.sendMessageToUser(targetUser, content);
                        break;
                    case "4":
                        System.out.println("发送文件");
                        break;
                    case "9":
                        userClientService.exit();
                        loop = false;
                        break;
                }
            }
        } else {
            System.out.println("==========登录失败==========");
        }
    }

}
