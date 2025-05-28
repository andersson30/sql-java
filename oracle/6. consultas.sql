-- Consulta para verificar usuarios
SELECT * FROM USUARIO;

-- Consulta para verificar comerciantes
SELECT * FROM COMERCIANTE;

-- Consulta para verificar establecimientos
SELECT e.*, c.nombre_razon_social 
FROM ESTABLECIMIENTO e
JOIN COMERCIANTE c ON e.id_comerciante = c.id_comerciante;