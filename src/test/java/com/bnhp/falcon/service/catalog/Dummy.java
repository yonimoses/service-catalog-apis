package com.bnhp.falcon.service.catalog;

//import org.junit.Test;
//import org.junit.runner.RunWith;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class Dummy {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap> m = objectMapper.readValue(IOUtils.toString(new ClassPathResource("service-domain.json").getInputStream()), List.class);

        for (int i = 0; i < 200; i++) {
            HashMap copy = new HashMap<>();
            copy.putAll(m.get(0));
            copy.put("domain_name",m.get(0).get("domain_name")  + "-" + UUID.randomUUID().toString().substring(0,6));
            m.add(copy);
        }
		System.out.println(objectMapper.writeValueAsString(m));
    }

}

