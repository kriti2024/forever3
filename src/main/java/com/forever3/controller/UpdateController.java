package com.forever3.controller;

import com.forever3.model.UserModel;
import com.forever3.service.ProfileService;
import com.forever3.util.PasswordUtil;
import com.forever3.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = {"/updateProfile"})
@MultipartConfig
public class UpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ProfileService profileService = new ProfileService();

    // Folder to save uploaded images - adjust this path according to your project
    private static final String UPLOAD_DIR = "resources/imagescustomer";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String oldUsername = request.getParameter("oldUsername");
        String newUsername = request.getParameter("userName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String number = request.getParameter("number");
        String password = request.getParameter("password");

        // Encrypt password if provided
        String encryptedPassword = null;
        if (password != null && !password.trim().isEmpty()) {
            encryptedPassword = PasswordUtil.encrypt(newUsername, password);
        }

        // Handle profile image upload
        String newImageUrl = null;
        Part filePart = request.getPart("profileImage");
        if (filePart != null && filePart.getSize() > 0) {
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadPath = applicationPath + File.separator + UPLOAD_DIR;

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String submittedFileName = filePart.getSubmittedFileName();
            String fileExtension = "";
            if (submittedFileName != null && submittedFileName.contains(".")) {
                fileExtension = submittedFileName.substring(submittedFileName.lastIndexOf("."));
            }
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
            filePart.write(uploadPath + File.separator + uniqueFileName);
            newImageUrl = uniqueFileName;
        } else {
            UserModel existingUser = profileService.getUserProfile(oldUsername);
            if (existingUser != null) {
                newImageUrl = existingUser.getImageUrl();
            }
        }

        // 🔍 Add these debug logs here
        System.out.println("Old username: " + oldUsername);
        System.out.println("New username: " + newUsername);
        System.out.println("First name: " + firstName);
        System.out.println("Password encrypted? " + (encryptedPassword != null));
        System.out.println("Image URL: " + newImageUrl);

        UserModel userToUpdate = new UserModel();
        userToUpdate.setUserName(newUsername);
        userToUpdate.setFirstName(firstName);
        userToUpdate.setLastName(lastName);
        userToUpdate.setEmail(email);
        userToUpdate.setNumber(number);
        userToUpdate.setImageUrl(newImageUrl);
        if (encryptedPassword != null) {
            userToUpdate.setPassword(encryptedPassword);
        }

        boolean updated = profileService.updateUserProfile(oldUsername, userToUpdate);

        // 🔍 Print update result
        System.out.println("Update result: " + updated);

        if (updated) {
            if (oldUsername != null && !oldUsername.equals(newUsername)) {
                SessionUtil.setAttribute(request, "username", newUsername);
            }
            request.setAttribute("successMessage", "Profile updated successfully.");
        } else {
            request.setAttribute("errorMessage", "Unable to update profile. Please try again.");
        }

        UserModel updatedUser = profileService.getUserProfile(newUsername);
        request.setAttribute("user", updatedUser);
        request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
    }
}
