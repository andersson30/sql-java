-- 1. Eliminar objetos existentes (si es necesario)
BEGIN
   EXECUTE IMMEDIATE 'DROP FUNCTION obtener_comerciantes_activos';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP FUNCTION es_comerciante_activo';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP FUNCTION calcular_totales_comerciante';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

-- 2. Crear función auxiliar para validar comerciante activo
CREATE OR REPLACE FUNCTION es_comerciante_activo(
    p_id_comerciante IN NUMBER
) RETURN BOOLEAN
IS
    v_estado COMERCIANTE.estado%TYPE;
BEGIN
    SELECT estado INTO v_estado
    FROM COMERCIANTE
    WHERE id_comerciante = p_id_comerciante;
    
    RETURN (v_estado = 'Activo');
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END es_comerciante_activo;
/

-- 3. Crear función auxiliar para calcular totales
CREATE OR REPLACE FUNCTION calcular_totales_comerciante(
    p_id_comerciante IN NUMBER,
    p_cant_establecimientos OUT NUMBER,
    p_total_ingresos OUT NUMBER,
    p_total_empleados OUT NUMBER
) RETURN BOOLEAN
IS
BEGIN
    SELECT 
        COUNT(id_establecimiento),
        NVL(SUM(ingresos), 0),
        NVL(SUM(numero_empleados), 0)
    INTO 
        p_cant_establecimientos,
        p_total_ingresos,
        p_total_empleados
    FROM 
        ESTABLECIMIENTO
    WHERE 
        id_comerciante = p_id_comerciante;
    
    RETURN TRUE;
EXCEPTION
    WHEN OTHERS THEN
        p_cant_establecimientos := 0;
        p_total_ingresos := 0;
        p_total_empleados := 0;
        RETURN FALSE;
END calcular_totales_comerciante;
/

-- 4. Crear función principal que retorna el cursor
CREATE OR REPLACE FUNCTION obtener_comerciantes_activos
RETURN SYS_REFCURSOR
IS
    c_comerciantes SYS_REFCURSOR;
BEGIN
    OPEN c_comerciantes FOR
        SELECT 
            c.nombre_razon_social AS "Nombre o Razón Social",
            c.municipio AS "Municipio",
            c.telefono AS "Teléfono",
            c.correo_electronico AS "Correo Electrónico",
            c.fecha_registro AS "Fecha de Registro",
            c.estado AS "Estado",
            COUNT(e.id_establecimiento) AS "Cantidad de Establecimientos",
            NVL(SUM(e.ingresos), 0) AS "Total Ingresos",
            NVL(SUM(e.numero_empleados), 0) AS "Cantidad de Empleados"
        FROM 
            COMERCIANTE c
        LEFT JOIN 
            ESTABLECIMIENTO e ON c.id_comerciante = e.id_comerciante
        WHERE 
            c.estado = 'Activo'
        GROUP BY 
            c.id_comerciante,
            c.nombre_razon_social,
            c.municipio,
            c.telefono,
            c.correo_electronico,
            c.fecha_registro,
            c.estado
        ORDER BY 
            COUNT(e.id_establecimiento) DESC;
    
    RETURN c_comerciantes;
END obtener_comerciantes_activos;
/

-- 5. Bloque PL/SQL para probar la función
SET SERVEROUTPUT ON SIZE 1000000;
DECLARE
    c_comerciantes SYS_REFCURSOR;
    v_nombre COMERCIANTE.nombre_razon_social%TYPE;
    v_municipio COMERCIANTE.municipio%TYPE;
    v_telefono COMERCIANTE.telefono%TYPE;
    v_correo COMERCIANTE.correo_electronico%TYPE;
    v_fecha_registro COMERCIANTE.fecha_registro%TYPE;
    v_estado COMERCIANTE.estado%TYPE;
    v_cant_establecimientos NUMBER;
    v_total_ingresos NUMBER;
    v_total_empleados NUMBER;
    v_contador NUMBER := 0;
BEGIN
    -- Obtener el cursor de la función
    c_comerciantes := obtener_comerciantes_activos();
    
    -- Encabezado del reporte
    DBMS_OUTPUT.PUT_LINE('REPORTE DE COMERCIANTES ACTIVOS');
    DBMS_OUTPUT.PUT_LINE('========================================');
    DBMS_OUTPUT.PUT_LINE('Ordenados por cantidad de establecimientos (descendente)');
    DBMS_OUTPUT.PUT_LINE('========================================');
    DBMS_OUTPUT.PUT_LINE('');
    
    -- Procesar los resultados
    LOOP
        FETCH c_comerciantes INTO 
            v_nombre, v_municipio, v_telefono, v_correo, 
            v_fecha_registro, v_estado, v_cant_establecimientos,
            v_total_ingresos, v_total_empleados;
        EXIT WHEN c_comerciantes%NOTFOUND;
        
        v_contador := v_contador + 1;
        
        DBMS_OUTPUT.PUT_LINE('COMERCIANTE #' || v_contador);
        DBMS_OUTPUT.PUT_LINE('----------------------------------------');
        DBMS_OUTPUT.PUT_LINE('• Nombre/Razón Social: ' || v_nombre);
        DBMS_OUTPUT.PUT_LINE('• Municipio: ' || v_municipio);
        DBMS_OUTPUT.PUT_LINE('• Teléfono: ' || NVL(v_telefono, 'No registrado'));
        DBMS_OUTPUT.PUT_LINE('• Correo Electrónico: ' || NVL(v_correo, 'No registrado'));
        DBMS_OUTPUT.PUT_LINE('• Fecha de Registro: ' || TO_CHAR(v_fecha_registro, 'DD/MM/YYYY'));
        DBMS_OUTPUT.PUT_LINE('• Estado: ' || v_estado);
        DBMS_OUTPUT.PUT_LINE('• Establecimientos asociados: ' || v_cant_establecimientos);
        DBMS_OUTPUT.PUT_LINE('• Ingresos totales: $' || TO_CHAR(v_total_ingresos, '999,999,999.00'));
        DBMS_OUTPUT.PUT_LINE('• Empleados totales: ' || v_total_empleados);
        DBMS_OUTPUT.PUT_LINE('----------------------------------------');
        DBMS_OUTPUT.PUT_LINE('');
    END LOOP;
    
    -- Mostrar resumen
    IF v_contador = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No se encontraron comerciantes activos.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Total de comerciantes activos: ' || v_contador);
    END IF;
    
    CLOSE c_comerciantes;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        IF c_comerciantes%ISOPEN THEN
            CLOSE c_comerciantes;
        END IF;
END;
/