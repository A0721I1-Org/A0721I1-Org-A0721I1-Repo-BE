package projecta07.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import projecta07.model.User;
import projecta07.service.IUserService;

@RestController
@RequestMapping("/manager/api/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/update_password")
    public ResponseEntity<String> processUpdatePassword(@RequestParam(value="userName", required = false) String userName,
                                                        @RequestParam(value="password", required = false) String password) {
        User user = userService.getUserByUsername(userName);
        String message = "";
        if (user == null) {
            message = "User khong ton tai";
        } else {
            // Ma hoa password
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            userService.save(user);
            message = "Đã thay đổi password thành công";
        }
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
