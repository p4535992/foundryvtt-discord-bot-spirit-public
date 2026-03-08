package com.foundryvtt.bot.spirit.openapi.relay.system.core.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
 * Request payload for roll execution.
 **/

@org.eclipse.microprofile.openapi.annotations.media.Schema(description="Request payload for roll execution.")

@JsonTypeName("RollRequest")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")    @XmlAccessorType(XmlAccessType.FIELD)
     @XmlType(name = "RollRequest", propOrder =
    { "formula", "itemUuid", "flavor", "createChatMessage", "target", "speaker", "whisper"
    })
    
    

public class RollRequest extends HashMap<String, Object>  {
  private String
 formula;
  private String
 itemUuid;
  private String
 flavor;
  private Boolean
 createChatMessage = true;
  private String
 target;
  private String
 speaker;
  private List<
String
>
 whisper = new ArrayList<>();

  public RollRequest() {
  }

  @JsonCreator
  public RollRequest(
    @JsonProperty(required = true, value = "formula") String
 formula
  ) {
    super(
    );
    this.formula = formula;
  }

  /**
   * Dice formula to execute.
   **/
  public RollRequest formula(String
 formula) {
    this.formula = formula;
    return this;
  }

      @XmlElement(name="formula", required = true)
  
  @org.eclipse.microprofile.openapi.annotations.media.Schema(required = true, description = "Dice formula to execute.")
  
  @JsonProperty(required = true, value = "formula")
  @NotNull 
public String
 getFormula() {
    return formula;
  }

  @JsonProperty(required = true, value = "formula")
  public void setFormula(String
 formula) {
    this.formula = formula;
  }

  /**
   **/
  public RollRequest itemUuid(String
 itemUuid) {
    this.itemUuid = itemUuid;
    return this;
  }

      @XmlElement(name="itemUuid")
  
  @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "")
  
  @JsonProperty("itemUuid")
  
public String
 getItemUuid() {
    return itemUuid;
  }

  @JsonProperty("itemUuid")
  public void setItemUuid(String
 itemUuid) {
    this.itemUuid = itemUuid;
  }

  /**
   **/
  public RollRequest flavor(String
 flavor) {
    this.flavor = flavor;
    return this;
  }

      @XmlElement(name="flavor")
  
  @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "")
  
  @JsonProperty("flavor")
  
public String
 getFlavor() {
    return flavor;
  }

  @JsonProperty("flavor")
  public void setFlavor(String
 flavor) {
    this.flavor = flavor;
  }

  /**
   **/
  public RollRequest createChatMessage(Boolean
 createChatMessage) {
    this.createChatMessage = createChatMessage;
    return this;
  }

      @XmlElement(name="createChatMessage")
  
  @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "")
  
  @JsonProperty("createChatMessage")
  
public Boolean
 getCreateChatMessage() {
    return createChatMessage;
  }

  @JsonProperty("createChatMessage")
  public void setCreateChatMessage(Boolean
 createChatMessage) {
    this.createChatMessage = createChatMessage;
  }

  /**
   **/
  public RollRequest target(String
 target) {
    this.target = target;
    return this;
  }

      @XmlElement(name="target")
  
  @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "")
  
  @JsonProperty("target")
  
public String
 getTarget() {
    return target;
  }

  @JsonProperty("target")
  public void setTarget(String
 target) {
    this.target = target;
  }

  /**
   **/
  public RollRequest speaker(String
 speaker) {
    this.speaker = speaker;
    return this;
  }

      @XmlElement(name="speaker")
  
  @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "")
  
  @JsonProperty("speaker")
  
public String
 getSpeaker() {
    return speaker;
  }

  @JsonProperty("speaker")
  public void setSpeaker(String
 speaker) {
    this.speaker = speaker;
  }

  /**
   **/
  public RollRequest whisper(List<
String
>
 whisper) {
    this.whisper = whisper;
    return this;
  }

      @XmlElement(name="whisper")
  
  @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "")
  
  @JsonProperty("whisper")
  
public List<
String
>
 getWhisper() {
    return whisper;
  }

  @JsonProperty("whisper")
  public void setWhisper(List<
String
>
 whisper) {
    this.whisper = whisper;
  }

  public RollRequest addWhisperItem(String whisperItem) {
    if (this.whisper == null) {
      this.whisper = new ArrayList<>();
    }

    this.whisper.add(whisperItem);
    return this;
  }

  public RollRequest removeWhisperItem(String whisperItem) {
    if (whisperItem != null && this.whisper != null) {
      this.whisper.remove(whisperItem);
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
    RollRequest rollRequest = (RollRequest) o;
    return Objects.equals(this.formula, rollRequest.formula) &&
        Objects.equals(this.itemUuid, rollRequest.itemUuid) &&
        Objects.equals(this.flavor, rollRequest.flavor) &&
        Objects.equals(this.createChatMessage, rollRequest.createChatMessage) &&
        Objects.equals(this.target, rollRequest.target) &&
        Objects.equals(this.speaker, rollRequest.speaker) &&
        Objects.equals(this.whisper, rollRequest.whisper) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(formula, itemUuid, flavor, createChatMessage, target, speaker, whisper, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RollRequest {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    formula: ").append(toIndentedString(formula)).append("\n");
    sb.append("    itemUuid: ").append(toIndentedString(itemUuid)).append("\n");
    sb.append("    flavor: ").append(toIndentedString(flavor)).append("\n");
    sb.append("    createChatMessage: ").append(toIndentedString(createChatMessage)).append("\n");
    sb.append("    target: ").append(toIndentedString(target)).append("\n");
    sb.append("    speaker: ").append(toIndentedString(speaker)).append("\n");
    sb.append("    whisper: ").append(toIndentedString(whisper)).append("\n");
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

