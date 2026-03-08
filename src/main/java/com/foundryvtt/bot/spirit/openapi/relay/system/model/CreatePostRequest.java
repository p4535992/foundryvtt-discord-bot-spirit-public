package com.foundryvtt.bot.spirit.openapi.relay.system.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.foundryvtt.bot.spirit.openapi.relay.system.model.Actor;
import com.foundryvtt.bot.spirit.openapi.relay.system.model.Item;
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



@org.eclipse.microprofile.openapi.annotations.media.Schema(description="")

@JsonTypeName("_create_post_request")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")    @XmlAccessorType(XmlAccessType.FIELD)
     @XmlType(name = "CreatePostRequest", propOrder =
    { "type", "folder", "data", "flags"
    })
    
    @XmlRootElement(name="CreatePostRequest")

public class CreatePostRequest   {
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

  protected CreatePostRequest(CreatePostRequestBuilder<?, ?> b) {
    this.type = b.type;
    this.folder = b.folder;
    this.data = b.data;
    this.flags = b.flags;
  }

  public CreatePostRequest() {
  }

  /**
   * Foundry item type.
   **/
  public CreatePostRequest type(String
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
  public CreatePostRequest folder(String
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
  public CreatePostRequest data(Map<String, 
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

  public CreatePostRequest putDataItem(String key, Object dataItem) {
    if (this.data == null) {
      this.data = new HashMap<>();
    }

    this.data.put(key, dataItem);
    return this;
  }

  public CreatePostRequest removeDataItem(String key) {
    if (this.data != null) {
      this.data.remove(key);
    }

    return this;
  }
  /**
   **/
  public CreatePostRequest flags(Map<String, 
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

  public CreatePostRequest putFlagsItem(String key, Object flagsItem) {
    if (this.flags == null) {
      this.flags = new HashMap<>();
    }

    this.flags.put(key, flagsItem);
    return this;
  }

  public CreatePostRequest removeFlagsItem(String key) {
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
    CreatePostRequest createPostRequest = (CreatePostRequest) o;
    return Objects.equals(this.type, createPostRequest.type) &&
        Objects.equals(this.folder, createPostRequest.folder) &&
        Objects.equals(this.data, createPostRequest.data) &&
        Objects.equals(this.flags, createPostRequest.flags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, folder, data, flags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreatePostRequest {\n");
    
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


  public static CreatePostRequestBuilder<?, ?> builder() {
    return new CreatePostRequestBuilderImpl();
  }

  private static final class CreatePostRequestBuilderImpl extends CreatePostRequestBuilder<CreatePostRequest, CreatePostRequestBuilderImpl> {

    @Override
    protected CreatePostRequestBuilderImpl self() {
      return this;
    }

    @Override
    public CreatePostRequest build() {
      return new CreatePostRequest(this);
    }
  }

  public static abstract class CreatePostRequestBuilder<C extends CreatePostRequest, B extends CreatePostRequestBuilder<C, B>>  {
    private String type;
    private String folder;
    private Map<String, Object> data = new HashMap<>();
    private Map<String, Object> flags = new HashMap<>();
    protected abstract B self();

    public abstract C build();

    public B type(String type) {
      this.type = type;
      return self();
    }
    public B folder(String folder) {
      this.folder = folder;
      return self();
    }
    public B data(Map<String, Object> data) {
      this.data = data;
      return self();
    }
    public B flags(Map<String, Object> flags) {
      this.flags = flags;
      return self();
    }
  }
}

