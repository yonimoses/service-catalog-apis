package com.bnhp.falcon.service.catalog.capacity;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CostController {
//    OpenShiftClient osClient = new DefaultOpenShiftClient();
////\
//
//    String url = "http://prometheus-demo-route-pad-monitoring.2886795279-80-host02nc.environments.katacoda.com";
//        //    @Value("${openshift.url}")
////    private String url;
//
//
//    @PostConstruct
//    public void postConstruct(){
//        Config config = new ConfigBuilder().withMasterUrl(url)
//                .withOauthToken("ZdxMNNlykioIxKQrS_H2Z2PUyBvb8TVvDOzL6MVQrFg")
//                .build();
//        osClient = new DefaultOpenShiftClient(config);


    @GetMapping("/capacity/list")
    public List<HashMap> getServers() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(IOUtils.toString(new ClassPathResource("capacity.json").getInputStream()), List.class);
    }

    @GetMapping("/capacity/aris/{id}")
    public HashMap getByAris(@PathVariable String id) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap> map = objectMapper.readValue(IOUtils.toString(new ClassPathResource("capacity.json").getInputStream()), List.class);
        return map.get(0);
    }

    @GetMapping("/cost/services")
    public List<HashMap> getPricingUnits() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(IOUtils.toString(new ClassPathResource("services-costs.json").getInputStream()), List.class);
    }


    @GetMapping("/cost/aris/{id}")
    public ArisCost costByAris(@PathVariable String id) throws IOException {
        List<ServiceCost> costs = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap> list = objectMapper.readValue(IOUtils.toString(new ClassPathResource("capacity.json").getInputStream()), List.class);
        Map aris = (Map) list.get(0);
        Map aris_usage = (Map) aris.get("usage");
        ArisCost arisCost = new ArisCost();
        arisCost.setArisCode(aris.get("aris").toString());
        arisCost.setArisName(aris.get("title").toString());
        arisCost.setArisOwner(aris.get("owner_name").toString());
        List<HashMap> cost = getPricingUnits();
        cost.forEach(service -> {
            if (aris_usage.containsKey(service.get("service_name"))) {
                ServiceCost sc = new ServiceCost();
                sc.setServiceName(service.get("service_name").toString());
                sc.setServiceType(service.get("service_type").toString());
                sc.setHelp(service.get("help").toString());
                sc.setQuantity(Float.parseFloat(aris_usage.get(service.get("service_name")).toString()));
                sc.setPricePerUnit(Float.parseFloat(service.get("unit_price").toString()));
                sc.setPrettyName(service.get("pretty_name").toString());

                sc.setTotal(sc.getPricePerUnit() * sc.getQuantity());
                arisCost.getServices().add(sc);
            }
        });
        return arisCost;
    }


///**
//GET /api/v1/query_range?query=kube_pod_labels%7Blabel_component%3D%22import-dashboards%22%7D&start=1616435221.363&end=1616525221.363&step=360&_=1616522057040 HTTP/1.1
//Host: test-monitoring.192.168.99.100.nip.io
//Connection: keep-alive
//sec-ch-ua: "Google Chrome";v="89", "Chromium";v="89", ";Not A Brand";v="99"
//Accept: application/json, text/javascript, */*; q=0.01
//        DNT: 1
//        X-Requested-With: XMLHttpRequest
//        sec-ch-ua-mobile: ?0
//        User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36
//        Sec-Fetch-Site: same-origin
//        Sec-Fetch-Mode: cors
//        Sec-Fetch-Dest: empty
//        Referer: https://test-monitoring.192.168.99.100.nip.io/graph?g0.range_input=25h&g0.expr=kube_pod_labels%7Blabel_component%3D%22import-dashboards%22%7D&g0.tab=0
//        Accept-Encoding: gzip, deflate, br
//        Accept-Language: en-US,en;q=0.9,he;q=0.8
//        Cookie: 71a755cf82c444d9bf2a9e974f44a99d=5e2c23a25a9609266e9181d0e261f721

// */


//
//    }
//
//
//    @GetMapping("/service-domain/info/{name}")
//    public Map getInfo(@PathVariable String name){
//        Resource<Namespace> namespace = osClient.namespaces().withName(name);
//        Map m = new HashMap();
//        m.put("annotations",namespace.get().getMetadata().getAnnotations());
//        m.put("labels",namespace.get().getMetadata().getLabels());
//        return m;
//    }
//
//
//    @GetMapping("/service-domain/prom/info/{name}")
//    public String prom(@PathVariable String name){
//       Unirest.
//    }
//
//    @GetMapping("/service-domain/images/{name}")
//    public Map getImages(@PathVariable String name){
//
//
//        Resource<Namespace> namespace = osClient.namespaces().withName(name);
//        Map m = new HashMap();
//        m.put("annotations",namespace.get().getMetadata().getAnnotations());
//        m.put("labels",namespace.get().getMetadata().getLabels());
//        return m;
//    }
//
//
//    @GetMapping("/service-domain/quota/{name}")
//    public Map getQuota(@PathVariable String name){
//        NonNamespaceOperation<ClusterResourceQuota, ClusterResourceQuotaList, Resource<ClusterResourceQuota>> quota = osClient.quotas().clusterResourceQuotas().inNamespace(name);
////        quota.w
////        Map m = new HashMap();
////        m.put("annotations",namespace.().().getAnnotations());
////        m.put("labels",namespace.get().getMetadata().getLabels());
//        return new HashMap();
//    }
//   HashMap


//    oc login https://192.168.99.100:8443 --token=ZdxMNNlykioIxKQrS_H2Z2PUyBvb8TVvDOzL6MVQrFg
}
