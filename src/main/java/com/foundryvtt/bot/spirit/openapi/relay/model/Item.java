package com.foundryvtt.bot.spirit.openapi.relay.model;

import java.util.HashMap;
import java.util.Map;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * Generic Item payload aligned with Foundry document shape.
 **/

@org.eclipse.microprofile.openapi.annotations.media.Schema(description="Generic Item payload aligned with Foundry document shape.")

@JsonTypeName("Item")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")    @XmlAccessorType(XmlAccessType.FIELD)
     @XmlType(name = "Item", propOrder =
    { "type", "folder", "data", "flags"
    })
    
    

public class Item extends HashMap<String, Object>  {
  private String
 type;
  private String
 folder;
  private Map<String, 
Object
>
 data = new HashMap<>();
  private Map<String, 
Object
>
 flags = new HashMap<>();

  public Item() {
  }

  /**
   * Foundry item type.
   **/
  public Item type(String
 type) {
    this.type = type;
    return this;
  }

      @XmlElement(name="type")
  
  @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "Foundry item type.")
  
  @JsonProperty("type")
  
public String
 getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String
 type) {
    this.type = type;
  }

  /**
   **/
  public Item folder(String
 folder) {
    this.folder = folder;
    return this;
  }

      @XmlElement(name="folder")
  
  @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "")
  
  @JsonProperty("folder")
  
public String
 getFolder() {
    return folder;
  }

  @JsonProperty("folder")
  public void setFolder(String
 folder) {
    this.folder = folder;
  }

  /**
   **/
  public Item data(Map<String, 
Object
>
 data) {
    this.data = data;
    return this;
  }

      @XmlElement(name="data")
  
  @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "")
  
  @JsonProperty("data")
  
public Map<String, 
Object
>
 getData() {
    return data;
  }

  @JsonProperty("data")
  public void setData(Map<String, 
Object
>
 data) {
    this.data = data;
  }

  public Item putDataItem(String key, Object dataItem) {
    if (this.data == null) {
      this.data = new HashMap<>();
    }

    this.data.put(key, dataItem);
    return this;
  }

  public Item removeDataItem(String key) {
    if (this.data != null) {
      this.data.remove(key);
    }

    return this;
  }
  /**
   **/
  public Item flags(Map<String, 
Object
>
 flags) {
    this.flags = flags;
    return this;
  }

      @XmlElement(name="flags")
  
  @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "")
  
  @JsonProperty("flags")
  
public Map<String, 
Object
>
 getFlags() {
    return flags;
  }

  @JsonProperty("flags")
  public void setFlags(Map<String, 
Object
>
 flags) {
    this.flags = flags;
  }

  public Item putFlagsItem(String key, Object flagsItem) {
    if (this.flags == null) {
      this.flags = new HashMap<>();
    }

    this.flags.put(key, flagsItem);
    return this;
  }

  public Item removeFlagsItem(String key) {
    if (this.flags != null) {
      this.flags.remove(key);
    }

    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(this.type, item.type) &&
        Objects.equals(this.folder, item.folder) &&
        Objects.equals(this.data, item.data) &&
        Objects.equals(this.flags, item.flags) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, folder, data, flags, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Item {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    folder: ").append(toIndentedString(folder)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    flags: ").append(toIndentedString(flags)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


}

