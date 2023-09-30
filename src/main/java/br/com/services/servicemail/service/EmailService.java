package br.com.services.servicemail.service;

import br.com.services.servicemail.model.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmail(EmailDetails details){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(details.getRecipient());
        simpleMailMessage.setText(details.getMessageBody());
        simpleMailMessage.setSubject(details.getSubject());

        javaMailSender.send(simpleMailMessage);
        System.out.println("E-mail enviado com sucesso");
    }

    public void sendEmailWitchAttachment(EmailDetails details) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);


        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(details.getRecipient());
        mimeMessageHelper.setText(details.getMessageBody());
        mimeMessageHelper.setSubject(details.getSubject());

        FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
        mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

        javaMailSender.send(mimeMessageHelper.getMimeMessage());
        System.out.println("E-mail enviado com sucesso");

    }
}
