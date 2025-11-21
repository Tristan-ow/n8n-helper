package org.owlaw.n8nhelper.pdf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.owlaw.n8nhelper.pdf.dto.PdfMultiStampRequest;
import org.owlaw.n8nhelper.pdf.service.PdfService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private final PdfService pdfService;
    private final ObjectMapper objectMapper;

    public PdfController(PdfService pdfService, ObjectMapper objectMapper) {
        this.pdfService = pdfService;
        this.objectMapper = objectMapper;
    }

    @PostMapping(
            value = "/stamp",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<byte[]> stamp(
            @RequestPart("file") MultipartFile file,
            @RequestPart("stamps") String stampsJson,
            @RequestPart("outputFileName") String outputFileName
    ) {
        try {
            PdfMultiStampRequest request =
                    objectMapper.readValue(stampsJson, PdfMultiStampRequest.class);

            byte[] inputPdf = file.getBytes();
            byte[] stampedPdf = pdfService.applyStamps(inputPdf, request);

            if (outputFileName == null || outputFileName.trim().isEmpty()) {
                outputFileName = "stamped.pdf";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                    ContentDisposition.attachment()
                            .filename(outputFileName)
                            .build()
            );

            return new ResponseEntity<>(stampedPdf, headers, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
