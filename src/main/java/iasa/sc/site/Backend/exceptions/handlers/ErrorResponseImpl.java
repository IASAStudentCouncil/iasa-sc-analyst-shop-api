package iasa.sc.site.Backend.exceptions.handlers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

@RequiredArgsConstructor
public class ErrorResponseImpl implements ErrorResponse {
    private final String errorCode;
    private final String errorMessage;

    @Override
    @NonNull
    public HttpStatusCode getStatusCode() {
        return HttpStatusCode.valueOf(Integer.parseInt(errorCode));
    }

    @Override
    @NonNull
    public ProblemDetail getBody() {
        ProblemDetail details = ProblemDetail.forStatus(getStatusCode());
        details.setDetail(errorMessage);
        return details;
    }
}
