package com.sample.kubernetes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class SpringBootK8Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootK8Application.class, args);
	}

//	@Bean
//	public HalConfiguration linkRelationBasedPolicy() {
//		return new HalConfiguration()
//				.withRenderSingleLinksFor("ACTIONS", HalConfiguration.RenderSingleLinks.AS_ARRAY);
//	}
}
