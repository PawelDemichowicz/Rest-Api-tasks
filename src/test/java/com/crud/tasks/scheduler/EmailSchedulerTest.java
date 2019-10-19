package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {

    @InjectMocks
    EmailScheduler emailScheduler;

    @Mock
    SimpleEmailService simpleEmailService;

    @Mock
    AdminConfig adminConfig;

    @Mock
    TaskRepository repository;

    @Captor
    ArgumentCaptor<Mail> mailArgumentCaptor;

    @Test
    public void sendInformationEmailTest(){
        //Given
        when(repository.count()).thenReturn(1L);
        when(adminConfig.getAdminMail()).thenReturn("AdminMockMail");

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(mailArgumentCaptor.capture(),eq("mail/quantity-tasks-mail"));
        Mail mail = mailArgumentCaptor.getValue();
        Assert.assertEquals(mail.getMailTo(),"AdminMockMail");
        Assert.assertEquals(mail.getSubject(),"Tasks: Once a day email");
        Assert.assertEquals(mail.getMessage(),"Currently in database you got: 1 task");
        Assert.assertEquals(mail.getToCc(),"");
    }
}
