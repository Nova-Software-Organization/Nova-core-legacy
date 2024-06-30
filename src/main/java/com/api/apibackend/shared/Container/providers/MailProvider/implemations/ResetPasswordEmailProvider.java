/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Aviso! O mail provider sera ativado quando se tratar do ambiente local de desenvolvimento da aplicação!
 */
package com.api.apibackend.shared.Container.providers.MailProvider.implemations;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.api.apibackend.shared.Container.providers.MailProvider.IResetPasswordMailProvider;

import jakarta.mail.MessagingException;

public class ResetPasswordEmailProvider implements IResetPasswordMailProvider {
    private final TemplateEngine templateEngine;

    public ResetPasswordEmailProvider(TemplateEngine templateEngine) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateResolver.setPrefix("templates/email/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");

        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(templateResolver);

        this.templateEngine = springTemplateEngine;
    }

    public void resetPasswordMail(MimeMessageHelper helper, String resetCode, String templateType, String user) throws MessagingException {
        helper.setSubject("Redefinição de Senha - Código de Verificação");
        Context context = new Context();
        context.setVariable("resetCode", resetCode);
        context.setVariable("user", user);
        

        String templatePath = "reset-password" + "/" + templateType;
        String htmlContent = templateEngine.process(templatePath, context);
        helper.setText(htmlContent, true);
    }
}
