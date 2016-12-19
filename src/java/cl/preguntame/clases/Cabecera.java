/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.preguntame.clases;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author Hector
 */
public class Cabecera extends PdfPageEventHelper {

    private String encabezado;
    PdfTemplate total;

    /**
     * Crea el objecto PdfTemplate el cual contiene el numero total de paginas
     * en el documento
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }

    /**
     * Esta es el metodo a llamar cuando ocurra el evento onEndPage, es en este
     * evento donde crearemos el encabeazado de la pagina con los elementos
     * indicados.
     */
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(3);
        try {
            // Se determina el ancho y altura de la tabla 
            table.setWidths(new int[]{24, 24, 2});
            table.setTotalWidth(527);
            table.setLockedWidth(true);
            table.getDefaultCell().setFixedHeight(20);

            // Borde de la celda
            table.getDefaultCell().setBorder(Rectangle.BOTTOM);

            table.addCell(encabezado);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            table.addCell(String.format("Pagina % 01d de ", writer.getPageNumber()));

            PdfPCell cell = new PdfPCell(Image.getInstance(total));

            cell.setBorder(Rectangle.BOTTOM);

            table.addCell(cell);
            // Esta linea escribe la tabla como encabezado
            table.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    /**
     * Realiza el conteo de paginas al momento de cerrar el documento
     */
    public void onCloseDocument(PdfWriter writer, Document document) {
        int totalLength = String.valueOf(writer.getPageNumber()).length();
        int totalWidth = totalLength * 5;
        ColumnText.showTextAligned(total, Element.ALIGN_RIGHT,
                new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)),
                totalWidth, 6, 0);
    }

    // Getter and Setters
    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }
}
