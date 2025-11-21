package org.owlaw.n8nhelper.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.owlaw.n8nhelper.pdf.dto.PdfMultiStampRequest;
import org.owlaw.n8nhelper.pdf.dto.PdfStamp;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] applyStamps(byte[] pdfBytes, PdfMultiStampRequest request) throws Exception {
        if (pdfBytes == null || pdfBytes.length == 0) {
            throw new IllegalArgumentException("PDF file must not be empty.");
        }
        if (request == null || request.getStamps() == null || request.getStamps().isEmpty()) {
            throw new IllegalArgumentException("At least one stamp is required.");
        }

        try (PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes))) {

            for (PdfStamp stamp : request.getStamps()) {
                int pageIndex = stamp.getPage();
                if (pageIndex < 0 || pageIndex >= document.getNumberOfPages()) {
                    throw new IllegalArgumentException("Page index out of range: " + pageIndex);
                }

                PDPage page = document.getPage(pageIndex);

                try (PDPageContentStream cs = new PDPageContentStream(
                        document,
                        page,
                        PDPageContentStream.AppendMode.APPEND,
                        true,
                        true
                )) {
                    cs.beginText();
                    cs.setFont(PDType1Font.HELVETICA, stamp.getFontSize());
                    cs.newLineAtOffset(stamp.getX(), stamp.getY());
                    cs.showText(stamp.getText() != null ? stamp.getText() : "");
                    cs.endText();
                }
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            return baos.toByteArray();
        }
    }
}
