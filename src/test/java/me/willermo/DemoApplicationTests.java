package me.willermo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class DemoApplicationTests {
    
      
        @Autowired
        private TestRestTemplate restTemplate;
        
        @LocalServerPort
        private int port;

	@Test
	public void contextLoads() {
	}
        
        @Test
        public void homeUnauthenticated() throws Exception{
            HttpHeaders headers = new HttpHeaders();
            ResponseEntity<String> entity= this.restTemplate.exchange("/", HttpMethod.GET,new HttpEntity<>(headers),String.class);
            assertThat(entity.getStatusCodeValue()).isEqualTo(401);
            
        }
        
        @Test
        public void homeAuthenticated() throws Exception{
            HttpHeaders headers = new HttpHeaders();
            ResponseEntity<String> entity= this.restTemplate
                                    .withBasicAuth("usuarioA", "asd")
                                        .exchange("/",HttpMethod.GET,new HttpEntity<>(headers),String.class);
            assertThat(entity.getStatusCodeValue()).isEqualTo(200);
        }
        
        @Test
        public void getProductByIdShouldRespondOk() throws Exception{
            HttpHeaders headers = new HttpHeaders();
            ResponseEntity<String> entity= this.restTemplate
                                    .withBasicAuth("usuarioA", "asd")
                                        .exchange("/productos/1",HttpMethod.GET,new HttpEntity<>(headers),String.class);
            assertThat(entity.getStatusCodeValue()).isEqualTo(200);
        }
        
        @Test
        public void getProductByIdShouldRespondForbidden() throws Exception{
            HttpHeaders headers = new HttpHeaders();
            ResponseEntity<String> entity= this.restTemplate
                                    .withBasicAuth("usuarioA", "asd")
                                        .exchange("/productos/2",HttpMethod.GET,new HttpEntity<>(headers),String.class);
            assertThat(entity.getStatusCodeValue()).isEqualTo(403);
        }
        @Test
        public void getProductByIdWithUserBShouldRespondOk() throws Exception{
            HttpHeaders headers = new HttpHeaders();
            ResponseEntity<String> entity= this.restTemplate
                                    .withBasicAuth("usuarioB", "asd")
                                        .exchange("/productos/2",HttpMethod.GET,new HttpEntity<>(headers),String.class);
            assertThat(entity.getStatusCodeValue()).isEqualTo(200);
        }

}
