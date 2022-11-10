package com.piratenKapern;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Player{

    private static final long serialVersionUID = 1L;
    private String name;
    private int score;
    private int playerId;

    private static Client clientConnection;

    public Player(){
        name = "";
        score = 0;
        playerId = 0;
    }

    public Player(String name){
        this.name = name;
        score = 0;
        playerId = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    /*
     * ----------Network Stuff------------
     */

    public void connectToClient() {
        clientConnection = new Client();
    }

    public void connectToClient(int port) {
        clientConnection = new Client(port);
    }

    public void startGame(){
        //msg = You are player X
        String msgFromServer = clientConnection.receiveString();
        String msgToServer = "";
        System.out.println(msgFromServer);
        Scanner in = new Scanner(System.in);

        while(true){
            msgFromServer = clientConnection.receiveString();
            if(msgFromServer.contains("dead") || msgFromServer.contains("Skulls")){
                System.out.println(Config.ANSI_RED + msgFromServer + Config.ANSI_RESET);
            }

            //If the current player wins
            else if(msgFromServer.contains("You have won")){
                System.out.println(Config.ANSI_GREEN + msgFromServer + Config.ANSI_RESET);
            }

            //If another player wins
            else if(msgFromServer.contains("has won")){
                System.out.println(Config.ANSI_RED + msgFromServer + Config.ANSI_RESET);
            }

            else if(msgFromServer.contains("?")){
                System.out.println(msgFromServer);
                msgToServer = in.nextLine();
                clientConnection.sendString(msgToServer);
            }

            else{
                System.out.println(msgFromServer);
            }

        }
    }


    private class Client {
        Socket socket;
        private ObjectInputStream inputFromServer;
        private ObjectOutputStream outputToServer;

        public Client() {
            try {
                socket = new Socket("localhost", Config.PORT);
                outputToServer = new ObjectOutputStream(socket.getOutputStream());
                inputFromServer = new ObjectInputStream(socket.getInputStream());

                playerId = inputFromServer.readInt();

                System.out.println("Connected as Player " + playerId);
                Scanner in = new Scanner(System.in);
                System.out.println("What is your name?");
                String name = in.nextLine();
                sendString(name);

            } catch (IOException ex) {
                System.out.println("Client failed to open");
            }
        }

        public Client(int portId) {
            try {
                socket = new Socket("localhost", portId);
                outputToServer = new ObjectOutputStream(socket.getOutputStream());
                inputFromServer = new ObjectInputStream(socket.getInputStream());

                playerId = inputFromServer.readInt();
                System.out.println("Connected as " + playerId);
            } catch (IOException ex) {
                System.out.println("Client failed to open");
            }
        }


        public String receiveString() {
            try {
                return inputFromServer.readUTF();
            } catch (IOException ex) {
                System.out.println("String not sent");
                ex.printStackTrace();
            }
            return "";
        }


        public void sendString(String str) {
            try {
                outputToServer.writeUTF(str);
                outputToServer.flush();
            } catch (IOException ex) {
                System.out.println("String not sent");
                ex.printStackTrace();
            }
        }

    }


        /*
         * ---------Constructor and Main class-----------
         */
        public static void main(String args[]) {
            Player p = new Player();
            p.connectToClient();
            p.startGame();
        }
    }

