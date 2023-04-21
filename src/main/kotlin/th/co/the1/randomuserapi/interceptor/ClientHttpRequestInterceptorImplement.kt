package th.co.the1.randomuserapi.interceptor

import org.slf4j.LoggerFactory
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.util.StreamUtils
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets


class ClientHttpRequestInterceptorImplement : ClientHttpRequestInterceptor {

    private val logger: org.slf4j.Logger = LoggerFactory.getLogger(this.javaClass)

    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        this.requestLog(request, body)
        val clientHttpResponse: ClientHttpResponse = execution.execute(request, body)
        val bodyResponse: String = StreamUtils.copyToString(clientHttpResponse.body, Charset.defaultCharset())
        val response: ClientHttpResponse = ClientHttpResponseImplement(clientHttpResponse, bodyResponse)
        this.responseLog(response, bodyResponse.toByteArray(StandardCharsets.UTF_8))
        return response
    }


    private fun requestLog(request: HttpRequest, body: ByteArray) {
        logger.debug("===========================request============================================")
        logger.debug("URI         : {}", request.uri)
        logger.debug("Method      : {}", request.method)
        logger.debug("Headers     : {}", request.headers)
        logger.debug("Request body: {}", String(body, StandardCharsets.UTF_8))
        logger.debug("==============================================================================")
    }


    private fun responseLog(response: ClientHttpResponse, body: ByteArray) {
        logger.debug("============================response==========================================")
        logger.debug("Status code  : {}", response.statusCode)
        logger.debug("Status text  : {}", response.statusText)
        logger.debug("Headers      : {}", response.headers)
        logger.debug("Response body: {}", String(body, StandardCharsets.UTF_8))
        logger.debug("==============================================================================")
    }

}