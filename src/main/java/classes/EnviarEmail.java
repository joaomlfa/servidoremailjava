/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Joao
 */
public class EnviarEmail {

    private String email;
    private String password;
    private String hostName;
    private String sslPorta;
    private int servidor;
    private String subject;
    private String to;
    private String message;
    private String caminho;
    private String arquivoDescricao;
    private String arquivoNome;
          

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getServidor() {
        return servidor;
    }

    public String getHostName() {
        return hostName;
    }

    public String getSslPorta() {
        return sslPorta;
    }

    public String getSubject() {
        return subject;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    public String getCaminho() {
        return caminho;
    }

    public String getArquivoDescricao() {
        return arquivoDescricao;
    }

    public String getArquivoNome() {
        return arquivoNome;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public void setArquivoDescricao(String arquivoDescricao) {
        this.arquivoDescricao = arquivoDescricao;
    }

    public void setArquivoNome(String arquivoNome) {
        this.arquivoNome = arquivoNome;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setSslPorta(String sslPorta) {
        this.sslPorta = sslPorta;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setServidor(int servidor) {
        this.servidor = servidor;
    }

    public EnviarEmail(String email, String password, int servidor, String subject, String to, String message) {
        this.email = email;
        this.password = password;
        this.servidor = servidor;
        this.subject = subject;
        this.to = to;
        this.message = message;
    }
     public EnviarEmail(String email, String password, int servidor, String subject, String to, String message, String caminho, String arquivoDescricao, String arquivoNome) {
        this.email = email;
        this.password = password;
        this.servidor = servidor;
        this.subject = subject;
        this.to = to;
        this.message = message;
        this.arquivoDescricao = arquivoDescricao;
        this.caminho = caminho;
        this.arquivoNome = arquivoNome;
    }
    

    public void enviar() {
        SimpleEmail enviarEmail = new SimpleEmail();
        verificarServidor();
        enviarEmail.setStartTLSEnabled(true);
        enviarEmail.setStartTLSRequired(true);
        enviarEmail.setSSLOnConnect(true);
        enviarEmail.setHostName(this.hostName);
        enviarEmail.setSslSmtpPort(this.sslPorta);
        enviarEmail.setAuthenticator(new DefaultAuthenticator(this.email, this.password));
        try {
            enviarEmail.setFrom(this.email);
            
            enviarEmail.setDebug(true);
            
            enviarEmail.setSubject(this.subject);
            enviarEmail.addTo(this.to);
            enviarEmail.setMsg(this.message);
            enviarEmail.send();
            
        } catch (EmailException ex) {
            Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void enviarComAnexo() {
        MultiPartEmail enviarEmail = new MultiPartEmail();
        verificarServidor();
        enviarEmail.setStartTLSEnabled(true);
        enviarEmail.setStartTLSRequired(true);
        enviarEmail.setSSLOnConnect(true);
        enviarEmail.setHostName(this.hostName);
        enviarEmail.setSslSmtpPort(this.sslPorta);
        enviarEmail.setAuthenticator(new DefaultAuthenticator(this.email, this.password));
        try {
            enviarEmail.setFrom(this.email);
            
            enviarEmail.setDebug(true);
            
            enviarEmail.setSubject(this.subject);
            enviarEmail.addTo(this.to);
            enviarEmail.setMsg(this.message);
            
            EmailAttachment anexo = new EmailAttachment();
            anexo.setPath(this.caminho);
            anexo.setDisposition(EmailAttachment.ATTACHMENT);
            anexo.setDescription(this.arquivoDescricao);
            anexo.setName(this.arquivoNome);
            enviarEmail.attach(anexo);
            enviarEmail.send();
            
        } catch (EmailException ex) {
            Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void verificarServidor() {
        if (getServidor() == 1) {
            setHostName("smtp.gmail.com");
            setSslPorta("465");

        }
        if (getServidor() == 2) {
            setHostName("smtp.live.com");
            setSslPorta("587");
        }
        if (getServidor() == 3) {
            setHostName("smtp.office365.com");
            setSslPorta("587");
        }
        if (getServidor() == 4) {
            setHostName("smtp.mail.yahoo.com");
            setSslPorta("465");
        }
        if (getServidor() == 5) {
            setHostName("smtps.bol.com");
            setSslPorta("465");
        }
    }
}
