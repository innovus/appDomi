package com.google.devrel.training.conference.servlet;

import com.google.appengine.api.utils.SystemProperty;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet for sending a notification e-mail.
 */
public class SendConfirmationEmailServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(
            SendConfirmationEmailServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String conferenceInfo = request.getParameter("conferenceInfo");
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        String body = "pulguita, acabaste de crear una conferencia.\n" + conferenceInfo;
        try {
            Message message = new MimeMessage(session);
            //quien manda el mensaje
            InternetAddress from = new InternetAddress(
                    String.format("noreply@%s.appspotmail.com",
                            SystemProperty.applicationId.get()), "Central de Conferencias");
            message.setFrom(from);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, ""));
          //asunto
            message.setSubject("Pulguita creaste una nueva conferencia!");
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            LOG.log(Level.WARNING, String.format("Failed to send an mail to %s", email), e);
            throw new RuntimeException(e);
        }
    }
}
