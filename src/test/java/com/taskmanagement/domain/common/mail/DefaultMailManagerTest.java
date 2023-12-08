package com.taskmanagement.domain.common.mail;

import freemarker.template.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class DefaultMailManagerTest {

  @TestConfiguration
  static class DefaultMessageCreatorConfiguration {
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreemarkerConfiguration() {
      FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
      factoryBean.setTemplateLoaderPath("/mail-templates/");
      return factoryBean;
    }
  }

  @Autowired
  private Configuration configuration;
  private Mailer mailerMock;
  private DefaultMailManager instance;

  @Before
  public void setUp() {
    mailerMock = mock(Mailer.class);
    instance = new DefaultMailManager("test@test.com", mailerMock, configuration);
  }

  @Test(expected = IllegalArgumentException.class)
  public void send_nullEmailAddress_shouldFail() {
    instance.send(null, "Test subject", "test.ftl");
  }

  @Test(expected = IllegalArgumentException.class)
  public void send_emptyEmailAddress_shouldFail() {
    instance.send("test@test.com", null, "test.ftl");
  }

  @Test(expected = IllegalArgumentException.class)
  public void send_nullSubject_shouldFail() {
    instance.send("test@test.com", "Test subject", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void send_emptyTemplateName_shouldFail() {
    instance.send("test@test.com", "Test subject", "");
  }

  @Test
  public void send_validParameters_shouldSucceed() {
    String to = "system@test.com";
    String subject = "Test subject";
    String templateName ="test.ftl";

    instance.send(to, subject, templateName, MessageVariable.from("name", "test"));
    ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
    verify(mailerMock).send(messageArgumentCaptor.capture());

    Message messageSent = messageArgumentCaptor.getValue();
    assertEquals(to, messageSent.getTo());
    assertEquals(subject, messageSent.getSubject());
    assertEquals("test@test.com", messageSent.getFrom());
    assertEquals("Hello, test\n", messageSent.getBody());
  }
}
