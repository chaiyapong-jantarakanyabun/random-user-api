package th.co.the1.randomuserapi.service.rest

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.util.UriComponentsBuilder
import th.co.the1.randomuserapi.interceptor.ClientHttpRequestInterceptorImplement
import java.net.URI


@Service
class RandomUserApiService {

    fun call(seed: String): String {
        val uri: URI = UriComponentsBuilder
            .fromUri(URI.create("https://randomuser.me/api/"))
            .queryParam("seed", seed)
            .build()
            .toUri()
        val restTemplate = RestTemplate()
        val interceptors = restTemplate.interceptors
        interceptors.add(ClientHttpRequestInterceptorImplement())
        restTemplate.interceptors = interceptors
        return restTemplate.getForObject<String>(uri)
    }

}