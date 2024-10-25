//Code with vulnerability
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse 
response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simulate checking credentials against a database
        if (checkCredentials(username, password)) {
            request.getSession().setAttribute("user", username);
            response.getWriter().println("Login successful");
        } else {
            response.getWriter().println("Invalid username or password");
        }
    }

    private boolean checkCredentials(String username, String password) {
        // Dummy method: Insecurely compares plain text password
        return "admin".equals(username) && "password123".equals(password);
    }
}

// Secure code
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    private static final Map<String, String> userStore = new HashMap<>();

    static {
        // Pre-store users with hashed passwords
        userStore.put("admin", hashPassword("password123"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse 
response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (checkCredentials(username, password)) {
            request.getSession().setAttribute("user", username);
            response.getWriter().println("Login successful");
        } else {
            response.getWriter().println("Invalid username or password");
        }
    }

    private boolean checkCredentials(String username, String password) {
        // Check if the username exists and the hashed password matches
        String storedPasswordHash = userStore.get(username);
        return storedPasswordHash != null && 
storedPasswordHash.equals(hashPassword(password));
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}