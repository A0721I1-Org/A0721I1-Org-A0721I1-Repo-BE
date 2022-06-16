package projecta07.controller;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import projecta07.exception.EmployeeNotFoundException;
import projecta07.model.Employee;
import projecta07.service.IEmployeeService;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/account")
public class ForgotPasswordController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/forgot_password")
    public ResponseEntity<String> processForgotPasswordForm(@RequestBody String comingEmail) {
        String message = "";
        String email = comingEmail;
        String token = RandomString.make(45);
        System.out.println("Email:" + email);
        System.out.println("Token:" + token);
        Employee employee = employeeService.getByEmail(email);
        try {
            // Hàm kiêm tra email này của người dùng nào, nếu không có trả về exception, có sẽ thực hiện set token.
            employeeService.updateResetPasswordToken(token, email);
            // Tạo đường dẫn đến form đổi mật khẩu
            String resetPasswordLink = "http://localhost:4200/"+"reset_password?token=" + token;
            System.out.println("resetpasswordlink: " + resetPasswordLink);
            // Hàm để gửi email
            sendEmail(email, resetPasswordLink);
            message = "We have sent a reset password link to your email. Please check.";

        } catch (EmployeeNotFoundException exception) {
            message = exception.getMessage();
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedEncodingException | MessagingException exception) {
            message = "Error while sending email";
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    // Hàm được định nghĩa.
    private ResponseEntity<Void> sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@a0721i1.com", "A0721i1 group- Coffee shop");
        helper.setTo(email);
        System.out.println("email1: " + email);
        String subject = "Here's the link to reset your password";
        String content = "<p> Hello,</p>"
                + "<p>You have requested to reset your password,</p>"
                + "<p> Click the link below to change your password</p>"
                + "<p><b><a href=\"" + resetPasswordLink + "\">Change my password </a><b></p>"
                + "<p>Ignore this email if you do remember your password, or you have not made the request</p>";
        helper.setSubject(subject);
        helper.setText(content, true);
        // Đoạn nay de gui email/ không có sẽ rơi exception
        mailSender.send(message);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/reset_password")
    public ResponseEntity<String> processResetPassword(@PathVariable("token") String comingToken,
                                                       @PathVariable("password") String newPassword) {
        String message = "";      // Tin nhắn gửi lên giao diện
        String token = comingToken;
        String password = newPassword;
        Employee employee = employeeService.getByResetPasswordToken(token);
        if (employee == null) {
            message = "Invalid token";
        } else {
            employeeService.updatePassword(employee, password);
            message = "You have succesfully changed your password";
        }
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
