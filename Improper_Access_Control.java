//Code with vulnerability
public void viewUserProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String userId = request.getParameter("userId");
    User user = getUserFromDatabase(userId);
    response.getWriter().println("User Profile: " + user.getProfile());

// Secure code
public void viewUserProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String userId = request.getParameter("userId");
    User currentUser = getCurrentUser(request);
    if (!currentUser.hasPermission("VIEW_USER_PROFILE") || 
!currentUser.getId().equals(userId)) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
        return;
    }
    User user = getUserFromDatabase(userId);
    response.getWriter().println("User Profile: " + user.getProfile());
}