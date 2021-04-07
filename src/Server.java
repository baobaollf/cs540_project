public class Server {

    String content;

    Server(String content) {
        this.content = "content";
    }

    public String send() {
        return content;
    }

    public boolean receive(String message) {
        content = message;
        return true;
    }

}
