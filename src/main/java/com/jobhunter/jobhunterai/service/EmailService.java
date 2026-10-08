package com.jobhunter.jobhunterai.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationEmail(String to, String verificationUrl) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("JobHunterAI — Verifica tu correo");

        message.setText("""
                Hola,

                Has creado una cuenta en JobHunterAI.

                Para verificar tu dirección de correo, pulsa el siguiente enlace:

                %s

                Este enlace caduca en 24 horas y solo puede utilizarse una vez.

                Si no has creado esta cuenta, puedes ignorar este correo.

                JobHunterAI
                """.formatted(verificationUrl));

        mailSender.send(message);
    }

    public void sendPasswordResetEmail(String to, String resetUrl) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("JobHunterAI — Restablece tu contraseña");

        message.setText("""
            Hola,

            Hemos recibido una solicitud para restablecer la contraseña de tu cuenta de JobHunterAI.

            Para establecer una nueva contraseña, pulsa el siguiente enlace:

            %s

            Este enlace caduca en 1 hora y solo puede utilizarse una vez.

            Si no has solicitado restablecer tu contraseña, puedes ignorar este correo.

            JobHunterAI
            """.formatted(resetUrl));

        mailSender.send(message);
    }
}