package com.example.ecommerceapi.config;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	//Testar:http://localhost:8080/swagger-ui.html
	
    private static final String BASE_PACKAGE = "com.example.ecommerceapi.resources";
    private static final String API_TITLE = "E-commerce API";
    private static final String API_DESCRIPTION = "API REST para e-commerce";
    private static final String CONTACT_NAME = "João Vitor";
    private static final String CONTACT_GITHUB = "https://gtihub.com/joaovitor-cbc";
    private static final String CONTACT_EMAIL = "joaovitor.novacruz@gmail.com";
    private static final String LINCESE = "Apache License 2.0";
    private static final String LINCESE_URL = "https://choosealicense.com/licenses/apache-2.0/";
    
    //configurando msgs globais de status
    private final ResponseMessage m201 = customMessage1();
    private final ResponseMessage m204put = simpleMessage(204, "Atualização ok");
    private final ResponseMessage m204del = simpleMessage(204, "Deleção ok");
    private final ResponseMessage m403 = simpleMessage(403, "Não autorizado");
    private final ResponseMessage m404 = simpleMessage(404, "Não encontrado");
    private final ResponseMessage m422 = simpleMessage(422, "Erro de validação");
    private final ResponseMessage m500 = simpleMessage(500, "Erro inesperado");
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		//desabilitando as respostas padrão e configurando
        		.useDefaultResponseMessages(false)
        		.globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
        		.globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
        		.globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m422, m500))
        		.globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))
                .select()
                ///pacotes para rastreio
                .apis(basePackage(BASE_PACKAGE))
                //caminhos para rastreio
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInfo());
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version("1.0.0")
                .contact(new Contact(CONTACT_NAME, CONTACT_GITHUB, CONTACT_EMAIL))
                .license(LINCESE)
                .licenseUrl(LINCESE_URL)
                .build();
    }
    
    private ResponseMessage simpleMessage(int code, String msg) {
    	return new ResponseMessageBuilder().code(code).message(msg).build();
    	}
    
    //Response headers personalizados
    private ResponseMessage customMessage1() {
    	Map<String, Header> map = new HashMap<>();
    	map.put("location", new Header("location", "URI do novo recurso", new ModelRef("string")));
    	
    	return new ResponseMessageBuilder()
    	.code(201)
    	.message("Recurso criado")
    	.headersWithDescription(map)
    	.build();
    	}
}
