import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AuthServer extends Server{

    //       security key   object
    private Map<String, SecurityObject> loggedInList;
    private List<ContentServer> contentServers;
    private Map<String, String> users;

    public void setContentServers(List<ContentServer> contentServers) {
        this.contentServers = contentServers;
    }

    public void setUsers(String username, String password) {
        this.users.put(username, password);
    }

    AuthServer(String IP) {
        super(IP);
        users = new HashMap<>();
        loggedInList = new HashMap<>();
    }

    /**
     * helper function to determine if user has timed out
     * if the response is in the hashmap, meaning the user have logged in before;
     * update the timestamp if it hasn't expire.
     * @param response client input
     * @return `True` if the user is in authorizedObjects and timestamp hasn't expire
     */
    private boolean validate(Response response) {
        if (loggedInList.containsKey(response.key)) {

            if (loggedInList.get(response.key).getTime().timeStamp >= System.currentTimeMillis()) {
                loggedInList.get(response.key).updateTime();
                return true;
            }
        }
        return false;
    }


    /**
     * helper function to check if the username and password is valid
     * @param username client input
     * @param password client input
     * @return `True` if username and password matches database.
     */
    private boolean credentialInDatabase(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    /**
     * if `request` is true, meaning client wants content
     *      - validate the response
     * @param response input information from client
     * @return requested contents
     */
    public Response getContent(Response response) {
        if (response.request && validate(response)) {
            // TODO populate content servers
            response.content = contentServers.get(0).fetchContent(10);
            // TODO notify nearby clusters through backbone that user is active
            return response;
        }
        return response;
    }

    /**
     * if the username and password matches database
     *      we create a security object and put it in hashmap and notify nearby
     *      CDN clusters through backbone
     * @param username user username
     * @param password user password
     * @return login status
     */
    public boolean login(String username, String password) {
        if (credentialInDatabase(username, password)) {
            SecurityObject securityObject = new SecurityObject(username, password);
            loggedInList.put(securityObject.getHashKey(), securityObject);
            // TODO notify nearby clusters through backbone
            return true;
        }
        return false;
    }

    /**
     * with valid username and password, remove the user from logged in list
     * @param username client input
     * @param password client input
     */
    public void logout(String username, String password) {
        if (credentialInDatabase(username, password)) {
            loggedInList.remove(username);
            // TODO notify nearby clusters through backbone
        }
    }


}
