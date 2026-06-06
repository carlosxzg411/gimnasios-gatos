package com.gimnasios.notification;

import com.gimnasios.order.Order;
import com.gimnasios.order.OrderItem;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${app.email.from}")
    private String fromEmail;

    @Value("${app.email.fabricante-email}")
    private String fabricanteEmail;

    public void sendInvoiceToCustomer(Order order) {
        try {
            Context context = new Context();
            context.setVariable("order", order);
            context.setVariable("cliente", order.getUsuario());
            String html = templateEngine.process("invoice", context);
            sendHtmlEmail(order.getUsuario().getEmail(), "Factura - Pedido #" + order.getId(), html);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar factura: " + e.getMessage());
        }
    }

    public void sendNotificationToFabricante(Order order) {
        try {
            Context context = new Context();
            context.setVariable("order", order);
            context.setVariable("cliente", order.getUsuario());
            String html = templateEngine.process("fabricante-notification", context);
            sendHtmlEmail(fabricanteEmail, "Nuevo Pedido #" + order.getId() + " - Especificaciones", html);
        } catch (Exception e) {
            throw new RuntimeException("Error al notificar fabricante: " + e.getMessage());
        }
    }

    private void sendHtmlEmail(String to, String subject, String html) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar email: " + e.getMessage());
        }
    }
}
