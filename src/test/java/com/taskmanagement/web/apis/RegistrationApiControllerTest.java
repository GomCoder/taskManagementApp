package com.taskmanagement.web.apis;

import com.taskmanagement.utils.JsonUtils;
import com.taskmanagement.config.SecurityConfiguration;
import com.taskmanagement.domain.application.UserService;
import com.taskmanagement.domain.model.user.EmailAddressExistsException;
import com.taskmanagement.domain.model.user.UsernameExistsException;
import com.taskmanagement.web.payload.RegistrationPayload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * REST API 테스트
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes ={SecurityConfiguration.class, RegistrationApiController.class})
@WebMvcTest
public class RegistrationApiControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserService serviceMock;

  @Test
  @WithMockUser
  public void register_blankPayload_shouldFailAndReturn400() throws Exception {
    mvc.perform(post("/api/registrations").with(csrf()))
      .andExpect(status().is(400));
  }

  @Test
  @WithMockUser
  public void register_existedUsername_shouldFailAndReturn400() throws Exception {
    RegistrationPayload payload = new RegistrationPayload();
    payload.setUsername("exist");
    payload.setEmailAddress("test@test.com");
    payload.setPassword("MyPassword!");

    doThrow(UsernameExistsException.class)
      .when(serviceMock)
      .register(payload.toCommand());

    mvc.perform(
        post("/api/registrations").with(csrf())
          .contentType(MediaType.APPLICATION_JSON)
          .content(JsonUtils.toJson(payload)))
      .andExpect(status().is(400))
      .andExpect(jsonPath("$.message").value("Username already exists"));
  }

  @Test
  @WithMockUser
  public void register_existedEmailAddress_shouldFailAndReturn400() throws Exception {
    RegistrationPayload payload = new RegistrationPayload();
    payload.setUsername("test");
    payload.setEmailAddress("exist@test.com");
    payload.setPassword("MyPassword");

    doThrow(EmailAddressExistsException.class)
      .when(serviceMock)
      .register(payload.toCommand());

    mvc.perform(
      post("/api/registrations").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtils.toJson(payload)))
      .andExpect(status().is(400))
      .andExpect(jsonPath("$.message").value("Email address already exists"));
  }

  @Test
  @WithMockUser
  public void register_validPayload_shouldSucceedAndReturn201() throws Exception {
    RegistrationPayload payload = new RegistrationPayload();
    payload.setUsername("test");
    payload.setEmailAddress("test@test.com");
    payload.setPassword("MyPassword");

    doNothing().when(serviceMock)
      .register(payload.toCommand());

    mvc.perform(
      post("/api/registrations").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtils.toJson(payload)))
      .andExpect(status().is(201));
  }

}
