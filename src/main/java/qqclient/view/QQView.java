package qqclient.view;

import java.util.Scanner;

/**
 * @author k
 * 2024/3/26 10:46
 * @version 1.0
 */
public class QQView {

    private boolean loop = true;
    private String key = "";

    public static void main(String[] args) {
        new QQView().mainMenu();
        System.out.println("==========感谢使用==========");
    }

    //显示主菜单
    private void mainMenu() {
        while (loop) {
            System.out.println("==========欢迎登录网络通信系统==========");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.println("请输入你的选择");
            Scanner scanner = new Scanner(System.in);
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

        if (true) {
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
                        break;
                    case "2":
                        System.out.println("群发消息");
                        break;
                    case "3":
                        System.out.println("私聊消息");
                        break;
                    case "4":
                        System.out.println("发送文件");
                        break;
                    case "9":
                        loop = false;
                        break;
                }
            }
        } else {
            System.out.println("==========登录失败==========");
        }
    }
}
