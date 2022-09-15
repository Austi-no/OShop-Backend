package com.austin.Oshop.config.filter;

import com.austin.Oshop.utils.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import javax.servlet.http.*;
import java.io.*;

import static com.austin.Oshop.constants.SecurityConstant.FORBIDDEN_MESSAGE;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 08/Aug/2022 - 2:14 PM
 * @project supportPortal
 */
@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        HttpResponse httpResponse = new HttpResponse(FORBIDDEN.value(), FORBIDDEN, FORBIDDEN.getReasonPhrase().toUpperCase(), FORBIDDEN_MESSAGE);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(FORBIDDEN.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, httpResponse);
        outputStream.flush();
    }
}
