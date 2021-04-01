package com.bnhp.falcon.service.catalog.capacity;

import lombok.Data;

@Data
public class ServiceCost {

    private String serviceName;
    private String serviceType;
    private String prettyName;
    private String help;
    private float quantity;
    private float pricePerUnit;
    private float total;

}
