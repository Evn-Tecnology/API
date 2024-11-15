package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.exception.ResourceNotFoundException;
import com.TecnologyGroup.Event_Tecnology.integration.email.dto.Mail;
import com.TecnologyGroup.Event_Tecnology.integration.email.service.EmailService;
import com.TecnologyGroup.Event_Tecnology.model.entity.PasswordResetToken;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import com.TecnologyGroup.Event_Tecnology.repository.PasswordResetTokenRepository;
import com.TecnologyGroup.Event_Tecnology.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Transactional
    public void createAndSendPasswordResetToken(String email) throws Exception {
        User user = userRepository.findAnyUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con email: " + email));

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(UUID.randomUUID().toString());
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiration(10);
        passwordResetTokenRepository.save(passwordResetToken);

        Map<String, Object> model =new  HashMap<>();
        String resetUrl = "http://localhost:4200/#/forgot/" + passwordResetToken.getToken();
        model.put("user", user.getUserEmail());
        model.put("resetUrl", resetUrl);

        Mail mail = emailService.createMail(
                user.getUserEmail(),
                "Restablecer Contraseña",
                model,
                mailFrom
        );

        emailService.sendEmail(mail,"email/password-reset-template");
    }

    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Token de restablecimiento de contraseña no encontrado"));
    }

    public void removeResetToken(PasswordResetToken passwordResetToken) {
        passwordResetTokenRepository.delete(passwordResetToken);
    }

    public boolean isValidToken(String token) {
        return passwordResetTokenRepository.findByToken(token)
                .filter(t->!t.isExpired())
                .isPresent();
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .filter(t->!t.isExpired())
                .orElseThrow(() -> new ResourceNotFoundException("Token invalido o expirado"));

        User user = resetToken.getUser();
        user.setUserPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        passwordResetTokenRepository.delete(resetToken);
    }
}
