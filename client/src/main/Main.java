package main;

import java.io.*;
import java.net.Socket;

public class Main {
    private static String address;
    private static int port;

    public static void main(String[] args) throws IOException {
        address = "127.0.0.1";
        port = 8081;
        Client client = new Client(address, port);

        client.login();

        loggedInActions(client);
    }

    /**
     * 1: get content
     * 2: switch AS
     * 3: logout
     * 4: exit
     */
    private static void loggedInActions(Client client) throws IOException {
        while (true) {
            int input = getUserInput();
            if (input == 3) {
                client.logout();
                break;
            }
            switch (input) {
                case 1:
                    client.getContent();
                    break;
                case 2:
                    client.switchAS();
                    break;
            }
        }
    }

    private static int getUserInput() throws IOException {
        System.out.println("1: get content");
        System.out.println("2: switch AS and get content");
        System.out.println("3: logout");
        System.out.print("Enter your input: ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        int num = input.charAt(0) - 48;
        if (num == 1 || num == 2 || num == 3) {
            return num;
        }
        return getUserInput();
    }
}
