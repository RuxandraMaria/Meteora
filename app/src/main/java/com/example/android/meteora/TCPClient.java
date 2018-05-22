package com.example.android.meteora;


import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

class ConnectionFlag
{
    public static final int S_REGISTER = 0;
    public static final int S_REGISTER_NOTIFY = 1;
    public static final int S_LOGIN = 2;
    public static final int S_LOGIN_OK = 3;
    public static final int S_LOGIN_NAME = 4;
    public static final int S_LOGIN_PASSWORD = 5;
    public static final int S_MESSAGE = 6;
    public static final int S_INIT_DATA = 7;
    public static final int S_STOP = 8;
}

class MessageType
{
    public static final int TEXT = 0;
    public static final int FILE = 0;
}



/// User class

class TCPNotifications extends Thread
{
    private DataInputStream inputStream;
    private AtomicBoolean stopThread;
    TCPClient client;

    public TCPNotifications(TCPClient client)
    {
        this.client = client;
        this.inputStream = client.inputStream;
        this.stopThread = new AtomicBoolean(false);
    }

    // Make sure this method is called before stopping the main tcp thread
    public void stopNotifications()
    {
        this.stopThread.set(true);
    }

    public String GetMessageString()
    {
        String value = "";

        try
        {
            int num = BitConverter.ReadInt32(inputStream);
            byte[] arr = new byte[num];
            inputStream.readFully(arr);

            value = new String(arr);
        } catch (Exception e) { e.printStackTrace(); }

        return value;
    }

    public User RecieveUser()
    {
        User usr = new User();
        usr.ID = Integer.parseInt(GetMessageString());

        System.out.println("usr id" + usr.ID);
        usr.userName = GetMessageString();

        if (usr.userName.compareTo(TCPClient.User) == 0)
        {
            TCPClient.UserID = usr.ID;
        }

        System.out.println("usr UserName " + usr.userName);
        usr.password = GetMessageString();
        usr.nickName = GetMessageString();
        usr.Status = GetMessageString();
        usr.Missions = GetMessageString();

        System.out.println("usr UserName " + usr.password);
        System.out.println("usr UserName " + usr.nickName);
        System.out.println("usr UserName " + usr.Status);
        System.out.println("usr UserName " + usr.Missions);

        return usr;
    }

    public ArrayList<User> ReceiveInitData()
    {
        ArrayList<User> users = new ArrayList<>();

        try
        {
            int num = BitConverter.ReadInt32(inputStream);
            for (int i = 0; i < num; i++)
            {
                User usr = new User();
                usr.ID = Integer.parseInt(GetMessageString());

                System.out.println("usr id" + usr.ID);
                usr.userName = GetMessageString();

                if (usr.userName.compareTo(TCPClient.User) == 0)
                {
                    TCPClient.UserID = usr.ID;
                }

                System.out.println("usr UserName " + usr.userName);
                usr.password = GetMessageString();
                usr.nickName = GetMessageString();
                usr.Status = GetMessageString();
                usr.Missions = GetMessageString();
                users.add(usr);
            }

        }
        catch (Exception e) { e.printStackTrace(); }

        return users;
    }

    public SendMessage ReceiveMessage()
    {
        int from = BitConverter.ReadInt32(inputStream);
        int myID = BitConverter.ReadInt32(inputStream);
        int messType = BitConverter.ReadInt32(inputStream);

        String message = GetMessageString();
        System.out.println("Received from [" + from + "]: " + message);

        SendMessage ret = new SendMessage(from, message);

        return ret;
    }


