package pl.knap.shop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.knap.shop.security.model.dto.ChangePassword;
import pl.knap.shop.security.model.dto.EmailObject;
import pl.knap.shop.security.service.LostPasswordService;

@RestController
@RequiredArgsConstructor
public class LostPasswordController {

    private final LostPasswordService lostPasswordService;

    @PostMapping("/lostPassword")
    public void lostPassword(@RequestBody EmailObject emailObject) {
        lostPasswordService.sendLostPasswordLink(emailObject);
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody ChangePassword changePassword) {
        lostPasswordService.changePassword(changePassword);
    }
}