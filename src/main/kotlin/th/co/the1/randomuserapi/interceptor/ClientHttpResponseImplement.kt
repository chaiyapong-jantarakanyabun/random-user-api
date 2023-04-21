package th.co.the1.randomuserapi.interceptor

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import java.io.InputStream
import java.nio.charset.StandardCharsets

class ClientHttpResponseImplement(
    private val clientHttpResponse: ClientHttpResponse,
    private val bodyResponse: String
) : ClientHttpResponse {

    override fun getHeaders(): HttpHeaders {
        return this.clientHttpResponse.headers
    }

    override fun getBody(): InputStream {
        return this.bodyResponse.byteInputStream(StandardCharsets.UTF_8)
    }

    override fun close() {
        this.clientHttpResponse.close()
    }

    override fun getStatusCode(): HttpStatus {
        return this.clientHttpResponse.statusCode
    }

    override fun getRawStatusCode(): Int {
        return this.clientHttpResponse.rawStatusCode
    }

    override fun getStatusText(): String {
        return this.clientHttpResponse.statusText
    }

}