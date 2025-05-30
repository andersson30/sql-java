package com.example.entrega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@Service
public class ComercianteService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Resource generarCSVComerciantesActivos() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Write header
            writer.write("Nombre o Razón Social|Municipio|Teléfono|Correo Electrónico|Fecha de Registro|Estado|Cantidad de Establecimientos|Total Ingresos|Cantidad de Empleados\n");

            // Execute the function and process results
            jdbcTemplate.query(
                "SELECT * FROM TABLE(obtener_comerciantes_activos())",
                (ResultSet rs) -> {
                    try {
                        writer.write(rs.getString("Nombre o Razón Social") + "|");
                        writer.write(rs.getString("Municipio") + "|");
                        writer.write(rs.getString("Teléfono") + "|");
                        writer.write(rs.getString("Correo Electrónico") + "|");
                        writer.write(dateFormat.format(rs.getDate("Fecha de Registro")) + "|");
                        writer.write(rs.getString("Estado") + "|");
                        writer.write(rs.getString("Cantidad de Establecimientos") + "|");
                        writer.write(rs.getString("Total Ingresos") + "|");
                        writer.write(rs.getString("Cantidad de Empleados") + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException("Error writing CSV data", e);
                    }
                }
            );

            writer.flush();
            writer.close();

            return new ByteArrayResource(outputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Error generating CSV file", e);
        }
    }
} 