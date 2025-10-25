package com.example.legacy.config;

import java.util.Map;

public class AppSettings {
    private String serviceName;
    private String environment;
    private Map<String, String> featureFlags;

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }
    public Map<String, String> getFeatureFlags() { return featureFlags; }
    public void setFeatureFlags(Map<String, String> featureFlags) { this.featureFlags = featureFlags; }
}
