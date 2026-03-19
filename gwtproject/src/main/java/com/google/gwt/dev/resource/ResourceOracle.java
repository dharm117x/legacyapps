package com.google.gwt.dev.resource;
import java.util.Map;
import java.util.Set;

public abstract class ResourceOracle {
  public abstract Set<Resource> getResources();
  
  // This is the missing method GXT 3.1.1 looks for
  public Map<String, Resource> getResourceMap() {
    // In GWT 2.7.0+, this logic is usually handled differently, 
    // but GXT needs this signature to exist to compile.
    return null; 
  }
}
