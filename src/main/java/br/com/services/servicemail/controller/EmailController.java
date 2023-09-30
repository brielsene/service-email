package br.com.services.servicemail.controller;

import br.com.services.servicemail.model.EmailDetails;
import br.com.services.servicemail.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendSimpleMail")
    public ResponseEntity sendMail(@RequestBody EmailDetails details){
        emailService.sendEmail(details);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/sendMailWithAttachment")
    public ResponseEntity sendMailWithAttachment(@RequestBody EmailDetails details) throws MessagingException {
        emailService.sendEmailWitchAttachment(details);
        return ResponseEntity.ok().build();
    }


}
