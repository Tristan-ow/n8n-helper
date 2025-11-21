package org.owlaw.n8nhelper.pdf.dto;

public class PdfTextResponse {

    private String file; // Base64 der modifizierten PDF

    public PdfTextResponse(String file) {
        this.file = file;
    }

    public PdfTextResponse() {
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
