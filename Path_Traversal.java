//Code with vulnerability
public void readFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String fileName = request.getParameter("fileName");
    File file = new File("/secure-dir/" + fileName);
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line;
    while ((line = reader.readLine()) != null) {
        response.getWriter().println(line);
    }
    reader.close(
}

// Secure code
public void readFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String fileName = request.getParameter("fileName");
    if (!isValidFileName(fileName)) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid file name");
        return;
    }
    File file = new File("/secure-dir/" + fileName);
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line;
    while ((line = reader.readLine()) != null) {
        response.getWriter().println(line);
    }
    reader.close();
}
private boolean isValidFileName(String fileName) {
    return fileName != null && !fileName.contains("..") && 
fileName.matches("[a-zA-Z0-9._-]+");
}