package th.co.the1.randomuserapi

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import th.co.the1.randomuserapi.model.controller.RandomUserResponse
import th.co.the1.randomuserapi.service.controller.RandomUserService
import th.co.the1.randomuserapi.service.rest.RandomUserApiService

@SpringBootTest
class RandomUserServiceTest {

    @Mock
    lateinit var randomUserApiService: RandomUserApiService

    private lateinit var randomUserService: RandomUserService

    @BeforeEach
    fun initService() {
        MockitoAnnotations.openMocks(this)
        this.randomUserService = RandomUserService(this.randomUserApiService)

    }

    @Test
    fun `Test call seed user api success`() {
        Mockito.`when`(this.randomUserApiService.call(Mockito.anyString())).thenReturn(
            "{\"results\":[{\"gender\":\"female\",\"name\":{\"title\":\"Mr\",\"first\":\"Chaiyapong\",\"last\":\"Jantarakanyabun\"},\"location\":{\"street\":{\"number\":9617,\"name\":\"نام قدیم میدان های تهران\"},\"city\":\"تهران\",\"state\":\"گلستان\",\"country\":\"Iran\",\"postcode\":66909,\"coordinates\":{\"latitude\":\"17.2334\",\"longitude\":\"88.9196\"},\"timezone\":{\"offset\":\"+4:30\",\"description\":\"Kabul\"}},\"email\":\"chaiyapong.jantarakanyabun@gmail.com\",\"login\":{\"uuid\":\"2d5afd5e-a598-469f-9933-4edba18bcefb\",\"username\":\"blueelephant921\",\"password\":\"cccc\",\"salt\":\"uJHyJ9ZL\",\"md5\":\"1c609a27f083f9557166876073ffc83b\",\"sha1\":\"f57a35f6b04535b2f3c7024b5befdbcb84e47d7e\",\"sha256\":\"cb364efc8489b343c746a11bee2de4fd6f83c38c4811a6b84b1526a74672e8f5\"},\"dob\":{\"date\":\"1992-02-07T17:56:13.316Z\",\"age\":31},\"registered\":{\"date\":\"2016-03-25T07:13:10.667Z\",\"age\":7},\"phone\":\"005-78896313\",\"cell\":\"0964-597-8705\",\"id\":{\"name\":\"\",\"value\":null},\"picture\":{\"large\":\"https://randomuser.me/api/portraits/women/62.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/women/62.jpg\",\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/women/62.jpg\"},\"nat\":\"IR\"}],\"info\":{\"seed\":\"foobar\",\"results\":1,\"page\":1,\"version\":\"1.4\"}}"
        )
        val randomUserResponse: RandomUserResponse = this.randomUserService.users("foobar")
        Assertions.assertEquals("chaiyapong.jantarakanyabun@gmail.com", randomUserResponse.email)
    }

    @Test
    fun `Test call seed user api no data`() {
        Mockito.`when`(this.randomUserApiService.call(Mockito.anyString())).thenReturn(null)
        val illegalArgumentException: IllegalArgumentException = Assertions.assertThrows(
            IllegalArgumentException::class.java
        ) {
            val randomUserResponse: RandomUserResponse = this.randomUserService.users("foobar")
            randomUserResponse.email
        }
        Assertions.assertEquals(
            "argument \"content\" is null",
            illegalArgumentException.message
        )
    }

    @Test
    fun `Test verify call seed user api`() {
        Mockito.`when`(this.randomUserApiService.call(Mockito.anyString())).thenReturn(
            "{\"results\":[{\"gender\":\"female\",\"name\":{\"title\":\"Mr\",\"first\":\"Chaiyapong\",\"last\":\"Jantarakanyabun\"},\"location\":{\"street\":{\"number\":9617,\"name\":\"نام قدیم میدان های تهران\"},\"city\":\"تهران\",\"state\":\"گلستان\",\"country\":\"Iran\",\"postcode\":66909,\"coordinates\":{\"latitude\":\"17.2334\",\"longitude\":\"88.9196\"},\"timezone\":{\"offset\":\"+4:30\",\"description\":\"Kabul\"}},\"email\":\"chaiyapong.jantarakanyabun@gmail.com\",\"login\":{\"uuid\":\"2d5afd5e-a598-469f-9933-4edba18bcefb\",\"username\":\"blueelephant921\",\"password\":\"cccc\",\"salt\":\"uJHyJ9ZL\",\"md5\":\"1c609a27f083f9557166876073ffc83b\",\"sha1\":\"f57a35f6b04535b2f3c7024b5befdbcb84e47d7e\",\"sha256\":\"cb364efc8489b343c746a11bee2de4fd6f83c38c4811a6b84b1526a74672e8f5\"},\"dob\":{\"date\":\"1992-02-07T17:56:13.316Z\",\"age\":31},\"registered\":{\"date\":\"2016-03-25T07:13:10.667Z\",\"age\":7},\"phone\":\"005-78896313\",\"cell\":\"0964-597-8705\",\"id\":{\"name\":\"\",\"value\":null},\"picture\":{\"large\":\"https://randomuser.me/api/portraits/women/62.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/women/62.jpg\",\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/women/62.jpg\"},\"nat\":\"IR\"}],\"info\":{\"seed\":\"foobar\",\"results\":1,\"page\":1,\"version\":\"1.4\"}}"
        )
        val randomUserResponse: RandomUserResponse = this.randomUserService.users("foobar")
        Assertions.assertEquals("chaiyapong.jantarakanyabun@gmail.com", randomUserResponse.email)
        Mockito.verify(this.randomUserApiService).call(Mockito.anyString())
    }

}
