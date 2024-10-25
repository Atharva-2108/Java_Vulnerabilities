//Code with vulnerability
public User getUserByUsername(String username) {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    User user = null;

    try {
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        stmt = conn.createStatement();
        String sql = "SELECT * FROM users WHERE username = '" + username + "'";
        rs = stmt.executeQuery(sql);

        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
    }

    return user;
}

// Secure code
public User getUserByUsername(String username) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    User user = null;

    try {
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String sql = "SELECT * FROM users WHERE username = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
    }

    return user;
}