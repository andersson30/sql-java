-- Trigger para USUARIO
CREATE OR REPLACE TRIGGER trg_usuario_id
BEFORE INSERT ON USUARIO
FOR EACH ROW
BEGIN
    SELECT seq_usuario.NEXTVAL INTO :NEW.id_usuario FROM DUAL;
END;
/

-- Trigger para COMERCIANTE
CREATE OR REPLACE TRIGGER trg_comerciante_id
BEFORE INSERT ON COMERCIANTE
FOR EACH ROW
BEGIN
    SELECT seq_comerciante.NEXTVAL INTO :NEW.id_comerciante FROM DUAL;
END;
/

-- Trigger para ESTABLECIMIENTO
CREATE OR REPLACE TRIGGER trg_establecimiento_id
BEFORE INSERT ON ESTABLECIMIENTO
FOR EACH ROW
BEGIN
    SELECT seq_establecimiento.NEXTVAL INTO :NEW.id_establecimiento FROM DUAL;
END;
/