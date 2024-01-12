/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.shared.Container.providers.MailProvider;

import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;

public interface IMailProvider {
    public void resetPasswordMail(MimeMessageHelper helper, String resetCode, String templateType) throws MessagingException;
}
