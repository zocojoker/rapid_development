package com.zoco.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author zoco
 * @creat 2019-11-20-15:29
 */
public class BIOSocket {
    private final static int port = 4343; //端口号

    public static void main(String[] args) {
        // Socket 服务器端（简单的发送信息）
        Thread sThread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                while (true) {
                    // 等待连接
                    Socket socket = serverSocket.accept();
                    Thread sHandlerThread = new Thread(() -> {
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
                                    printWriter.println("hello world！");
                                    printWriter.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 1000, 1000);

                    });
                    sHandlerThread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        sThread.start();

        // Socket 客户端（接收信息并打印）
        try (
                Socket cSocket = new Socket(InetAddress.getLocalHost(), port))

        {
            for (; ; ) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
                bufferedReader.lines().forEach(s -> System.out.println("客户端：" + s));
            }
        } catch (UnknownHostException e)

        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
