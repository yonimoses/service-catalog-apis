package com.bnhp.falcon.service.catalog.capacity;

import lombok.Data;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

@Data
public class ArisCost {

    private String arisName;
    private String arisCode;
    private String arisOwner;
    private List<ServiceCost> services = new ArrayList<>();


}
