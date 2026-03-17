package com.foundryvtt.bot.spirit.openapi.relay.server.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Generic Actor payload aligned with Foundry document shape.
 **/
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")
@org.eclipse.microprofile.openapi.annotations.media.Schema(description = "Generic Actor payload aligned with Foundry document shape.")
@JsonTypeName("Actor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Actor", propOrder = {
        "type", "folder", "data", "flags"
})
public class Actor extends HashMap<String, Object> {
    private String type;
    private String folder;
    private Map<String, Object> data = new HashMap<>();
    private Map<String, Object> flags = new HashMap<>();

    public Actor() {
    }

    /**
     * Foundry document type (for example npc, character).
     **/
    public Actor type(String type) {
        this.type = type;
        return this;
    }

    @XmlElement(name = "type")
    @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "Foundry document type (for example npc, character).")
    @JsonProperty("type")

    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     **/
    public Actor folder(String folder) {
        this.folder = folder;
        return this;
    }

    @XmlElement(name = "folder")
    @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "")
    @JsonProperty("folder")

    public String getFolder() {
        return folder;
    }

    @JsonProperty("folder")
    public void setFolder(String folder) {
        this.folder = folder;
    }

    /**
     **/
    public Actor data(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    @XmlElement(name = "data")
    @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "")
    @JsonProperty("data")

    public Map<String, Object> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Actor putDataItem(String key, Object dataItem) {
        if (this.data == null) {
            this.data = new HashMap<>();
        }

        this.data.put(key, dataItem);
        return this;
    }

    public Actor removeDataItem(String key) {
        if (this.data != null) {
            this.data.remove(key);
        }

        return this;
    }

    /**
     **/
    public Actor flags(Map<String, Object> flags) {
        this.flags = flags;
        return this;
    }

    @XmlElement(name = "flags")
    @org.eclipse.microprofile.openapi.annotations.media.Schema(description = "")
    @JsonProperty("flags")

    public Map<String, Object> getFlags() {
        return flags;
    }

    @JsonProperty("flags")
    public void setFlags(Map<String, Object> flags) {
        this.flags = flags;
    }

    public Actor putFlagsItem(String key, Object flagsItem) {
        if (this.flags == null) {
            this.flags = new HashMap<>();
        }

        this.flags.put(key, flagsItem);
        return this;
    }

    public Actor removeFlagsItem(String key) {
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
        Actor actor = (Actor) o;
        return Objects.equals(this.type, actor.type) &&
                Objects.equals(this.folder, actor.folder) &&
                Objects.equals(this.data, actor.data) &&
                Objects.equals(this.flags, actor.flags) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, folder, data, flags, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Actor {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    folder: ").append(toIndentedString(folder)).append("\n");
        sb.append("    data: ").append(toIndentedString(data)).append("\n");
        sb.append("    flags: ").append(toIndentedString(flags)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
