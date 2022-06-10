package kr.rojae.rest.props;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiProp {

    @Value("${api.get1}")
    public String getUrl1;

    @Value("${api.get2}")
    public String getUrl2;

    @Value("${api.post}")
    public String postUrl;

    @Value("${api.put}")
    public String putUrl;

    @Value("${api.patch}")
    public String patchUrl;

    @Value("${api.delete}")
    public String deleteUrl;

}

