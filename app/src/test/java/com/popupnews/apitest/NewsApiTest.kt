import com.popupnews.data.api.NewsApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: NewsApi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // Teszt szerver URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Test
    suspend fun `test API returns correct data`() {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""{"status":"ok","totalResults":1,"articles":[]}""")

        mockWebServer.enqueue(mockResponse)

        val response = apiService.searchArticle("bitcoin")

        assertEquals(200, response.code())
        assertEquals("ok", response.body()?.status)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}
