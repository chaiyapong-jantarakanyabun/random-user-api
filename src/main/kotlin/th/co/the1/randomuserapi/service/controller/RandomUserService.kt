package th.co.the1.randomuserapi.service.controller

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import th.co.the1.randomuserapi.model.controller.RandomUserResponse
import th.co.the1.randomuserapi.service.rest.RandomUserApiService


@Service
class RandomUserService @Autowired constructor(private val randomUserApiService: RandomUserApiService) {

    fun users(seed: String): RandomUserResponse {
        val node: JsonNode = ObjectMapper().readValue(this.randomUserApiService.call(seed))
        val randomUserResponse = RandomUserResponse()
        if (null != node.get("results")) {
            val results: JsonNode = node.get("results")
            var name: JsonNode
            for (result in results) {
                if (null != result.get("name")) {
                    name = result.get("name")
                    if (null != name.get("first")) {
                        randomUserResponse.firstname = "${name.get("title").textValue()} ${name.get("first").textValue()}"
                    }
                    if (null != name.get("last")) {
                        randomUserResponse.lastname = name.get("last").textValue()
                    }
                }
                if (null != result.get("gender")) {
                    randomUserResponse.gender = result.get("gender").textValue()
                }
                if (null != result.get("email")) {
                    randomUserResponse.email = result.get("email").textValue()
                }
            }
        }
        return randomUserResponse
    }

}