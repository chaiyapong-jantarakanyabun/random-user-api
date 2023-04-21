package th.co.the1.randomuserapi.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import th.co.the1.randomuserapi.model.controller.RandomUserResponse
import th.co.the1.randomuserapi.service.controller.RandomUserService

@RestController
class RandomUserController @Autowired constructor(private val randomUserService: RandomUserService) {

    @GetMapping("/v1/users/{seed}")
    fun users(@PathVariable seed: String): RandomUserResponse {
        return this.randomUserService.users(seed)
    }

}

