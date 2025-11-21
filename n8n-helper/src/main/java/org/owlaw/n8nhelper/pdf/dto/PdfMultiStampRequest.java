// Wrapper für mehrere Stamps
package org.owlaw.n8nhelper.pdf.dto;

import java.util.List;

public class PdfMultiStampRequest {
    private List<PdfStamp> stamps;

    public List<PdfStamp> getStamps() { return stamps; }
    public void setStamps(List<PdfStamp> stamps) { this.stamps = stamps; }
}
