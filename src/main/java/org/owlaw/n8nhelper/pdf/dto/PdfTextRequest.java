package org.owlaw.n8nhelper.pdf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PdfTextRequest {

    private float x;
    private float y;
    private String text;
    private int page;

    // "file" kommt aus dem JSON, intern nennen wir es templateBase64
    @JsonProperty("file")
    private String templateBase64;

    private float fontSize;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTemplateBase64() {
        return templateBase64;
    }

    public void setTemplateBase64(String templateBase64) {
        this.templateBase64 = templateBase64;
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }
}
