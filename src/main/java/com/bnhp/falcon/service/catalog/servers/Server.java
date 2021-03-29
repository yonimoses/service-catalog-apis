package com.bnhp.falcon.service.catalog.servers;

import lombok.Data;

import java.util.List;

@Data
public class Server {

    private String hostname;
    private String dns;
    private String ipaddress;
    private int cpu;
    private int mem;
    private int storage;
    private String group;
    private List<String> products;
}
