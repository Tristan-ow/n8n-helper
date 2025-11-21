// einzelnes Textobjekt
package org.owlaw.n8nhelper.pdf.dto;

public class PdfStamp {
    private float x;
    private float y;
    private int page;
    private float fontSize;
    private String text;

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public float getFontSize() { return fontSize; }
    public void setFontSize(float fontSize) { this.fontSize = fontSize; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
