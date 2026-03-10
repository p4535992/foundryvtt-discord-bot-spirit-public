package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Foundry v13 Actor prototype token payload.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoundryPrototypeToken extends AbstractFoundryModel {

    private String name;
    private Integer displayName;
    private String actorId;
    private Boolean actorLink;
    private Double width;
    private Double height;
    private Integer shape;
    private Boolean locked;
    private Boolean lockRotation;
    private Double rotation;
    private Double alpha;
    private Boolean hidden;
    private Integer disposition;
    private Integer displayBars;
    private String movementAction;
    private Boolean randomImg;
    private Boolean appendNumber;
    private Boolean prependAdjective;
    private FoundryTextureData texture;
    private FoundryTokenBar bar1;
    private FoundryTokenBar bar2;
    private FoundryLightData light;
    private FoundryTokenSight sight;
    private List<FoundryTokenDetectionMode> detectionModes = new ArrayList<FoundryTokenDetectionMode>();
    private FoundryTokenOccludable occludable;
    private FoundryTokenRing ring;
    private FoundryTurnMarker turnMarker;
    private FoundryDocumentFlags flags;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(Integer displayName) {
        this.displayName = displayName;
    }

    public String getActorId() {
        return this.actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public Boolean getActorLink() {
        return this.actorLink;
    }

    public void setActorLink(Boolean actorLink) {
        this.actorLink = actorLink;
    }

    public Double getWidth() {
        return this.width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return this.height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getShape() {
        return this.shape;
    }

    public void setShape(Integer shape) {
        this.shape = shape;
    }

    public Boolean getLocked() {
        return this.locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getLockRotation() {
        return this.lockRotation;
    }

    public void setLockRotation(Boolean lockRotation) {
        this.lockRotation = lockRotation;
    }

    public Double getRotation() {
        return this.rotation;
    }

    public void setRotation(Double rotation) {
        this.rotation = rotation;
    }

    public Double getAlpha() {
        return this.alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public Boolean getHidden() {
        return this.hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Integer getDisposition() {
        return this.disposition;
    }

    public void setDisposition(Integer disposition) {
        this.disposition = disposition;
    }

    public Integer getDisplayBars() {
        return this.displayBars;
    }

    public void setDisplayBars(Integer displayBars) {
        this.displayBars = displayBars;
    }

    public String getMovementAction() {
        return this.movementAction;
    }

    public void setMovementAction(String movementAction) {
        this.movementAction = movementAction;
    }

    public Boolean getRandomImg() {
        return this.randomImg;
    }

    public void setRandomImg(Boolean randomImg) {
        this.randomImg = randomImg;
    }

    public Boolean getAppendNumber() {
        return this.appendNumber;
    }

    public void setAppendNumber(Boolean appendNumber) {
        this.appendNumber = appendNumber;
    }

    public Boolean getPrependAdjective() {
        return this.prependAdjective;
    }

    public void setPrependAdjective(Boolean prependAdjective) {
        this.prependAdjective = prependAdjective;
    }

    public FoundryTextureData getTexture() {
        return this.texture;
    }

    public void setTexture(FoundryTextureData texture) {
        this.texture = texture;
    }

    public FoundryTokenBar getBar1() {
        return this.bar1;
    }

    public void setBar1(FoundryTokenBar bar1) {
        this.bar1 = bar1;
    }

    public FoundryTokenBar getBar2() {
        return this.bar2;
    }

    public void setBar2(FoundryTokenBar bar2) {
        this.bar2 = bar2;
    }

    public FoundryLightData getLight() {
        return this.light;
    }

    public void setLight(FoundryLightData light) {
        this.light = light;
    }

    public FoundryTokenSight getSight() {
        return this.sight;
    }

    public void setSight(FoundryTokenSight sight) {
        this.sight = sight;
    }

    public List<FoundryTokenDetectionMode> getDetectionModes() {
        return this.detectionModes;
    }

    public void setDetectionModes(List<FoundryTokenDetectionMode> detectionModes) {
        this.detectionModes = detectionModes;
    }

    public FoundryTokenOccludable getOccludable() {
        return this.occludable;
    }

    public void setOccludable(FoundryTokenOccludable occludable) {
        this.occludable = occludable;
    }

    public FoundryTokenRing getRing() {
        return this.ring;
    }

    public void setRing(FoundryTokenRing ring) {
        this.ring = ring;
    }

    public FoundryTurnMarker getTurnMarker() {
        return this.turnMarker;
    }

    public void setTurnMarker(FoundryTurnMarker turnMarker) {
        this.turnMarker = turnMarker;
    }

    public FoundryDocumentFlags getFlags() {
        return this.flags;
    }

    public void setFlags(FoundryDocumentFlags flags) {
        this.flags = flags;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryTextureData {

        private String src;
        private Double anchorX;
        private Double anchorY;
        private String fit;
        private Double alphaThreshold;
        private Double scaleX;
        private Double scaleY;
        private Double offsetX;
        private Double offsetY;
        private Double rotation;
        private String tint;

        public String getSrc() { return this.src; }
        public void setSrc(String src) { this.src = src; }
        public Double getAnchorX() { return this.anchorX; }
        public void setAnchorX(Double anchorX) { this.anchorX = anchorX; }
        public Double getAnchorY() { return this.anchorY; }
        public void setAnchorY(Double anchorY) { this.anchorY = anchorY; }
        public String getFit() { return this.fit; }
        public void setFit(String fit) { this.fit = fit; }
        public Double getAlphaThreshold() { return this.alphaThreshold; }
        public void setAlphaThreshold(Double alphaThreshold) { this.alphaThreshold = alphaThreshold; }
        public Double getScaleX() { return this.scaleX; }
        public void setScaleX(Double scaleX) { this.scaleX = scaleX; }
        public Double getScaleY() { return this.scaleY; }
        public void setScaleY(Double scaleY) { this.scaleY = scaleY; }
        public Double getOffsetX() { return this.offsetX; }
        public void setOffsetX(Double offsetX) { this.offsetX = offsetX; }
        public Double getOffsetY() { return this.offsetY; }
        public void setOffsetY(Double offsetY) { this.offsetY = offsetY; }
        public Double getRotation() { return this.rotation; }
        public void setRotation(Double rotation) { this.rotation = rotation; }
        public String getTint() { return this.tint; }
        public void setTint(String tint) { this.tint = tint; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryTokenBar {

        private String attribute;

        public String getAttribute() { return this.attribute; }
        public void setAttribute(String attribute) { this.attribute = attribute; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryLightData {

        private Boolean negative;
        private Integer priority;
        private Double alpha;
        private Double angle;
        private Double bright;
        private String color;
        private Integer coloration;
        private Double dim;
        private Double attenuation;
        private Double luminosity;
        private Double saturation;
        private Double contrast;
        private Double shadows;
        private FoundryLightAnimation animation;
        private FoundryDarknessRange darkness;

        public Boolean getNegative() { return this.negative; }
        public void setNegative(Boolean negative) { this.negative = negative; }
        public Integer getPriority() { return this.priority; }
        public void setPriority(Integer priority) { this.priority = priority; }
        public Double getAlpha() { return this.alpha; }
        public void setAlpha(Double alpha) { this.alpha = alpha; }
        public Double getAngle() { return this.angle; }
        public void setAngle(Double angle) { this.angle = angle; }
        public Double getBright() { return this.bright; }
        public void setBright(Double bright) { this.bright = bright; }
        public String getColor() { return this.color; }
        public void setColor(String color) { this.color = color; }
        public Integer getColoration() { return this.coloration; }
        public void setColoration(Integer coloration) { this.coloration = coloration; }
        public Double getDim() { return this.dim; }
        public void setDim(Double dim) { this.dim = dim; }
        public Double getAttenuation() { return this.attenuation; }
        public void setAttenuation(Double attenuation) { this.attenuation = attenuation; }
        public Double getLuminosity() { return this.luminosity; }
        public void setLuminosity(Double luminosity) { this.luminosity = luminosity; }
        public Double getSaturation() { return this.saturation; }
        public void setSaturation(Double saturation) { this.saturation = saturation; }
        public Double getContrast() { return this.contrast; }
        public void setContrast(Double contrast) { this.contrast = contrast; }
        public Double getShadows() { return this.shadows; }
        public void setShadows(Double shadows) { this.shadows = shadows; }
        public FoundryLightAnimation getAnimation() { return this.animation; }
        public void setAnimation(FoundryLightAnimation animation) { this.animation = animation; }
        public FoundryDarknessRange getDarkness() { return this.darkness; }
        public void setDarkness(FoundryDarknessRange darkness) { this.darkness = darkness; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryLightAnimation {

        private String type;
        private Integer speed;
        private Integer intensity;
        private Boolean reverse;

        public String getType() { return this.type; }
        public void setType(String type) { this.type = type; }
        public Integer getSpeed() { return this.speed; }
        public void setSpeed(Integer speed) { this.speed = speed; }
        public Integer getIntensity() { return this.intensity; }
        public void setIntensity(Integer intensity) { this.intensity = intensity; }
        public Boolean getReverse() { return this.reverse; }
        public void setReverse(Boolean reverse) { this.reverse = reverse; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryDarknessRange {

        private Double min;
        private Double max;

        public Double getMin() { return this.min; }
        public void setMin(Double min) { this.min = min; }
        public Double getMax() { return this.max; }
        public void setMax(Double max) { this.max = max; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryTokenSight {

        private Boolean enabled;
        private Double range;
        private Double angle;
        private String visionMode;
        private String color;
        private Double attenuation;
        private Double brightness;
        private Double saturation;
        private Double contrast;

        public Boolean getEnabled() { return this.enabled; }
        public void setEnabled(Boolean enabled) { this.enabled = enabled; }
        public Double getRange() { return this.range; }
        public void setRange(Double range) { this.range = range; }
        public Double getAngle() { return this.angle; }
        public void setAngle(Double angle) { this.angle = angle; }
        public String getVisionMode() { return this.visionMode; }
        public void setVisionMode(String visionMode) { this.visionMode = visionMode; }
        public String getColor() { return this.color; }
        public void setColor(String color) { this.color = color; }
        public Double getAttenuation() { return this.attenuation; }
        public void setAttenuation(Double attenuation) { this.attenuation = attenuation; }
        public Double getBrightness() { return this.brightness; }
        public void setBrightness(Double brightness) { this.brightness = brightness; }
        public Double getSaturation() { return this.saturation; }
        public void setSaturation(Double saturation) { this.saturation = saturation; }
        public Double getContrast() { return this.contrast; }
        public void setContrast(Double contrast) { this.contrast = contrast; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryTokenDetectionMode {

        private String id;
        private Boolean enabled;
        private Double range;

        public String getId() { return this.id; }
        public void setId(String id) { this.id = id; }
        public Boolean getEnabled() { return this.enabled; }
        public void setEnabled(Boolean enabled) { this.enabled = enabled; }
        public Double getRange() { return this.range; }
        public void setRange(Double range) { this.range = range; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryTokenOccludable {

        private Double radius;

        public Double getRadius() { return this.radius; }
        public void setRadius(Double radius) { this.radius = radius; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryTokenRing {

        private Boolean enabled;
        private FoundryTokenRingColors colors;
        private Integer effects;
        private FoundryTokenRingSubject subject;

        public Boolean getEnabled() { return this.enabled; }
        public void setEnabled(Boolean enabled) { this.enabled = enabled; }
        public FoundryTokenRingColors getColors() { return this.colors; }
        public void setColors(FoundryTokenRingColors colors) { this.colors = colors; }
        public Integer getEffects() { return this.effects; }
        public void setEffects(Integer effects) { this.effects = effects; }
        public FoundryTokenRingSubject getSubject() { return this.subject; }
        public void setSubject(FoundryTokenRingSubject subject) { this.subject = subject; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryTokenRingColors {

        private String ring;
        private String background;

        public String getRing() { return this.ring; }
        public void setRing(String ring) { this.ring = ring; }
        public String getBackground() { return this.background; }
        public void setBackground(String background) { this.background = background; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryTokenRingSubject {

        private Double scale;
        private String texture;

        public Double getScale() { return this.scale; }
        public void setScale(Double scale) { this.scale = scale; }
        public String getTexture() { return this.texture; }
        public void setTexture(String texture) { this.texture = texture; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryTurnMarker {

        private Integer mode;
        private String animation;
        private String src;
        private Boolean disposition;

        public Integer getMode() { return this.mode; }
        public void setMode(Integer mode) { this.mode = mode; }
        public String getAnimation() { return this.animation; }
        public void setAnimation(String animation) { this.animation = animation; }
        public String getSrc() { return this.src; }
        public void setSrc(String src) { this.src = src; }
        public Boolean getDisposition() { return this.disposition; }
        public void setDisposition(Boolean disposition) { this.disposition = disposition; }
    }
}