    public void run()
    {
        boolean notifAlive = true;
        while (notifAlive)
        {
            try
            {
                int serverFlag = BitConverter.ReadInt32(inputStream);
                System.out.println();
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);
                System.out.println("Notification: " + serverFlag);


                switch (serverFlag)
                {
                    case ConnectionFlag.S_LOGIN_OK:
                    {
                        System.out.println("Login ok");

                        Login.mainHandler.post(new HandlerJob(ConnectionFlag.S_LOGIN_OK));

                        break;
                    }

                    case ConnectionFlag.S_LOGIN_NAME:
                    {
                        System.out.println("Login NO name");

                        Login.mainHandler.post(new HandlerJob(ConnectionFlag.S_LOGIN_NAME));

                        break;
                    }

                    case ConnectionFlag.S_LOGIN_PASSWORD:
                    {
                        System.out.println("Login NO pass");

                        Login.mainHandler.post(new HandlerJob(ConnectionFlag.S_LOGIN_PASSWORD));

                        break;
                    }

                    case ConnectionFlag.S_INIT_DATA:
                    {
                        System.out.println("S_INIT_DATA");

                        ArrayList<User> users = ReceiveInitData();
                        for (User usr : users)
                        {
                            if (usr.userName.compareTo(Login.LOGIN_NAME) == 0)
                            {
                                Login.MY_ID = usr.ID;
                                break;
                            }
                        }

                        Login.mainHandler.post(new HandlerJob(ConnectionFlag.S_INIT_DATA, users));


                        break;
                    }

                    case ConnectionFlag.S_REGISTER_NOTIFY:
                    {
                        System.out.println("S_INIT_DATA");

                        User user = RecieveUser();
                        Login.mainHandler.post(new HandlerJob(ConnectionFlag.S_REGISTER_NOTIFY, user));

                        break;
                    }

                    case ConnectionFlag.S_MESSAGE:
                    {
                        SendMessage messge = ReceiveMessage();

                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        System.out.println("RECEIVIGN: FROM : " + messge.dest + " MESSAGE: " + messge.message);
                        Login.mainHandler.post(new HandlerJob(ConnectionFlag.S_MESSAGE, messge));
                        break;
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

class UserLogin
{
    public String userName;
    public String password;

    public UserLogin(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }
}

class SendMessage
{
    public int dest;
    public String message;

    public SendMessage(int dest, String message)
    {
        this.dest = dest;
        this.message = message;
    }
}


class BitConverter
{
    public static byte[] ToInt32(int value)
    {
        byte[] buffer = new byte[4];
        int offset = 0;

        buffer[offset++] = (byte)value;
        buffer[offset++] = (byte)(value>>8);
        buffer[offset++] = (byte)(value>>16);
        buffer[offset++] = (byte)(value>>24);

        return buffer;
    }

    public static int ReadInt32(DataInputStream in)
    {
        int value = 0;
        try
        {
            int ch1 = in.read();
            int ch2 = in.read();
            int ch3 = in.read();
            int ch4 = in.read();

            value = (ch1 + (ch2 << 8) + (ch3 << 16) + (ch4 << 24));

        } catch (Exception e) { e.printStackTrace(); }

        return value;
    }
}



class TCPClient extends Thread {

    private int serverPort;
    private String ip;
    private int action;
    private Object data;


    static public String User;
    static public String Password;
    static public int UserID;


    private class Actions {
        public int action;
        public Object data;

        public Actions(int action, Object data) {
            this.action = action;
            this.data = data;
        }
    }

    private Queue<Actions> actions;
    private Semaphore receivedActions;
    public DataInputStream inputStream;
    public DataOutputStream outputStream;

    public void postAction(int action, Object data)
    {
        System.out.println();
        System.out.println("POSTING ACTION: " + action);

        actions.add(new Actions(action, data));
        // Signal action received
        this.receivedActions.release();

        try {
            Thread.sleep(1000);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private Actions getAction()
    {
        try
        {
            this.receivedActions.acquire();
        } catch (Exception e) { e.printStackTrace(); }

        return actions.remove();
    }

    public TCPClient(String ip, int serverPort)
    {
        this.receivedActions = new Semaphore(0);
        this.ip = ip;
        this.serverPort = serverPort;
        this.actions = new LinkedList<>();
    }

    public void run()
    {
        Socket server = null;
        try
        {
            // Connect to server
            server = new Socket(ip, serverPort);

            inputStream = new DataInputStream(server.getInputStream());
            outputStream = new DataOutputStream(server.getOutputStream());

            // Run notifications

            TCPNotifications notifications = new TCPNotifications(this);
            notifications.start();

            boolean clientAlive = true;
            while (clientAlive)
            {
                // Wait actions
                Actions act = getAction();
                this.action = act.action;
                this.data = act.data;

                System.out.println("SEMAPHORE RELEASED: " + this.action);

                switch (action)
                {
                    case ConnectionFlag.S_LOGIN:
                    {
                        System.out.println("Login: ");

                        outputStream.write(BitConverter.ToInt32(ConnectionFlag.S_LOGIN));
                        UserLogin userData = (UserLogin) data;

                        TCPClient.User = userData.userName;
                        TCPClient.Password = userData.password;

                        outputStream.write(BitConverter.ToInt32(userData.userName.length()));
                        outputStream.writeBytes(userData.userName);

                        outputStream.write(BitConverter.ToInt32(userData.password.length()));
                        outputStream.writeBytes(userData.password);

                        break;
                    }

                    case ConnectionFlag.S_MESSAGE:
                    {
                        SendMessage sendMessage = (SendMessage) data;
                        outputStream.write(BitConverter.ToInt32(ConnectionFlag.S_MESSAGE));

                        // From
                        outputStream.write(BitConverter.ToInt32(Login.MY_ID));


                        // Dest
                        System.out.println("dest " + sendMessage.dest);
                        outputStream.write(BitConverter.ToInt32(sendMessage.dest));

                        outputStream.write(BitConverter.ToInt32(MessageType.FILE));


                        outputStream.write(BitConverter.ToInt32(sendMessage.message.length()));

                        outputStream.writeBytes(sendMessage.message);

                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            e.printStackTrace();
            e.printStackTrace();
            e.printStackTrace();
            e.printStackTrace();
            e.printStackTrace();
            e.printStackTrace();
            e.printStackTrace();
            e.printStackTrace();
            e.printStackTrace();
            e.printStackTrace();
        }
        finally
        {
            if (server != null)
            {
                try
                {
                    server.close();
                } catch (IOException e) { e.printStackTrace(); }
            }
        }
    }
}

