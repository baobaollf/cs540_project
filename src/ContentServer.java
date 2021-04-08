public class ContentServer extends Server {

    String content;

    ContentServer(String IP) {
        super(IP);
    }

    public String fetchContent(int id) {
        return content + id;
    }
}
