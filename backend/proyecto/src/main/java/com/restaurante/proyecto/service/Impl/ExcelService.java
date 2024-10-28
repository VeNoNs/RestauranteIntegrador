package com.restaurante.proyecto.service.Impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Servicio para la generación de informes en formato Excel.
 * Utiliza Apache POI para crear y manipular archivos Excel.
 */
@Service
public class ExcelService {

    /**
     * Genera un informe de empleados en formato Excel.
     *
     * @param empleados Lista de empleados a incluir en el informe.
     * @return Un array de bytes que representa el archivo Excel generado.
     * @throws IOException Si ocurre un error al escribir el archivo Excel.
     */
    public byte[] generarInformeEmpleados(List<Empleado> empleados) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Empleados");

        // Crear encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Apellido");
        headerRow.createCell(3).setCellValue("Tipo de Empleado");

        // Agregar datos
        int rowNum = 1;
        for (Empleado empleado : empleados) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(empleado.getId());
            row.createCell(1).setCellValue(empleado.getNombreEmpleado());
            row.createCell(2).setCellValue(empleado.getApellidoEmpleado());
            row.createCell(3).setCellValue(empleado.getTipoEmpleado());
        }

        // Escribir el archivo a un ByteArrayOutputStream
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } finally {
            workbook.close();
        }
    }
}
